package io.github.riej.lsl.formatting

import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.lang.Language
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import io.github.riej.lsl.LslLanguage

class LslLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
    override fun getLanguage(): Language = LslLanguage.INSTANCE

    override fun getCodeSample(settingsType: SettingsType): String = """
integer foo = 123;

string bar() {
    return (string)(foo * 2);
}

default {
    state_entry() {
        llSay(0, bar());
    }
}
    """.trimIndent()

    override fun getIndentOptionsEditor() = SmartIndentOptionsEditor()
}