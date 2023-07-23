package io.github.riej.lsl.psi

import com.intellij.psi.PsiElement
import io.github.riej.lsl.scope.LslScope
import io.github.riej.lsl.scope.LslScopeUtils

interface LslScopedElement : PsiElement {
    val scope: LslScope
        get() = LslScopeUtils.getScopeFor(this)
}