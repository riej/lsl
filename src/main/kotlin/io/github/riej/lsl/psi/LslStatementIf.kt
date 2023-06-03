package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslStatementIf(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val condition: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    val statement: LslStatement?
        get() = findChildByType(LslTypes.STATEMENTS)

    val statementElse: LslStatement?
        get() = findChildByType(LslTypes.STATEMENT_ELSE)
}