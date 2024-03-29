package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionQuaternion(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val expressions: List<LslExpression>
        get() = findChildrenByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.QUATERNION
}