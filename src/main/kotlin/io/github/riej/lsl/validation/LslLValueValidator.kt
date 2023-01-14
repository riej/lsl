package io.github.riej.lsl.validation

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.util.elementType
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.psi.impl.LslPsiImplUtil
import io.github.riej.lsl.validation.fixes.RemoveCodeLocalQuickFix

object LslLValueValidator {
    fun validate(element: LslLValue, holder: AnnotationHolder) {
        val identifier = element.identifierList.first()
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier.")
                .range(identifier.textRange)
                .create()
            return
        }

        if (existingIdentifier !is LslGlobalVariableDeclaration && existingIdentifier !is LslLocalVariableDeclaration && existingIdentifier !is LslArgument) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Expected variable, got ${(existingIdentifier as PsiElement).elementType!!.debugName.lowercase().replace('_', ' ').removeSuffix(" declaration")}.")
                .range(identifier.textRange)
                .create()
            return
        }

        if (existingIdentifier !is LslTypedElement) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Invalid identifier.")
                .range(identifier.textRange)
                .create()
            return
        }

        val primitiveType = try {
            existingIdentifier.getPrimitiveType()
        } catch (e: LslPrimitiveType.TypeMismatch) {
            holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                .range(element.textRange)
                .create()
            return
        }

        if (element.identifierList.size > 1) {
            val item = element.identifierList.last()
            val itemRangeWithDot = TextRange.create(element.dot!!.startOffset, item.endOffset)
            val removeItemFix = RemoveCodeLocalQuickFix("Remove item", element, itemRangeWithDot)

            when (primitiveType) {
                LslPrimitiveType.QUATERNION -> if (!arrayOf(
                        "x",
                        "y",
                        "z",
                        "s"
                    ).contains(item.text)
                ) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Invalid variable item.")
                        .range(item.textRange)
                        .newLocalQuickFix(removeItemFix, InspectionManager.getInstance(element.project).createProblemDescriptor(element, itemRangeWithDot.shiftLeft(element.startOffset), removeItemFix.message, ProblemHighlightType.ERROR, true, removeItemFix))
                        .registerFix()
                        .create()
                    return
                }

                LslPrimitiveType.VECTOR -> if (!arrayOf("x", "y", "z").contains(element.identifierList.last().text)) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Invalid variable item.")
                        .range(item.textRange)
                        .newLocalQuickFix(removeItemFix, InspectionManager.getInstance(element.project).createProblemDescriptor(element, itemRangeWithDot.shiftLeft(element.startOffset), removeItemFix.message, ProblemHighlightType.ERROR, true, removeItemFix))
                        .registerFix()
                        .create()
                    return
                }

                else -> {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Invalid variable item.")
                        .range(item.textRange)
                        .newLocalQuickFix(removeItemFix, InspectionManager.getInstance(element.project).createProblemDescriptor(element, itemRangeWithDot.shiftLeft(element.startOffset), removeItemFix.message, ProblemHighlightType.ERROR, true, removeItemFix))
                        .registerFix()
                        .create()
                    return
                }
            }
        }
    }
}