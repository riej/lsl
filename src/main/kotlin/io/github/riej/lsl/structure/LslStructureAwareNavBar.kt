package io.github.riej.lsl.structure

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension
import com.intellij.lang.Language
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslItemPresentation
import javax.swing.Icon

class LslStructureAwareNavBar(override val language: Language = LslLanguage.INSTANCE) :
    StructureAwareNavBarModelExtension() {
    override fun getPresentableText(o: Any?): String? =
        when (o) {
            is PsiElement -> LslItemPresentation(o).presentableText
            else -> null
        }

    override fun getIcon(o: Any?): Icon? =
        when (o) {
            is PsiElement -> LslItemPresentation(o).getIcon(false)
            else -> null
        }
}