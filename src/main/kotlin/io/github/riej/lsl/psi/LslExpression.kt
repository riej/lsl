package io.github.riej.lsl.psi

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement

interface LslExpression : PsiElement, LslTypedElement, LslAnnotatedElement, LslScopedElement {
    override fun annotate(holder: AnnotationHolder) {
        val elementType = try {
            lslType
        } catch (e: LslPrimitiveType.TypeMismatch) {
            LslPrimitiveType.INVALID
        }
        if (elementType == LslPrimitiveType.INVALID) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Invalid expression type.")
                .range(textRange)
                .create()
        }
    }
}