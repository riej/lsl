package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiManager
import com.intellij.psi.util.elementType
import com.intellij.psi.util.findTopmostParentInFile
import io.github.riej.lsl.psi.*

object LslJumpStatementValidator {
    fun validate(element: LslJumpStatement, holder: AnnotationHolder) {
        val identifier = element.identifier ?: return
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier.")
                .create()
            return
        }

        if (existingIdentifier !is LslLabelStatement) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Expected label, got ${(existingIdentifier as PsiElement).elementType!!.debugName.lowercase().replace('_', ' ').removeSuffix(" declaration")}.")
                .range(identifier.textRange)
                .create()
            return
        }
    }
}