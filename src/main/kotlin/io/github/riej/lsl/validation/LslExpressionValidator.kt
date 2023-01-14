package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.LslExpression

object LslExpressionValidator {
    fun validate(element: LslExpression, holder: AnnotationHolder) {
        val elementType = try {
            element.getPrimitiveType()
        } catch (e: LslPrimitiveType.TypeMismatch) {
            LslPrimitiveType.INVALID
        }
        if (elementType == LslPrimitiveType.INVALID) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Invalid expression type.")
                .range(element.textRange)
                .create()
        }
    }
}