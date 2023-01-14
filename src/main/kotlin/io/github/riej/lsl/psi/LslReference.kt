package io.github.riej.lsl.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiReferenceBase
import com.intellij.psi.ResolvingHint
import com.intellij.util.ReflectionUtil

class LslReference(private val element_: PsiElement) : PsiReferenceBase<PsiElement>(element_), ResolvingHint {
    override fun resolve(): PsiElement? = when (element_) {
        is LslLValue -> LslScopeUtils.findElementByName(
            element_,
            element_.identifierList.first().text
        )?.identifyingElement

        is LslCallExpr -> LslScopeUtils.findElementByName(
            element_,
            element_.identifier.text
        )?.identifyingElement

        is LslJumpStatement -> LslScopeUtils.findElementByName(
            element_,
            element_.identifier?.text
        )?.identifyingElement

        is LslStateStatement -> LslScopeUtils.findElementByName(
            element_,
            if (element_.default != null) "default" else element_.identifier?.text
        )?.identifyingElement

        else -> null
    }

    override fun canResolveTo(elementClass: Class<out PsiElement>?): Boolean =
        elementClass != null && ReflectionUtil.isAssignable(PsiFile::class.java, elementClass)

    override fun getRangeInElement(): TextRange =
        when (element_) {
            is LslLValue -> element_.identifierList.first().textRange.shiftLeft(element_.textRange.startOffset)
            is LslCallExpr -> element_.identifier.textRange.shiftLeft(element_.textRange.startOffset)
            is LslJumpStatement -> element_.identifier?.textRange?.shiftLeft(element_.textRange.startOffset)
                ?: super.getRangeInElement()

            is LslStateStatement -> (element_.default ?: element_.identifier)?.textRange?.shiftLeft(element_.textRange.startOffset)
                ?: super.getRangeInElement()

            else -> super.getRangeInElement()
        }
}