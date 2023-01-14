package io.github.riej.lsl.validation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import io.github.riej.lsl.psi.LslCallExpr
import io.github.riej.lsl.psi.LslFunctionDeclaration
import io.github.riej.lsl.psi.LslScopeUtils
import io.github.riej.lsl.psi.LslStateDeclaration
import io.github.riej.lsl.psi.LslStateStatement

object LslStateStatementValidator {
    fun validate(element: LslStateStatement, holder: AnnotationHolder) {
        val identifier = element.identifier ?: return
        val name = identifier.text
        val existingIdentifier = LslScopeUtils.findElementByName(element, name)

        if (existingIdentifier == null) {
            holder.newAnnotation(HighlightSeverity.ERROR, "Undeclared identifier.")
                .create()
            return
        }

        if (existingIdentifier !is LslStateDeclaration) {
            holder.newAnnotation(
                HighlightSeverity.ERROR,
                "Expected state, got ${
                    (existingIdentifier as PsiElement).elementType!!.debugName.lowercase().replace('_', ' ')
                        .removeSuffix(" declaration")
                }."
            )
                .range(identifier.textRange)
                .create()
            return
        }
    }
}