package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionBinary(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val expressionLeft: LslExpression?
        get() = expressions.getOrNull(0)

    val operator: IElementType?
        get() = findChildByType<PsiElement?>(LslTypes.OPERATORS)?.elementType

    val expressionRight: LslExpression?
        get() = expressions.getOrNull(1)

    private val expressions: List<LslExpression>
        get() = findChildrenByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() {
            if (LslTypes.COMPARISON_OPERATORS.contains(operator)) {
                return LslPrimitiveType.INTEGER
            }

            return (expressionLeft?.lslType ?: LslPrimitiveType.INVALID).operationTo(
                (expressionRight?.lslType ?: LslPrimitiveType.INVALID),
                operator
            )
        }
}