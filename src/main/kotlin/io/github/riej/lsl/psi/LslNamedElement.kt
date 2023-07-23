package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix

interface LslNamedElement : PsiNameIdentifierOwner, LslAnnotatedElement, LslScopedElement {
    override fun annotate(holder: AnnotationHolder) {
        val scope = scope
        val existingElement = scope.getDeclaredElement(name)
        if (existingElement != null && existingElement != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .highlightType(ProblemHighlightType.ERROR)
                .range(identifyingElement?.textRange ?: textRange)

            if (existingElement is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingElement, "Navigate to declaration"))
            }

            builder.create()
        } else {
            val existingElementInUpperScope = scope.parentScope?.findElementByName(name)
            if (existingElementInUpperScope != null) {
                var builder = holder.newAnnotation(HighlightSeverity.WARNING, "Redeclared identifier")
                    .highlightType(ProblemHighlightType.WARNING)
                    .range(identifyingElement?.textRange ?: textRange)

                if (existingElementInUpperScope is NavigatablePsiElement) {
                    builder =
                        builder.withFix(NavigateToElementFix(existingElementInUpperScope, "Navigate to declaration"))
                }

                builder.create()
            }
        }
    }
}