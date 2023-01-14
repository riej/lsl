package io.github.riej.lsl.syntax

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.psi.LslTypes

class LslPairedBraceMatcher : PairedBraceMatcher {
    override fun getPairs(): Array<BracePair> = arrayOf(
        BracePair(LslTypes.BRACES_LEFT, LslTypes.BRACES_RIGHT, true),
        BracePair(LslTypes.BRACKETS_LEFT, LslTypes.BRACKETS_RIGHT, true),
        BracePair(LslTypes.PARENTHESES_LEFT, LslTypes.PARENTHESES_RIGHT, false),
    )

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?): Boolean = false

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int): Int = openingBraceOffset
}