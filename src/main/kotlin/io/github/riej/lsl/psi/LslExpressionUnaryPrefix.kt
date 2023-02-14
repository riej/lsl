package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.elementType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionUnaryPrefix(node: ASTNode) : ASTWrapperPsiElement(node), io.github.riej.lsl.psi.LslExpression {
    val operator: IElementType?
        get() = findChildByType<PsiElement?>(LslTypes.OPERATORS)?.elementType

    val expression: io.github.riej.lsl.psi.LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = expression?.lslType ?: LslPrimitiveType.INVALID
}