package io.github.riej.lsl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.LslLanguage
import javax.swing.Icon

class LslFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LslLanguage.INSTANCE), ItemPresentation {
    override fun getFileType(): FileType = LslFileType.INSTANCE
    override fun toString() = "LSL file"

    override fun getPresentableText(): String = name

    override fun getIcon(unused: Boolean): Icon = LslIcons.FILE

    val kwdbData: KwdbData by lazy { KwdbData(project) }

    fun getGlobalVariable(name: String): LslGlobalVariable? =
        children.firstOrNull { (it is LslGlobalVariable) && (it.name == name) } as LslGlobalVariable?

    fun getFunction(name: String): LslFunction? =
        children.firstOrNull { (it is LslFunction) && (it.name == name) } as LslFunction?

    fun getState(name: String): LslState? =
        children.firstOrNull { (it is LslState) && (it.name == name) } as LslState?
}