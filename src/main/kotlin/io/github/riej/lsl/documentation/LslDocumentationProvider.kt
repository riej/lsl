package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.util.containers.nullize
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslNamedElement

class LslDocumentationProvider : DocumentationProvider {
    override fun getUrlFor(element: PsiElement, originalElement: PsiElement?): List<String>? =
        when (element) {
            is LslNamedElement ->
                listOfNotNull(
                    DocumentationUtils.getUrlFor(
                        // provide URL only for predefined elements
                        (element.containingFile as LslFile).kwdbData.scope.findElementByName(element.name)?.name
                    )
                ).nullize()

            else -> null
        }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? =
        (element as? LslDocumentedElement)?.generateDocumentation()

    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement?): String? =
        generateDoc(element, originalElement)
}