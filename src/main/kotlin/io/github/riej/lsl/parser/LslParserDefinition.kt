package io.github.riej.lsl.parser

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
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.psi.LslFile

class LslParserDefinition : ParserDefinition {
    override fun createLexer(project: Project?): Lexer = LslLexerAdapter()
    override fun createParser(project: Project?): PsiParser = LslParserAdapter()
    override fun getFileNodeType(): IFileElementType = FILE

    override fun getWhitespaceTokens(): TokenSet = LslTypes.WHITESPACES
    override fun getCommentTokens(): TokenSet = LslTypes.COMMENTS
    override fun getStringLiteralElements(): TokenSet = LslTypes.STRINGS

    override fun createElement(node: ASTNode?): PsiElement = LslTypes.Factory.createElement(node)
    override fun createFile(viewProvider: FileViewProvider): PsiFile = LslFile(viewProvider)

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements =
        ParserDefinition.SpaceRequirements.MAY

    companion object {
        val FILE = IFileElementType(LslLanguage.INSTANCE)
    }
}