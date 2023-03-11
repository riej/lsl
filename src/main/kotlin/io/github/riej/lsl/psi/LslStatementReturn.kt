package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.util.findParentOfType
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.parser.LslTypes

class LslStatementReturn(node: ASTNode) : ASTWrapperPsiElement(node), LslStatement, LslAnnotatedElement {
    val lslType: LslPrimitiveType
        get() = expression?.lslType ?: LslPrimitiveType.VOID

    val expression: LslExpression?
        get() = findChildByType(LslTypes.EXPRESSIONS)

    override fun annotate(holder: AnnotationHolder) {
        val function = findParentOfType<LslFunction>()
        val functionType = function?.lslType ?: LslPrimitiveType.VOID

        if (lslType != LslPrimitiveType.VOID || functionType != LslPrimitiveType.VOID) {
            val resultType = try {
                lslType.operationTo(functionType, LslTypes.ASSIGN)
            } catch (e: LslPrimitiveType.TypeMismatch) {
                LslPrimitiveType.INVALID
            }

            if (resultType == LslPrimitiveType.INVALID) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Invalid expression type.")
                    .range(expression!!.textRange)
                    .create()
            }
        }
    }
}