package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import com.intellij.psi.util.parentOfType
import io.github.riej.lsl.parser.LslTypes

class LslStatementJump(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    val labelNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val labelName: String?
        get() = labelNameIdentifier?.text

    val label: LslStatementLabel?
        get() = if (labelName == null) null else (parentOfType<LslStatementBlock>()?.labels?.get(labelName))

    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = references.firstOrNull()
}