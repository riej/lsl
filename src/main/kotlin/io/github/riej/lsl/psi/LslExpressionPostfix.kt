package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionPostfix(node: ASTNode) : ASTWrapperPsiElement(node), io.github.riej.lsl.psi.LslExpression {
    val expression: io.github.riej.lsl.psi.LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = expression?.lslType ?: LslPrimitiveType.INVALID
}