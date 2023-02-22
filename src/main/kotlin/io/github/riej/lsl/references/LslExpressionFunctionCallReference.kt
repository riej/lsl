package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import io.github.riej.lsl.psi.LslExpressionFunctionCall

class LslExpressionFunctionCallReference(val element: LslExpressionFunctionCall) :
    PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? = element.function

    override fun getRangeInElement(): TextRange =
        element.functionNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE
}