package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.search.searches.ReferencesSearch
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.util.parentsOfType
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.psi.LslArgument
import io.github.riej.lsl.psi.LslEvent

class LslUnusedArgumentInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Unused argument"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslArgument::class.java)
            .filter { !it.textRange.isEmpty }
            .filter { it.parentsOfType(LslEvent::class.java).none() }
            .filter { ReferencesSearch.search(it).none() }
            .forEach {
                problemsHolder.registerProblem(
                    it,
                    "Unused argument",
                    ProblemHighlightType.LIKE_UNUSED_SYMBOL,
                    it.identifyingElement?.textRangeInParent,
                    RemoveUnusedArgumentFix(it)
                )
            }

        return problemsHolder.resultsArray
    }

    class RemoveUnusedArgumentFix(argument: LslArgument) : LocalQuickFixOnPsiElement(argument) {
        override fun getFamilyName(): String = "Remove unused argument"

        override fun getText(): String = this.familyName

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            val parentChildren = startElement.parent.node.getChildren(null).toList()

            // Delete both argument and comma before it.
            listOfNotNull(
                parentChildren
                    .subList(0, parentChildren.indexOf(startElement.node))
                    .findLast { it.elementType == LslTypes.COMMA }
                    ?.psi,
                startElement
            ).forEach { it.delete() }
        }
    }
}
