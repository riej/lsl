package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.*

class LslInvalidExpressionTypeInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid expression type"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslExpressionBinary::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val typeLeft = it.expressionLeft?.lslType ?: LslPrimitiveType.INVALID
                val typeRight = it.expressionRight?.lslType ?: LslPrimitiveType.INVALID

                if (typeLeft != LslPrimitiveType.INVALID && typeRight != LslPrimitiveType.INVALID && typeLeft.operationTo(
                        typeRight,
                        it.operator
                    ) == LslPrimitiveType.INVALID
                ) {
                    val expressionRight = it.expressionRight

                    val fixes = listOfNotNull(
                        if (expressionRight != null)
                            TypeCastFix(expressionRight, typeLeft)
                        else null
                    ).toTypedArray()

                    problemsHolder.registerProblem(
                        it,
                        "Type mismatch (expected %s, got %s)".format(typeLeft, typeRight),
                        ProblemHighlightType.GENERIC_ERROR,
                        TextRange(0, it.textLength),
                        *fixes,
                    )
                }
            }

        PsiTreeUtil.collectElementsOfType(file, LslExpressionVector::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                it.expressions.forEach {
                    val expressionType = it.lslType
                    if (expressionType != LslPrimitiveType.INVALID && LslPrimitiveType.FLOAT.operationTo(
                            expressionType,
                            LslTypes.ASSIGN
                        ) == LslPrimitiveType.INVALID
                    ) {
                        problemsHolder.registerProblem(
                            it,
                            "Type mismatch (expected float, got %s)".format(expressionType),
                            ProblemHighlightType.GENERIC_ERROR,
                            TextRange(0, it.textLength),
                            TypeCastFix(it, LslPrimitiveType.FLOAT),
                        )
                    }
                }
            }

        PsiTreeUtil.collectElementsOfType(file, LslExpressionQuaternion::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                it.expressions.forEach {
                    val expressionType = it.lslType
                    if (expressionType != LslPrimitiveType.INVALID && LslPrimitiveType.FLOAT.operationTo(
                            expressionType,
                            LslTypes.ASSIGN
                        ) == LslPrimitiveType.INVALID
                    ) {
                        problemsHolder.registerProblem(
                            it,
                            "Type mismatch (expected float, got %s)".format(expressionType),
                            ProblemHighlightType.GENERIC_ERROR,
                            TextRange(0, it.textLength),
                            TypeCastFix(it, LslPrimitiveType.FLOAT),
                        )
                    }
                }
            }

        return problemsHolder.resultsArray
    }

    class TypeCastFix(expression: LslExpression, val type: LslPrimitiveType) : LocalQuickFixOnPsiElement(expression) {
        override fun getFamilyName(): String = "Cast to $type"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val expression = startElement as LslExpression
            when (expression) {
                is LslExpressionBinary -> {
                    expression.replace(
                        LslElementFactory.createTypeCast(
                            project,
                            type,
                            LslElementFactory.createParentheses(project, expression),
                        ),
                    )
                }

                else -> {
                    expression.replace(
                        LslElementFactory.createTypeCast(
                            project,
                            type,
                            expression,
                        ),
                    )
                }
            }
        }
    }
}
