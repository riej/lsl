package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache
import io.github.riej.lsl.psi.LslState
import io.github.riej.lsl.psi.LslStatementState

class LslStatementStateReference(val element: LslStatementState) :
    PsiReferenceBase<PsiElement>(element), PsiPolyVariantReference {
    override fun resolve(): PsiElement? =
        multiResolve(false).firstOrNull()?.element

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> =
        ResolveCache.getInstance(element.project).resolveWithCaching(
            this,
            { referenceBase, _ -> referenceBase.resolveInner() },
            false, incompleteCode,
        )

    override fun getRangeInElement(): TextRange =
        element.stateNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE

    private fun resolveInner(): Array<ResolveResult> =
        element
            .containingFile
            .children
            .filterIsInstance<LslState>()
            .filter { it.name == element.stateName }
            .map { PsiElementResolveResult(it) }
            .toTypedArray()
}