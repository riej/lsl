package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.parser.LslTypes

class LslFormattingModelBuilder : FormattingModelBuilder {
    fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
        val commonSettings = settings.getCommonSettings(LslLanguage.INSTANCE)

        return SpacingBuilder(settings, LslLanguage.INSTANCE)
            .after(LslTypes.LINE_COMMENT).lineBreakInCode()

            .around(LslTypes.ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.PLUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MINUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MULTIPLE_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.DIVIDE_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MODULUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)

            .around(LslTypes.BOOLEAN_OR).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)
            .around(LslTypes.BOOLEAN_AND).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)

            .around(LslTypes.EQUAL).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)
            .around(LslTypes.NOT_EQUAL).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)

            .aroundInside(LslTypes.GREATER, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.GREATER_EQUAL, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.LESS, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.LESS_EQUAL, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)

            .afterInside(LslTypes.GREATER, LslTypes.EXPRESSION_VECTOR).spaceIf(false)
            .beforeInside(LslTypes.LESS, LslTypes.EXPRESSION_VECTOR).spaceIf(false)
            .afterInside(LslTypes.GREATER, LslTypes.EXPRESSION_QUATERNION).spaceIf(false)
            .beforeInside(LslTypes.LESS, LslTypes.EXPRESSION_QUATERNION).spaceIf(false)

            .around(LslTypes.BITWISE_AND).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)
            .around(LslTypes.BITWISE_OR).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)
            .around(LslTypes.BITWISE_XOR).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)

            .aroundInside(LslTypes.PLUS, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)
            .aroundInside(LslTypes.MINUS, LslTypes.EXPRESSION_BINARY).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)

            .around(LslTypes.MULTIPLE).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
            .around(LslTypes.DIVIDE).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
            .around(LslTypes.MODULUS).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)

            .around(LslTypes.SHIFT_LEFT).spaceIf(commonSettings.SPACE_AROUND_SHIFT_OPERATORS)
            .around(LslTypes.SHIFT_RIGHT).spaceIf(commonSettings.SPACE_AROUND_SHIFT_OPERATORS)

            .aroundInside(LslTypes.OPERATORS, LslTypes.EXPRESSION_UNARY_PREFIX).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)
            .aroundInside(LslTypes.OPERATORS, LslTypes.EXPRESSION_UNARY_POSTFIX).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)

            .after(LslTypes.COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
            .before(LslTypes.COMMA).spaceIf(commonSettings.SPACE_BEFORE_COMMA)

            .around(LslTypes.DEFAULT_STATE_DECLARATION).blankLines(commonSettings.BLANK_LINES_AROUND_CLASS)
            .around(LslTypes.STATE_DECLARATION).blankLines(commonSettings.BLANK_LINES_AROUND_CLASS)

            .afterInside(LslTypes.BRACE_LEFT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .afterInside(LslTypes.BRACE_LEFT, LslTypes.STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACE_RIGHT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACE_RIGHT, LslTypes.STATE_DECLARATION).lineBreakInCode()

            .around(LslTypes.FUNCTION).blankLines(settings.BLANK_LINES_AROUND_METHOD)
            .around(LslTypes.EVENT).blankLines(settings.BLANK_LINES_AROUND_METHOD)
            .around(LslTypes.GLOBAL_VARIABLE).blankLines(settings.BLANK_LINES_AROUND_FIELD)

            .afterInside(LslTypes.SEMICOLON, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_AFTER_SEMICOLON)
            .beforeInside(LslTypes.SEMICOLON, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_BEFORE_SEMICOLON)

            .aroundInside(LslTypes.EXPRESSIONS, LslTypes.EXPRESSION_PARENTHESES).spaceIf(commonSettings.SPACE_WITHIN_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.EXPRESSION_FUNCTION_CALL).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.EXPRESSION_FUNCTION_CALL).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.FUNCTION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.FUNCTION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.EVENT).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.EVENT).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_IF).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATEMENT_IF).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_WHILE).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATEMENT_WHILE).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_DO).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATEMENT_DO).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)

            .aroundInside(LslTypes.EXPRESSIONS, LslTypes.EXPRESSION_PARENTHESES).spaceIf(commonSettings.SPACE_WITHIN_CAST_PARENTHESES)

            .afterInside(LslTypes.BRACKET_LEFT, LslTypes.EXPRESSION_LIST).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)
            .beforeInside(LslTypes.BRACKET_RIGHT, LslTypes.EXPRESSION_LIST).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)

            .before(LslTypes.BRACE_LEFT).lineBreakOrForceSpace(commonSettings.BRACE_STYLE != CommonCodeStyleSettings.END_OF_LINE, commonSettings.BRACE_STYLE == CommonCodeStyleSettings.END_OF_LINE)
            .after(LslTypes.BRACE_LEFT).lineBreakInCode()
            .before(LslTypes.BRACE_RIGHT).lineBreakInCode()

            .afterInside(LslTypes.PARENTHESES_RIGHT, LslTypes.EXPRESSION_TYPE_CAST).spaceIf(commonSettings.SPACE_AFTER_TYPE_CAST)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.EXPRESSION_FUNCTION_CALL).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.FUNCTION).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.EVENT).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_IF).spaceIf(commonSettings.SPACE_BEFORE_IF_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_WHILE).spaceIf(commonSettings.SPACE_BEFORE_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_DO).spaceIf(commonSettings.SPACE_BEFORE_WHILE_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_BEFORE_FOR_PARENTHESES)

            .beforeInside(LslTypes.BRACE_LEFT, LslTypes.DEFAULT_STATE_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_CLASS_LBRACE)
            .beforeInside(LslTypes.BRACE_LEFT, LslTypes.STATE_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_CLASS_LBRACE)

            .afterInside(LslTypes.BRACE_LEFT, LslTypes.FUNCTION).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)
            .afterInside(LslTypes.BRACE_LEFT, LslTypes.EVENT).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)

            .beforeInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_IF).spaceIf(commonSettings.SPACE_BEFORE_IF_LBRACE)

            .betweenInside(TokenSet.create(LslTypes.ELSE), LslTypes.STATEMENTS, LslTypes.STATEMENT_IF).spaceIf(commonSettings.SPACE_BEFORE_ELSE_LBRACE)

            .beforeInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_WHILE).spaceIf(commonSettings.SPACE_BEFORE_WHILE_LBRACE)

            .beforeInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_FOR).spaceIf(commonSettings.SPACE_BEFORE_FOR_LBRACE)

            .beforeInside(LslTypes.STATEMENTS, LslTypes.STATEMENT_DO).spaceIf(commonSettings.SPACE_BEFORE_DO_LBRACE)

            .beforeInside(LslTypes.ELSE, LslTypes.STATEMENT_IF).lineBreakOrForceSpace(commonSettings.ELSE_ON_NEW_LINE, commonSettings.SPACE_BEFORE_ELSE_KEYWORD)

            .beforeInside(LslTypes.WHILE, LslTypes.STATEMENT_DO).lineBreakOrForceSpace(commonSettings.WHILE_ON_NEW_LINE, commonSettings.SPACE_BEFORE_WHILE_KEYWORD)

            .between(LslTypes.STATEMENTS, LslTypes.LINE_COMMENT).spaceIf(true)

            .around(LslTypes.STATEMENTS).lineBreakInCode()
    }

    override fun createModel(formattingContext: FormattingContext): FormattingModel =
        FormattingModelProvider.createFormattingModelForPsiFile(
            formattingContext.containingFile,
            LslBlock(
                null,
                formattingContext.node,
                formattingContext.codeStyleSettings,
                null,
                Indent.getSmartIndent(Indent.Type.CONTINUATION),
                null,
                createSpacingBuilder(formattingContext.codeStyleSettings)
            ),
            formattingContext.codeStyleSettings,
        )
}