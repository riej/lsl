package io.github.riej.lsl.references

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolvingHint
import com.intellij.refactoring.suggested.startOffset
import com.intellij.util.ReflectionUtil
import io.github.riej.lsl.psi.LslExpressionFunctionCall
import io.github.riej.lsl.psi.LslLValue
import io.github.riej.lsl.psi.LslStatementJump

class LslStatementJumpReference(val element: LslStatementJump) : PsiReferenceBase<PsiElement>(element) {
    override fun resolve(): PsiElement? = element.label

    override fun getRangeInElement(): TextRange =
        element.labelNameIdentifier?.textRangeInParent ?: TextRange.EMPTY_RANGE
}