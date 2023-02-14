package io.github.riej.lsl.psi

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiReference
import io.github.riej.lsl.LslScopeUtils
import io.github.riej.lsl.annotation.LslAnnotatedElement
import io.github.riej.lsl.annotation.fixes.DeleteElementsFix
import io.github.riej.lsl.annotation.fixes.NavigateToElementFix
import io.github.riej.lsl.references.LslStateReference

interface LslState : PsiElement, PsiNameIdentifierOwner, NavigatablePsiElement {
    val events: List<LslEvent>
        get() = this.children.mapNotNull { it as? LslEvent }
}