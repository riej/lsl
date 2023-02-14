package io.github.riej.lsl.annotation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class LslAnnotationProvider : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        (element as? LslAnnotatedElement)?.annotate(holder)
    }
}