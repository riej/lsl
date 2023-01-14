package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiManager
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.LslGlobalVariableDeclaration
import io.github.riej.lsl.psi.LslScopeUtils
import io.github.riej.lsl.psi.LslStateEvent
import io.github.riej.lsl.psi.LslTypes

object LslGlobalVariableValidator {
    fun validate(element: LslGlobalVariableDeclaration, holder: AnnotationHolder) {
        val identifier = element.getIdentifier()
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)
        val psiManager = PsiManager.getInstance(element.project)

        if (existingIdentifier != null && !psiManager.areElementsEquivalent(element, existingIdentifier)) {
            if (existingIdentifier is LslStateEvent) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Name reserved for state events.")
                    .range(identifier.textRange)
                    .create()
            } else {
                holder.newAnnotation(HighlightSeverity.ERROR, "Name previously declared within scope.")
                    .range(identifier.textRange)
                    .create()
            }
        }

        try {
            val resultType =
                element.expression?.getPrimitiveType()?.operationTo(element.getPrimitiveType(), LslTypes.ASSIGN)
            if (resultType == LslPrimitiveType.INVALID) {
                throw LslPrimitiveType.TypeMismatch(element.getPrimitiveType(), resultType)
            }
        } catch (e: LslPrimitiveType.TypeMismatch) {
            holder.newAnnotation(HighlightSeverity.ERROR, e.message!!)
                .range(identifier.textRange)
                .create()
        }
    }
}