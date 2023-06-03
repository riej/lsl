package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.navigation.ItemPresentation
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.scope.ChildCache
import javax.swing.Icon

class LslStateDefault(node: ASTNode) : ASTWrapperPsiElement(node), LslState, ItemPresentation {

    override val declarations = ChildCache(this) {events}

    override fun setName(name: String): PsiElement =
        this

    override fun getNameIdentifier(): PsiElement? = findChildByType(LslTypes.DEFAULT)

    override fun getName(): String? = nameIdentifier?.text

    override fun getPresentableText(): String = "default"

    override fun getIcon(unused: Boolean): Icon = LslIcons.STATE
}