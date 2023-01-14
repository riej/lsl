package io.github.riej.lsl.psi

import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType

interface LslTypedElement : PsiElement {
    fun getPrimitiveType(): LslPrimitiveType
}