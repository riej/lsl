package io.github.riej.lsl.references

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.psi.impl.source.resolve.ResolveCache
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.psi.LslExpressionFunctionCall
import io.github.riej.lsl.psi.LslFunction
import io.github.riej.lsl.psi.LslState
import io.github.riej.lsl.psi.LslStatementState

class LslStateReference(val element: LslState) : PsiReferenceBase<PsiElement>(element), PsiPolyVariantReference {
    override fun resolve(): PsiElement? = multiResolve(false).firstOrNull()?.element

    override fun multiResolve(incompleteCode: Boolean): Array<ResolveResult> =
        PsiTreeUtil.collectElements(element.containingFile) {
            (it is LslStatementState) && (it.state == element)
        }.map { PsiElementResolveResult(it) }.toTypedArray()

    override fun getRangeInElement(): TextRange =
        element.nameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE

    override fun getVariants(): Array<LookupElement> =
        multiResolve(false).mapNotNull { LookupElementBuilder.createWithSmartPointer(element.name!!, it.element!!) }.toTypedArray()
}