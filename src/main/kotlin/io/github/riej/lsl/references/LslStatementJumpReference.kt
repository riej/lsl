package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import io.github.riej.lsl.psi.LslStatementJump

class LslStatementJumpReference(val element: LslStatementJump) : PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? = element.label

    override fun getRangeInElement(): TextRange =
        element.labelNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE
}