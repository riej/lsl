package io.github.riej.lsl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.parser.LslTypes
import io.github.riej.lsl.references.LslLValueReference

class LslLValue(node: ASTNode) : ASTWrapperPsiElement(node), LslExpression, LslAnnotatedElement {
    val variableNameIdentifier: PsiElement?
        get() = findChildByType(LslTypes.IDENTIFIER)

    val variableName: String?
        get() = identifiers.getOrNull(0)

    val item: String?
        get() = identifiers.getOrNull(1)

    private val identifiers: List<String>
        get() = findChildrenByType<PsiElement>(LslTypes.IDENTIFIER).map { it.text }

    val variable: LslVariable?
        get() = scope.findElementByName(variableName) as? LslVariable

    override fun getReference(): PsiReference = LslLValueReference(this)

    override val lslType: LslPrimitiveType
        get() {
            val variableType = variable?.lslType ?: LslPrimitiveType.INVALID

            if (item != null) {
                when (variableType) {
                    LslPrimitiveType.VECTOR -> {
                        if (listOf("x", "y", "z").contains(item)) {
                            return LslPrimitiveType.FLOAT
                        }

                        return LslPrimitiveType.INVALID
                    }

                    LslPrimitiveType.QUATERNION -> {
                        if (listOf("x", "y", "z", "s").contains(item)) {
                            return LslPrimitiveType.FLOAT
                        }

                        return LslPrimitiveType.INVALID
                    }

                    else -> return LslPrimitiveType.INVALID
                }
            }

            return variableType
        }

    override fun annotate(holder: AnnotationHolder) {
        val existingIdentifier = scope?.findElementByName(variableName)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier").create()
        } else if (existingIdentifier !is LslVariable) {
            var builder = holder.newAnnotation(HighlightSeverity.ERROR, "Variable expected")

            if (existingIdentifier is NavigatablePsiElement) {
                builder = builder.withFix(NavigateToElementFix(existingIdentifier, "Navigate to declaration"))
            }

            builder.create()
        } else if (item != null) {
            val variableType = variable?.lslType ?: LslPrimitiveType.INVALID

            if ((variableType == LslPrimitiveType.VECTOR && listOf(
                    "x",
                    "y",
                    "z"
                ).contains(item)) || (variableType == LslPrimitiveType.QUATERNION && listOf(
                    "x",
                    "y",
                    "z",
                    "s"
                ).contains(item))
            ) {
                // everything okay here
            } else {
                val dotEl = findChildByType<PsiElement>(LslTypes.DOT)
                val itemEl = findChildrenByType<PsiElement>(LslTypes.IDENTIFIER).last()

                if (dotEl != null && itemEl != null) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Invalid item")
                        .withFix(DeleteElementsFix(listOfNotNull(dotEl, itemEl), "Remove item"))
                        .range(TextRange.create(dotEl.startOffset, itemEl.endOffset))
                        .create()
                }
            }
        }
    }
}