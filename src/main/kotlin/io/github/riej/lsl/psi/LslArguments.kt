package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.text.BlockSupport
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import com.intellij.util.FileContentUtil
import com.intellij.util.FileContentUtilCore
import io.github.riej.lsl.parser.LslTypes

class LslArguments(node: ASTNode) : ASTWrapperPsiElement(node) {
    val arguments: List<LslArgument>
        get() = findChildrenByType(LslTypes.ARGUMENT)
}