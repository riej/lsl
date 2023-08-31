package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslExpressionFunctionCall
import kotlin.math.min

class LslInvalidFunctionCallArgumentInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid function call argument"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslExpressionFunctionCall::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val arguments = it.function?.arguments
                val expressions = it.expressions
                if (arguments != null) {
                    if (expressions.isNotEmpty()) {
                        (0 until min(expressions.size, arguments.size)).forEach { i ->
                            val argumentType = arguments[i].lslType
                            val expressionType = expressions[i].lslType

                            if (argumentType.operationTo(expressionType, LslTypes.ASSIGN) == LslPrimitiveType.INVALID) {
                                problemsHolder.registerProblem(
                                    it,
                                    "Type mismatch (expected %s, got %s)".format(argumentType, expressionType),
                                    ProblemHighlightType.GENERIC_ERROR,
                                    expressions[i].textRangeInParent,
                                    LslInvalidExpressionTypeInspection.TypeCastFix(expressions[i], argumentType),
                                )
                            }
                        }
                    }

                    if (expressions.size < arguments.size) {
                        problemsHolder.registerProblem(
                            it,
                            "Wrong arguments count (expected ${arguments.size}, got ${expressions.size})",
                            ProblemHighlightType.GENERIC_ERROR,
                            it.parenthesesRightEl?.textRangeInParent ?: it.lastChild.textRangeInParent,
                        )
                    } else if (expressions.size > arguments.size) {
                        val firstExtraExpression = if (arguments.isNotEmpty())
                            expressions[arguments.size]
                        else
                            expressions.first()

                        val firstExtraExpressionComma = it.node.getChildren(null)
                            .filter { it.elementType == LslTypes.COMMA }
                            .lastOrNull { it.psi.endOffset < firstExtraExpression.startOffset }
                            ?.psi

                        val lastExtraExpression = expressions.last()

                        problemsHolder.registerProblem(
                            it,
                            "Wrong arguments count (expected ${arguments.size}, got ${expressions.size})",
                            ProblemHighlightType.GENERIC_ERROR,
                            TextRange(
                                firstExtraExpressionComma?.textRangeInParent?.startOffset
                                    ?: firstExtraExpression.textRangeInParent.startOffset,
                                lastExtraExpression.textRangeInParent.endOffset,
                            ),
                            RemoveExtraArgumentsFix(
                                firstExtraExpressionComma ?: firstExtraExpression,
                                lastExtraExpression
                            ),
                        )
                    }
                }
            }

        return problemsHolder.resultsArray
    }

    class RemoveExtraArgumentsFix(startElement: PsiElement, endElement: PsiElement) :
        LocalQuickFixOnPsiElement(startElement, endElement) {
        override fun getFamilyName(): String = "Remove extra arguments"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.parent.deleteChildRange(startElement, endElement)
        }
    }
}
