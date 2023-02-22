package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionTypeCast(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(findChildByType<PsiElement?>(LslTypes.TYPE_NAME)?.text)
}