package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.parser.LslTypes

class LslExpressionFunctionCall(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression {
    val functionNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val functionName: String?
        get() = functionNameIdentifier?.text

    val expressions: List<LslExpression>
        get() = findChildrenByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = (reference?.resolve() as? LslFunction)?.lslType ?: LslPrimitiveType.INVALID

    val function: LslFunction?
        get() = reference?.resolve() as? LslFunction

    val parenthesesLeftEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_LEFT)
    val parenthesesRightEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_RIGHT)

    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = references.firstOrNull()
}