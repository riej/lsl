package io.github.riej.lsl.parser

import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.TokenSet
import io.github.riej.lsl.psi.*
import org.antlr.v4.runtime.Token

/**
 * I need this thing to be able to use antlr4 parser.
 * It's kinda "manually generated" file, based on generated LSLLexer and LSLParser.
 * Yep, I know there's antlr4-intellij-adaptor library, but it looks outdated.
 *
 * TODO: generate this file automatically, together with LSL.g4
 */
object LslTypes {
    // Tokens list, from LSLLexer
    val DEFAULT = LslTokenType("Default")
    val STATE = LslTokenType("State")
    val JUMP = LslTokenType("Jump")
    val RETURN = LslTokenType("Return")
    val IF = LslTokenType("If")
    val ELSE = LslTokenType("Else")
    val WHILE = LslTokenType("While")
    val DO = LslTokenType("Do")
    val FOR = LslTokenType("For")
    val PRINT = LslTokenType("Print")
    val TYPE_NAME = LslTokenType("TypeName")
    val PARENTHESES_LEFT = LslTokenType("ParenthesesLeft")
    val PARENTHESES_RIGHT = LslTokenType("ParenthesesRight")
    val BRACKET_LEFT = LslTokenType("BracketLeft")
    val BRACKET_RIGHT = LslTokenType("BracketRight")
    val BRACE_LEFT = LslTokenType("BraceLeft")
    val BRACE_RIGHT = LslTokenType("BraceRight")
    val LESS = LslTokenType("Less")
    val LESS_EQUAL = LslTokenType("LessEqual")
    val GREATER = LslTokenType("Greater")
    val GREATER_EQUAL = LslTokenType("GreaterEqual")
    val EQUAL = LslTokenType("Equal")
    val NOT_EQUAL = LslTokenType("NotEqual")
    val SHIFT_LEFT = LslTokenType("ShiftLeft")
    val SHIFT_RIGHT = LslTokenType("ShiftRight")
    val PLUS = LslTokenType("Plus")
    val PLUS_PLUS = LslTokenType("PlusPlus")
    val MINUS = LslTokenType("Minus")
    val MINUS_MINUS = LslTokenType("MinusMinus")
    val MULTIPLE = LslTokenType("Multiple")
    val DIVIDE = LslTokenType("Divide")
    val MODULUS = LslTokenType("Modulus")
    val BOOLEAN_AND = LslTokenType("BooleanAnd")
    val BOOLEAN_OR = LslTokenType("BooleanOr")
    val BOOLEAN_NOT = LslTokenType("BooleanNot")
    val BITWISE_AND = LslTokenType("BitwiseAnd")
    val BITWISE_OR = LslTokenType("BitwiseOr")
    val BITWISE_XOR = LslTokenType("BitwiseXor")
    val BITWISE_NOT = LslTokenType("BitwiseNot")
    val SEMICOLON = LslTokenType("Semicolon")
    val COMMA = LslTokenType("Comma")
    val LABEL = LslTokenType("Label")
    val ASSIGN = LslTokenType("Assign")
    val PLUS_ASSIGN = LslTokenType("PlusAssign")
    val MINUS_ASSIGN = LslTokenType("MinusAssign")
    val MULTIPLE_ASSIGN = LslTokenType("MultipleAssign")
    val DIVIDE_ASSIGN = LslTokenType("DivideAssign")
    val MODULUS_ASSIGN = LslTokenType("ModulusAssign")
    val DOT = LslTokenType("Dot")
    val IDENTIFIER = LslTokenType("Identifier")
    val INTEGER_CONSTANT = LslTokenType("IntegerConstant")
    val FLOATING_CONSTANT = LslTokenType("FloatingConstant")
    val STRING_CONSTANT = LslTokenType("StringConstant")
    val WHITESPACE = LslTokenType("Whitespace")
    val NEWLINE = LslTokenType("Newline")
    val BLOCK_COMMENT = LslTokenType("BlockComment")
    val LINE_COMMENT = LslTokenType("LineComment")
    val UNCLOSED_STRING_CONSTANT = LslTokenType("UnclosedStringConstant")

    val WHITESPACES = TokenSet.create(WHITESPACE, NEWLINE)
    val COMMENTS = TokenSet.create(LINE_COMMENT, BLOCK_COMMENT)
    val STRINGS = TokenSet.create(STRING_CONSTANT, UNCLOSED_STRING_CONSTANT)
    val NUMBERS = TokenSet.create(INTEGER_CONSTANT, FLOATING_CONSTANT)
    val KEYWORDS = TokenSet.create(
        DEFAULT,
        STATE,
        JUMP,
        RETURN,
        IF,
        ELSE,
        FOR,
        WHILE,
        DO,
        PRINT
    )
    val OPERATORS = TokenSet.create(
        PARENTHESES_LEFT,
        PARENTHESES_RIGHT,
        BRACKET_LEFT,
        BRACKET_RIGHT,
        BRACE_LEFT,
        BRACE_RIGHT,
        LESS,
        LESS_EQUAL,
        GREATER,
        GREATER_EQUAL,
        EQUAL,
        NOT_EQUAL,
        SHIFT_LEFT,
        SHIFT_RIGHT,
        PLUS,
        PLUS_PLUS,
        MINUS,
        MINUS_MINUS,
        MULTIPLE,
        DIVIDE,
        MODULUS,
        BOOLEAN_AND,
        BOOLEAN_OR,
        BOOLEAN_NOT,
        BITWISE_AND,
        BITWISE_OR,
        BITWISE_XOR,
        BITWISE_NOT,
        SEMICOLON,
        COMMA,
        LABEL,
        ASSIGN,
        PLUS_ASSIGN,
        MINUS_ASSIGN,
        MULTIPLE_ASSIGN,
        DIVIDE_ASSIGN,
        MODULUS_ASSIGN,
        DOT,
    )

    val ASSIGNMENT_OPERATORS = TokenSet.create(
        ASSIGN,
        PLUS_ASSIGN,
        MINUS_ASSIGN,
        MULTIPLE_ASSIGN,
        DIVIDE_ASSIGN,
        MODULUS_ASSIGN,
    )

    val COMPARISON_OPERATORS = TokenSet.create(
        LESS,
        LESS_EQUAL,
        GREATER,
        GREATER_EQUAL,
        EQUAL,
        NOT_EQUAL,
        BOOLEAN_AND,
        BOOLEAN_OR,
    )

    val BINARY_OPERATORS = TokenSet.create(
        LESS,
        LESS_EQUAL,
        GREATER,
        GREATER_EQUAL,
        EQUAL,
        NOT_EQUAL,
        SHIFT_LEFT,
        SHIFT_RIGHT,
        PLUS,
        MINUS,
        MULTIPLE,
        DIVIDE,
        MODULUS,
        BOOLEAN_AND,
        BOOLEAN_OR,
        BITWISE_AND,
        BITWISE_OR,
        BITWISE_XOR,
        ASSIGN,
        PLUS_ASSIGN,
        MINUS_ASSIGN,
        MULTIPLE_ASSIGN,
        DIVIDE_ASSIGN,
        MODULUS_ASSIGN,
    )

    fun getLslTokenTypeForAntlrToken(token: Token): IElementType? =
        when (token.type) {
            1 -> DEFAULT
            2 -> STATE
            3 -> JUMP
            4 -> RETURN
            5 -> IF
            6 -> ELSE
            7 -> WHILE
            8 -> DO
            9 -> FOR
            10 -> PRINT
            11 -> TYPE_NAME
            12 -> PARENTHESES_LEFT
            13 -> PARENTHESES_RIGHT
            14 -> BRACKET_LEFT
            15 -> BRACKET_RIGHT
            16 -> BRACE_LEFT
            17 -> BRACE_RIGHT
            18 -> LESS
            19 -> LESS_EQUAL
            20 -> GREATER
            21 -> GREATER_EQUAL
            22 -> EQUAL
            23 -> NOT_EQUAL
            24 -> SHIFT_LEFT
            25 -> SHIFT_RIGHT
            26 -> PLUS
            27 -> PLUS_PLUS
            28 -> MINUS
            29 -> MINUS_MINUS
            30 -> MULTIPLE
            31 -> DIVIDE
            32 -> MODULUS
            33 -> BOOLEAN_AND
            34 -> BOOLEAN_OR
            35 -> BOOLEAN_NOT
            36 -> BITWISE_AND
            37 -> BITWISE_OR
            38 -> BITWISE_XOR
            39 -> BITWISE_NOT
            40 -> SEMICOLON
            41 -> COMMA
            42 -> LABEL
            43 -> ASSIGN
            44 -> PLUS_ASSIGN
            45 -> MINUS_ASSIGN
            46 -> MULTIPLE_ASSIGN
            47 -> DIVIDE_ASSIGN
            48 -> MODULUS_ASSIGN
            49 -> DOT
            50 -> IDENTIFIER
            51 -> INTEGER_CONSTANT
            52 -> FLOATING_CONSTANT
            53 -> STRING_CONSTANT
            54 -> WHITESPACE
            55 -> NEWLINE
            56 -> BLOCK_COMMENT
            57 -> LINE_COMMENT
            58 -> UNCLOSED_STRING_CONSTANT
            else -> null
        }

    // List of available PSI elements
    val GLOBAL_VARIABLE = LslTokenType("globalVariable")
    val FUNCTION = LslTokenType("function")
    val DEFAULT_STATE_DECLARATION = LslTokenType("defaultStateDeclaration")
    val STATE_DECLARATION = LslTokenType("stateDeclaration")

    val EVENT = LslTokenType("event")
    val EVENTS = LslTokenType("events")

    val ARGUMENT = LslTokenType("argument")
    val ARGUMENTS = LslTokenType("arguments")

    val STATEMENT_EMPTY = LslTokenType("statementEmpty")
    val STATEMENT_BLOCK = LslTokenType("statementBlock")
    val STATEMENT_VARIABLE = LslTokenType("statementVariable")
    val STATEMENT_LABEL = LslTokenType("statementLabel")
    val STATEMENT_JUMP = LslTokenType("statementJump")
    val STATEMENT_STATE = LslTokenType("statementState")
    val STATEMENT_RETURN = LslTokenType("statementReturn")
    val STATEMENT_IF = LslTokenType("statementIf")
    val STATEMENT_WHILE = LslTokenType("statementWhile")
    val STATEMENT_DO = LslTokenType("statementDo")
    val STATEMENT_FOR = LslTokenType("statementFor")
    val STATEMENT_EXPRESSION = LslTokenType("statementExpression")

    val STATEMENT_ELSE = LslTokenType("statementElse") // not a real statement

    val EXPRESSION_ASSIGNMENT = LslTokenType("expressionAssignment")
    val EXPRESSION_BINARY = LslTokenType("expressionBinary")
    val EXPRESSION_UNARY_PREFIX = LslTokenType("expressionUnaryPrefix")
    val EXPRESSION_UNARY_POSTFIX = LslTokenType("expressionUnaryPostfix")
    val EXPRESSION_FUNCTION_CALL = LslTokenType("expressionFunctionCall")
    val EXPRESSION_PRINT = LslTokenType("expressionPrint")
    val EXPRESSION_TYPE_CAST = LslTokenType("expressionTypeCast")
    val EXPRESSION_LIST = LslTokenType("expressionList")
    val EXPRESSION_VECTOR = LslTokenType("expressionVector")
    val EXPRESSION_QUATERNION = LslTokenType("expressionQuaternion")
    val EXPRESSION_PARENTHESES = LslTokenType("expressionParentheses")
    val L_VALUE = LslTokenType("lValue")
    val CONSTANT = LslTokenType("constant")

    val STATEMENTS = TokenSet.create(
        STATEMENT_EMPTY,
        STATEMENT_BLOCK,
        STATEMENT_VARIABLE,
        STATEMENT_LABEL,
        STATEMENT_JUMP,
        STATEMENT_STATE,
        STATEMENT_RETURN,
        STATEMENT_IF,
        STATEMENT_WHILE,
        STATEMENT_DO,
        STATEMENT_FOR,
        STATEMENT_EXPRESSION,
    )

    val GLOBAL_DECLARATIONS = TokenSet.create(
        GLOBAL_VARIABLE,
        FUNCTION,
        DEFAULT_STATE_DECLARATION,
        STATE_DECLARATION,
    )

    val EXPRESSIONS = TokenSet.create(
        EXPRESSION_ASSIGNMENT,
        EXPRESSION_BINARY,
        EXPRESSION_UNARY_PREFIX,
        EXPRESSION_UNARY_POSTFIX,
        EXPRESSION_FUNCTION_CALL,
        EXPRESSION_PRINT,
        EXPRESSION_TYPE_CAST,
        EXPRESSION_LIST,
        EXPRESSION_VECTOR,
        EXPRESSION_QUATERNION,
        EXPRESSION_PARENTHESES,
        L_VALUE,
        CONSTANT,
    )

    object Factory {
        @JvmStatic
        fun createElement(node: ASTNode?): PsiElement =
            when (node?.elementType) {
                GLOBAL_VARIABLE -> LslGlobalVariable(node)
                FUNCTION -> LslFunction(node)
                DEFAULT_STATE_DECLARATION -> LslStateDefault(node)
                STATE_DECLARATION -> LslStateCustom(node)

                EVENT -> LslEvent(node)
                EVENTS -> LslEvents(node)

                ARGUMENT -> LslArgument(node)
                ARGUMENTS -> LslArguments(node)

                STATEMENT_EMPTY -> LslStatementEmpty(node)
                STATEMENT_BLOCK -> LslStatementBlock(node)
                STATEMENT_VARIABLE -> LslStatementVariable(node)
                STATEMENT_LABEL -> LslStatementLabel(node)
                STATEMENT_JUMP -> LslStatementJump(node)
                STATEMENT_STATE -> LslStatementState(node)
                STATEMENT_RETURN -> LslStatementReturn(node)
                STATEMENT_IF -> LslStatementIf(node)
                STATEMENT_WHILE -> LslStatementWhile(node)
                STATEMENT_DO -> LslStatementDo(node)
                STATEMENT_FOR -> LslStatementFor(node)
                STATEMENT_EXPRESSION -> LslStatementExpression(node)

                STATEMENT_ELSE -> LslStatementElse(node)

                EXPRESSION_ASSIGNMENT -> LslExpressionAssignment(node)
                EXPRESSION_BINARY -> LslExpressionBinary(node)
                EXPRESSION_UNARY_PREFIX -> LslExpressionUnaryPrefix(node)
                EXPRESSION_UNARY_POSTFIX -> LslExpressionUnaryPostfix(node)
                EXPRESSION_FUNCTION_CALL -> LslExpressionFunctionCall(node)
                EXPRESSION_PRINT -> LslExpressionPrint(node)
                EXPRESSION_TYPE_CAST -> LslExpressionTypeCast(node)
                EXPRESSION_LIST -> LslExpressionList(node)
                EXPRESSION_VECTOR -> LslExpressionVector(node)
                EXPRESSION_QUATERNION -> LslExpressionQuaternion(node)
                EXPRESSION_PARENTHESES -> LslExpressionParentheses(node)
                L_VALUE -> LslLValue(node)
                CONSTANT -> LslConstant(node)

                else -> throw AssertionError("Unknown element type: ${node?.elementType}")
            }
    }
}