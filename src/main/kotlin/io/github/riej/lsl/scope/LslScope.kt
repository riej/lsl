package io.github.riej.lsl.scope

import io.github.riej.lsl.psi.LslNamedElement

interface LslScope {
    val declaredElements: List<LslNamedElement>

    val allDeclaredElements: Map<String, LslNamedElement>

    val parentScope: LslScope?

    fun getDeclaredElement(name: String?): LslNamedElement? =
        declaredElements.find { it.name == name }

    fun findElementByName(name: String?): LslNamedElement? =
        allDeclaredElements[name]
}