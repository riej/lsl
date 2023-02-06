// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.riej.lsl.psi.impl.*;

public interface LslTypes {

  IElementType ADD_EXPR = new LslElementType("ADD_EXPR");
  IElementType AND_EXPR = new LslElementType("AND_EXPR");
  IElementType ARGUMENT = new LslElementType("ARGUMENT");
  IElementType ASSIGN_EXPR = new LslElementType("ASSIGN_EXPR");
  IElementType BLOCK = new LslElementType("BLOCK");
  IElementType CALL_EXPR = new LslElementType("CALL_EXPR");
  IElementType CONDITIONAL_EXPR = new LslElementType("CONDITIONAL_EXPR");
  IElementType CONSTANT_VALUE = new LslElementType("CONSTANT_VALUE");
  IElementType CONVERSION_EXPR = new LslElementType("CONVERSION_EXPR");
  IElementType DEFAULT_STATE_DECLARATION = new LslElementType("DEFAULT_STATE_DECLARATION");
  IElementType DO_STATEMENT = new LslElementType("DO_STATEMENT");
  IElementType ELSE_STATEMENT = new LslElementType("ELSE_STATEMENT");
  IElementType EMPTY_STATEMENT = new LslElementType("EMPTY_STATEMENT");
  IElementType EXPRESSION = new LslElementType("EXPRESSION");
  IElementType EXPRESSION_STATEMENT = new LslElementType("EXPRESSION_STATEMENT");
  IElementType FOR_STATEMENT = new LslElementType("FOR_STATEMENT");
  IElementType FUNCTION_DECLARATION = new LslElementType("FUNCTION_DECLARATION");
  IElementType GLOBAL_VARIABLE_DECLARATION = new LslElementType("GLOBAL_VARIABLE_DECLARATION");
  IElementType IDENTIFIER = new LslElementType("IDENTIFIER");
  IElementType IF_STATEMENT = new LslElementType("IF_STATEMENT");
  IElementType JUMP_STATEMENT = new LslElementType("JUMP_STATEMENT");
  IElementType LABEL_STATEMENT = new LslElementType("LABEL_STATEMENT");
  IElementType LIST_EXPR = new LslElementType("LIST_EXPR");
  IElementType LITERAL = new LslElementType("LITERAL");
  IElementType LOCAL_VARIABLE_DECLARATION = new LslElementType("LOCAL_VARIABLE_DECLARATION");
  IElementType L_VALUE = new LslElementType("L_VALUE");
  IElementType MUL_EXPR = new LslElementType("MUL_EXPR");
  IElementType OR_EXPR = new LslElementType("OR_EXPR");
  IElementType PARENTHESES_EXPR = new LslElementType("PARENTHESES_EXPR");
  IElementType PRINT_EXPR = new LslElementType("PRINT_EXPR");
  IElementType RETURN_STATEMENT = new LslElementType("RETURN_STATEMENT");
  IElementType STATEMENT = new LslElementType("STATEMENT");
  IElementType STATE_DECLARATION = new LslElementType("STATE_DECLARATION");
  IElementType STATE_EVENT = new LslElementType("STATE_EVENT");
  IElementType STATE_STATEMENT = new LslElementType("STATE_STATEMENT");
  IElementType TYPE_NAME = new LslElementType("TYPE_NAME");
  IElementType UNARY_EXPR = new LslElementType("UNARY_EXPR");
  IElementType UNARY_POSTFIX_EXPR = new LslElementType("UNARY_POSTFIX_EXPR");
  IElementType VECTOR_OR_QUATERNION_EXPR = new LslElementType("VECTOR_OR_QUATERNION_EXPR");
  IElementType WHILE_STATEMENT = new LslElementType("WHILE_STATEMENT");

  IElementType AND = new LslTokenType("&&");
  IElementType ASSIGN = new LslTokenType("=");
  IElementType BITWISE_AND = new LslTokenType("&");
  IElementType BITWISE_NOT = new LslTokenType("~");
  IElementType BITWISE_OR = new LslTokenType("|");
  IElementType BITWISE_XOR = new LslTokenType("^");
  IElementType BRACES_LEFT = new LslTokenType("{");
  IElementType BRACES_RIGHT = new LslTokenType("}");
  IElementType BRACKETS_LEFT = new LslTokenType("[");
  IElementType BRACKETS_RIGHT = new LslTokenType("]");
  IElementType COMMA = new LslTokenType(",");
  IElementType DEFAULT = new LslTokenType("default");
  IElementType DIVIDE = new LslTokenType("/");
  IElementType DIVIDE_ASSIGN = new LslTokenType("/=");
  IElementType DO = new LslTokenType("do");
  IElementType DOT = new LslTokenType(".");
  IElementType ELSE = new LslTokenType("else");
  IElementType EQ = new LslTokenType("==");
  IElementType FLOAT = new LslTokenType("float");
  IElementType FLOAT_VALUE = new LslTokenType("float_value");
  IElementType FOR = new LslTokenType("for");
  IElementType GREATER = new LslTokenType(">");
  IElementType GREATER_EQ = new LslTokenType(">=");
  IElementType HEX_INTEGER_VALUE = new LslTokenType("hex_integer_value");
  IElementType IF = new LslTokenType("if");
  IElementType INTEGER = new LslTokenType("integer");
  IElementType INTEGER_VALUE = new LslTokenType("integer_value");
  IElementType JUMP = new LslTokenType("jump");
  IElementType KEY = new LslTokenType("key");
  IElementType LABEL = new LslTokenType("@");
  IElementType LESS = new LslTokenType("<");
  IElementType LESS_EQ = new LslTokenType("<=");
  IElementType LIST = new LslTokenType("list");
  IElementType MINUS = new LslTokenType("-");
  IElementType MINUS_ASSIGN = new LslTokenType("-=");
  IElementType MINUS_MINUS = new LslTokenType("--");
  IElementType MODULUS = new LslTokenType("%");
  IElementType MODULUS_ASSIGN = new LslTokenType("%=");
  IElementType MULTIPLE = new LslTokenType("*");
  IElementType MULTIPLE_ASSIGN = new LslTokenType("*=");
  IElementType NOT = new LslTokenType("!");
  IElementType NOT_EQ = new LslTokenType("!=");
  IElementType OR = new LslTokenType("||");
  IElementType PARENTHESES_LEFT = new LslTokenType("(");
  IElementType PARENTHESES_RIGHT = new LslTokenType(")");
  IElementType PLUS = new LslTokenType("+");
  IElementType PLUS_ASSIGN = new LslTokenType("+=");
  IElementType PLUS_PLUS = new LslTokenType("++");
  IElementType PRINT = new LslTokenType("print");
  IElementType QUATERNION = new LslTokenType("quaternion");
  IElementType RAW_IDENTIFIER = new LslTokenType("raw_identifier");
  IElementType RETURN = new LslTokenType("return");
  IElementType ROTATION = new LslTokenType("rotation");
  IElementType SEMICOLON = new LslTokenType(";");
  IElementType SHIFT_LEFT = new LslTokenType("<<");
  IElementType SHIFT_RIGHT = new LslTokenType(">>");
  IElementType STATE = new LslTokenType("state");
  IElementType STRING = new LslTokenType("string");
  IElementType STRING_VALUE = new LslTokenType("string_value");
  IElementType VECTOR = new LslTokenType("vector");
  IElementType WHILE = new LslTokenType("while");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADD_EXPR) {
        return new LslAddExprImpl(node);
      }
      else if (type == AND_EXPR) {
        return new LslAndExprImpl(node);
      }
      else if (type == ARGUMENT) {
        return new LslArgumentImpl(node);
      }
      else if (type == ASSIGN_EXPR) {
        return new LslAssignExprImpl(node);
      }
      else if (type == BLOCK) {
        return new LslBlockImpl(node);
      }
      else if (type == CALL_EXPR) {
        return new LslCallExprImpl(node);
      }
      else if (type == CONDITIONAL_EXPR) {
        return new LslConditionalExprImpl(node);
      }
      else if (type == CONSTANT_VALUE) {
        return new LslConstantValueImpl(node);
      }
      else if (type == CONVERSION_EXPR) {
        return new LslConversionExprImpl(node);
      }
      else if (type == DEFAULT_STATE_DECLARATION) {
        return new LslDefaultStateDeclarationImpl(node);
      }
      else if (type == DO_STATEMENT) {
        return new LslDoStatementImpl(node);
      }
      else if (type == ELSE_STATEMENT) {
        return new LslElseStatementImpl(node);
      }
      else if (type == EMPTY_STATEMENT) {
        return new LslEmptyStatementImpl(node);
      }
      else if (type == EXPRESSION_STATEMENT) {
        return new LslExpressionStatementImpl(node);
      }
      else if (type == FOR_STATEMENT) {
        return new LslForStatementImpl(node);
      }
      else if (type == FUNCTION_DECLARATION) {
        return new LslFunctionDeclarationImpl(node);
      }
      else if (type == GLOBAL_VARIABLE_DECLARATION) {
        return new LslGlobalVariableDeclarationImpl(node);
      }
      else if (type == IDENTIFIER) {
        return new LslIdentifierImpl(node);
      }
      else if (type == IF_STATEMENT) {
        return new LslIfStatementImpl(node);
      }
      else if (type == JUMP_STATEMENT) {
        return new LslJumpStatementImpl(node);
      }
      else if (type == LABEL_STATEMENT) {
        return new LslLabelStatementImpl(node);
      }
      else if (type == LIST_EXPR) {
        return new LslListExprImpl(node);
      }
      else if (type == LITERAL) {
        return new LslLiteralImpl(node);
      }
      else if (type == LOCAL_VARIABLE_DECLARATION) {
        return new LslLocalVariableDeclarationImpl(node);
      }
      else if (type == L_VALUE) {
        return new LslLValueImpl(node);
      }
      else if (type == MUL_EXPR) {
        return new LslMulExprImpl(node);
      }
      else if (type == OR_EXPR) {
        return new LslOrExprImpl(node);
      }
      else if (type == PARENTHESES_EXPR) {
        return new LslParenthesesExprImpl(node);
      }
      else if (type == PRINT_EXPR) {
        return new LslPrintExprImpl(node);
      }
      else if (type == RETURN_STATEMENT) {
        return new LslReturnStatementImpl(node);
      }
      else if (type == STATEMENT) {
        return new LslStatementImpl(node);
      }
      else if (type == STATE_DECLARATION) {
        return new LslStateDeclarationImpl(node);
      }
      else if (type == STATE_EVENT) {
        return new LslStateEventImpl(node);
      }
      else if (type == STATE_STATEMENT) {
        return new LslStateStatementImpl(node);
      }
      else if (type == TYPE_NAME) {
        return new LslTypeNameImpl(node);
      }
      else if (type == UNARY_EXPR) {
        return new LslUnaryExprImpl(node);
      }
      else if (type == UNARY_POSTFIX_EXPR) {
        return new LslUnaryPostfixExprImpl(node);
      }
      else if (type == VECTOR_OR_QUATERNION_EXPR) {
        return new LslVectorOrQuaternionExprImpl(node);
      }
      else if (type == WHILE_STATEMENT) {
        return new LslWhileStatementImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
