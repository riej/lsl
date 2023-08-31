package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionAssignment(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val lValue: LslLValue?
        get() = findChildByType(LslTypes.L_VALUE)

    val operator: IElementType?
        get() = findChildByType<PsiElement?>(LslTypes.OPERATORS)?.elementType

    val expression: LslExpression?
        get() = findChildrenByType<LslExpression>(LslTypes.EXPRESSIONS).lastOrNull()

    override val lslType: LslPrimitiveType
        get() = lValue?.lslType?.operationTo(expression?.lslType ?: LslPrimitiveType.INVALID, operator)
            ?: LslPrimitiveType.INVALID
}