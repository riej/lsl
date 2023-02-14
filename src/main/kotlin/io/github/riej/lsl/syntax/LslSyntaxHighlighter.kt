package io.github.riej.lsl.syntax

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import io.github.riej.lsl.parser.LslLexerAdapter
import io.github.riej.lsl.parser.LslTypes

class LslSyntaxHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer = LslLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        return when (tokenType) {
            LslTypes.IDENTIFIER -> arrayOf(IDENTIFIER)
            LslTypes.INTEGER_CONSTANT, LslTypes.FLOATING_CONSTANT -> arrayOf(NUMBER)
            LslTypes.DEFAULT, LslTypes.STATE, LslTypes.JUMP, LslTypes.RETURN, LslTypes.IF, LslTypes.ELSE, LslTypes.FOR, LslTypes.DO, LslTypes.WHILE, LslTypes.PRINT -> arrayOf(
                KEYWORD
            )
            LslTypes.STRING_CONSTANT, LslTypes.UNCLOSED_STRING_CONSTANT -> arrayOf(STRING)
            LslTypes.BLOCK_COMMENT -> arrayOf(BLOCK_COMMENT)
            LslTypes.LINE_COMMENT -> arrayOf(LINE_COMMENT)

            LslTypes.ASSIGN, LslTypes.PLUS_ASSIGN, LslTypes.MINUS_ASSIGN, LslTypes.MULTIPLE_ASSIGN, LslTypes.DIVIDE_ASSIGN, LslTypes.MODULUS_ASSIGN, LslTypes.PLUS, LslTypes.MINUS, LslTypes.MULTIPLE, LslTypes.DIVIDE, LslTypes.MODULUS, LslTypes.PLUS_PLUS, LslTypes.MINUS_MINUS, LslTypes.EQUAL, LslTypes.NOT_EQUAL, LslTypes.LESS, LslTypes.LESS_EQUAL, LslTypes.GREATER, LslTypes.GREATER_EQUAL, LslTypes.BITWISE_OR, LslTypes.BITWISE_XOR, LslTypes.BITWISE_AND, LslTypes.BITWISE_NOT, LslTypes.BOOLEAN_NOT, LslTypes.BOOLEAN_AND, LslTypes.BOOLEAN_OR, LslTypes.SHIFT_LEFT, LslTypes.SHIFT_RIGHT -> arrayOf(
                OPERATION_SIGN
            )

            LslTypes.BRACE_LEFT, LslTypes.BRACE_RIGHT -> arrayOf(BRACES)
            LslTypes.DOT -> arrayOf(DOT)
            LslTypes.SEMICOLON -> arrayOf(SEMICOLON)
            LslTypes.COMMA -> arrayOf(COMMA)
            LslTypes.PARENTHESES_LEFT, LslTypes.PARENTHESES_RIGHT -> arrayOf(PARENTHESES)
            LslTypes.BRACKET_LEFT, LslTypes.BRACKET_RIGHT -> arrayOf(BRACKETS)
            LslTypes.LABEL -> arrayOf(LABEL)
            //LslTypes.TRUE, LslTypes.FALSE, LslTypes.ZERO_VECTOR, LslTypes.ZERO_ROTATION, LslTypes.NULL_KEY -> arrayOf(CONSTANT)
            LslTypes.TYPE_NAME -> arrayOf(
                TYPENAME
            )

            else -> EMPTY_KEYS
        }
    }

    companion object {
        val IDENTIFIER = TextAttributesKey.createTextAttributesKey("IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val NUMBER = TextAttributesKey.createTextAttributesKey("NUMBER", DefaultLanguageHighlighterColors.NUMBER)
        val KEYWORD = TextAttributesKey.createTextAttributesKey("KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = TextAttributesKey.createTextAttributesKey("STRING", DefaultLanguageHighlighterColors.STRING)
        val BLOCK_COMMENT = TextAttributesKey.createTextAttributesKey("BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)
        val LINE_COMMENT = TextAttributesKey.createTextAttributesKey("LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

        val OPERATION_SIGN = TextAttributesKey.createTextAttributesKey("OPERATION_SIGN", DefaultLanguageHighlighterColors.OPERATION_SIGN)
        val BRACES = TextAttributesKey.createTextAttributesKey("BRACES", DefaultLanguageHighlighterColors.BRACES)
        val DOT = TextAttributesKey.createTextAttributesKey("DOT", DefaultLanguageHighlighterColors.DOT)
        val SEMICOLON =
            TextAttributesKey.createTextAttributesKey("SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
        val COMMA = TextAttributesKey.createTextAttributesKey("COMMA", DefaultLanguageHighlighterColors.COMMA)
        val PARENTHESES =
            TextAttributesKey.createTextAttributesKey("PARENTHESES", DefaultLanguageHighlighterColors.PARENTHESES)
        val BRACKETS = TextAttributesKey.createTextAttributesKey("BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)
        val LABEL = TextAttributesKey.createTextAttributesKey("LABEL", DefaultLanguageHighlighterColors.LABEL)
        val CONSTANT = TextAttributesKey.createTextAttributesKey("CONSTANT", DefaultLanguageHighlighterColors.CONSTANT)
        val TYPENAME = TextAttributesKey.createTextAttributesKey("TYPENAME", DefaultLanguageHighlighterColors.KEYWORD)

        val EMPTY_KEYS = emptyArray<TextAttributesKey>()
    }
}