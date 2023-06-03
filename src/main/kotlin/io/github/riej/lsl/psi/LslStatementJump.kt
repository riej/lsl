package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslStatementJumpReference

class LslStatementJump(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement, LslAnnotatedElement {
    val labelNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val labelName: String?
        get() = labelNameIdentifier?.text

    val label: LslStatementLabel?
        get() = LslScopeUtils.findElementByName(this, labelName) as? LslStatementLabel?

    override fun getReference(): PsiReference = LslStatementJumpReference(this)

    override fun annotate(holder: AnnotationHolder) {
        val existingIdentifier = LslScopeUtils.findElementByName(this, labelName)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier").create()
        } else if (existingIdentifier !is LslStatementLabel) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Label expected")

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        }
    }
}