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
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.parser.LslTypes

class LslStatementBlock(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement, LslAnnotatedElement {
    val statements: List<LslStatement>
        get() = findChildrenByType(LslTypes.STATEMENTS)

    val braceLeftEl: PsiElement?
        get() = findChildByType(LslTypes.BRACE_LEFT)

    val braceRightEl: PsiElement?
        get() = findChildByType(LslTypes.BRACE_RIGHT)

    override fun annotate(holder: AnnotationHolder) {
        val statementReturn = statements.indexOfFirst { it is LslStatementReturn }
        if (statementReturn >= 0 && statementReturn < statements.lastIndex) {
            val statementsAfterReturn = statements.subList(statementReturn + 1, statements.size)

            holder.newAnnotation(HighlightSeverity.INFORMATION, "Unreachable code")
                .highlightType(ProblemHighlightType.LIKE_UNUSED_SYMBOL)
                .withFix(DeleteElementsFix(statementsAfterReturn, "Remove unreachable code"))
                .range(
                    TextRange.create(
                        statementsAfterReturn.first().startOffset,
                        statementsAfterReturn.last().endOffset
                    )
                )
                .create()
        }
    }
}