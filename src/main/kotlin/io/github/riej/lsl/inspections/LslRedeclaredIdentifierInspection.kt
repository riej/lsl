package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslNamedElement

class LslRedeclaredIdentifierInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Redeclared identifier"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslNamedElement::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                // Look for existing identifier in current parent node first.
                // If found - treat is as error.
                val existingIdentifierInCurrentScope = it.parent.children
                    .takeWhile { child -> child != it }
                    .filterIsInstance<LslNamedElement>()
                    .firstOrNull { child -> child.name == it.name }

                if (existingIdentifierInCurrentScope != null) {
                    problemsHolder.registerProblem(
                        it,
                        "Redeclared identifier",
                        ProblemHighlightType.GENERIC_ERROR,
                        it.identifyingElement?.textRangeInParent,
                        NavigateToElementFix(existingIdentifierInCurrentScope),
                    )
                    return@forEach
                }

                // Check existing identifiers in another scopes.
                var node = it.parent.parent
                while (node != null) {
                    val existingIdentifier = node.children
                        .filterIsInstance<LslNamedElement>()
                        .firstOrNull { child -> child.name == it.name }

                    if (existingIdentifier != null) {
                        problemsHolder.registerProblem(
                            it,
                            "Redeclared identifier",
                            ProblemHighlightType.WARNING,
                            it.identifyingElement?.textRangeInParent,
                            NavigateToElementFix(existingIdentifier),
                        )
                        return@forEach
                    }

                    node = node.parent
                }
            }

        return problemsHolder.resultsArray
    }

    class NavigateToElementFix(element: PsiElement) : LocalQuickFixOnPsiElement(element) {
        override fun startInWriteAction(): Boolean = false

        override fun getText(): String = familyName

        override fun getFamilyName(): String = "Navigate to previous declaration"

        override fun invoke(project: Project, file: PsiFile, startElement: PsiElement, endElement: PsiElement) {
            if (startElement is Navigatable) {
                startElement.navigate(true)
            }
        }
    }
}