package io.github.riej.lsl.formatting

import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import com.intellij.application.options.SmartIndentOptionsEditor
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import com.intellij.lang.Language
import com.intellij.openapi.util.NlsContexts
import com.intellij.psi.codeStyle.CodeStyleConfigurable
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CodeStyleSettingsCustomizable
import com.intellij.psi.codeStyle.LanguageCodeStyleSettingsProvider
import io.github.riej.lsl.LslLanguage

class LslLanguageCodeStyleSettingsProvider : LanguageCodeStyleSettingsProvider() {
    override fun getLanguage(): Language = LslLanguage.INSTANCE

    override fun getCodeSample(settingsType: SettingsType): String = """
integer foo = 123;

string bar(integer a) {
    list result = ["a"];
    
    if (a == 0) return "zero";
    else if (a < 0) return "negative";
    else if (a > 0) {
        do {
            integer i = 0;
            result += (string)a;
            for (; i < 10; i++) {{
                a -= i ^ (1 >> i);
                if (a < 0) jump break;
            }}
        } while (a > 0);
    } else {
        return "huh?";
    }
    @break;
    return llList2CSV(result);
}

default {
    state_entry() {
        llSay(0, (string)llGetKey() + ": " + bar(foo));
    }
}
    """.trimIndent()

    override fun getIndentOptionsEditor() = SmartIndentOptionsEditor()

    override fun getConfigurableDisplayName(): String = "Linden Script"

    override fun createConfigurable(
        baseSettings: CodeStyleSettings,
        modelSettings: CodeStyleSettings
    ): CodeStyleConfigurable {
        return LslCodeStyleConfigurable(baseSettings, modelSettings, configurableDisplayName)
    }

    override fun customizeSettings(consumer: CodeStyleSettingsCustomizable, settingsType: SettingsType) {
        when (settingsType) {
            SettingsType.BLANK_LINES_SETTINGS -> {
                consumer.showStandardOptions("BLANK_LINES_AROUND_METHOD")
                consumer.showStandardOptions("BLANK_LINES_AROUND_CLASS")
                consumer.showStandardOptions("BLANK_LINES_AROUND_METHOD_IN_INTERFACE")

                consumer.renameStandardOption("BLANK_LINES_AROUND_METHOD", "Blank lines around function")
                consumer.renameStandardOption("BLANK_LINES_AROUND_CLASS", "Blank lines around state")
                consumer.renameStandardOption("BLANK_LINES_AROUND_METHOD_IN_INTERFACE", "Blank lines around event")
            }

            SettingsType.LANGUAGE_SPECIFIC -> {
                consumer.showStandardOptions("ELSE_ON_NEW_LINE")
                consumer.showStandardOptions("WHILE_ON_NEW_LINE")
            }

            SettingsType.WRAPPING_AND_BRACES_SETTINGS -> {
                consumer.showStandardOptions("ALIGN_CONSECUTIVE_VARIABLE_DECLARATIONS")
            }

            SettingsType.SPACING_SETTINGS -> {
                consumer.showStandardOptions("SPACE_AROUND_ASSIGNMENT_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_LOGICAL_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_EQUALITY_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_RELATIONAL_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_BITWISE_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_ADDITIVE_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_MULTIPLICATIVE_OPERATORS")
                consumer.showStandardOptions("SPACE_AROUND_SHIFT_OPERATORS")

                consumer.showStandardOptions("SPACE_AFTER_COMMA")
                consumer.showStandardOptions("SPACE_BEFORE_COMMA")
                consumer.showStandardOptions("SPACE_AFTER_SEMICOLON")
                consumer.showStandardOptions("SPACE_BEFORE_SEMICOLON")
                consumer.showStandardOptions("SPACE_WITHIN_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_METHOD_CALL_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_EMPTY_METHOD_CALL_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_IF_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_WHILE_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_FOR_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_CAST_PARENTHESES")
                consumer.showStandardOptions("SPACE_WITHIN_BRACKETS")
                consumer.showStandardOptions("SPACE_WITHIN_BRACES")

                consumer.renameStandardOption("SPACE_AFTER_QUEST", "Space after parentheses")
                consumer.showStandardOptions("SPACE_AFTER_TYPE_CAST")
                consumer.showStandardOptions("SPACE_BEFORE_METHOD_CALL_PARENTHESES")
                consumer.showStandardOptions("SPACE_BEFORE_METHOD_PARENTHESES")
                consumer.showStandardOptions("SPACE_BEFORE_IF_PARENTHESES")
                consumer.showStandardOptions("SPACE_BEFORE_WHILE_PARENTHESES")
                consumer.showStandardOptions("SPACE_BEFORE_FOR_PARENTHESES")

                consumer.showStandardOptions("SPACE_BEFORE_CLASS_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_METHOD_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_IF_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_ELSE_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_WHILE_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_FOR_LBRACE")
                consumer.showStandardOptions("SPACE_BEFORE_DO_LBRACE")

                consumer.showStandardOptions("SPACE_BEFORE_ELSE_KEYWORD")
                consumer.showStandardOptions("SPACE_BEFORE_WHILE_KEYWORD")

                consumer.renameStandardOption("SPACE_WITHIN_METHOD_CALL_PARENTHESES", "Function call parentheses")
                consumer.renameStandardOption(
                    "SPACE_WITHIN_EMPTY_METHOD_CALL_PARENTHESES",
                    "Empty function call parentheses"
                )
                consumer.renameStandardOption("SPACE_WITHIN_BRACKETS", "List initializer brackets")
                consumer.renameStandardOption("SPACE_BEFORE_METHOD_CALL_PARENTHESES", "Function call parentheses")
                consumer.renameStandardOption(
                    "SPACE_BEFORE_METHOD_PARENTHESES",
                    "Function/event declaration parentheses"
                )
                consumer.renameStandardOption("SPACE_BEFORE_CLASS_LBRACE", "State left brace")
                consumer.renameStandardOption("SPACE_BEFORE_METHOD_LBRACE", "Function/event left brace")
            }

            else -> super.customizeSettings(consumer, settingsType)
        }
    }

    class LslCodeStyleConfigurable(
        settings: CodeStyleSettings,
        cloneSettings: CodeStyleSettings?,
        displayName: @NlsContexts.ConfigurableName String?
    ) : CodeStyleAbstractConfigurable(settings, cloneSettings, displayName) {
        override fun createPanel(settings: CodeStyleSettings?): CodeStyleAbstractPanel =
            LslCodeStyleMainPanel(currentSettings, settings)
    }

    class LslCodeStyleMainPanel(
        currentSettings: CodeStyleSettings?,
        settings: CodeStyleSettings?
    ) : TabbedLanguageCodeStylePanel(LslLanguage.INSTANCE, currentSettings, settings)
}