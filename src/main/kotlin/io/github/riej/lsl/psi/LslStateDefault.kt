package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.parser.LslTypes
import javax.swing.Icon

class LslStateDefault(node: ASTNode) : ASTWrapperPsiElement(node), LslState, ItemPresentation {
    override fun setName(name: String): PsiElement =
        this

    override fun getNameIdentifier(): PsiElement? = this.findChildByType(LslTypes.DEFAULT)

    override fun getPresentableText(): String = "default"

    override fun getIcon(unused: Boolean): Icon = LslIcons.STATE
}