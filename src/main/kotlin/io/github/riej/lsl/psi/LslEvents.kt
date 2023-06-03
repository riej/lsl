package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslEvents(node: ASTNode) : ASTWrapperPsiElement(node) {
    val events: List<LslEvent>
        get() = findChildrenByType(LslTypes.EVENT)
}