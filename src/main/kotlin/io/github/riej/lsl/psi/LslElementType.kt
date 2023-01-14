package io.github.riej.lsl.psi

import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.LslLanguage
import org.jetbrains.annotations.NonNls

class LslElementType(debugName: @NonNls String) : IElementType(debugName, LslLanguage.INSTANCE) {
}