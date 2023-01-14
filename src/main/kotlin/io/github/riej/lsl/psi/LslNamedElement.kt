package io.github.riej.lsl.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface LslNamedElement : PsiNameIdentifierOwner, NavigatablePsiElement {
    fun getIdentifier(): PsiElement?
}