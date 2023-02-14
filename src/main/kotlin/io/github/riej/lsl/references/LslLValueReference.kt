package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import io.github.riej.lsl.psi.LslLValue

class LslLValueReference(val element: LslLValue) : PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? = element.variable

    override fun getRangeInElement(): TextRange =
        try {
            element.variableNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE
        } catch (e: IllegalArgumentException) {
            TextRange.EMPTY_RANGE
        }
}