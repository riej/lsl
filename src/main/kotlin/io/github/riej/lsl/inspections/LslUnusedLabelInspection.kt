package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslStatementLabel

class LslUnusedLabelInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unused label"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslStatementLabel::class.java)
            .filter { ReferencesSearch.search(it).none() }
            .filter { !it.textRange.isEmpty }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused label",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedLabelFix(it)
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveUnusedLabelFix(label: LslStatementLabel) : LocalQuickFixOnPsiElement(label) {
        override fun getFamilyName(): String = "Remove unused label"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.delete()
        }
    }
}