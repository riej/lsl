package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.searches.ReferencesSearch
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslStateCustom

class LslUnusedStateInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unused state"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        file.children
            .filterIsInstance<LslStateCustom>()
            .filter { !it.textRange.isEmpty }
            .filter { ReferencesSearch.search(it).none() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused state",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedStateFix(it)
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveUnusedStateFix(state: LslStateCustom) : LocalQuickFixOnPsiElement(state) {
        override fun getFamilyName(): String = "Remove unused state"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.delete()
        }
    }
}