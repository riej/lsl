// This is a generated file. Not intended for manual editing.
package io.github.riej.lsl;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.riej.lsl.psi.LslTypes.*;
import static io.github.riej.lsl.LslParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class LslParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, EXTENDS_SETS_);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return LslScript(b, l + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADD_EXPR, AND_EXPR, ASSIGN_EXPR, CALL_EXPR,
      CONDITIONAL_EXPR, CONVERSION_EXPR, EXPRESSION, LIST_EXPR,
      LITERAL, MUL_EXPR, OR_EXPR, PARENTHESES_EXPR,
      PRINT_EXPR, UNARY_EXPR, UNARY_POSTFIX_EXPR, VECTOR_OR_QUATERNION_EXPR),
  };

  /* ********************************************************** */
  // '+' | '-' | '|' | '^'
  static boolean AddOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddOp")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, BITWISE_OR);
    if (!r) r = consumeToken(b, BITWISE_XOR);
    return r;
  }

  /* ********************************************************** */
  // TypeName Identifier
  public static boolean Argument(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Argument")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ARGUMENT, "<argument>");
    r = TypeName(b, l + 1);
    p = r; // pin = 1
    r = r && Identifier(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Argument (',' Argument)*
  static boolean ArgumentsList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentsList")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = Argument(b, l + 1);
    p = r; // pin = 1
    r = r && ArgumentsList_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' Argument)*
  private static boolean ArgumentsList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentsList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ArgumentsList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ArgumentsList_1", c)) break;
    }
    return true;
  }

  // ',' Argument
  private static boolean ArgumentsList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ArgumentsList_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && Argument(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // '+=' | '-=' | '*=' | '/=' | '%=' | '='
  static boolean AssignOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignOp")) return false;
    boolean r;
    r = consumeToken(b, PLUS_ASSIGN);
    if (!r) r = consumeToken(b, MINUS_ASSIGN);
    if (!r) r = consumeToken(b, MULTIPLE_ASSIGN);
    if (!r) r = consumeToken(b, DIVIDE_ASSIGN);
    if (!r) r = consumeToken(b, MODULUS_ASSIGN);
    if (!r) r = consumeToken(b, ASSIGN);
    return r;
  }

  /* ********************************************************** */
  // '{' Statements? '}'
  public static boolean Block(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block")) return false;
    if (!nextTokenIs(b, BRACES_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, BLOCK, null);
    r = consumeToken(b, BRACES_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, Block_1(b, l + 1));
    r = p && consumeToken(b, BRACES_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Statements?
  private static boolean Block_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Block_1")) return false;
    Statements(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '(' Expression ')'
  static boolean Condition(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Condition")) return false;
    if (!nextTokenIs(b, PARENTHESES_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, PARENTHESES_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && consumeToken(b, PARENTHESES_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // string_value
  //     | float_value
  //     | hex_integer_value
  //     | integer_value
  public static boolean ConstantValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConstantValue")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONSTANT_VALUE, "<constant value>");
    r = consumeToken(b, STRING_VALUE);
    if (!r) r = consumeToken(b, FLOAT_VALUE);
    if (!r) r = consumeToken(b, HEX_INTEGER_VALUE);
    if (!r) r = consumeToken(b, INTEGER_VALUE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // default StateBody
  public static boolean DefaultStateDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DefaultStateDeclaration")) return false;
    if (!nextTokenIs(b, DEFAULT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DEFAULT_STATE_DECLARATION, null);
    r = consumeToken(b, DEFAULT);
    p = r; // pin = 1
    r = r && StateBody(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // do Statement while Condition ';'
  public static boolean DoStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DoStatement")) return false;
    if (!nextTokenIs(b, DO)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DO_STATEMENT, null);
    r = consumeToken(b, DO);
    p = r; // pin = 1
    r = r && report_error_(b, Statement(b, l + 1));
    r = p && report_error_(b, consumeToken(b, WHILE)) && r;
    r = p && report_error_(b, Condition(b, l + 1)) && r;
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // else Statement
  public static boolean ElseStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ElseStatement")) return false;
    if (!nextTokenIs(b, ELSE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, ELSE_STATEMENT, null);
    r = consumeToken(b, ELSE);
    p = r; // pin = 1
    r = r && Statement(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // ';'
  public static boolean EmptyStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EmptyStatement")) return false;
    if (!nextTokenIs(b, SEMICOLON)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEMICOLON);
    exit_section_(b, m, EMPTY_STATEMENT, r);
    return r;
  }

  /* ********************************************************** */
  // ExpressionWithRecover (',' ExpressionWithRecover)*
  static boolean ExpressionList(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = ExpressionWithRecover(b, l + 1);
    p = r; // pin = 1
    r = r && ExpressionList_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // (',' ExpressionWithRecover)*
  private static boolean ExpressionList_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ExpressionList_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ExpressionList_1", c)) break;
    }
    return true;
  }

  // ',' ExpressionWithRecover
  private static boolean ExpressionList_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionList_1_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, COMMA);
    p = r; // pin = 1
    r = r && ExpressionWithRecover(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // !('!' | '%' | '&&' | '&' | '~' | '(' | ')' | '*' | '*=' | '+' | '++' | '+=' | ',' | '-' | '--' | '-=' | '/' | '/=' | ';' | '<' | '<<' | '<=' | '=' | '==' | '>' | '>=' | '>>' | '[' | ']' | '^' | '{' | '|' | '||' | '}' | '@' | if | for | while | do | state | jump | string_value | float_value | hex_integer_value | integer_value | raw_identifier)
  static boolean ExpressionListRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionListRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !ExpressionListRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '!' | '%' | '&&' | '&' | '~' | '(' | ')' | '*' | '*=' | '+' | '++' | '+=' | ',' | '-' | '--' | '-=' | '/' | '/=' | ';' | '<' | '<<' | '<=' | '=' | '==' | '>' | '>=' | '>>' | '[' | ']' | '^' | '{' | '|' | '||' | '}' | '@' | if | for | while | do | state | jump | string_value | float_value | hex_integer_value | integer_value | raw_identifier
  private static boolean ExpressionListRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionListRecover_0")) return false;
    boolean r;
    r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, MODULUS);
    if (!r) r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, BITWISE_AND);
    if (!r) r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, PARENTHESES_LEFT);
    if (!r) r = consumeToken(b, PARENTHESES_RIGHT);
    if (!r) r = consumeToken(b, MULTIPLE);
    if (!r) r = consumeToken(b, MULTIPLE_ASSIGN);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, PLUS_PLUS);
    if (!r) r = consumeToken(b, PLUS_ASSIGN);
    if (!r) r = consumeToken(b, COMMA);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, MINUS_MINUS);
    if (!r) r = consumeToken(b, MINUS_ASSIGN);
    if (!r) r = consumeToken(b, DIVIDE);
    if (!r) r = consumeToken(b, DIVIDE_ASSIGN);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, SHIFT_LEFT);
    if (!r) r = consumeToken(b, LESS_EQ);
    if (!r) r = consumeToken(b, ASSIGN);
    if (!r) r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, GREATER);
    if (!r) r = consumeToken(b, GREATER_EQ);
    if (!r) r = consumeToken(b, SHIFT_RIGHT);
    if (!r) r = consumeToken(b, BRACKETS_LEFT);
    if (!r) r = consumeToken(b, BRACKETS_RIGHT);
    if (!r) r = consumeToken(b, BITWISE_XOR);
    if (!r) r = consumeToken(b, BRACES_LEFT);
    if (!r) r = consumeToken(b, BITWISE_OR);
    if (!r) r = consumeToken(b, OR);
    if (!r) r = consumeToken(b, BRACES_RIGHT);
    if (!r) r = consumeToken(b, LABEL);
    if (!r) r = consumeToken(b, IF);
    if (!r) r = consumeToken(b, FOR);
    if (!r) r = consumeToken(b, WHILE);
    if (!r) r = consumeToken(b, DO);
    if (!r) r = consumeToken(b, STATE);
    if (!r) r = consumeToken(b, JUMP);
    if (!r) r = consumeToken(b, STRING_VALUE);
    if (!r) r = consumeToken(b, FLOAT_VALUE);
    if (!r) r = consumeToken(b, HEX_INTEGER_VALUE);
    if (!r) r = consumeToken(b, INTEGER_VALUE);
    if (!r) r = consumeToken(b, RAW_IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // ExpressionWithRecover ';'
  public static boolean ExpressionStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionStatement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION_STATEMENT, "<expression statement>");
    r = ExpressionWithRecover(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // Expression
  static boolean ExpressionWithRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ExpressionWithRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = Expression(b, l + 1, -1);
    exit_section_(b, l, m, r, false, LslParser::ExpressionListRecover);
    return r;
  }

  /* ********************************************************** */
  // for '(' ExpressionList? ';' ExpressionWithRecover? ';' ExpressionList ')' Statement
  public static boolean ForStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement")) return false;
    if (!nextTokenIs(b, FOR)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, FOR_STATEMENT, null);
    r = consumeTokens(b, 1, FOR, PARENTHESES_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, ForStatement_2(b, l + 1));
    r = p && report_error_(b, consumeToken(b, SEMICOLON)) && r;
    r = p && report_error_(b, ForStatement_4(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, SEMICOLON)) && r;
    r = p && report_error_(b, ExpressionList(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, PARENTHESES_RIGHT)) && r;
    r = p && Statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ExpressionList?
  private static boolean ForStatement_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_2")) return false;
    ExpressionList(b, l + 1);
    return true;
  }

  // ExpressionWithRecover?
  private static boolean ForStatement_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ForStatement_4")) return false;
    ExpressionWithRecover(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // TypeName? Identifier '(' ArgumentsList? ')' Block
  public static boolean FunctionDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FUNCTION_DECLARATION, "<function declaration>");
    r = FunctionDeclaration_0(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_LEFT);
    r = r && FunctionDeclaration_3(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_RIGHT);
    r = r && Block(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TypeName?
  private static boolean FunctionDeclaration_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration_0")) return false;
    TypeName(b, l + 1);
    return true;
  }

  // ArgumentsList?
  private static boolean FunctionDeclaration_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FunctionDeclaration_3")) return false;
    ArgumentsList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // GlobalVariableDeclaration | FunctionDeclaration | ';'
  static boolean GlobalDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = GlobalVariableDeclaration(b, l + 1);
    if (!r) r = FunctionDeclaration(b, l + 1);
    if (!r) r = consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, LslParser::GlobalDeclarationRecover);
    return r;
  }

  /* ********************************************************** */
  // !(TypeName | raw_identifier | default | ';')
  static boolean GlobalDeclarationRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalDeclarationRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !GlobalDeclarationRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // TypeName | raw_identifier | default | ';'
  private static boolean GlobalDeclarationRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalDeclarationRecover_0")) return false;
    boolean r;
    r = TypeName(b, l + 1);
    if (!r) r = consumeToken(b, RAW_IDENTIFIER);
    if (!r) r = consumeToken(b, DEFAULT);
    if (!r) r = consumeToken(b, SEMICOLON);
    return r;
  }

  /* ********************************************************** */
  // TypeName Identifier ('=' Expression)? ';'
  public static boolean GlobalVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalVariableDeclaration")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GLOBAL_VARIABLE_DECLARATION, "<global variable declaration>");
    r = TypeName(b, l + 1);
    r = r && Identifier(b, l + 1);
    r = r && GlobalVariableDeclaration_2(b, l + 1);
    r = r && consumeToken(b, SEMICOLON);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ('=' Expression)?
  private static boolean GlobalVariableDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalVariableDeclaration_2")) return false;
    GlobalVariableDeclaration_2_0(b, l + 1);
    return true;
  }

  // '=' Expression
  private static boolean GlobalVariableDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalVariableDeclaration_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && Expression(b, l + 1, -1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // raw_identifier
  public static boolean Identifier(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Identifier")) return false;
    if (!nextTokenIs(b, RAW_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RAW_IDENTIFIER);
    exit_section_(b, m, IDENTIFIER, r);
    return r;
  }

  /* ********************************************************** */
  // if Condition Statement ElseStatement?
  public static boolean IfStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement")) return false;
    if (!nextTokenIs(b, IF)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, IF_STATEMENT, null);
    r = consumeToken(b, IF);
    p = r; // pin = 1
    r = r && report_error_(b, Condition(b, l + 1));
    r = p && report_error_(b, Statement(b, l + 1)) && r;
    r = p && IfStatement_3(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ElseStatement?
  private static boolean IfStatement_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IfStatement_3")) return false;
    ElseStatement(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // jump Identifier ';'
  public static boolean JumpStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "JumpStatement")) return false;
    if (!nextTokenIs(b, JUMP)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, JUMP_STATEMENT, null);
    r = consumeToken(b, JUMP);
    p = r; // pin = 1
    r = r && report_error_(b, Identifier(b, l + 1));
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Identifier '.' Identifier
  //          | Identifier
  public static boolean LValue(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LValue")) return false;
    if (!nextTokenIs(b, RAW_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LValue_0(b, l + 1);
    if (!r) r = Identifier(b, l + 1);
    exit_section_(b, m, L_VALUE, r);
    return r;
  }

  // Identifier '.' Identifier
  private static boolean LValue_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LValue_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, DOT);
    r = r && Identifier(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '@' Identifier ';'
  public static boolean LabelStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelStatement")) return false;
    if (!nextTokenIs(b, LABEL)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LABEL_STATEMENT, null);
    r = consumeToken(b, LABEL);
    p = r; // pin = 1
    r = r && report_error_(b, Identifier(b, l + 1));
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // TypeName Identifier ('=' ExpressionWithRecover)? ';'
  public static boolean LocalVariableDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LOCAL_VARIABLE_DECLARATION, "<local variable declaration>");
    r = TypeName(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, Identifier(b, l + 1));
    r = p && report_error_(b, LocalVariableDeclaration_2(b, l + 1)) && r;
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ('=' ExpressionWithRecover)?
  private static boolean LocalVariableDeclaration_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration_2")) return false;
    LocalVariableDeclaration_2_0(b, l + 1);
    return true;
  }

  // '=' ExpressionWithRecover
  private static boolean LocalVariableDeclaration_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LocalVariableDeclaration_2_0")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = consumeToken(b, ASSIGN);
    p = r; // pin = 1
    r = r && ExpressionWithRecover(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // GlobalDeclaration* DefaultStateDeclaration StateDeclaration*
  static boolean LslScript(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LslScript")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_);
    r = LslScript_0(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, DefaultStateDeclaration(b, l + 1));
    r = p && LslScript_2(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // GlobalDeclaration*
  private static boolean LslScript_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LslScript_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!GlobalDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LslScript_0", c)) break;
    }
    return true;
  }

  // StateDeclaration*
  private static boolean LslScript_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LslScript_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StateDeclaration(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "LslScript_2", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // '*' | '/' | '%' | '<<' | '>>' | '&'
  static boolean MulOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "MulOp")) return false;
    boolean r;
    r = consumeToken(b, MULTIPLE);
    if (!r) r = consumeToken(b, DIVIDE);
    if (!r) r = consumeToken(b, MODULUS);
    if (!r) r = consumeToken(b, SHIFT_LEFT);
    if (!r) r = consumeToken(b, SHIFT_RIGHT);
    if (!r) r = consumeToken(b, BITWISE_AND);
    return r;
  }

  /* ********************************************************** */
  // '==' | '!=' | '<' | '<=' | '>' | '>='
  static boolean RelOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RelOp")) return false;
    boolean r;
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NOT_EQ);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, LESS_EQ);
    if (!r) r = consumeToken(b, GREATER);
    if (!r) r = consumeToken(b, GREATER_EQ);
    return r;
  }

  /* ********************************************************** */
  // return ExpressionWithRecover? ';'
  public static boolean ReturnStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement")) return false;
    if (!nextTokenIs(b, RETURN)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, RETURN_STATEMENT, null);
    r = consumeToken(b, RETURN);
    p = r; // pin = 1
    r = r && report_error_(b, ReturnStatement_1(b, l + 1));
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ExpressionWithRecover?
  private static boolean ReturnStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ReturnStatement_1")) return false;
    ExpressionWithRecover(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // '{' StateEvent* '}'
  static boolean StateBody(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateBody")) return false;
    if (!nextTokenIs(b, BRACES_LEFT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BRACES_LEFT);
    r = r && StateBody_1(b, l + 1);
    r = r && consumeToken(b, BRACES_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // StateEvent*
  private static boolean StateBody_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateBody_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StateEvent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "StateBody_1", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // state Identifier StateBody
  public static boolean StateDeclaration(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateDeclaration")) return false;
    if (!nextTokenIs(b, STATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STATE_DECLARATION, null);
    r = consumeToken(b, STATE);
    p = r; // pin = 1
    r = r && report_error_(b, Identifier(b, l + 1));
    r = p && StateBody(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Identifier '(' ArgumentsList? ')' Block
  public static boolean StateEvent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateEvent")) return false;
    if (!nextTokenIs(b, RAW_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STATE_EVENT, null);
    r = Identifier(b, l + 1);
    p = r; // pin = 1
    r = r && report_error_(b, consumeToken(b, PARENTHESES_LEFT));
    r = p && report_error_(b, StateEvent_2(b, l + 1)) && r;
    r = p && report_error_(b, consumeToken(b, PARENTHESES_RIGHT)) && r;
    r = p && Block(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ArgumentsList?
  private static boolean StateEvent_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateEvent_2")) return false;
    ArgumentsList(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // state (Identifier | default) ';'
  public static boolean StateStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateStatement")) return false;
    if (!nextTokenIs(b, STATE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, STATE_STATEMENT, null);
    r = consumeToken(b, STATE);
    p = r; // pin = 1
    r = r && report_error_(b, StateStatement_1(b, l + 1));
    r = p && consumeToken(b, SEMICOLON) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Identifier | default
  private static boolean StateStatement_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StateStatement_1")) return false;
    boolean r;
    r = Identifier(b, l + 1);
    if (!r) r = consumeToken(b, DEFAULT);
    return r;
  }

  /* ********************************************************** */
  // LocalVariableDeclaration
  //     | ExpressionStatement
  //     | LabelStatement
  //     | JumpStatement
  //     | StateStatement
  //     | ReturnStatement
  //     | Block
  //     | IfStatement
  //     | ForStatement
  //     | WhileStatement
  //     | DoStatement
  //     | EmptyStatement
  public static boolean Statement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statement")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STATEMENT, "<statement>");
    r = LocalVariableDeclaration(b, l + 1);
    if (!r) r = ExpressionStatement(b, l + 1);
    if (!r) r = LabelStatement(b, l + 1);
    if (!r) r = JumpStatement(b, l + 1);
    if (!r) r = StateStatement(b, l + 1);
    if (!r) r = ReturnStatement(b, l + 1);
    if (!r) r = Block(b, l + 1);
    if (!r) r = IfStatement(b, l + 1);
    if (!r) r = ForStatement(b, l + 1);
    if (!r) r = WhileStatement(b, l + 1);
    if (!r) r = DoStatement(b, l + 1);
    if (!r) r = EmptyStatement(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !('!' | '~' | '(' | '+' | '-' | ';' | '{' | '}' | '@' | '<' | jump | state | return | if | else | for | while | do | TypeName | print | raw_identifier)
  static boolean StatementRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !StatementRecover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '!' | '~' | '(' | '+' | '-' | ';' | '{' | '}' | '@' | '<' | jump | state | return | if | else | for | while | do | TypeName | print | raw_identifier
  private static boolean StatementRecover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementRecover_0")) return false;
    boolean r;
    r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, BITWISE_NOT);
    if (!r) r = consumeToken(b, PARENTHESES_LEFT);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, SEMICOLON);
    if (!r) r = consumeToken(b, BRACES_LEFT);
    if (!r) r = consumeToken(b, BRACES_RIGHT);
    if (!r) r = consumeToken(b, LABEL);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, JUMP);
    if (!r) r = consumeToken(b, STATE);
    if (!r) r = consumeToken(b, RETURN);
    if (!r) r = consumeToken(b, IF);
    if (!r) r = consumeToken(b, ELSE);
    if (!r) r = consumeToken(b, FOR);
    if (!r) r = consumeToken(b, WHILE);
    if (!r) r = consumeToken(b, DO);
    if (!r) r = TypeName(b, l + 1);
    if (!r) r = consumeToken(b, PRINT);
    if (!r) r = consumeToken(b, RAW_IDENTIFIER);
    return r;
  }

  /* ********************************************************** */
  // Statement
  static boolean StatementWithRecover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "StatementWithRecover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_);
    r = Statement(b, l + 1);
    exit_section_(b, l, m, r, false, LslParser::StatementRecover);
    return r;
  }

  /* ********************************************************** */
  // StatementWithRecover*
  static boolean Statements(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Statements")) return false;
    while (true) {
      int c = current_position_(b);
      if (!StatementWithRecover(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Statements", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // integer
  //     | float
  //     | string
  //     | key
  //     | vector
  //     | rotation
  //     | quaternion
  //     | list
  public static boolean TypeName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "TypeName")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TYPE_NAME, "<type name>");
    r = consumeToken(b, INTEGER);
    if (!r) r = consumeToken(b, FLOAT);
    if (!r) r = consumeToken(b, STRING);
    if (!r) r = consumeToken(b, KEY);
    if (!r) r = consumeToken(b, VECTOR);
    if (!r) r = consumeToken(b, ROTATION);
    if (!r) r = consumeToken(b, QUATERNION);
    if (!r) r = consumeToken(b, LIST);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // '++' | '--' | '+' | '-' | '!' | '^' | '~'
  static boolean UnaryOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryOp")) return false;
    boolean r;
    r = consumeToken(b, PLUS_PLUS);
    if (!r) r = consumeToken(b, MINUS_MINUS);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    if (!r) r = consumeToken(b, NOT);
    if (!r) r = consumeToken(b, BITWISE_XOR);
    if (!r) r = consumeToken(b, BITWISE_NOT);
    return r;
  }

  /* ********************************************************** */
  // '++' | '--'
  static boolean UnaryPostfixOp(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryPostfixOp")) return false;
    if (!nextTokenIs(b, "", MINUS_MINUS, PLUS_PLUS)) return false;
    boolean r;
    r = consumeToken(b, PLUS_PLUS);
    if (!r) r = consumeToken(b, MINUS_MINUS);
    return r;
  }

  /* ********************************************************** */
  // while Condition Statement
  public static boolean WhileStatement(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "WhileStatement")) return false;
    if (!nextTokenIs(b, WHILE)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, WHILE_STATEMENT, null);
    r = consumeToken(b, WHILE);
    p = r; // pin = 1
    r = r && report_error_(b, Condition(b, l + 1));
    r = p && Statement(b, l + 1) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  /* ********************************************************** */
  // Expression root: Expression
  // Operator priority table:
  // 0: BINARY(OrExpr)
  // 1: BINARY(AndExpr)
  // 2: BINARY(ConditionalExpr)
  // 3: BINARY(AddExpr)
  // 4: BINARY(MulExpr)
  // 5: PREFIX(AssignExpr)
  // 6: PREFIX(UnaryExpr)
  // 7: POSTFIX(UnaryPostfixExpr)
  // 8: PREFIX(ConversionExpr)
  // 9: ATOM(PrintExpr) ATOM(CallExpr) ATOM(ListExpr) ATOM(VectorOrQuaternionImplExpr)
  //    ATOM(Literal)
  // 10: ATOM(ParenthesesExpr)
  public static boolean Expression(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression")) return false;
    addVariant(b, "<expression>");
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, "<expression>");
    r = AssignExpr(b, l + 1);
    if (!r) r = UnaryExpr(b, l + 1);
    if (!r) r = ConversionExpr(b, l + 1);
    if (!r) r = PrintExpr(b, l + 1);
    if (!r) r = CallExpr(b, l + 1);
    if (!r) r = ListExpr(b, l + 1);
    if (!r) r = parseVectorOrQuaternionExpr(b, l + 1);
    if (!r) r = Literal(b, l + 1);
    if (!r) r = ParenthesesExpr(b, l + 1);
    p = r;
    r = r && Expression_0(b, l + 1, g);
    exit_section_(b, l, m, null, r, p, null);
    return r || p;
  }

  public static boolean Expression_0(PsiBuilder b, int l, int g) {
    if (!recursion_guard_(b, l, "Expression_0")) return false;
    boolean r = true;
    while (true) {
      Marker m = enter_section_(b, l, _LEFT_, null);
      if (g < 0 && consumeTokenSmart(b, OR)) {
        r = Expression(b, l, 0);
        exit_section_(b, l, m, OR_EXPR, r, true, null);
      }
      else if (g < 1 && consumeTokenSmart(b, AND)) {
        r = Expression(b, l, 1);
        exit_section_(b, l, m, AND_EXPR, r, true, null);
      }
      else if (g < 2 && RelOp(b, l + 1)) {
        r = Expression(b, l, 2);
        exit_section_(b, l, m, CONDITIONAL_EXPR, r, true, null);
      }
      else if (g < 3 && AddOp(b, l + 1)) {
        r = Expression(b, l, 3);
        exit_section_(b, l, m, ADD_EXPR, r, true, null);
      }
      else if (g < 4 && MulOp(b, l + 1)) {
        r = Expression(b, l, 4);
        exit_section_(b, l, m, MUL_EXPR, r, true, null);
      }
      else if (g < 7 && UnaryPostfixOp(b, l + 1)) {
        r = true;
        exit_section_(b, l, m, UNARY_POSTFIX_EXPR, r, true, null);
      }
      else {
        exit_section_(b, l, m, null, false, false, null);
        break;
      }
    }
    return r;
  }

  public static boolean AssignExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignExpr")) return false;
    if (!nextTokenIsSmart(b, RAW_IDENTIFIER)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = AssignExpr_0(b, l + 1);
    p = r;
    r = p && Expression(b, l, 5);
    exit_section_(b, l, m, ASSIGN_EXPR, r, p, null);
    return r || p;
  }

  // LValue AssignOp
  private static boolean AssignExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssignExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LValue(b, l + 1);
    r = r && AssignOp(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  public static boolean UnaryExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "UnaryExpr")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = UnaryOp(b, l + 1);
    p = r;
    r = p && Expression(b, l, 6);
    exit_section_(b, l, m, UNARY_EXPR, r, p, null);
    return r || p;
  }

  public static boolean ConversionExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConversionExpr")) return false;
    if (!nextTokenIsSmart(b, PARENTHESES_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, null);
    r = ConversionExpr_0(b, l + 1);
    p = r;
    r = p && Expression(b, l, 8);
    exit_section_(b, l, m, CONVERSION_EXPR, r, p, null);
    return r || p;
  }

  // '(' TypeName ')'
  private static boolean ConversionExpr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConversionExpr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenSmart(b, PARENTHESES_LEFT);
    r = r && TypeName(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_RIGHT);
    exit_section_(b, m, null, r);
    return r;
  }

  // print '(' Expression ')'
  public static boolean PrintExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PrintExpr")) return false;
    if (!nextTokenIsSmart(b, PRINT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PRINT_EXPR, null);
    r = consumeTokensSmart(b, 1, PRINT, PARENTHESES_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && consumeToken(b, PARENTHESES_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Identifier '(' ExpressionList? ')'
  public static boolean CallExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpr")) return false;
    if (!nextTokenIsSmart(b, RAW_IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Identifier(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_LEFT);
    r = r && CallExpr_2(b, l + 1);
    r = r && consumeToken(b, PARENTHESES_RIGHT);
    exit_section_(b, m, CALL_EXPR, r);
    return r;
  }

  // ExpressionList?
  private static boolean CallExpr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CallExpr_2")) return false;
    ExpressionList(b, l + 1);
    return true;
  }

  // '[' ExpressionList? ']'
  public static boolean ListExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListExpr")) return false;
    if (!nextTokenIsSmart(b, BRACKETS_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, LIST_EXPR, null);
    r = consumeTokenSmart(b, BRACKETS_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, ListExpr_1(b, l + 1));
    r = p && consumeToken(b, BRACKETS_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // ExpressionList?
  private static boolean ListExpr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ListExpr_1")) return false;
    ExpressionList(b, l + 1);
    return true;
  }

  // ConstantValue
  //     | LValue
  public static boolean Literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Literal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LITERAL, "<literal>");
    r = ConstantValue(b, l + 1);
    if (!r) r = LValue(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '(' Expression ')'
  public static boolean ParenthesesExpr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ParenthesesExpr")) return false;
    if (!nextTokenIsSmart(b, PARENTHESES_LEFT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, PARENTHESES_EXPR, null);
    r = consumeTokenSmart(b, PARENTHESES_LEFT);
    p = r; // pin = 1
    r = r && report_error_(b, Expression(b, l + 1, -1));
    r = p && consumeToken(b, PARENTHESES_RIGHT) && r;
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

}
