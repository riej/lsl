package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.parser.LslTypes

class LslStatementDo(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val statement: LslStatement?
        get() = findChildByType(LslTypes.STATEMENTS)

    val condition: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)
}