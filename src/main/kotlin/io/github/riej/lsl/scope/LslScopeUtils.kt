package io.github.riej.lsl.scope

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
            val element = if (parent is LslScope<*>) parent.declarations[name] else null
            if (element != null) {
                return element
            }
            parent = parent.parent
        }
        return null
    }

    fun getGlobalElementOrFindAny(currElement: PsiElement, name: String?): LslNamedElement? =
        (currElement.containingFile as LslFile).declarations[name] ?: findElementByName(currElement, name)

    fun getEventByName(currElement: PsiElement, name: String?): LslEvent? =
        (currElement.containingFile as LslFile).kwdbData.events[name]
}