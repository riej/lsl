package io.github.riej.lsl.annotation

import com.intellij.lang.annotation.AnnotationHolder

interface LslAnnotatedElement {
    fun annotate(holder: AnnotationHolder)
}