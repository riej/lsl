package io.github.riej.lsl.parser

import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.LslLanguage
import org.jetbrains.annotations.NonNls

class LslTokenType(debugName: @NonNls String) : IElementType(debugName, LslLanguage.INSTANCE) {
}