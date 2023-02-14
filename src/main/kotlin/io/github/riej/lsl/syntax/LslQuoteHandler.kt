package io.github.riej.lsl.syntax

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import io.github.riej.lsl.parser.LslTypes

class LslQuoteHandler : SimpleTokenSetQuoteHandler(LslTypes.STRING_CONSTANT) {
}