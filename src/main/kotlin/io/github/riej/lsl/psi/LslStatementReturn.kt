package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.util.findParentOfType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslStatementReturn(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val lslType: LslPrimitiveType
        get() = expression?.lslType ?: LslPrimitiveType.VOID

    val expression: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    val returnKeywordEl: PsiElement
        get() = findChildByType(LslTypes.RETURN)!!

    val semicolonEl: PsiElement?
        get() = findChildByType(LslTypes.SEMICOLON)

    val parentFunction: LslFunction?
        get() = findParentOfType<LslFunction>()

    val parentEvent: LslEvent?
        get() = findParentOfType<LslEvent>()
}