package io.github.riej.lsl.scope

import io.github.riej.lsl.psi.LslNamedElement

interface LslScope<T: LslNamedElement> {

    val declarations: ChildCache<T>
}