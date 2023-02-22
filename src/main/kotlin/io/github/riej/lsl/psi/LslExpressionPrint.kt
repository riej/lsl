package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionPrint(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val expression: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.INVALID
}