package io.github.riej.lsl.psi

import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import io.github.riej.lsl.parser.LslTypes

interface LslState : LslNamedElement, PsiNameIdentifierOwner, NavigatablePsiElement, LslSymbolDeclaration {
    val events: List<LslEvent>
        get() = eventsEl?.events.orEmpty()

    val eventsEl: LslEvents?
        get() = children.firstNotNullOfOrNull { it as? LslEvents }

    val braceLeftEl: PsiElement?
        get() = node.findChildByType(LslTypes.BRACE_LEFT)?.psi

    val braceRightEl: PsiElement?
        get() = node.findChildByType(LslTypes.BRACE_RIGHT)?.psi
}