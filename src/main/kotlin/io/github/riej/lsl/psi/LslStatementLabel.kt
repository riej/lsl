package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiReference
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.references.LslStatementLabelReference
import io.github.riej.lsl.scope.LslScopeUtils

class LslStatementLabel(node: ASTNode) : ASTWrapperLslNamedElement(node), LslStatement, NavigatablePsiElement,
    LslAnnotatedElement {
    override fun getReference(): PsiReference = LslStatementLabelReference(this)

    override fun annotate(holder: AnnotationHolder) {
        if ((containingFile as? LslFile)?.kwdbData?.events?.get(name) != null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Reserved identifier")
                .create()
            return
        }

        val existingIdentifier = LslScopeUtils.findElementByName(this, name)

        if (existingIdentifier != this) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Redeclared identifier")
                .range(identifyingElement?.textRange ?: textRange)

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
            return
        }

        if (reference.resolve() == null) {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Unused label")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(DeleteElementsFix(listOf(this), "Remove label"))
                .create()
        }
    }
}