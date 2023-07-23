package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.util.parentOfType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslStatementJumpReference

class LslStatementJump(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement, LslAnnotatedElement {
    val labelNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val labelName: String?
        get() = labelNameIdentifier?.text

    val label: LslStatementLabel?
        get() = if (labelName == null) null else (parentOfType<LslStatementBlock>()?.labels?.get(labelName))

    override fun getReference(): PsiReference = LslStatementJumpReference(this)

    override fun annotate(holder: AnnotationHolder) {
        if (label == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier").create()
        }
    }
}