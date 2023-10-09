package io.github.riej.lsl.inspections

import com.intellij.codeInspection.*
import com.intellij.openapi.project.Project
import com.intellij.pom.Navigatable
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslNamedElement
import io.github.riej.lsl.references.LslReferenceUtils

class LslRedeclaredIdentifierInspection : LocalInspectionTool() {
    override fun getDisplayName(): String = "Redeclared identifier"
    override fun getGroupDisplayName(): String = LslLanguage.INSTANCE.displayName
    override fun isEnabledByDefault(): Boolean = true

    override fun checkFile(file: PsiFile, manager: InspectionManager, isOnTheFly: Boolean): Array<ProblemDescriptor> {
        val problemsHolder = ProblemsHolder(manager, file, isOnTheFly)

        PsiTreeUtil.collectElementsOfType(file, LslNamedElement::class.java)
            .filter { !it.textRange.isEmpty }
            .forEach {
                val name = it.name ?: return@forEach
                val existingIdentifier = LslReferenceUtils.findNamedElement(it, name) ?: return@forEach

                if (existingIdentifier == it) {
                    return@forEach
                }

                problemsHolder.registerProblem(
                    it,
                    "Redeclared identifier",
                    if (existingIdentifier.parent == it.parent)
                        ProblemHighlightType.GENERIC_ERROR
                    else
                        ProblemHighlightType.WARNING,
                    it.identifyingElement?.textRangeInParent,
                    NavigateToElementFix(existingIdentifier),
                )
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