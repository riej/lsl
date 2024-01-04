package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.LocalSearchScope
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.parentOfType
import com.intellij.psi.util.parents
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
            .forEach { block ->
                val unreachableCodeRanges = findUnreachableCodeRanges(block)

                unreachableCodeRanges.forEach {
                    problemsHolder.registerProblem(
                        block,
                        "Unreachable code",
                        ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                        TextRange(it.first().startOffsetInParent, it.last().textRangeInParent.endOffset),
                        RemoveUnreachableCodeFix(
                            it.first(),
                            it.last()
                        )
                    )
                }
            }

        return problemsHolder.resultsArray
    }

    private fun findUnreachableCodeRanges(block: LslStatementBlock): List<Array<PsiElement>> {
        var isReachable = true
        val currentUnreachableBlock = ArrayList<PsiElement>()
        val result = ArrayList<Array<PsiElement>>()

        val searchScope = LocalSearchScope(
            block.parents(false).filter { it is LslFunction || it is LslEvent }.first()
        )

        block.children.forEach {
            // if code is unreachable, but it's label with existing jump, make it reachable again
            // but this code will mark as reachable this:
            //   return;
            //   @someLabel; // label has jump - so...
            //   jump someLabel;
            //   llOwnerSay("meow");
            // but well, it already works better than before
            // TODO: follow all code branches and find truly unreachable ones
            if (!isReachable && (it is LslStatementLabel) && ReferencesSearch.search(it, searchScope).findFirst() != null) {
                isReachable = true

                if (currentUnreachableBlock.isNotEmpty()) {
                    result.add(currentUnreachableBlock.toTypedArray())
                    currentUnreachableBlock.clear()
                }
            }

            if (!isReachable) {
                currentUnreachableBlock.add(it)
            }

            // make code unreachable if it meets return, state switch or final function call (llDie, llResetScript)
            isReachable = isReachable && when (it) {
                is LslStatementReturn -> false
                is LslStatementState -> false
                is LslStatementExpression -> !isFinalFunctionCall(it.children.singleOrNull() as? LslExpressionFunctionCall)

                else -> true
            }
        }

        if (currentUnreachableBlock.isNotEmpty()) {
            result.add(currentUnreachableBlock.toTypedArray())
            currentUnreachableBlock.clear()
        }

        return result
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
