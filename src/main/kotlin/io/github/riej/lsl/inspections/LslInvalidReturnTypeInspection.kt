package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslElementFactory
import io.github.riej.lsl.psi.LslFunction
import io.github.riej.lsl.psi.LslStatementReturn

class LslInvalidReturnTypeInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid return type"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslStatementReturn::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val expression = it.expression
                val expressionType = expression?.lslType ?: LslPrimitiveType.VOID

                // Events cannot return any values.
                if (it.parentEvent != null && expression != null) {
                    problemsHolder.registerProblem(
                        it,
                        "Invalid return type (expected void, got %s)".format(expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        expression.textRangeInParent,
                        RemoveReturnExpression(it),
                    )
                    return@forEach
                }

                val function = it.parentFunction
                val functionType = function?.lslType ?: return@forEach

                if (functionType != LslPrimitiveType.VOID && expression == null) {
                    problemsHolder.registerProblem(
                        it,
                        "Return value expected".format(functionType, expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        TextRange(0, it.textLength),
                        ChangeFunctionReturnType(function, LslPrimitiveType.VOID),
                    )
                    return@forEach
                }

                if (functionType == LslPrimitiveType.VOID && expression == null) {
                    return@forEach
                }

                if (expressionType != LslPrimitiveType.INVALID && functionType.operationTo(
                        expressionType,
                        LslTypes.ASSIGN
                    ) == LslPrimitiveType.INVALID
                ) {
                    val fixes = listOfNotNull(
                        if (expression != null && functionType != LslPrimitiveType.VOID)
                            LslInvalidExpressionTypeInspection.TypeCastFix(expression, functionType)
                        else null,
                        RemoveReturnExpression(it),
                        ChangeFunctionReturnType(function, expressionType),
                    ).toTypedArray()

                    problemsHolder.registerProblem(
                        it,
                        "Invalid return type (expected %s, got %s)".format(functionType, expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        it.expression?.textRangeInParent ?: TextRange(0, it.textLength),
                        *fixes,
                    )
                }
            }

        return problemsHolder.resultsArray
    }

    class RemoveReturnExpression(statementReturn: LslStatementReturn) : LocalQuickFixOnPsiElement(statementReturn) {
        override fun getFamilyName(): String = "Remove return expression"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val statementReturn = startElement as? LslStatementReturn ?: return

            if (statementReturn.expression != null) {
                CodeEditUtil.removeChildren(
                    statementReturn.node,
                    statementReturn.returnKeywordEl.node.treeNext,
                    statementReturn.semicolonEl?.node?.treePrev ?: statementReturn.lastChild.node,
                )
            }
        }
    }

    class ChangeFunctionReturnType(function: LslFunction, val newReturnType: LslPrimitiveType) :
        LocalQuickFixOnPsiElement(function) {
        override fun getFamilyName(): String = "Change return type to $newReturnType"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val function = startElement as? LslFunction ?: return
            val existingTypeNameEl = function.typeNameEl
            if (newReturnType == LslPrimitiveType.VOID) {
                if (existingTypeNameEl != null) {
                    CodeEditUtil.removeChildren(
                        function.node,
                        existingTypeNameEl.node,
                        function.nameIdentifier?.node?.treePrev ?: existingTypeNameEl.node
                    )
                }
            } else {
                val newTypeNameEl = LslElementFactory.createTypeName(project, newReturnType)
                if (existingTypeNameEl == null) {
                    CodeEditUtil.addChildren(
                        function.node,
                        newTypeNameEl.node,
                        newTypeNameEl.node.treeNext,
                        function.firstChild.node
                    )
                } else {
                    CodeEditUtil.replaceChild(function.node, existingTypeNameEl.node, newTypeNameEl.node)
                }
            }
        }
    }
}
