package io.github.riej.lsl;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import io.github.riej.lsl.psi.LslTypes;
import com.intellij.psi.TokenType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static io.github.riej.lsl.LslParserDefinition.*;

%%

%{

  private int nestedCommentDepth = 0;
  private int tokenStringDepth = 0;

  public LslLexer() {
    this((java.io.Reader)null);
  }
%}

%class LslLexer
%implements FlexLexer, LslTypes
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

NEW_LINE = [\n\r]+
WHITE_SPACE = [\ \t\f]

LINE_COMMENT = "//" [^\r\n]*
MULTILINE_COMMENT = "/*" ( ([^"*"]|[\r\n])* ("*"+ [^"*""/"] )? )* ("*" | "*"+"/")?

LETTER = [:letter:] | "_"
DIGIT = [:digit:]
HEX_DIGIT = [0-9A-Fa-f]



INTEGER_VALUE = "0" | ([1-9] {DIGIT}*)
HEX_INTEGER_VALUE =  ("0x" | "0X") {HEX_DIGIT}+

FLOAT_EXPONENT = [eE] [+-]? {DIGIT}+
FLOAT_VALUE = ((({DIGIT}+ "." {DIGIT}*) | ({DIGIT}* "." {DIGIT}+) ) {FLOAT_EXPONENT}?) | ({DIGIT}+ {FLOAT_EXPONENT})

RAW_IDENTIFIER = {LETTER} ({LETTER} | {DIGIT})*

STRING_QUOTE =      "\""
STRING_VALUE = {STRING_QUOTE} ( [^\"\\\n\r] | "\\" ("\\" | {STRING_QUOTE} | {ESCAPES} | [0-8xuU] ) )* {STRING_QUOTE}?

ESCAPES = [nt]

%%

<YYINITIAL> {
    {WHITE_SPACE}+ { return WHITE_SPACE; }
    {NEW_LINE}+ { return WHITE_SPACE; }

    {LINE_COMMENT} { return LINE_COMMENT; }
    {MULTILINE_COMMENT} { return MULTILINE_COMMENT; }

    "." { return DOT; }
    "{" { return BRACES_LEFT; }
    "}" { return BRACES_RIGHT; }

    "[" { return BRACKETS_LEFT; }
    "]" { return BRACKETS_RIGHT; }

    "(" { return PARENTHESES_LEFT; }
    ")" { return PARENTHESES_RIGHT; }

    ";" { return SEMICOLON; }
    "," { return COMMA; }

    "==" { return EQ; }
    "=" { return ASSIGN; }

    "!=" { return NOT_EQ; }
    "!" { return NOT; }

    "++" { return PLUS_PLUS; }
    "+=" { return PLUS_ASSIGN; }
    "+" { return PLUS; }

    "--" { return MINUS_MINUS; }
    "-=" { return MINUS_ASSIGN; }
    "-" { return MINUS; }

    "||" { return OR; }
    "|" { return BITWISE_OR; }

    "&&" { return AND; }
    "&" { return BITWISE_AND; }

    "<<" { return SHIFT_LEFT; }
    "<=" { return LESS_EQ; }
    "<"  { return LESS; }

    ">>" { return SHIFT_RIGHT; }
    ">=" { return GREATER_EQ; }
    ">" { return GREATER; }

    "*=" { return MULTIPLE_ASSIGN; }
    "*" { return MULTIPLE; }

    "/=" { return DIVIDE_ASSIGN; }
    "/" { return DIVIDE; }

    "%=" { return MODULUS_ASSIGN; }
    "%" { return MODULUS; }

    "~" { return BITWISE_NOT; }
    "^" { return BITWISE_XOR; }

    "@" { return LABEL; }

    "default" { return DEFAULT; }
    "state" { return STATE; }
    "jump" { return JUMP; }
    "return" { return RETURN; }
    "if" { return IF; }
    "else" { return ELSE; }
    "for" { return FOR; }
    "while" { return WHILE; }
    "do" { return DO; }
    "print" { return PRINT; }

    "integer"                   { return INTEGER; }
    "float"                     { return FLOAT; }
    "string"                    { return STRING; }
    "key"                       { return KEY; }
    "vector"                    { return VECTOR; }
    "rotation"                  { return ROTATION; }
    "quaternion"                { return QUATERNION; }
    "list"                      { return LIST; }

    {RAW_IDENTIFIER} { return RAW_IDENTIFIER; }
    {STRING_VALUE} {return STRING_VALUE; }
    {FLOAT_VALUE} { return FLOAT_VALUE; }
    {HEX_INTEGER_VALUE} { return HEX_INTEGER_VALUE; }
    {INTEGER_VALUE} { return INTEGER_VALUE; }

    . { return BAD_CHARACTER; }
}

