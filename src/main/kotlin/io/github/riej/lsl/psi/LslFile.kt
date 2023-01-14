package io.github.riej.lsl.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.LslFileType
import io.github.riej.lsl.LslLanguage

class LslFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, LslLanguage.INSTANCE) {
    override fun getFileType(): FileType = LslFileType.INSTANCE
    override fun toString() = "LSL file"

    val kwdbData: KwdbData by lazy { KwdbData(project) }

    fun getGlobalVariable(name: String): LslGlobalVariableDeclaration? =
        children.firstOrNull { (it is LslGlobalVariableDeclaration) && (it.getIdentifier().text == name) } as LslGlobalVariableDeclaration?

    fun getFunction(name: String): LslFunctionDeclaration? =
        children.firstOrNull { (it is LslFunctionDeclaration) && (it.getIdentifier().text == name) } as LslFunctionDeclaration?
}