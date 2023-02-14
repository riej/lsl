package io.github.riej.lsl.structure

import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension
import com.intellij.lang.Language
import com.intellij.navigation.ItemPresentation
import io.github.riej.lsl.LslLanguage
import javax.swing.Icon

class LslStructureAwareNavBar(override val language: Language = LslLanguage.INSTANCE) :
    StructureAwareNavBarModelExtension() {
    override fun getPresentableText(o: Any?): String? =
        when (o) {
            is ItemPresentation -> o.presentableText
            else -> null
        }

    override fun getIcon(o: Any?): Icon? =
        when (o) {
            is ItemPresentation -> o.getIcon(false)
            else -> null
        }
}