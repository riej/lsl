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
        val existingIdentifier = scope?.getDeclaredElement(name)
        if (existingIdentifier != null && existingIdentifier != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .highlightType(ProblemHighlightType.ERROR)
                .range(identifyingElement?.textRange ?: textRange)

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        } else {
            val existingIdentifierInUpperScope = scope?.parentScope?.findElementByName(name)
            if (existingIdentifierInUpperScope != null) {
                var builder = holder.newAnnotation(HighlightSeverity.WARNING, "Redeclared identifier")
                    .highlightType(ProblemHighlightType.POSSIBLE_PROBLEM)
                    .range(identifyingElement?.textRange ?: textRange)

                if (existingIdentifierInUpperScope is NavigatablePsiElement) {
                    builder =
                        builder.withFix(NavigateToElementFix(existingIdentifierInUpperScope, "Navigate to declaration"))
                }

                builder.create()
            }
        }
    }
}