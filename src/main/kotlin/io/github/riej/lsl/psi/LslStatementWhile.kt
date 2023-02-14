package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import io.github.riej.lsl.parser.LslTypes

class LslStatementWhile(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val condition: io.github.riej.lsl.psi.LslExpression?
        get() = this.findChildByType(LslTypes.EXPRESSIONS)
    
    val statement: LslStatement?
        get() = this.findChildByType(LslTypes.STATEMENTS)
}