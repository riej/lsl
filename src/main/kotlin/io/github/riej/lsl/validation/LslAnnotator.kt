package io.github.riej.lsl.validation

import com.intellij.codeInspection.InspectionManager
import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.util.descendants
import com.intellij.refactoring.suggested.endOffset
import com.intellij.refactoring.suggested.startOffset
import io.github.riej.lsl.psi.*
import io.github.riej.lsl.psi.impl.LslPsiImplUtil
import io.github.riej.lsl.validation.*
import kotlin.math.min

class LslAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is LslCallExpr -> LslCallExprValidator.validate(element, holder)
            is LslLValue -> LslLValueValidator.validate(element, holder)
            is LslFunctionDeclaration -> LslFunctionDeclarationValidator.validate(element, holder)
            is LslLocalVariableDeclaration -> LslLocalVariableValidator.validate(element, holder)
            is LslGlobalVariableDeclaration -> LslGlobalVariableValidator.validate(element, holder)
            is LslExpression -> LslExpressionValidator.validate(element, holder)
            is LslStateDeclaration -> LslStateDeclarationValidator.validate(element, holder)
            is LslStateStatement -> LslStateStatementValidator.validate(element, holder)
            is LslLabelStatement -> LslLabelStatementValidator.validate(element, holder)
            is LslJumpStatement -> LslJumpStatementValidator.validate(element, holder)
            is LslStateEvent -> LslStateEventValidator.validate(element, holder)
        }
    }
}