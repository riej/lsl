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
    val arguments: List<LslArgument> = findChildrenByType(LslTypes.ARGUMENT)

    override fun subtreeChanged() {
        // There is problem when modification of function arguments doesn't affects to function body.
        // For example:
        // integer meow() { return a + b; }
        // IDE will show errors at `a` and `b` - because they does not exists yet.
        // But if you write arguments (`integer a, integer b`), IDE still will show errors.
        // I really have no idea how to fix this behavior except reparsing whole file.
        // This is slow, but it works.
        // TODO: find proper solution.
        FileContentUtilCore.reparseFiles(this.containingFile.virtualFile)
    }
}