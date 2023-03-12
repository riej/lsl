package io.github.riej.lsl.psi

import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

interface LslVariable : LslNamedElement, LslTypedElement, ItemPresentation, LslSymbolDeclaration {
    override fun getPresentableText(): String = "$lslType $name"

    val expression: LslExpression?

    val usages: List<PsiElement>
        get() = PsiTreeUtil.collectElements(containingFile) {
            (it is LslLValue) && (it.variable == this)
        }.toList()
}