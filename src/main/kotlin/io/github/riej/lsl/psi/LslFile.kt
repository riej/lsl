package io.github.riej.lsl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslIcons
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.scope.ChildCache
import io.github.riej.lsl.scope.LslScope
import javax.swing.Icon

class LslFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LslLanguage.INSTANCE), ItemPresentation,
    LslScope<LslNamedElement> {

    override val declarations = ChildCache(this) {
        (kwdbData.generated.children + children).filterIsInstance<LslNamedElement>()
    }

    override fun getFileType(): FileType = LslFileType.INSTANCE

    override fun toString() = "LSL file"

    override fun getPresentableText(): String = name

    override fun getIcon(unused: Boolean): Icon = LslIcons.FILE

    val kwdbData: KwdbData by lazy { KwdbData(project) }
}