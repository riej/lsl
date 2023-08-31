package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.searches.ReferencesSearch
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslFunction

class LslUnusedFunctionInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unused function"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        file.children
            .filterIsInstance<LslFunction>()
            .filter { !it.textRange.isEmpty }
            .filter { ReferencesSearch.search(it).none() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused function",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedFunctionFix(it)
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveUnusedFunctionFix(function: LslFunction) : LocalQuickFixOnPsiElement(function) {
        override fun getFamilyName(): String = "Remove unused function"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.delete()
        }
    }
}