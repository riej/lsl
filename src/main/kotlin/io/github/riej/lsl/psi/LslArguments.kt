package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslArguments(node: ASTNode) : ASTWrapperPsiElement(node) {
    var arguments: List<LslArgument> = findChildrenByType(LslTypes.ARGUMENT)
        private set

    override fun subtreeChanged() {
        arguments = findChildrenByType(LslTypes.ARGUMENT)
    }
}