package io.github.riej.lsl.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner

interface LslState : PsiElement, PsiNameIdentifierOwner, NavigatablePsiElement {
    val events: List<LslEvent>
        get() = eventsEl?.events.orEmpty()

    val eventsEl: LslEvents?
        get() = this.children.firstNotNullOfOrNull { it as? LslEvents }
}