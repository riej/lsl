// Generated from java-escape by ANTLR 4.11.1
package io.github.riej.lsl.parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LSLParser}.
 */
public interface LSLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LSLParser#file}.
	 * @param ctx the parse tree
	 */
	void enterFile(LSLParser.FileContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#file}.
	 * @param ctx the parse tree
	 */
	void exitFile(LSLParser.FileContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#globalVariable}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariable(LSLParser.GlobalVariableContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#globalVariable}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariable(LSLParser.GlobalVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableValueList}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableValueList(LSLParser.GlobalVariableValueListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableValueList}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableValueList(LSLParser.GlobalVariableValueListContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableValueVector}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableValueVector(LSLParser.GlobalVariableValueVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableValueVector}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableValueVector(LSLParser.GlobalVariableValueVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableValueQuaternion}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableValueQuaternion(LSLParser.GlobalVariableValueQuaternionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableValueQuaternion}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableValueQuaternion(LSLParser.GlobalVariableValueQuaternionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableLValue}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableLValue(LSLParser.GlobalVariableLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableLValue}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableLValue(LSLParser.GlobalVariableLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableValueConstantNegative}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableValueConstantNegative(LSLParser.GlobalVariableValueConstantNegativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableValueConstantNegative}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableValueConstantNegative(LSLParser.GlobalVariableValueConstantNegativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code GlobalVariableValueConstant}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void enterGlobalVariableValueConstant(LSLParser.GlobalVariableValueConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code GlobalVariableValueConstant}
	 * labeled alternative in {@link LSLParser#globalVariableValue}.
	 * @param ctx the parse tree
	 */
	void exitGlobalVariableValueConstant(LSLParser.GlobalVariableValueConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(LSLParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(LSLParser.FunctionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(LSLParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(LSLParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(LSLParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(LSLParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#defaultStateDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterDefaultStateDeclaration(LSLParser.DefaultStateDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#defaultStateDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitDefaultStateDeclaration(LSLParser.DefaultStateDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#stateDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterStateDeclaration(LSLParser.StateDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#stateDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitStateDeclaration(LSLParser.StateDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#event}.
	 * @param ctx the parse tree
	 */
	void enterEvent(LSLParser.EventContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#event}.
	 * @param ctx the parse tree
	 */
	void exitEvent(LSLParser.EventContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#events}.
	 * @param ctx the parse tree
	 */
	void enterEvents(LSLParser.EventsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#events}.
	 * @param ctx the parse tree
	 */
	void exitEvents(LSLParser.EventsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementEmpty}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementEmpty(LSLParser.StatementEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementEmpty}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementEmpty(LSLParser.StatementEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementBlock2}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock2(LSLParser.StatementBlock2Context ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementBlock2}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock2(LSLParser.StatementBlock2Context ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementVariable}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementVariable(LSLParser.StatementVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementVariable}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementVariable(LSLParser.StatementVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementLabel}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementLabel(LSLParser.StatementLabelContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementLabel}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementLabel(LSLParser.StatementLabelContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementJump}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementJump(LSLParser.StatementJumpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementJump}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementJump(LSLParser.StatementJumpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementState}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementState(LSLParser.StatementStateContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementState}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementState(LSLParser.StatementStateContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementReturn(LSLParser.StatementReturnContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementReturn}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementReturn(LSLParser.StatementReturnContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementIf}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementIf(LSLParser.StatementIfContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementIf}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementIf(LSLParser.StatementIfContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementWhile(LSLParser.StatementWhileContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementWhile}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementWhile(LSLParser.StatementWhileContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementDo}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementDo(LSLParser.StatementDoContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementDo}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementDo(LSLParser.StatementDoContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementFor(LSLParser.StatementForContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementFor}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementFor(LSLParser.StatementForContext ctx);
	/**
	 * Enter a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatementExpression(LSLParser.StatementExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code StatementExpression}
	 * labeled alternative in {@link LSLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatementExpression(LSLParser.StatementExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#statementElse}.
	 * @param ctx the parse tree
	 */
	void enterStatementElse(LSLParser.StatementElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#statementElse}.
	 * @param ctx the parse tree
	 */
	void exitStatementElse(LSLParser.StatementElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void enterStatementBlock(LSLParser.StatementBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#statementBlock}.
	 * @param ctx the parse tree
	 */
	void exitStatementBlock(LSLParser.StatementBlockContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionTypeCast}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionTypeCast(LSLParser.ExpressionTypeCastContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionTypeCast}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionTypeCast(LSLParser.ExpressionTypeCastContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionBinary(LSLParser.ExpressionBinaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionBinary}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionBinary(LSLParser.ExpressionBinaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionUnaryPrefix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionUnaryPrefix(LSLParser.ExpressionUnaryPrefixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionUnaryPrefix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionUnaryPrefix(LSLParser.ExpressionUnaryPrefixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionVector}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionVector(LSLParser.ExpressionVectorContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionVector}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionVector(LSLParser.ExpressionVectorContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionPrint}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionPrint(LSLParser.ExpressionPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionPrint}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionPrint(LSLParser.ExpressionPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionLValue}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionLValue(LSLParser.ExpressionLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionLValue}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionLValue(LSLParser.ExpressionLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionConstant}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionConstant(LSLParser.ExpressionConstantContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionConstant}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionConstant(LSLParser.ExpressionConstantContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionQuaternion}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionQuaternion(LSLParser.ExpressionQuaternionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionQuaternion}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionQuaternion(LSLParser.ExpressionQuaternionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionUnaryPrefixLValue}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionUnaryPrefixLValue(LSLParser.ExpressionUnaryPrefixLValueContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionUnaryPrefixLValue}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionUnaryPrefixLValue(LSLParser.ExpressionUnaryPrefixLValueContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionUnaryPostfix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionUnaryPostfix(LSLParser.ExpressionUnaryPostfixContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionUnaryPostfix}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionUnaryPostfix(LSLParser.ExpressionUnaryPostfixContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionParentheses}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionParentheses(LSLParser.ExpressionParenthesesContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionParentheses}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionParentheses(LSLParser.ExpressionParenthesesContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionAssignment(LSLParser.ExpressionAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionAssignment}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionAssignment(LSLParser.ExpressionAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionFunctionCall(LSLParser.ExpressionFunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionFunctionCall}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionFunctionCall(LSLParser.ExpressionFunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ExpressionList}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(LSLParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ExpressionList}
	 * labeled alternative in {@link LSLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(LSLParser.ExpressionListContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#expressions}.
	 * @param ctx the parse tree
	 */
	void enterExpressions(LSLParser.ExpressionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#expressions}.
	 * @param ctx the parse tree
	 */
	void exitExpressions(LSLParser.ExpressionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#lValue}.
	 * @param ctx the parse tree
	 */
	void enterLValue(LSLParser.LValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#lValue}.
	 * @param ctx the parse tree
	 */
	void exitLValue(LSLParser.LValueContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#constant}.
	 * @param ctx the parse tree
	 */
	void enterConstant(LSLParser.ConstantContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#constant}.
	 * @param ctx the parse tree
	 */
	void exitConstant(LSLParser.ConstantContext ctx);
	/**
	 * Enter a parse tree produced by {@link LSLParser#constantNumeric}.
	 * @param ctx the parse tree
	 */
	void enterConstantNumeric(LSLParser.ConstantNumericContext ctx);
	/**
	 * Exit a parse tree produced by {@link LSLParser#constantNumeric}.
	 * @param ctx the parse tree
	 */
	void exitConstantNumeric(LSLParser.ConstantNumericContext ctx);
}