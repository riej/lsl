package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.*
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslExpressionFunctionCallReference
import kotlin.math.min

class LslExpressionFunctionCall(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression, LslAnnotatedElement {
    val functionNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val functionName: String?
        get() = functionNameIdentifier?.text

    val function: LslFunction?
        get() = LslScopeUtils.findElementByName(this, functionName) as? LslFunction?

    val expressions: List<LslExpression>
        get() = findChildrenByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = function?.lslType ?: LslPrimitiveType.INVALID

    override fun getReference(): PsiReference
        = LslExpressionFunctionCallReference(this)

    override fun annotate(holder: AnnotationHolder) {
        val existingIdentifier = LslScopeUtils.findElementByName(this, functionName)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier").create()
        } else if (existingIdentifier !is LslFunction) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Function expected")

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        } else {
            if (expressions.isNotEmpty()) {
                (0 until min(expressions.size, existingIdentifier.arguments.size)).forEach { i ->
                    try {
                        val resultType = expressions[i].lslType
                            .operationTo(existingIdentifier.arguments[i].lslType, LslTypes.ASSIGN)
                        if (resultType == LslPrimitiveType.INVALID) {
                            throw LslPrimitiveType.TypeMismatch(existingIdentifier.arguments[i].lslType, resultType)
                        }
                    } catch (e: LslPrimitiveType.TypeMismatch) {
                        holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                            .range(expressions[i].textRange)
                            .create()
                    }
                }
            }

            val parenthesesLeftEl = findChildByType<PsiElement>(LslTypes.PARENTHESES_LEFT)
            val parenthesesRightEl = findChildByType<PsiElement>(LslTypes.PARENTHESES_RIGHT)

            if (expressions.size < existingIdentifier.arguments.size) {
                holder.newAnnotation(
                    HighlightSeverity.ERROR,
                    "Wrong arguments count (expected ${existingIdentifier.arguments.size}, got ${expressions.size})"
                )
                    .range(parenthesesRightEl?.textRange ?: expressions.lastOrNull()?.textRange ?: parenthesesLeftEl?.textRange ?: textRange)
                    .create()
            } else if (expressions.size > existingIdentifier.arguments.size) {
                val range = TextRange.create(
                    if (existingIdentifier.arguments.isNotEmpty())
                        expressions[existingIdentifier.arguments.size - 1].endOffset
                    else
                        parenthesesLeftEl?.endOffset ?: functionNameIdentifier?.endOffset ?: startOffset,
                    parenthesesRightEl?.startOffset ?: endOffset,
                )
                val elementsToRemove = children.filter { range.contains(it.textRange) }

                holder.newAnnotation(HighlightSeverity.ERROR, "Wrong arguments count (expected ${existingIdentifier.arguments.size}, got ${expressions.size})")
                    .range(range)
                    .withFix(DeleteElementsFix(elementsToRemove, "Remove extra arguments"))
                    .create()
            }
        }
    }
}