package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.LslExpressionFunctionCall
import io.github.riej.lsl.psi.LslFunction

class LslExpressionFunctionCallReference(val element: LslExpressionFunctionCall) :
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
        element.functionNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE

    private fun resolveInner(): Array<ResolveResult> =
        element
            .containingFile
            .children
            .filterIsInstance<LslFunction>()
            .filter { it.name == element.functionName }
            .plus(KwdbData.getInstance(element.project).functions[element.functionName])
            .filterNotNull()
            .map { PsiElementResolveResult(it) }
            .toTypedArray()
}