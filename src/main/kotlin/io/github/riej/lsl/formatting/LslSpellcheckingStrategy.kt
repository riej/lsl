package io.github.riej.lsl.formatting

import com.intellij.psi.PsiElement
import com.intellij.psi.util.elementType
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.parser.LslTypes

class LslSpellcheckingStrategy : SpellcheckingStrategy() {
    override fun getTokenizer(element: PsiElement?): Tokenizer<*> = when {
        element?.elementType == LslTypes.STRING_CONSTANT -> TEXT_TOKENIZER
        else -> super.getTokenizer(element)
    }

    override fun isMyContext(element: PsiElement): Boolean =
        LslLanguage.INSTANCE.`is`(element.language)
}