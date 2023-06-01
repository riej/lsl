package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.ReplaceElementsFix
import io.github.riej.lsl.parser.LslTypes

class LslExpressionTypeCast(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression, LslAnnotatedElement {
    override val lslType: LslPrimitiveType
        get() = LslPrimitiveType.fromString(typeNameEl?.text)

    val expression: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    val parenthesesLeftEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_LEFT)

    val typeNameEl: PsiElement?
        get() = findChildByType(LslTypes.TYPE_NAME)

    val parenthesesRightEl: PsiElement?
        get() = findChildByType(LslTypes.PARENTHESES_RIGHT)

    override fun annotate(holder: AnnotationHolder) {
        val expression = expression
        if (lslType != LslPrimitiveType.INVALID && lslType == expression?.lslType) {
            holder.newAnnotation(HighlightSeverity.WEAK_WARNING, "Redundant type cast")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(ReplaceElementsFix(this, expression, "Remove redundant type cast"))
                .range(
                    TextRange.create(
                        parenthesesLeftEl?.startOffset ?: startOffset,
                        parenthesesRightEl?.endOffset ?: endOffset
                    )
                )
                .create()
        }
    }
}