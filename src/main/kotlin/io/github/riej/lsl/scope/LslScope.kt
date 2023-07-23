package io.github.riej.lsl.scope

import com.intellij.openapi.util.Key
import com.intellij.psi.util.CachedValue
import io.github.riej.lsl.psi.LslNamedElement

class LslScope(val parentScope: LslScope? = null, val declaredElements: List<LslNamedElement> = emptyList()) {
    companion object {
        val KEY = Key.create<LslScope>("LSL_SCOPE")
        val CACHED_KEY = Key.create<CachedValue<LslScope>>("LSL_SCOPE_CACHED")
    }

    fun getDeclaredElement(name: String?): LslNamedElement? =
        declaredElements.find { it.name == name }

    fun findElementByName(name: String?): LslNamedElement? =
        getDeclaredElement(name) ?: parentScope?.findElementByName(name)
}