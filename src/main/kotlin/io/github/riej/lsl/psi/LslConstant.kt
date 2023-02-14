package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslConstant(node: ASTNode) : ASTWrapperPsiElement(node), io.github.riej.lsl.psi.LslExpression,
    io.github.riej.lsl.psi.LslTypedElement {
    val integerValue: PsiElement?
        get() = findChildByType(LslTypes.INTEGER_CONSTANT)

    val floatValue: PsiElement?
        get() = findChildByType(LslTypes.FLOATING_CONSTANT)

    val stringValue: PsiElement?
        get() = findChildByType(LslTypes.STRING_CONSTANT)

    override val lslType: LslPrimitiveType
        get() = when {
            integerValue != null -> LslPrimitiveType.INTEGER
            floatValue != null -> LslPrimitiveType.FLOAT
            stringValue != null -> LslPrimitiveType.STRING
            else -> LslPrimitiveType.INVALID
        }
}