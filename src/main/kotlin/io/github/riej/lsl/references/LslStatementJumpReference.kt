package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache
import com.intellij.psi.util.parentsOfType
import io.github.riej.lsl.psi.LslStatementBlock
import io.github.riej.lsl.psi.LslStatementJump
import io.github.riej.lsl.psi.LslStatementLabel

class LslStatementJumpReference(val element: LslStatementJump) :
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
        element.labelNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE

    // TODO: check real resolve order in SL
    private fun resolveInner(): Array<ResolveResult> =
        element.parentsOfType<LslStatementBlock>(false)
            .flatMap { it.children.filter { it is LslStatementLabel && it.name == element.labelName } }
            .map { PsiElementResolveResult(it) }
            .toList()
            .toTypedArray()
}