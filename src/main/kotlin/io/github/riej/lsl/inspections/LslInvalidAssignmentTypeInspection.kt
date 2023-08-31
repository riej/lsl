package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslExpressionAssignment
import io.github.riej.lsl.psi.LslGlobalVariable
import io.github.riej.lsl.psi.LslStatementVariable

class LslInvalidAssignmentTypeInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Invalid assignment type"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslExpressionAssignment::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val variableType = it.lValue?.variable?.lslType ?: return@forEach
                val expressionType = it.expression?.lslType ?: LslPrimitiveType.INVALID

                if (expressionType != LslPrimitiveType.INVALID && variableType.operationTo(
                        expressionType,
                        it.operator
                    ) == LslPrimitiveType.INVALID
                ) {
                    problemsHolder.registerProblem(
                        it,
                        "Invalid assignment type (expected %s, got %s)".format(variableType, expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        it.expression?.textRangeInParent ?: TextRange(0, it.textLength),
                    )
                }
            }

        file.children.filterIsInstance<LslGlobalVariable>()
            .filter { !it.textRange.isEmpty }
            .forEach {
                val variableType = it.lslType
                val expressionType = it.expression?.lslType ?: LslPrimitiveType.INVALID

                if (expressionType != LslPrimitiveType.INVALID && variableType.operationTo(
                        expressionType,
                        LslTypes.ASSIGN
                    ) == LslPrimitiveType.INVALID
                ) {
                    problemsHolder.registerProblem(
                        it,
                        "Invalid assignment type (expected %s, got %s)".format(variableType, expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        it.expression?.textRangeInParent ?: TextRange(0, it.textLength),
                    )
                }
            }

        PsiTreeUtil.collectElementsOfType(file, LslStatementVariable::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val variableType = it.lslType
                val expressionType = it.expression?.lslType ?: LslPrimitiveType.INVALID

                if (expressionType != LslPrimitiveType.INVALID && variableType.operationTo(
                        expressionType,
                        LslTypes.ASSIGN
                    ) == LslPrimitiveType.INVALID
                ) {
                    problemsHolder.registerProblem(
                        it,
                        "Invalid assignment type (expected %s, got %s)".format(variableType, expressionType),
                        ProblemHighlightType.GENERIC_ERROR,
                        it.expression?.textRangeInParent ?: TextRange(0, it.textLength),
                    )
                }
            }

        return problemsHolder.resultsArray
    }
}
