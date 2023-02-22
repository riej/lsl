package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslStatementBlock(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val statements: List<LslStatement>
        get() = this.findChildrenByType(LslTypes.STATEMENTS)
}