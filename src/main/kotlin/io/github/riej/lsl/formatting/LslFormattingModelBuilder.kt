package io.github.riej.lsl.formatting

import com.intellij.formatting.*
import com.intellij.psi.PsiComment
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.psi.codeStyle.CommonCodeStyleSettings
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.LslLanguage
import io.github.riej.lsl.LslParserDefinition
import io.github.riej.lsl.psi.LslTypes
import javax.swing.text.Position.Bias

class LslFormattingModelBuilder : FormattingModelBuilder {
    fun createSpacingBuilder(settings: CodeStyleSettings): SpacingBuilder {
        val commonSettings = settings.getCommonSettings(LslLanguage.INSTANCE)

        return SpacingBuilder(settings, LslLanguage.INSTANCE)
            .after(LslParserDefinition.LINE_COMMENT).lineBreakInCode()

            .around(LslTypes.ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.PLUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MINUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MULTIPLE_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.DIVIDE_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)
            .around(LslTypes.MODULUS_ASSIGN).spaceIf(commonSettings.SPACE_AROUND_ASSIGNMENT_OPERATORS)

            .around(LslTypes.OR).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)
            .around(LslTypes.AND).spaceIf(commonSettings.SPACE_AROUND_LOGICAL_OPERATORS)

            .around(LslTypes.EQ).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)
            .around(LslTypes.NOT_EQ).spaceIf(commonSettings.SPACE_AROUND_EQUALITY_OPERATORS)

            .aroundInside(LslTypes.GREATER, LslTypes.CONDITIONAL_EXPR).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.GREATER_EQ, LslTypes.CONDITIONAL_EXPR).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.LESS, LslTypes.CONDITIONAL_EXPR).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)
            .aroundInside(LslTypes.LESS_EQ, LslTypes.CONDITIONAL_EXPR).spaceIf(commonSettings.SPACE_AROUND_RELATIONAL_OPERATORS)

            .afterInside(LslTypes.GREATER, LslTypes.VECTOR_OR_QUATERNION_EXPR).spaceIf(false)
            .beforeInside(LslTypes.LESS, LslTypes.VECTOR_OR_QUATERNION_EXPR).spaceIf(false)

            .around(LslTypes.BITWISE_AND).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)
            .around(LslTypes.BITWISE_OR).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)
            .around(LslTypes.BITWISE_XOR).spaceIf(commonSettings.SPACE_AROUND_BITWISE_OPERATORS)

            .aroundInside(LslTypes.PLUS, LslTypes.ADD_EXPR).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)
            .aroundInside(LslTypes.MINUS, LslTypes.ADD_EXPR).spaceIf(commonSettings.SPACE_AROUND_ADDITIVE_OPERATORS)

            .around(LslTypes.MULTIPLE).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
            .around(LslTypes.DIVIDE).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)
            .around(LslTypes.MODULUS).spaceIf(commonSettings.SPACE_AROUND_MULTIPLICATIVE_OPERATORS)

            .around(LslTypes.SHIFT_LEFT).spaceIf(commonSettings.SPACE_AROUND_SHIFT_OPERATORS)
            .around(LslTypes.SHIFT_RIGHT).spaceIf(commonSettings.SPACE_AROUND_SHIFT_OPERATORS)

            .beforeInside(LslTypes.EXPRESSION, LslTypes.UNARY_EXPR).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)
            .afterInside(LslTypes.EXPRESSION, LslTypes.UNARY_POSTFIX_EXPR).spaceIf(commonSettings.SPACE_AROUND_UNARY_OPERATOR)

            .after(LslTypes.COMMA).spaceIf(commonSettings.SPACE_AFTER_COMMA)
            .before(LslTypes.COMMA).spaceIf(commonSettings.SPACE_BEFORE_COMMA)

            .around(LslTypes.DEFAULT_STATE_DECLARATION).blankLines(commonSettings.BLANK_LINES_AROUND_CLASS)
            .around(LslTypes.STATE_DECLARATION).blankLines(commonSettings.BLANK_LINES_AROUND_CLASS)

            .afterInside(LslTypes.BRACES_LEFT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .afterInside(LslTypes.BRACES_LEFT, LslTypes.STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACES_RIGHT, LslTypes.DEFAULT_STATE_DECLARATION).lineBreakInCode()
            .beforeInside(LslTypes.BRACES_RIGHT, LslTypes.STATE_DECLARATION).lineBreakInCode()

            .around(LslTypes.FUNCTION_DECLARATION).blankLines(settings.BLANK_LINES_AROUND_METHOD)
            .around(LslTypes.STATE_EVENT).blankLines(settings.BLANK_LINES_AROUND_METHOD)
            .around(LslTypes.GLOBAL_VARIABLE_DECLARATION).blankLines(settings.BLANK_LINES_AROUND_FIELD)

            .afterInside(LslTypes.SEMICOLON, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_AFTER_SEMICOLON)
            .beforeInside(LslTypes.SEMICOLON, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_SEMICOLON)

            .aroundInside(LslTypes.EXPRESSION, LslTypes.PARENTHESES_EXPR).spaceIf(commonSettings.SPACE_WITHIN_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.CALL_EXPR).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.CALL_EXPR).spaceIf(commonSettings.SPACE_WITHIN_METHOD_CALL_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.FUNCTION_DECLARATION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.FUNCTION_DECLARATION).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATE_EVENT).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.STATE_EVENT).spaceIf(commonSettings.SPACE_WITHIN_METHOD_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.IF_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.IF_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_IF_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.WHILE_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.WHILE_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.DO_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.DO_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_WHILE_PARENTHESES)

            .afterInside(LslTypes.PARENTHESES_LEFT, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_RIGHT, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_WITHIN_FOR_PARENTHESES)

            .aroundInside(LslTypes.TYPE_NAME, LslTypes.CONVERSION_EXPR).spaceIf(commonSettings.SPACE_WITHIN_CAST_PARENTHESES)

            .afterInside(LslTypes.BRACKETS_LEFT, LslTypes.LIST_EXPR).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)
            .beforeInside(LslTypes.BRACKETS_RIGHT, LslTypes.LIST_EXPR).spaceIf(commonSettings.SPACE_WITHIN_BRACKETS)

            .before(LslTypes.BRACES_LEFT).lineBreakOrForceSpace(commonSettings.BRACE_STYLE != CommonCodeStyleSettings.END_OF_LINE, commonSettings.BRACE_STYLE == CommonCodeStyleSettings.END_OF_LINE)
            .after(LslTypes.BRACES_LEFT).lineBreakInCode()
            .before(LslTypes.BRACES_RIGHT).lineBreakInCode()
//            .afterInside(LslTypes.BRACES_LEFT, LslTypes.BLOCK).lineBreakOrForceSpace(true, commonSettings.SPACE_WITHIN_BRACES)
//            .beforeInside(LslTypes.BRACES_RIGHT, LslTypes.BLOCK).lineBreakOrForceSpace(commonSettings.BRACE_STYLE != CommonCodeStyleSettings.END_OF_LINE, commonSettings.SPACE_WITHIN_BRACES)

            .afterInside(LslTypes.PARENTHESES_RIGHT, LslTypes.CONVERSION_EXPR).spaceIf(commonSettings.SPACE_AFTER_TYPE_CAST)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.CALL_EXPR).spaceIf(commonSettings.SPACE_BEFORE_METHOD_CALL_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.FUNCTION_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.STATE_EVENT).spaceIf(commonSettings.SPACE_BEFORE_METHOD_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.IF_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_IF_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.WHILE_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_WHILE_PARENTHESES)
            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.DO_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_WHILE_PARENTHESES)

            .beforeInside(LslTypes.PARENTHESES_LEFT, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_FOR_PARENTHESES)

            .beforeInside(LslTypes.BRACES_LEFT, LslTypes.DEFAULT_STATE_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_CLASS_LBRACE)
            .beforeInside(LslTypes.BRACES_LEFT, LslTypes.STATE_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_CLASS_LBRACE)

            .beforeInside(LslTypes.BLOCK, LslTypes.FUNCTION_DECLARATION).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)
            .beforeInside(LslTypes.BLOCK, LslTypes.STATE_EVENT).spaceIf(commonSettings.SPACE_BEFORE_METHOD_LBRACE)

            .beforeInside(LslTypes.STATEMENT, LslTypes.IF_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_IF_LBRACE)

            .beforeInside(LslTypes.STATEMENT, LslTypes.ELSE_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_ELSE_LBRACE)

            .beforeInside(LslTypes.STATEMENT, LslTypes.WHILE_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_WHILE_LBRACE)

            .beforeInside(LslTypes.STATEMENT, LslTypes.FOR_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_FOR_LBRACE)

            .beforeInside(LslTypes.STATEMENT, LslTypes.DO_STATEMENT).spaceIf(commonSettings.SPACE_BEFORE_DO_LBRACE)

            .beforeInside(LslTypes.ELSE_STATEMENT, LslTypes.IF_STATEMENT).lineBreakOrForceSpace(commonSettings.ELSE_ON_NEW_LINE, commonSettings.SPACE_BEFORE_ELSE_KEYWORD)

            .beforeInside(LslTypes.WHILE, LslTypes.DO_STATEMENT).lineBreakOrForceSpace(commonSettings.WHILE_ON_NEW_LINE, commonSettings.SPACE_BEFORE_WHILE_KEYWORD)

            .between(LslTypes.STATEMENT, LslParserDefinition.LINE_COMMENT).spaceIf(true)

            .around(LslTypes.STATEMENT).lineBreakInCode()
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