package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.impl.source.resolve.reference.ReferenceProvidersRegistry
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.parser.LslTypes

class LslStatementState(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement {
    companion object {
        private val IDENTIFIER_OR_DEFAULT = TokenSet.create(LslTypes.IDENTIFIER, LslTypes.DEFAULT)
    }

    val stateNameIdentifier: PsiElement?
        get() = findChildByType(IDENTIFIER_OR_DEFAULT)

    val stateName: String?
        get() = stateNameIdentifier?.text

    val state: LslState?
        get() = (containingFile as LslFile).scope.findElementByName(stateName) as? LslState?

    override fun getReferences(): Array<PsiReference> = ReferenceProvidersRegistry.getReferencesFromProviders(this)

    override fun getReference(): PsiReference? = references.firstOrNull()
}