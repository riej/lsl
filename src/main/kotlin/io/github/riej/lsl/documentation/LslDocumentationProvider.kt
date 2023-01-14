package io.github.riej.lsl.documentation

import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.psi.PsiElement
import com.intellij.util.containers.nullize
import io.github.riej.lsl.psi.*

class LslDocumentationProvider : AbstractDocumentationProvider() {
    override fun getUrlFor(element: PsiElement, originalElement: PsiElement?): List<String>? =
        when (element) {
            is LslNamedElement ->
                listOfNotNull(
                    DocumentationUtils.getUrlFor(
                        // provide URL only for predefined elements
                        (element.containingFile as LslFile).kwdbData.findElementByName(element.getIdentifier()?.text)
                            ?.getIdentifier()?.text
                    )
                ).nullize()

            is LslIdentifier -> getUrlFor(element.parent, originalElement)
            else -> null
        }

    override fun generateDoc(element: PsiElement, originalElement: PsiElement?): String? =
        when (element) {
            is LslLocalVariableDeclaration -> LslLocalVariableDeclarationDocumentation.generateDoc(element)
            is LslGlobalVariableDeclaration -> LslGlobalVariableDeclarationDocumentation.generateDoc(element)
            is LslArgument -> LslArgumentDocumentation.generateDoc(element)
            is LslFunctionDeclaration -> LslFunctionDeclarationDocumentation.generateDoc(element)
            is LslStateEvent -> LslStateEventDocumentation.generateDoc(element)
            is LslIdentifier -> generateDoc(element.parent, originalElement)
            else -> null
        }

    override fun getQuickNavigateInfo(element: PsiElement, originalElement: PsiElement?): String? {
        return generateDoc(element, originalElement)
    }
}