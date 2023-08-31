package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache
import io.github.riej.lsl.KwdbData
import io.github.riej.lsl.psi.*

class LslLValueReference(val element: LslLValue) :
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
        element.variableNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE

    private fun resolveInner(): Array<ResolveResult> {
        val result = ArrayList<ResolveResult>()
        var node: PsiElement? = element
        while (node != null) {
            when (node) {
                is LslStatementBlock ->
                    result.addAll(
                        node.children.takeWhile { it != node }
                            .filterIsInstance<LslStatementVariable>()
                            .filter { it.name == element.variableName }
                            .reversed()
                            .map { PsiElementResolveResult(it) }
                    )

                is LslEvent ->
                    result.addAll(
                        node.arguments
                            .filter { it.name == element.variableName }
                            .map { PsiElementResolveResult(it) }
                    )

                is LslFunction ->
                    result.addAll(
                        node.arguments
                            .filter { it.name == element.variableName }
                            .map { PsiElementResolveResult(it) }
                    )

                is LslFile ->
                    return result.plus(
                        node.children
                            .filterIsInstance<LslGlobalVariable>()
                            .filter { it.name == element.variableName }
                            .reversed()
                            .map { PsiElementResolveResult(it) }
                    ).plus(
                        arrayOf(
                            KwdbData.getInstance(element.project).constants[element.variableName]
                        )
                            .filterNotNull()
                            .map { PsiElementResolveResult(it) }
                    ).toTypedArray()
            }

            node = node.parent
        }

        return result.toTypedArray()
    }
}