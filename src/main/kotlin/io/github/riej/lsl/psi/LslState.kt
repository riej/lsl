package io.github.riej.lsl.psi

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.scope.LslPsiScope

interface LslState : LslNamedElement, PsiNameIdentifierOwner, NavigatablePsiElement, LslSymbolDeclaration, LslPsiScope,
    LslAnnotatedElement {
    val events: List<LslEvent>
        get() = eventsEl?.events.orEmpty()

    val eventsEl: LslEvents?
        get() = children.firstNotNullOfOrNull { it as? LslEvents }

    val usages: List<PsiElement>
        get() = PsiTreeUtil.collectElements(containingFile) {
            (it is LslStatementState) && (it.state == this)
        }.toList()

    val braceLeftEl: PsiElement?
        get() = node.findChildByType(LslTypes.BRACE_LEFT)?.psi

    val braceRightEl: PsiElement?
        get() = node.findChildByType(LslTypes.BRACE_RIGHT)?.psi

    override fun annotate(holder: AnnotationHolder) {
        super.annotate(holder)

        if (events.isEmpty()) {
            holder.newAnnotation(HighlightSeverity.ERROR, "State is blank")
                .range(TextRange.create(braceLeftEl?.startOffset ?: startOffset, braceRightEl?.endOffset ?: endOffset))
                .create()
        }
    }
}