package io.github.riej.lsl.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.PsiTreeUtil

interface LslState : LslNamedElement, PsiNameIdentifierOwner, NavigatablePsiElement, LslSymbolDeclaration {
    val events: List<LslEvent>
        get() = eventsEl?.events.orEmpty()

    val eventsEl: LslEvents?
        get() = this.children.firstNotNullOfOrNull { it as? LslEvents }

    val usages: List<PsiElement>
        get() = PsiTreeUtil.collectElements(containingFile) {
            (it is LslStatementState) && (it.state == this)
        }.toList()
}