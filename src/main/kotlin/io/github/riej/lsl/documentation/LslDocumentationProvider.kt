package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.DocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.util.containers.nullize
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslNamedElement

class LslDocumentationProvider : DocumentationProvider {
    override fun getUrlFor(element: PsiElement, originalElement: PsiElement?): List<String>? =
        when (element) {
            is LslNamedElement ->
                listOfNotNull(
                    KwdbData.getInstance(element.project).getByName(element.name)?.name
                )
                    .mapNotNull { DocumentationUtils.getUrlFor(it) }
                    .nullize()

            else -> null
        }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? =
        (element as? LslDocumentedElement)?.generateDocumentation()

    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement?): String? =
        generateDoc(element, originalElement)
}