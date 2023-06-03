package io.github.riej.lsl.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import io.github.riej.lsl.scope.LslScope

interface LslScopedElement : PsiElement {
    val scope: LslScope?
        get() = PsiTreeUtil.findFirstParent(parent) { it is LslScope } as LslScope
}