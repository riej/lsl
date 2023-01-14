package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiManager
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslFunctionDeclaration
import io.github.riej.lsl.psi.LslScopeUtils
import io.github.riej.lsl.psi.LslStateEvent

object LslFunctionDeclarationValidator {
    fun validate(element: LslFunctionDeclaration, holder: AnnotationHolder) {
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
    }
}