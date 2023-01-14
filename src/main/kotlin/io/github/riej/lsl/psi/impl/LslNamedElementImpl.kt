package io.github.riej.lsl.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import io.github.riej.lsl.psi.LslItemPresentation
import io.github.riej.lsl.psi.LslNamedElement

abstract class LslNamedElementImpl(node: ASTNode) : LslNamedElement, ASTWrapperPsiElement(node) {
    override fun setName(name: String): PsiElement {
        val identifier = getIdentifier() ?: return this
        node.replaceChild(identifier.node, LslElementFactory.createIdentifier(project, name).node)
        return this
    }

    override fun getNameIdentifier(): PsiElement? = getIdentifier()

    override fun getName(): String? = getIdentifier()?.text

    override fun getPresentation(): ItemPresentation = LslItemPresentation(this)
}