package io.github.riej.lsl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.util.Key
import com.intellij.psi.FileViewProvider
import com.intellij.psi.impl.source.codeStyle.CodeEditUtil
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.scope.LslPsiScope
import io.github.riej.lsl.scope.LslScope
import javax.swing.Icon

class LslFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LslLanguage.INSTANCE), ItemPresentation,
    LslPsiScope {
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

    override val parentScope: LslScope?
        get() = if (CodeEditUtil.isNodeGenerated(node)) null else kwdbData
}