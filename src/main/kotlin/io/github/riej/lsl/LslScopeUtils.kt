package io.github.riej.lsl

import com.intellij.psi.PsiElement
import io.github.riej.lsl.psi.LslEvent
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslFunction
import io.github.riej.lsl.psi.LslNamedElement

object LslScopeUtils {
    fun findElementByName(currElement: PsiElement, name: String?): LslNamedElement? {
        if (name == null) {
            return null
        }

        var parent: PsiElement? = currElement.parent
        while (parent != null) {
            val element = when (parent) {
                is LslFunction -> parent.arguments.firstOrNull { it.name == name }
                is LslEvent -> parent.arguments.firstOrNull { it.name == name }
                else -> parent.children.filterIsInstance<LslNamedElement>().firstOrNull { (it.getName() == name) }
            }

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