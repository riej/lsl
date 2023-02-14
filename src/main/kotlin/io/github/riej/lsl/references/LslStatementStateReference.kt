package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReferenceBase
import io.github.riej.lsl.psi.LslStatementState

class LslStatementStateReference(val element: LslStatementState) : PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? = element.state

    override fun getRangeInElement(): TextRange =
        element.stateNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE
}