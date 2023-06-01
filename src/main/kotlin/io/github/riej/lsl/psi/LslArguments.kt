package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslArguments(node: ASTNode) : ASTWrapperPsiElement(node) {
    val arguments: List<LslArgument>
        get() = findChildrenByType(LslTypes.ARGUMENT)
}