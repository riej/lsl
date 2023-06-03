package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import io.github.riej.lsl.parser.LslTypes

abstract class ASTWrapperLslNamedElement(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner,
    LslNamedElement {
    override fun getNameIdentifier(): PsiElement? =
        findChildByType(LslTypes.IDENTIFIER)

    override fun getName(): String? =
        nameIdentifier?.text

    override fun setName(name: String): PsiElement {
        val identifier = nameIdentifier ?: return this
        node.replaceChild(
            identifier.node,
            LslElementFactory.createIdentifier(project, name).node
        )
        return this
    }
}