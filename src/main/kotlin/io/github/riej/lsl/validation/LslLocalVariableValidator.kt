package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiManager
import com.intellij.psi.util.parentOfTypes
import io.github.riej.lsl.LslPrimitiveType
import io.github.riej.lsl.psi.*

object LslLocalVariableValidator {
    fun validate(element: LslLocalVariableDeclaration, holder: AnnotationHolder) {
        val identifier = element.getIdentifier() ?: return
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)
        val psiManager = PsiManager.getInstance(element.project)

        // there's existing identifier and it's not current element
        if (existingIdentifier != null && !psiManager.areElementsEquivalent(element, existingIdentifier)) {
            // local variable in current block { ... }
            if (existingIdentifier is LslLocalVariableDeclaration && psiManager.areElementsEquivalent(
                    element.parentOfTypes(
                        LslBlock::class
                    ), existingIdentifier.parentOfTypes(LslBlock::class)
                )
            ) {
                holder.newAnnotation(HighlightSeverity.ERROR, "Name previously declared within scope.")
                    .range(identifier.textRange)
                    .create()
                return
            } else if (existingIdentifier.containingFile != element.containingFile) { // constant from kwdb - it has different containingFile
                if (existingIdentifier is LslStateEvent) {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Name reserved for state events.")
                        .range(identifier.textRange)
                        .create()
                } else {
                    holder.newAnnotation(HighlightSeverity.ERROR, "Name previously declared within scope.")
                        .range(identifier.textRange)
                        .create()
                }
            } else { // variable already declared, but somewhere else in code - so we can redeclare it
                holder.newAnnotation(HighlightSeverity.WARNING, "Name previously declared within scope.")
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