package io.github.riej.lsl.psi

import com.intellij.navigation.ItemPresentation

interface LslVariable : LslNamedElement, LslTypedElement, ItemPresentation, LslSymbolDeclaration {
    override fun getPresentableText(): String = "$lslType $name"

    val expression: LslExpression?
}