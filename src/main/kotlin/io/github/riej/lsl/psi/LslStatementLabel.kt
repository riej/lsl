package io.github.riej.lsl.psi

import com.intellij.lang.ASTNode
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement

class LslStatementLabel(node: ASTNode) : ASTWrapperLslNamedElement(node), LslStatement, NavigatablePsiElement,
    LslSymbolDeclaration {
    override fun getNavigationElement(): PsiElement =
        this.identifyingElement ?: this
}