package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.*

class LslUnreachableCodeInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unreachable code"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslStatementBlock::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val unreachableChildren = it.children.dropWhile {
                    when (it) {
                        is LslStatementReturn -> return@dropWhile false
                        is LslStatementState -> return@dropWhile false
                        is LslStatementExpression -> return@dropWhile !isFinalFunctionCall(it.children.singleOrNull() as? LslExpressionFunctionCall)

                        else -> return@dropWhile true
                    }
                }.drop(1)

                if (unreachableChildren.isNotEmpty()) {
                    problemsHolder.registerProblem(
                        it,
                        "Unreachable code",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                        TextRange(unreachableChildren.first().startOffsetInParent, it.textLength - 1),
                        RemoveUnreachableCodeFix(
                            unreachableChildren.first(),
                            it.lastChild.prevSibling, // lastChild is "}", so take it's previous sibling
                        )
                    )
                }
            }

        return problemsHolder.resultsArray
    }

    private fun isFinalFunctionCall(functionCall: LslExpressionFunctionCall?): Boolean {
        val functionName = functionCall?.functionName ?: return false

        return listOf(
            "llDie",
            "llResetScript"
        ).contains(functionName)
    }

    class RemoveUnreachableCodeFix(startElement: PsiElement, endElement: PsiElement) :
        LocalQuickFixOnPsiElement(startElement, endElement) {
        override fun getFamilyName(): String = "Remove unreachable code"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.parent.deleteChildRange(startElement, endElement)
        }
    }
}
