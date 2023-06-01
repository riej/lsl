package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslStatementStateReference

class LslStatementState(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement, LslAnnotatedElement {
    companion object {
        private val IDENTIFIER_OR_DEFAULT = TokenSet.create(LslTypes.IDENTIFIER, LslTypes.DEFAULT)
    }

    val stateNameIdentifier: PsiElement?
        get() = findChildByType(IDENTIFIER_OR_DEFAULT)

    val stateName: String?
        get() = stateNameIdentifier?.text

    val state: LslState?
        get() = (containingFile as LslFile).findElementByName(stateName) as? LslState?

    override fun getReference(): PsiReference = LslStatementStateReference(this)

    override fun annotate(holder: AnnotationHolder) {
        if (state == null) {
            // TODO: add create state fix
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared state")
                .range(stateNameIdentifier?.textRange ?: textRange).create()
//        } else if (existingIdentifier !is LslState) {
//            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "State expected")
//
//            if (existingIdentifier is NavigatablePsiElement) {
//                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
//            }
//
//            builder.create()
        }
    }
}