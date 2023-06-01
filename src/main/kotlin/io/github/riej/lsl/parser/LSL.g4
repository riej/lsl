grammar LSL;

file
    : (globalVariable | function)* defaultStateDeclaration stateDeclaration*
    ;

globalVariable
    : TypeName Identifier ('=' globalVariableValue)? ';'
    ;

// This type is little tricky. Global variables cannot use all expression, but short list of them.
globalVariableValue
    : '<' globalVariableValue ',' globalVariableValue ',' globalVariableValue '>' # GlobalVariableValueVector // treat as ExpressionVector
    | '<' globalVariableValue ',' globalVariableValue ',' globalVariableValue ',' globalVariableValue '>' # GlobalVariableValueQuaternion // treat as ExpressionQuaternion
    | Identifier # GlobalVariableLValue // treat as ExpressionLValue
    | constant # GlobalVariableValueConstant // treat as ExpressionConstant
    ;

function
    : TypeName? Identifier '(' arguments ')' statementBlock
    ;

argument
    : TypeName Identifier
    ;

arguments
    : (argument (',' argument)*)?
    ;

defaultStateDeclaration
    : Default '{' events '}'
    ;

stateDeclaration
    : State Identifier '{' events '}'
    ;

event
    : Identifier '(' arguments ')' statementBlock
    ;

events
    : event+
    ;

statement
    : ';' # StatementEmpty
    | statementBlock # StatementBlock2
    | TypeName Identifier ('=' expression)? ';' # StatementVariable
    | '@' Identifier ';' # StatementLabel
    | Jump Identifier ';' # StatementJump
    | State (Identifier | Default) ';' # StatementState
    | Return expression? ';' # StatementReturn
    | If '(' expression ')' statement statementElse? # StatementIf
    | While '(' expression ')' statement # StatementWhile
    | Do statement While '(' expression ')' ';' # StatementDo
    | For '(' expressions ';' expression ';' expressions ')' statement # StatementFor
    | expression ';' # StatementExpression
    ;

statementElse
    : Else statement
    ;

statementBlock
    : '{' statement* '}'
    ;

expression
    : '(' expression ')' # ExpressionParentheses
    | '[' expressions ']' # ExpressionList
    | '(' TypeName ')' expression # ExpressionTypeCast
    | ('++' | '--') lValue # ExpressionUnaryPrefixLValue
    | ('-' | '!' | '^' | '~') expression # ExpressionUnaryPrefix
    | lValue ('++' | '--') # ExpressionUnaryPostfix
    | expression ('*' | '/' | '%') expression # ExpressionBinary
    | expression '-' expression # ExpressionBinary
    | expression '+' expression # ExpressionBinary
    | expression ('<<' | '>>') expression # ExpressionBinary
    | expression ('<' | '<=' | '>' | '>=') expression # ExpressionBinary
    | expression ('==' | '!=') expression # ExpressionBinary
    | expression '&' expression # ExpressionBinary
    | expression '^' expression # ExpressionBinary
    | expression '|' expression # ExpressionBinary
    | expression '||' expression # ExpressionBinary
    | expression '&&' expression # ExpressionBinary
    | lValue ('=' | '+=' | '-=' | '*=' | '/=' | '%=') expression # ExpressionAssignment

    | Identifier '(' expressions ')' # ExpressionFunctionCall
    | Print '(' expression ')' # ExpressionPrint
    | '<' expression ',' expression ',' expression '>' # ExpressionVector
    | '<' expression ',' expression ',' expression ',' expression '>' # ExpressionQuaternion
    | lValue # ExpressionLValue
    | constant # ExpressionConstant
    ;

expressions
    : (expression (',' expression)*)?
    ;

lValue
    : Identifier ('.' Identifier)?
    ;

constant
    :   IntegerConstant
    |   FloatingConstant
    |   StringConstant
    ;

Default : 'default';
State : 'state';
Jump : 'jump';
Return : 'return';
If : 'if';
Else : 'else';
While : 'while';
Do : 'do';
For : 'for';
Print : 'print';


TypeName
    : 'integer'
    | 'float'
    | 'string'
    | 'key'
    | 'vector'
    | 'rotation'
    | 'quaternion'
    | 'list'
    ;

ParenthesesLeft : '(';
ParenthesesRight : ')';
BracketLeft : '[';
BracketRight : ']';
BraceLeft : '{';
BraceRight : '}';

Less : '<';
LessEqual : '<=';
Greater : '>';
GreaterEqual : '>=';
Equal : '==';
NotEqual : '!=';

ShiftLeft : '<<';
ShiftRight : '>>';

Plus : '+';
PlusPlus : '++';
Minus : '-';
MinusMinus : '--';
Multiple : '*';
Divide : '/';
Modulus : '%';

BooleanAnd : '&&';
BooleanOr : '||';
BooleanNot : '!';

BitwiseAnd : '&';
BitwiseOr : '|';
BitwiseXor : '^';
BitwiseNot : '~';

Semicolon : ';';
Comma : ',';
Label : '@';

Assign : '=';
PlusAssign : '+=';
MinusAssign : '-=';
MultipleAssign : '*=';
DivideAssign : '/=';
ModulusAssign : '%=';

Dot : '.';

Identifier : [a-zA-Z_][a-zA-Z0-9_]*;

fragment
Digit
    : [0-9]
    ;

fragment
NonZeroDigit
    : [1-9]
    ;

fragment
HexadecimalDigit
    : [0-9a-fA-F]
    ;

IntegerConstant
    :   '-'? DecimalConstant
    |   '-'? HexadecimalConstant
    ;

fragment
DecimalConstant
    : NonZeroDigit Digit*
    | '0'
    ;

fragment
HexadecimalConstant
    :   '0' [xX] HexadecimalDigit+
    ;

fragment
FloatingExponent
    : [eE] [+\-]? Digit+
    ;

FloatingConstant
    :   '-'? (((Digit+ '.' Digit*) | (Digit* '.' Digit+)) FloatingExponent?) | (Digit+ FloatingExponent)
    ;

StringConstant
    :   '"' StringCharacter* '"'
    ;

fragment
EscapeSequence
    :   '\\' ["nt\\]
    ;

fragment
StringCharacter
    :   ~["\\]
    |   EscapeSequence
    |   '\\\n'   // Added line
    |   '\\\r\n' // Added line
    ;

Whitespace
    :   [ \t]+
        -> channel(HIDDEN)
    ;

Newline
    :   (   '\r' '\n'?
        |   '\n'
        )
        -> channel(HIDDEN)
    ;

BlockComment
    :   '/*' .*? ('*/' | EOF)
        -> channel(HIDDEN)
    ;

LineComment
    :   '//' ~[\r\n]*
        -> channel(HIDDEN)
    ;

UnclosedStringConstant
    :   '"' StringCharacter* EOF
    ;