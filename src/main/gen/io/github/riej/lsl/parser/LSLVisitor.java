// Generated from java-escape by ANTLR 4.11.1
package io.github.riej.lsl.parser;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LSLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LSLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LSLParser#file}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFile(LSLParser.FileContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#globalVariable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGlobalVariable(LSLParser.GlobalVariableContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(LSLParser.FunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(LSLParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(LSLParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#defaultStateDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefaultStateDeclaration(LSLParser.DefaultStateDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#stateDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStateDeclaration(LSLParser.StateDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#event}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvent(LSLParser.EventContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#events}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEvents(LSLParser.EventsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementEmpty}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementEmpty(LSLParser.StatementEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementBlock2}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock2(LSLParser.StatementBlock2Context ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementVariable}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementVariable(LSLParser.StatementVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementLabel}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementLabel(LSLParser.StatementLabelContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementJump}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementJump(LSLParser.StatementJumpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementState}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementState(LSLParser.StatementStateContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementReturn(LSLParser.StatementReturnContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementIf}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementIf(LSLParser.StatementIfContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementWhile(LSLParser.StatementWhileContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementDo}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementDo(LSLParser.StatementDoContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementFor(LSLParser.StatementForContext ctx);
	/**
	 * Visit a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementExpression(LSLParser.StatementExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#statementElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementElse(LSLParser.StatementElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#statementBlock}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementBlock(LSLParser.StatementBlockContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionTypeCast}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionTypeCast(LSLParser.ExpressionTypeCastContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionBinary(LSLParser.ExpressionBinaryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionUnaryPrefix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionUnaryPrefix(LSLParser.ExpressionUnaryPrefixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionVector}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionVector(LSLParser.ExpressionVectorContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionPrint}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionPrint(LSLParser.ExpressionPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionLValue}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionLValue(LSLParser.ExpressionLValueContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionConstant}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionConstant(LSLParser.ExpressionConstantContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionQuaternion}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionQuaternion(LSLParser.ExpressionQuaternionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionUnaryPostfix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionUnaryPostfix(LSLParser.ExpressionUnaryPostfixContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionParentheses}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionParentheses(LSLParser.ExpressionParenthesesContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionAssignment(LSLParser.ExpressionAssignmentContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionFunctionCall(LSLParser.ExpressionFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ExpressionList}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(LSLParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#expressions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressions(LSLParser.ExpressionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#lValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLValue(LSLParser.LValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link LSLParser#constant}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConstant(LSLParser.ConstantContext ctx);
}