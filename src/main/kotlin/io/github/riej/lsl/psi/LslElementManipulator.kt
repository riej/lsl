package io.github.riej.lsl.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator

class LslElementManipulator : AbstractElementManipulator<LslLValue>() {
    override fun handleContentChange(element: LslLValue, range: TextRange, newContent: String?): LslLValue? {
        TODO("Not yet implemented")
    }
}