package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslGlobalVariable
import io.github.riej.lsl.psi.LslStatementVariable
import io.github.riej.lsl.psi.LslVariable

class LslUnusedVariableInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unused variable"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        file.children
            .filterIsInstance<LslGlobalVariable>()
            .filter { !it.textRange.isEmpty }
            .filter { ReferencesSearch.search(it).none() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused variable",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedVariableFix(it)
                )
            }

        PsiTreeUtil.collectElementsOfType(file, LslStatementVariable::class.java)
            .filter { !it.textRange.isEmpty }
            .filter { ReferencesSearch.search(it).none() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused variable",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedVariableFix(it)
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveUnusedVariableFix(variable: LslVariable) : LocalQuickFixOnPsiElement(variable) {
        override fun getFamilyName(): String = "Remove unused variable"

        override fun getText(): String = familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            startElement.delete()
        }
    }
}