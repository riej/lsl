package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslExpressionFunctionCallReference
import kotlin.math.min

class LslExpressionFunctionCall(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression, LslAnnotatedElement {
    val functionNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val functionName: String?
        get() = functionNameIdentifier?.text

    val function: LslFunction?
        get() = (containingFile as LslFile).findElementByName(functionName) as? LslFunction?

    val expressions: List<LslExpression>
        get() = findChildrenByType(LslTypes.EXPRESSIONS)

    override val lslType: LslPrimitiveType
        get() = function?.lslType ?: LslPrimitiveType.INVALID

    override fun getReference(): PsiReference = LslExpressionFunctionCallReference(this)

    override fun annotate(holder: AnnotationHolder) {
        val function = function

        if (function == null) {
            // TODO: add create function fix
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared function").create()
//        } else if (existingIdentifier != null && existingIdentifier != function) {
//            var builder = holder.newAnnotation(HighlightSeverity.WARNING, "Function expected")
//
//            if (existingIdentifier is NavigatablePsiElement) {
//                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
//            }
//
//            builder.create()
        } else {
            if (expressions.isNotEmpty()) {
                (0 until min(expressions.size, function.arguments.size)).forEach { i ->
                    try {
                        val resultType = expressions[i].lslType
                            .operationTo(function.arguments[i].lslType, LslTypes.ASSIGN)
                        if (resultType == LslPrimitiveType.INVALID) {
                            throw LslPrimitiveType.TypeMismatch(function.arguments[i].lslType, resultType)
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

            if (expressions.size < function.arguments.size) {
                holder.newAnnotation(
                    HighlightSeverity.ERROR,
                    "Wrong arguments count (expected ${function.arguments.size}, got ${expressions.size})"
                )
                    .range(
                        parenthesesRightEl?.textRange ?: expressions.lastOrNull()?.textRange
                        ?: parenthesesLeftEl?.textRange ?: textRange
                    )
                    .create()
            } else if (expressions.size > function.arguments.size) {
                val range = TextRange.create(
                    if (function.arguments.isNotEmpty())
                        expressions[function.arguments.size - 1].endOffset
                    else
                        parenthesesLeftEl?.endOffset ?: functionNameIdentifier?.endOffset ?: startOffset,
                    parenthesesRightEl?.startOffset ?: endOffset,
                )
                val elementsToRemove = children.filter { range.contains(it.textRange) }

                holder.newAnnotation(
                    HighlightSeverity.ERROR,
                    "Wrong arguments count (expected ${function.arguments.size}, got ${expressions.size})"
                )
                    .range(range)
                    .withFix(DeleteElementsFix(elementsToRemove, "Remove extra arguments"))
                    .create()
            }
        }
    }
}