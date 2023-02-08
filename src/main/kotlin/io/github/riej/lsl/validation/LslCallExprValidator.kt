package io.github.riej.lsl.validation

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.validation.fixes.RemoveCodeLocalQuickFix
import kotlin.math.min

object LslCallExprValidator {
    fun validate(element: LslCallExpr, holder: AnnotationHolder) {
        val identifier = element.identifier
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier.")
                .create()
            return
        }

        if (existingIdentifier !is LslFunctionDeclaration) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Expected function, got ${(existingIdentifier as PsiElement).elementType!!.toString().lowercase().replace('_', ' ').removeSuffix(" declaration")}.")
                .range(identifier.textRange)
                .create()
            return
        }

        if (element.expressionList.isNotEmpty()) {
            (0 until min(element.expressionList.size, existingIdentifier.argumentList.size)).forEach { i ->
                try {
                    val resultType = element.expressionList[i].getPrimitiveType().operationTo(existingIdentifier.argumentList[i].getPrimitiveType(), LslTypes.ASSIGN)
                    if (resultType == LslPrimitiveType.INVALID) {
                        throw LslPrimitiveType.TypeMismatch(element.getPrimitiveType(), resultType)
                    }
                } catch (e: LslPrimitiveType.TypeMismatch) {
                    holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                        .range(element.expressionList[i].textRange)
                        .create()
                }
            }
        }

        if (element.expressionList.size < existingIdentifier.argumentList.size) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Wrong arguments count (expected ${existingIdentifier.argumentList.size}, got ${element.expressionList.size}).")
                .range(element.parenthesesRight.textRange)
                .create()
        } else if (element.expressionList.size > existingIdentifier.argumentList.size) {
            val range = TextRange.create(
                if (existingIdentifier.argumentList.isNotEmpty())
                    element.expressionList[existingIdentifier.argumentList.size - 1].endOffset
                else
                    element.parenthesesLeft.endOffset,
                element.parenthesesRight.startOffset,
            )
            val quickFix = RemoveCodeLocalQuickFix("Remove extra arguments", element, range)
            holder.newAnnotation(HighlightSeverity.ERROR, "Wrong arguments count (expected ${existingIdentifier.argumentList.size}, got ${element.expressionList.size}).")
                .range(range)
                .newLocalQuickFix(quickFix, InspectionManager.getInstance(element.project).createProblemDescriptor(element, range.shiftLeft(element.startOffset), quickFix.message, ProblemHighlightType.ERROR, true, quickFix))
                .registerFix()
                .create()
        }
    }
}