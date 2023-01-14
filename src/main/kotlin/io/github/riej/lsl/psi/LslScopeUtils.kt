package io.github.riej.lsl.psi

import com.intellij.psi.PsiElement

object LslScopeUtils {
    fun findElementByName(currElement: PsiElement, name: String?): LslNamedElement? {
        if (name == null) {
            return null
        }

        var parent: PsiElement? = currElement.parent
        while (parent != null) {
            // there are LslLocalVariableDeclaration and LslLabelStatement inside LslStatement,
            // so we have to extract children from LslStatement
            val children = parent.children.flatMap {
                when (it) {
                    is LslStatement -> it.children.asIterable()
                    else -> listOf(it)
                }
            }

            val element =
                children.firstOrNull { (it is LslNamedElement) && (it.getIdentifier()?.text == name) } as LslNamedElement?
            if (element != null) {
                return element
            }

            if (parent is LslFile) {
                val element =
                    parent.kwdbData.generated.children.firstOrNull { (it is LslNamedElement) && (it.getIdentifier()?.text == name) } as LslNamedElement?
                if (element != null) {
                    return element
                }

                var event = parent.kwdbData.availableEvents[name]
                if (event != null) {
                    return event
                }

                return null
            }

            parent = parent.parent
        }
        return null
    }

    fun getEventByName(currElement: PsiElement, name: String): LslStateEvent? =
        (currElement.containingFile as LslFile).kwdbData.availableEvents[name]
}