package io.github.riej.lsl

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.psi.LslFile
import io.github.riej.lsl.psi.LslTokenType
import io.github.riej.lsl.psi.LslTypes

class LslParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = LslLexerAdapter()
    override fun createParser(project: Project?): PsiParser = LslParser()
    override fun getFileNodeType(): IFileElementType = FILE

    override fun getWhitespaceTokens(): TokenSet = WHITE_SPACES
    override fun getCommentTokens(): TokenSet = COMMENTS
    override fun getStringLiteralElements(): TokenSet = STRINGS

    override fun createElement(node: ASTNode?): PsiElement = LslTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = LslFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements =
        ParserDefinition.SpaceRequirements.MAY

    companion object {
        val FILE = IFileElementType(LslLanguage.INSTANCE)

        @JvmField
        val LINE_COMMENT = LslTokenType("LSL_LINE_COMMENT")

        @JvmField
        val MULTILINE_COMMENT = LslTokenType("LSL_MULTILINE_COMMENT")

        @JvmField
        val WHITE_SPACE = LslTokenType("LSL_WHITE_SPACE")

        @JvmField
        val NEW_LINE = LslTokenType("LSL_NEW_LINE")

        val WHITE_SPACES = TokenSet.create(WHITE_SPACE, NEW_LINE)
        val COMMENTS = TokenSet.create(LINE_COMMENT, MULTILINE_COMMENT)
        val STRINGS = TokenSet.create(LslTypes.STRING_VALUE)
        val NUMBERS = TokenSet.create(LslTypes.INTEGER_VALUE, LslTypes.HEX_INTEGER_VALUE, LslTypes.FLOAT_VALUE)
        val KEYWORDS = TokenSet.create(
            LslTypes.DEFAULT,
            LslTypes.STATE,
            LslTypes.JUMP,
            LslTypes.RETURN,
            LslTypes.IF,
            LslTypes.ELSE,
            LslTypes.FOR,
            LslTypes.WHILE,
            LslTypes.DO,
            LslTypes.PRINT
        )
        val OPERATORS = TokenSet.create(
            LslTypes.DOT,
            LslTypes.BRACES_LEFT,
            LslTypes.BRACES_RIGHT,
            LslTypes.BRACKETS_LEFT,
            LslTypes.BRACKETS_RIGHT,
            LslTypes.PARENTHESES_LEFT,
            LslTypes.PARENTHESES_RIGHT,
            LslTypes.SEMICOLON,
            LslTypes.COMMA,
            LslTypes.EQ,
            LslTypes.ASSIGN,
            LslTypes.NOT_EQ,
            LslTypes.NOT,
            LslTypes.PLUS_PLUS,
            LslTypes.PLUS_ASSIGN,
            LslTypes.PLUS,
            LslTypes.MINUS_MINUS,
            LslTypes.MINUS_ASSIGN,
            LslTypes.MINUS,
            LslTypes.OR,
            LslTypes.BITWISE_OR,
            LslTypes.AND,
            LslTypes.BITWISE_AND,
            LslTypes.SHIFT_LEFT,
            LslTypes.LESS_EQ,
            LslTypes.LESS,
            LslTypes.SHIFT_RIGHT,
            LslTypes.GREATER_EQ,
            LslTypes.GREATER,
            LslTypes.MULTIPLE_ASSIGN,
            LslTypes.MULTIPLE,
            LslTypes.DIVIDE_ASSIGN,
            LslTypes.DIVIDE,
            LslTypes.MODULUS_ASSIGN,
            LslTypes.MODULUS,
            LslTypes.BITWISE_NOT,
            LslTypes.BITWISE_XOR,
            LslTypes.LABEL
        )
    }
}