package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

// not a real statement
class LslStatementElse(node: ASTNode) : ASTWrapperPsiElement(node) {
    val statement: LslStatement?
        get() = findChildByType(LslTypes.STATEMENTS)
}