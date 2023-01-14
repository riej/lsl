package io.github.riej.lsl.validation

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiManager
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.validation.fixes.RemoveCodeLocalQuickFix
import kotlin.math.min

object LslStateEventValidator {
    fun validate(element: LslStateEvent, holder: AnnotationHolder) {
        val identifier = element.getIdentifier()
        val name = identifier.text
        val psiManager = PsiManager.getInstance(element.project)

        val definition = LslScopeUtils.getEventByName(element, name)
        if (definition == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unknown state event.")
                .range(identifier.textRange)
                .create()
            return
        }

        val existingIdentifier = element.parent.children.firstOrNull { (it is LslStateEvent) && (it.getIdentifier().text == name) }

        if (existingIdentifier != null && !psiManager.areElementsEquivalent(element, existingIdentifier)) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Name previously declared within scope.")
                .range(identifier.textRange)
                .create()
        }

        if (element.argumentList.isNotEmpty()) {
            (0 until min(element.argumentList.size, definition.argumentList.size)).forEach { i ->
                try {
                    val resultType = element.argumentList[i].getPrimitiveType().operationTo(definition.argumentList[i].getPrimitiveType(), LslTypes.ASSIGN)
                    if (resultType == LslPrimitiveType.INVALID) {
                        throw LslPrimitiveType.TypeMismatch(definition.argumentList[i].getPrimitiveType(), resultType)
                    }
                } catch (e: LslPrimitiveType.TypeMismatch) {
                    holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                        .range(element.argumentList[i].textRange)
                        .create()
                }
            }
        }

        if (element.argumentList.size < definition.argumentList.size) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Wrong arguments count (expected ${definition.argumentList.size}, got ${element.argumentList.size}).")
                .range(element.parenthesesRight!!.textRange)
                .create()
        } else if (element.argumentList.size > definition.argumentList.size) {
            val range = TextRange.create(
                if (definition.argumentList.isNotEmpty())
                    element.argumentList[definition.argumentList.size - 1].endOffset
                else
                    element.parenthesesLeft!!.endOffset,
                element.parenthesesRight!!.startOffset,
            )
            val quickFix = RemoveCodeLocalQuickFix("Remove extra arguments", element, range)
            holder.newAnnotation(HighlightSeverity.ERROR, "Wrong arguments count (expected ${definition.argumentList.size}, got ${element.argumentList.size}).")
                .range(range)
                .newLocalQuickFix(quickFix, InspectionManager.getInstance(element.project).createProblemDescriptor(element, range.shiftLeft(element.startOffset), quickFix.message, ProblemHighlightType.ERROR, true, quickFix))
                .registerFix()
                .create()
        }
    }
}