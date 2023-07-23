package io.github.riej.lsl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.util.Key
import com.intellij.psi.FileViewProvider
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.LslLanguage
import javax.swing.Icon

class LslFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LslLanguage.INSTANCE), ItemPresentation,
    LslScopedElement {
    override fun getFileType(): FileType = LslFileType.INSTANCE

    override fun toString() = "LSL file"

    override fun getPresentableText(): String = name

    override fun getIcon(unused: Boolean): Icon = LslIcons.FILE

    val KWDB_DATA_KEY = Key.create<KwdbData>("KWDB_DATA")
    val kwdbData: KwdbData
        get() =
            project.getUserData(KWDB_DATA_KEY) ?: let {
                val data = KwdbData(project)
                project.putUserData(KWDB_DATA_KEY, data)
                data
            }
}