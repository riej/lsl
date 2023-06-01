package io.github.riej.lsl.parser

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiBuilder
import com.intellij.lang.PsiParser
import com.intellij.psi.tree.IElementType
import org.antlr.v4.runtime.CodePointBuffer
import org.antlr.v4.runtime.CodePointCharStream
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.ErrorNode
import org.antlr.v4.runtime.tree.ParseTreeWalker
import org.antlr.v4.runtime.tree.TerminalNode
import java.nio.IntBuffer
import java.util.*

class LslParserAdapter : PsiParser {
    override fun parse(root: IElementType, builder: PsiBuilder): ASTNode {
        // Offsets in this class (startOffset, endOffset, getTokenStart, getTokenEnd) are in chars, not code points.
        // That's why I wrote such complicated code here.
        val charStream = CodePointCharStream.fromBuffer(
            CodePointBuffer.withInts(
                IntBuffer.wrap(
                    builder.originalText.toString().chars().toArray()
                )
            )
        )
        val lexer = LslLexerWrapper(charStream)
        val tokenStream = CommonTokenStream(lexer)

        val rootMark = builder.mark()

        val parser = LSLParser(tokenStream)
        val parserTreeWaker = ParseTreeWalker()

        parserTreeWaker.walk(LslTreeVisitor(builder), parser.file())

        while (!builder.eof()) builder.advanceLexer()

        rootMark.done(root)

        return builder.treeBuilt
    }

    class LslTreeVisitor(private val builder: PsiBuilder) : LSLListener {
        private val stack = Stack<PsiBuilder.Marker>()

        override fun visitTerminal(node: TerminalNode) {
            builder.advanceLexer()
        }

        override fun visitErrorNode(node: ErrorNode) {
            if (node.symbol.type == LSLLexer.UnclosedStringConstant) {
                builder.error("Parse error: unclosed string literal")
                return
            }

            builder.error("Parse error: ${node.text.trimStart('<').trimEnd('>')}")
        }

        override fun enterEveryRule(ctx: ParserRuleContext) {
        }

        override fun exitEveryRule(ctx: ParserRuleContext) {
        }

        override fun enterFile(ctx: LSLParser.FileContext?) {
        }

        override fun exitFile(ctx: LSLParser.FileContext?) {
        }

        override fun enterGlobalVariable(ctx: LSLParser.GlobalVariableContext?) {
            stack.push(builder.mark())
        }

        override fun exitGlobalVariable(ctx: LSLParser.GlobalVariableContext?) {
            stack.pop().done(LslTypes.GLOBAL_VARIABLE)
        }

        override fun enterFunction(ctx: LSLParser.FunctionContext?) {
            stack.push(builder.mark())
        }

        override fun exitFunction(ctx: LSLParser.FunctionContext?) {
            stack.pop().done(LslTypes.FUNCTION)
        }

        override fun enterArgument(ctx: LSLParser.ArgumentContext?) {
            stack.push(builder.mark())
        }

        override fun exitArgument(ctx: LSLParser.ArgumentContext?) {
            stack.pop().done(LslTypes.ARGUMENT)
        }

        override fun enterArguments(ctx: LSLParser.ArgumentsContext?) {
            stack.push(builder.mark())
        }

        override fun exitArguments(ctx: LSLParser.ArgumentsContext?) {
            stack.pop().done(LslTypes.ARGUMENTS)
        }

        override fun enterDefaultStateDeclaration(ctx: LSLParser.DefaultStateDeclarationContext?) {
            stack.push(builder.mark())
        }

        override fun exitDefaultStateDeclaration(ctx: LSLParser.DefaultStateDeclarationContext?) {
            stack.pop().done(LslTypes.DEFAULT_STATE_DECLARATION)
        }

        override fun enterStateDeclaration(ctx: LSLParser.StateDeclarationContext?) {
            stack.push(builder.mark())
        }

        override fun exitStateDeclaration(ctx: LSLParser.StateDeclarationContext?) {
            stack.pop().done(LslTypes.STATE_DECLARATION)
        }

        override fun enterEvent(ctx: LSLParser.EventContext?) {
            stack.push(builder.mark())
        }

        override fun exitEvent(ctx: LSLParser.EventContext?) {
            stack.pop().done(LslTypes.EVENT)
        }

        override fun enterEvents(ctx: LSLParser.EventsContext?) {
            stack.push(builder.mark())
        }

        override fun exitEvents(ctx: LSLParser.EventsContext?) {
            stack.pop().done(LslTypes.EVENTS)
        }

        override fun enterStatementEmpty(ctx: LSLParser.StatementEmptyContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementEmpty(ctx: LSLParser.StatementEmptyContext?) {
            stack.pop().done(LslTypes.STATEMENT_EMPTY)
        }

        override fun enterStatementBlock2(ctx: LSLParser.StatementBlock2Context?) {
        }

        override fun exitStatementBlock2(ctx: LSLParser.StatementBlock2Context?) {
        }

        override fun enterStatementBlock(ctx: LSLParser.StatementBlockContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementBlock(ctx: LSLParser.StatementBlockContext?) {
            stack.pop().done(LslTypes.STATEMENT_BLOCK)
        }

        override fun enterStatementVariable(ctx: LSLParser.StatementVariableContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementVariable(ctx: LSLParser.StatementVariableContext?) {
            stack.pop().done(LslTypes.STATEMENT_VARIABLE)
        }

        override fun enterStatementLabel(ctx: LSLParser.StatementLabelContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementLabel(ctx: LSLParser.StatementLabelContext?) {
            stack.pop().done(LslTypes.STATEMENT_LABEL)
        }

        override fun enterStatementJump(ctx: LSLParser.StatementJumpContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementJump(ctx: LSLParser.StatementJumpContext?) {
            stack.pop().done(LslTypes.STATEMENT_JUMP)
        }

        override fun enterStatementState(ctx: LSLParser.StatementStateContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementState(ctx: LSLParser.StatementStateContext?) {
            stack.pop().done(LslTypes.STATEMENT_STATE)
        }

        override fun enterStatementReturn(ctx: LSLParser.StatementReturnContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementReturn(ctx: LSLParser.StatementReturnContext?) {
            stack.pop().done(LslTypes.STATEMENT_RETURN)
        }

        override fun enterStatementIf(ctx: LSLParser.StatementIfContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementIf(ctx: LSLParser.StatementIfContext?) {
            stack.pop().done(LslTypes.STATEMENT_IF)
        }

        override fun enterStatementWhile(ctx: LSLParser.StatementWhileContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementWhile(ctx: LSLParser.StatementWhileContext?) {
            stack.pop().done(LslTypes.STATEMENT_WHILE)
        }

        override fun enterStatementDo(ctx: LSLParser.StatementDoContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementDo(ctx: LSLParser.StatementDoContext?) {
            stack.pop().done(LslTypes.STATEMENT_DO)
        }

        override fun enterStatementFor(ctx: LSLParser.StatementForContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementFor(ctx: LSLParser.StatementForContext?) {
            stack.pop().done(LslTypes.STATEMENT_FOR)
        }

        override fun enterStatementExpression(ctx: LSLParser.StatementExpressionContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementExpression(ctx: LSLParser.StatementExpressionContext?) {
            stack.pop().done(LslTypes.STATEMENT_EXPRESSION)
        }

        override fun enterStatementElse(ctx: LSLParser.StatementElseContext?) {
            stack.push(builder.mark())
        }

        override fun exitStatementElse(ctx: LSLParser.StatementElseContext?) {
            stack.pop().done(LslTypes.STATEMENT_ELSE)
        }

        override fun enterExpressionTypeCast(ctx: LSLParser.ExpressionTypeCastContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionTypeCast(ctx: LSLParser.ExpressionTypeCastContext?) {
            stack.pop().done(LslTypes.EXPRESSION_TYPE_CAST)
        }

        override fun enterExpressionBinary(ctx: LSLParser.ExpressionBinaryContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionBinary(ctx: LSLParser.ExpressionBinaryContext?) {
            stack.pop().done(LslTypes.EXPRESSION_BINARY)
        }

        override fun enterExpressionUnaryPrefix(ctx: LSLParser.ExpressionUnaryPrefixContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionUnaryPrefix(ctx: LSLParser.ExpressionUnaryPrefixContext?) {
            stack.pop().done(LslTypes.EXPRESSION_UNARY_PREFIX)
        }

        override fun enterExpressionVector(ctx: LSLParser.ExpressionVectorContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionVector(ctx: LSLParser.ExpressionVectorContext?) {
            stack.pop().done(LslTypes.EXPRESSION_VECTOR)
        }

        override fun enterExpressionPrint(ctx: LSLParser.ExpressionPrintContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionPrint(ctx: LSLParser.ExpressionPrintContext?) {
            stack.pop().done(LslTypes.EXPRESSION_PRINT)
        }

        override fun enterExpressionLValue(ctx: LSLParser.ExpressionLValueContext?) {
        }

        override fun exitExpressionLValue(ctx: LSLParser.ExpressionLValueContext?) {
        }

        override fun enterExpressionConstant(ctx: LSLParser.ExpressionConstantContext?) {
        }

        override fun exitExpressionConstant(ctx: LSLParser.ExpressionConstantContext?) {
        }

        override fun enterExpressionQuaternion(ctx: LSLParser.ExpressionQuaternionContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionQuaternion(ctx: LSLParser.ExpressionQuaternionContext?) {
            stack.pop().done(LslTypes.EXPRESSION_QUATERNION)
        }

        override fun enterExpressionUnaryPrefixLValue(ctx: LSLParser.ExpressionUnaryPrefixLValueContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionUnaryPrefixLValue(ctx: LSLParser.ExpressionUnaryPrefixLValueContext?) {
            stack.pop().done(LslTypes.EXPRESSION_UNARY_PREFIX)
        }

        override fun enterExpressionUnaryPostfix(ctx: LSLParser.ExpressionUnaryPostfixContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionUnaryPostfix(ctx: LSLParser.ExpressionUnaryPostfixContext?) {
            stack.pop().done(LslTypes.EXPRESSION_UNARY_POSTFIX)
        }

        override fun enterExpressionParentheses(ctx: LSLParser.ExpressionParenthesesContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionParentheses(ctx: LSLParser.ExpressionParenthesesContext?) {
            stack.pop().done(LslTypes.EXPRESSION_PARENTHESES)
        }

        override fun enterExpressionAssignment(ctx: LSLParser.ExpressionAssignmentContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionAssignment(ctx: LSLParser.ExpressionAssignmentContext?) {
            stack.pop().done(LslTypes.EXPRESSION_ASSIGNMENT)
        }

        override fun enterExpressionFunctionCall(ctx: LSLParser.ExpressionFunctionCallContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionFunctionCall(ctx: LSLParser.ExpressionFunctionCallContext?) {
            stack.pop().done(LslTypes.EXPRESSION_FUNCTION_CALL)
        }

        override fun enterExpressionList(ctx: LSLParser.ExpressionListContext?) {
            stack.push(builder.mark())
        }

        override fun exitExpressionList(ctx: LSLParser.ExpressionListContext?) {
            stack.pop().done(LslTypes.EXPRESSION_LIST)
        }

        override fun enterExpressions(ctx: LSLParser.ExpressionsContext?) {
        }

        override fun exitExpressions(ctx: LSLParser.ExpressionsContext?) {
        }

        override fun enterLValue(ctx: LSLParser.LValueContext?) {
            stack.push(builder.mark())
        }

        override fun exitLValue(ctx: LSLParser.LValueContext?) {
            stack.pop().done(LslTypes.L_VALUE)
        }

        override fun enterConstant(ctx: LSLParser.ConstantContext?) {
            stack.push(builder.mark())
        }

        override fun exitConstant(ctx: LSLParser.ConstantContext?) {
            stack.pop().done(LslTypes.CONSTANT)
        }
    }
}