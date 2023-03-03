package io.github.riej.lsl

import com.intellij.psi.PsiElement
import io.github.riej.lsl.psi.LslEvent
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslNamedElement

object LslScopeUtils {
    fun findElementByName(currElement: PsiElement, name: String?): LslNamedElement? {
        if (name == null) {
            return null
        }

        var parent: PsiElement? = currElement.parent
        while (parent != null) {
            val element =
                parent.children.firstOrNull { (it is LslNamedElement) && (it.getName() == name) } as LslNamedElement?
            if (element != null) {
                return element
            }

            if (parent is LslFile) {
                return parent.kwdbData.generated.children.firstOrNull { (it is LslNamedElement) && (it.getName() == name) } as LslNamedElement?
            }

            parent = parent.parent
        }
        return null
    }

    fun getEventByName(currElement: PsiElement, name: String?): LslEvent? =
        (currElement.containingFile as LslFile).kwdbData.events[name]
}