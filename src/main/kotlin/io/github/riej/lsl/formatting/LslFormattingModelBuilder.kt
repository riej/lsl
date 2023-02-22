package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.parser.LslTypes

class LslFormattingModelBuilder : FormattingModelBuilder {
    val identifierSet = TokenSet.create(LslTypes.IDENTIFIER)

    fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
        // TODO: use settings, not fixed rules

        return SpacingBuilder(settings, LslLanguage.INSTANCE)
            .after(LslTypes.LINE_COMMENT).lineBreakInCode()

            .after(LslTypes.GLOBAL_VARIABLE).lineBreakInCode()
            .aroundInside(LslTypes.STATEMENTS, LslTypes.FUNCTION).lineBreakInCode()
            .aroundInside(LslTypes.STATEMENTS, LslTypes.EVENT).lineBreakInCode()
            .aroundInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_BLOCK).lineBreakInCode()

            .before(LslTypes.SEMICOLON).none()
            .around(LslTypes.KEYWORDS).spaceIf(true)

            .aroundInside(LslTypes.OPERATORS, LslTypes.GLOBAL_VARIABLE).spaceIf(true)
            .aroundInside(LslTypes.OPERATORS, LslTypes.STATEMENT_VARIABLE).spaceIf(true)
            .aroundInside(LslTypes.OPERATORS, LslTypes.EXPRESSION_BINARY).spaceIf(true)
            .aroundInside(LslTypes.OPERATORS, LslTypes.EXPRESSION_ASSIGNMENT).spaceIf(true)

            .betweenInside(LslTypes.OPERATORS, identifierSet, LslTypes.EXPRESSION_UNARY_PREFIX).none()
            .betweenInside(LslTypes.OPERATORS, identifierSet, LslTypes.EXPRESSION_UNARY_POSTFIX).none()

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.EXPRESSION_PARENTHESES).none()
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.EXPRESSION_PARENTHESES).spaceIf(false)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.EXPRESSION_TYPE_CAST).none()
            .aroundInside(LslTypes.PARENTHESES_RIGHT, LslTypes.EXPRESSION_TYPE_CAST).none()

            .before(LslTypes.COMMA).none()
            .after(LslTypes.COMMA).spaceIf(true)

            .around(LslTypes.FUNCTION).blankLines(1)
            .around(LslTypes.DEFAULT_STATE_DECLARATION).blankLines(1)
            .around(LslTypes.STATE_DECLARATION).blankLines(1)
            .between(LslTypes.EVENT, LslTypes.EVENT).blankLines(1)

            .afterInside(LslTypes.BRACE_LEFT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .afterInside(LslTypes.BRACE_LEFT, LslTypes.STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACE_RIGHT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACE_RIGHT, LslTypes.STATE_DECLARATION).lineBreakInCode()
            .aroundInside(LslTypes.IDENTIFIER, LslTypes.DEFAULT_STATE_DECLARATION).spaceIf(true)
            .aroundInside(LslTypes.IDENTIFIER, LslTypes.STATE_DECLARATION).spaceIf(true)

            .between(LslTypes.PARENTHESES_RIGHT, LslTypes.BRACE_LEFT).spaceIf(true)

            .betweenInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATEMENT_BLOCK, LslTypes.STATEMENT_IF).spaceIf(true)
            .betweenInside(LslTypes.STATEMENT_BLOCK, LslTypes.ELSE, LslTypes.STATEMENT_IF).spaceIf(true)
            .betweenInside(LslTypes.ELSE, LslTypes.STATEMENT_BLOCK, LslTypes.STATEMENT_IF).spaceIf(true)

            .aroundInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_IF).spaceIf(true)
            .beforeInside(LslTypes.ELSE, LslTypes.STATEMENT_IF).lineBreakInCode()
    }

    override fun createModel(formattingContext: FormattingContext): FormattingModel =
        FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            LslBlock(
                formattingContext.node,
                null,
                null,
                createSpacingBuilder(formattingContext.codeStyleSettings)
            ),
            formattingContext.codeStyleSettings,
        )
}