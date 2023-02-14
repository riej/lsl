// Generated from java-escape by ANTLR 4.11.1
package io.github.riej.lsl.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LSLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		Default=1, State=2, Jump=3, Return=4, If=5, Else=6, While=7, Do=8, For=9, 
		Print=10, TypeName=11, ParenthesesLeft=12, ParenthesesRight=13, BracketLeft=14, 
		BracketRight=15, BraceLeft=16, BraceRight=17, Less=18, LessEqual=19, Greater=20, 
		GreaterEqual=21, Equal=22, NotEqual=23, ShiftLeft=24, ShiftRight=25, Plus=26, 
		PlusPlus=27, Minus=28, MinusMinus=29, Multiple=30, Divide=31, Modulus=32, 
		BooleanAnd=33, BooleanOr=34, BooleanNot=35, BitwiseAnd=36, BitwiseOr=37, 
		BitwiseXor=38, BitwiseNot=39, Semicolon=40, Comma=41, Label=42, Assign=43, 
		PlusAssign=44, MinusAssign=45, MultipleAssign=46, DivideAssign=47, ModulusAssign=48, 
		Dot=49, Identifier=50, IntegerConstant=51, FloatingConstant=52, StringConstant=53, 
		Whitespace=54, Newline=55, BlockComment=56, LineComment=57, UnclosedStringConstant=58;
	public static final int
		RULE_file = 0, RULE_globalVariable = 1, RULE_function = 2, RULE_argument = 3, 
		RULE_arguments = 4, RULE_defaultStateDeclaration = 5, RULE_stateDeclaration = 6, 
		RULE_event = 7, RULE_statement = 8, RULE_expression = 9, RULE_expressions = 10, 
		RULE_lValue = 11, RULE_constant = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"file", "globalVariable", "function", "argument", "arguments", "defaultStateDeclaration", 
			"stateDeclaration", "event", "statement", "expression", "expressions", 
			"lValue", "constant"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'default'", "'state'", "'jump'", "'return'", "'if'", "'else'", 
			"'while'", "'do'", "'for'", "'print'", null, "'('", "')'", "'['", "']'", 
			"'{'", "'}'", "'<'", "'<='", "'>'", "'>='", "'=='", "'!='", "'<<'", "'>>'", 
			"'+'", "'++'", "'-'", "'--'", "'*'", "'/'", "'%'", "'&&'", "'||'", "'!'", 
			"'&'", "'|'", "'^'", "'~'", "';'", "','", "'@'", "'='", "'+='", "'-='", 
			"'*='", "'/='", "'%='", "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "Default", "State", "Jump", "Return", "If", "Else", "While", "Do", 
			"For", "Print", "TypeName", "ParenthesesLeft", "ParenthesesRight", "BracketLeft", 
			"BracketRight", "BraceLeft", "BraceRight", "Less", "LessEqual", "Greater", 
			"GreaterEqual", "Equal", "NotEqual", "ShiftLeft", "ShiftRight", "Plus", 
			"PlusPlus", "Minus", "MinusMinus", "Multiple", "Divide", "Modulus", "BooleanAnd", 
			"BooleanOr", "BooleanNot", "BitwiseAnd", "BitwiseOr", "BitwiseXor", "BitwiseNot", 
			"Semicolon", "Comma", "Label", "Assign", "PlusAssign", "MinusAssign", 
			"MultipleAssign", "DivideAssign", "ModulusAssign", "Dot", "Identifier", 
			"IntegerConstant", "FloatingConstant", "StringConstant", "Whitespace", 
			"Newline", "BlockComment", "LineComment", "UnclosedStringConstant"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LSLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FileContext extends ParserRuleContext {
		public DefaultStateDeclarationContext defaultStateDeclaration() {
			return getRuleContext(DefaultStateDeclarationContext.class,0);
		}
		public List<GlobalVariableContext> globalVariable() {
			return getRuleContexts(GlobalVariableContext.class);
		}
		public GlobalVariableContext globalVariable(int i) {
			return getRuleContext(GlobalVariableContext.class,i);
		}
		public List<FunctionContext> function() {
			return getRuleContexts(FunctionContext.class);
		}
		public FunctionContext function(int i) {
			return getRuleContext(FunctionContext.class,i);
		}
		public List<StateDeclarationContext> stateDeclaration() {
			return getRuleContexts(StateDeclarationContext.class);
		}
		public StateDeclarationContext stateDeclaration(int i) {
			return getRuleContext(StateDeclarationContext.class,i);
		}
		public FileContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterFile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitFile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitFile(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FileContext file() throws RecognitionException {
		FileContext _localctx = new FileContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_file);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==TypeName || _la==Identifier) {
				{
				setState(28);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,0,_ctx) ) {
				case 1:
					{
					setState(26);
					globalVariable();
					}
					break;
				case 2:
					{
					setState(27);
					function();
					}
					break;
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			defaultStateDeclaration();
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==State) {
				{
				{
				setState(34);
				stateDeclaration();
				}
				}
				setState(39);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class GlobalVariableContext extends ParserRuleContext {
		public TerminalNode TypeName() { return getToken(LSLParser.TypeName, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public TerminalNode Assign() { return getToken(LSLParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public GlobalVariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalVariable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterGlobalVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitGlobalVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitGlobalVariable(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalVariableContext globalVariable() throws RecognitionException {
		GlobalVariableContext _localctx = new GlobalVariableContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_globalVariable);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(40);
			match(TypeName);
			setState(41);
			match(Identifier);
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==Assign) {
				{
				setState(42);
				match(Assign);
				setState(43);
				expression(0);
				}
			}

			setState(46);
			match(Semicolon);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public TerminalNode BraceLeft() { return getToken(LSLParser.BraceLeft, 0); }
		public TerminalNode BraceRight() { return getToken(LSLParser.BraceRight, 0); }
		public TerminalNode TypeName() { return getToken(LSLParser.TypeName, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_function);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TypeName) {
				{
				setState(48);
				match(TypeName);
				}
			}

			setState(51);
			match(Identifier);
			setState(52);
			match(ParenthesesLeft);
			setState(53);
			arguments();
			setState(54);
			match(ParenthesesRight);
			setState(55);
			match(BraceLeft);
			setState(59);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 16894856161222588L) != 0) {
				{
				{
				setState(56);
				statement();
				}
				}
				setState(61);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(62);
			match(BraceRight);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentContext extends ParserRuleContext {
		public TerminalNode TypeName() { return getToken(LSLParser.TypeName, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitArgument(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitArgument(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_argument);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(TypeName);
			setState(65);
			match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ArgumentsContext extends ParserRuleContext {
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(LSLParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(LSLParser.Comma, i);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitArguments(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitArguments(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==TypeName) {
				{
				setState(67);
				argument();
				setState(72);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(68);
					match(Comma);
					setState(69);
					argument();
					}
					}
					setState(74);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefaultStateDeclarationContext extends ParserRuleContext {
		public TerminalNode Default() { return getToken(LSLParser.Default, 0); }
		public TerminalNode BraceLeft() { return getToken(LSLParser.BraceLeft, 0); }
		public TerminalNode BraceRight() { return getToken(LSLParser.BraceRight, 0); }
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public DefaultStateDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultStateDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterDefaultStateDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitDefaultStateDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitDefaultStateDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefaultStateDeclarationContext defaultStateDeclaration() throws RecognitionException {
		DefaultStateDeclarationContext _localctx = new DefaultStateDeclarationContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_defaultStateDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(77);
			match(Default);
			setState(78);
			match(BraceLeft);
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(79);
				event();
				}
				}
				setState(84);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(85);
			match(BraceRight);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StateDeclarationContext extends ParserRuleContext {
		public TerminalNode State() { return getToken(LSLParser.State, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode BraceLeft() { return getToken(LSLParser.BraceLeft, 0); }
		public TerminalNode BraceRight() { return getToken(LSLParser.BraceRight, 0); }
		public List<EventContext> event() {
			return getRuleContexts(EventContext.class);
		}
		public EventContext event(int i) {
			return getRuleContext(EventContext.class,i);
		}
		public StateDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stateDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStateDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStateDeclaration(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStateDeclaration(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StateDeclarationContext stateDeclaration() throws RecognitionException {
		StateDeclarationContext _localctx = new StateDeclarationContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_stateDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(87);
			match(State);
			setState(88);
			match(Identifier);
			setState(89);
			match(BraceLeft);
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==Identifier) {
				{
				{
				setState(90);
				event();
				}
				}
				setState(95);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(96);
			match(BraceRight);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class EventContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public TerminalNode BraceLeft() { return getToken(LSLParser.BraceLeft, 0); }
		public TerminalNode BraceRight() { return getToken(LSLParser.BraceRight, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public EventContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_event; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterEvent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitEvent(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitEvent(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EventContext event() throws RecognitionException {
		EventContext _localctx = new EventContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_event);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
			match(Identifier);
			setState(99);
			match(ParenthesesLeft);
			setState(100);
			arguments();
			setState(101);
			match(ParenthesesRight);
			setState(102);
			match(BraceLeft);
			setState(106);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (((_la) & ~0x3f) == 0 && ((1L << _la) & 16894856161222588L) != 0) {
				{
				{
				setState(103);
				statement();
				}
				}
				setState(108);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(109);
			match(BraceRight);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
	 
		public StatementContext() { }
		public void copyFrom(StatementContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementIfContext extends StatementContext {
		public TerminalNode If() { return getToken(LSLParser.If, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public TerminalNode Else() { return getToken(LSLParser.Else, 0); }
		public StatementIfContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementIf(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementStateContext extends StatementContext {
		public TerminalNode State() { return getToken(LSLParser.State, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode Default() { return getToken(LSLParser.Default, 0); }
		public StatementStateContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementState(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementState(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementState(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementForContext extends StatementContext {
		public TerminalNode For() { return getToken(LSLParser.For, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public List<ExpressionsContext> expressions() {
			return getRuleContexts(ExpressionsContext.class);
		}
		public ExpressionsContext expressions(int i) {
			return getRuleContext(ExpressionsContext.class,i);
		}
		public List<TerminalNode> Semicolon() { return getTokens(LSLParser.Semicolon); }
		public TerminalNode Semicolon(int i) {
			return getToken(LSLParser.Semicolon, i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementForContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementFor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementFor(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementFor(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementLabelContext extends StatementContext {
		public TerminalNode Label() { return getToken(LSLParser.Label, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public StatementLabelContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementLabel(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementLabel(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementReturnContext extends StatementContext {
		public TerminalNode Return() { return getToken(LSLParser.Return, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementReturnContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementReturn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementReturn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementReturn(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementEmptyContext extends StatementContext {
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public StatementEmptyContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementJumpContext extends StatementContext {
		public TerminalNode Jump() { return getToken(LSLParser.Jump, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public StatementJumpContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementJump(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementJump(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementJump(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementWhileContext extends StatementContext {
		public TerminalNode While() { return getToken(LSLParser.While, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public StatementWhileContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementWhile(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementWhile(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementWhile(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementExpressionContext extends StatementContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public StatementExpressionContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementExpression(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementExpression(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementVariableContext extends StatementContext {
		public TerminalNode TypeName() { return getToken(LSLParser.TypeName, 0); }
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public TerminalNode Assign() { return getToken(LSLParser.Assign, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementVariableContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementVariable(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementVariable(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementDoContext extends StatementContext {
		public TerminalNode Do() { return getToken(LSLParser.Do, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode While() { return getToken(LSLParser.While, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public TerminalNode Semicolon() { return getToken(LSLParser.Semicolon, 0); }
		public StatementDoContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementDo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementDo(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementDo(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatementBlockContext extends StatementContext {
		public TerminalNode BraceLeft() { return getToken(LSLParser.BraceLeft, 0); }
		public TerminalNode BraceRight() { return getToken(LSLParser.BraceRight, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public StatementBlockContext(StatementContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterStatementBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitStatementBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitStatementBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_statement);
		int _la;
		try {
			setState(177);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case Semicolon:
				_localctx = new StatementEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(111);
				match(Semicolon);
				}
				break;
			case BraceLeft:
				_localctx = new StatementBlockContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(112);
				match(BraceLeft);
				setState(116);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (((_la) & ~0x3f) == 0 && ((1L << _la) & 16894856161222588L) != 0) {
					{
					{
					setState(113);
					statement();
					}
					}
					setState(118);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(119);
				match(BraceRight);
				}
				break;
			case TypeName:
				_localctx = new StatementVariableContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(120);
				match(TypeName);
				setState(121);
				match(Identifier);
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==Assign) {
					{
					setState(122);
					match(Assign);
					setState(123);
					expression(0);
					}
				}

				setState(126);
				match(Semicolon);
				}
				break;
			case Label:
				_localctx = new StatementLabelContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(127);
				match(Label);
				setState(128);
				match(Identifier);
				setState(129);
				match(Semicolon);
				}
				break;
			case Jump:
				_localctx = new StatementJumpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(130);
				match(Jump);
				setState(131);
				match(Identifier);
				setState(132);
				match(Semicolon);
				}
				break;
			case State:
				_localctx = new StatementStateContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(133);
				match(State);
				setState(134);
				_la = _input.LA(1);
				if ( !(_la==Default || _la==Identifier) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(135);
				match(Semicolon);
				}
				break;
			case Return:
				_localctx = new StatementReturnContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(136);
				match(Return);
				setState(138);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16889358603015168L) != 0) {
					{
					setState(137);
					expression(0);
					}
				}

				setState(140);
				match(Semicolon);
				}
				break;
			case If:
				_localctx = new StatementIfContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(141);
				match(If);
				setState(142);
				match(ParenthesesLeft);
				setState(143);
				expression(0);
				setState(144);
				match(ParenthesesRight);
				setState(145);
				statement();
				setState(148);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
				case 1:
					{
					setState(146);
					match(Else);
					setState(147);
					statement();
					}
					break;
				}
				}
				break;
			case While:
				_localctx = new StatementWhileContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(150);
				match(While);
				setState(151);
				match(ParenthesesLeft);
				setState(152);
				expression(0);
				setState(153);
				match(ParenthesesRight);
				setState(154);
				statement();
				}
				break;
			case Do:
				_localctx = new StatementDoContext(_localctx);
				enterOuterAlt(_localctx, 10);
				{
				setState(156);
				match(Do);
				setState(157);
				statement();
				setState(158);
				match(While);
				setState(159);
				match(ParenthesesLeft);
				setState(160);
				expression(0);
				setState(161);
				match(ParenthesesRight);
				setState(162);
				match(Semicolon);
				}
				break;
			case For:
				_localctx = new StatementForContext(_localctx);
				enterOuterAlt(_localctx, 11);
				{
				setState(164);
				match(For);
				setState(165);
				match(ParenthesesLeft);
				setState(166);
				expressions();
				setState(167);
				match(Semicolon);
				setState(168);
				expression(0);
				setState(169);
				match(Semicolon);
				setState(170);
				expressions();
				setState(171);
				match(ParenthesesRight);
				setState(172);
				statement();
				}
				break;
			case Print:
			case ParenthesesLeft:
			case BracketLeft:
			case Less:
			case Plus:
			case PlusPlus:
			case Minus:
			case MinusMinus:
			case BooleanNot:
			case BitwiseXor:
			case BitwiseNot:
			case Identifier:
			case IntegerConstant:
			case FloatingConstant:
			case StringConstant:
				_localctx = new StatementExpressionContext(_localctx);
				enterOuterAlt(_localctx, 12);
				{
				setState(174);
				expression(0);
				setState(175);
				match(Semicolon);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionTypeCastContext extends ExpressionContext {
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public TerminalNode TypeName() { return getToken(LSLParser.TypeName, 0); }
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExpressionTypeCastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionTypeCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionTypeCast(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionTypeCast(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionBinaryContext extends ExpressionContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public TerminalNode Multiple() { return getToken(LSLParser.Multiple, 0); }
		public TerminalNode Divide() { return getToken(LSLParser.Divide, 0); }
		public TerminalNode Modulus() { return getToken(LSLParser.Modulus, 0); }
		public TerminalNode Minus() { return getToken(LSLParser.Minus, 0); }
		public TerminalNode Plus() { return getToken(LSLParser.Plus, 0); }
		public TerminalNode ShiftLeft() { return getToken(LSLParser.ShiftLeft, 0); }
		public TerminalNode ShiftRight() { return getToken(LSLParser.ShiftRight, 0); }
		public TerminalNode Less() { return getToken(LSLParser.Less, 0); }
		public TerminalNode LessEqual() { return getToken(LSLParser.LessEqual, 0); }
		public TerminalNode Greater() { return getToken(LSLParser.Greater, 0); }
		public TerminalNode GreaterEqual() { return getToken(LSLParser.GreaterEqual, 0); }
		public TerminalNode Equal() { return getToken(LSLParser.Equal, 0); }
		public TerminalNode NotEqual() { return getToken(LSLParser.NotEqual, 0); }
		public TerminalNode BitwiseAnd() { return getToken(LSLParser.BitwiseAnd, 0); }
		public TerminalNode BitwiseXor() { return getToken(LSLParser.BitwiseXor, 0); }
		public TerminalNode BitwiseOr() { return getToken(LSLParser.BitwiseOr, 0); }
		public TerminalNode BooleanOr() { return getToken(LSLParser.BooleanOr, 0); }
		public TerminalNode BooleanAnd() { return getToken(LSLParser.BooleanAnd, 0); }
		public ExpressionBinaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionBinary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionBinary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionBinary(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionUnaryPrefixContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(LSLParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(LSLParser.MinusMinus, 0); }
		public TerminalNode Plus() { return getToken(LSLParser.Plus, 0); }
		public TerminalNode Minus() { return getToken(LSLParser.Minus, 0); }
		public TerminalNode BooleanNot() { return getToken(LSLParser.BooleanNot, 0); }
		public TerminalNode BitwiseXor() { return getToken(LSLParser.BitwiseXor, 0); }
		public TerminalNode BitwiseNot() { return getToken(LSLParser.BitwiseNot, 0); }
		public ExpressionUnaryPrefixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionUnaryPrefix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionUnaryPrefix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionUnaryPrefix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionVectorContext extends ExpressionContext {
		public TerminalNode Less() { return getToken(LSLParser.Less, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(LSLParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(LSLParser.Comma, i);
		}
		public TerminalNode Greater() { return getToken(LSLParser.Greater, 0); }
		public ExpressionVectorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionVector(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionVector(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionVector(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionPrintContext extends ExpressionContext {
		public TerminalNode Print() { return getToken(LSLParser.Print, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public ExpressionPrintContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionPrint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionPrint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionPrint(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionLValueContext extends ExpressionContext {
		public LValueContext lValue() {
			return getRuleContext(LValueContext.class,0);
		}
		public ExpressionLValueContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionLValue(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionConstantContext extends ExpressionContext {
		public ConstantContext constant() {
			return getRuleContext(ConstantContext.class,0);
		}
		public ExpressionConstantContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionConstant(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionQuaternionContext extends ExpressionContext {
		public TerminalNode Less() { return getToken(LSLParser.Less, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(LSLParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(LSLParser.Comma, i);
		}
		public TerminalNode Greater() { return getToken(LSLParser.Greater, 0); }
		public ExpressionQuaternionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionQuaternion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionQuaternion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionQuaternion(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionUnaryPostfixContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode PlusPlus() { return getToken(LSLParser.PlusPlus, 0); }
		public TerminalNode MinusMinus() { return getToken(LSLParser.MinusMinus, 0); }
		public ExpressionUnaryPostfixContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionUnaryPostfix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionUnaryPostfix(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionUnaryPostfix(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionParenthesesContext extends ExpressionContext {
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public ExpressionParenthesesContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionParentheses(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionParentheses(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionParentheses(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionAssignmentContext extends ExpressionContext {
		public LValueContext lValue() {
			return getRuleContext(LValueContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode Assign() { return getToken(LSLParser.Assign, 0); }
		public TerminalNode PlusAssign() { return getToken(LSLParser.PlusAssign, 0); }
		public TerminalNode MinusAssign() { return getToken(LSLParser.MinusAssign, 0); }
		public TerminalNode MultipleAssign() { return getToken(LSLParser.MultipleAssign, 0); }
		public TerminalNode DivideAssign() { return getToken(LSLParser.DivideAssign, 0); }
		public TerminalNode ModulusAssign() { return getToken(LSLParser.ModulusAssign, 0); }
		public ExpressionAssignmentContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionAssignment(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionAssignment(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionFunctionCallContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(LSLParser.Identifier, 0); }
		public TerminalNode ParenthesesLeft() { return getToken(LSLParser.ParenthesesLeft, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode ParenthesesRight() { return getToken(LSLParser.ParenthesesRight, 0); }
		public ExpressionFunctionCallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionFunctionCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionFunctionCall(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionFunctionCall(this);
			else return visitor.visitChildren(this);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionListContext extends ExpressionContext {
		public TerminalNode BracketLeft() { return getToken(LSLParser.BracketLeft, 0); }
		public ExpressionsContext expressions() {
			return getRuleContext(ExpressionsContext.class,0);
		}
		public TerminalNode BracketRight() { return getToken(LSLParser.BracketRight, 0); }
		public ExpressionListContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressionList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressionList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 18;
		enterRecursionRule(_localctx, 18, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(228);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				{
				_localctx = new ExpressionParenthesesContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(180);
				match(ParenthesesLeft);
				setState(181);
				expression(0);
				setState(182);
				match(ParenthesesRight);
				}
				break;
			case 2:
				{
				_localctx = new ExpressionListContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(184);
				match(BracketLeft);
				setState(185);
				expressions();
				setState(186);
				match(BracketRight);
				}
				break;
			case 3:
				{
				_localctx = new ExpressionTypeCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(188);
				match(ParenthesesLeft);
				setState(189);
				match(TypeName);
				setState(190);
				match(ParenthesesRight);
				setState(191);
				expression(21);
				}
				break;
			case 4:
				{
				_localctx = new ExpressionUnaryPrefixContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(192);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 860000092160L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(193);
				expression(20);
				}
				break;
			case 5:
				{
				_localctx = new ExpressionAssignmentContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(194);
				lValue();
				setState(195);
				_la = _input.LA(1);
				if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 554153860399104L) != 0) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(196);
				expression(7);
				}
				break;
			case 6:
				{
				_localctx = new ExpressionFunctionCallContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(198);
				match(Identifier);
				setState(199);
				match(ParenthesesLeft);
				setState(200);
				expressions();
				setState(201);
				match(ParenthesesRight);
				}
				break;
			case 7:
				{
				_localctx = new ExpressionPrintContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(203);
				match(Print);
				setState(204);
				match(ParenthesesLeft);
				setState(205);
				expression(0);
				setState(206);
				match(ParenthesesRight);
				}
				break;
			case 8:
				{
				_localctx = new ExpressionVectorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(208);
				match(Less);
				setState(209);
				expression(0);
				setState(210);
				match(Comma);
				setState(211);
				expression(0);
				setState(212);
				match(Comma);
				setState(213);
				expression(0);
				setState(214);
				match(Greater);
				}
				break;
			case 9:
				{
				_localctx = new ExpressionQuaternionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(216);
				match(Less);
				setState(217);
				expression(0);
				setState(218);
				match(Comma);
				setState(219);
				expression(0);
				setState(220);
				match(Comma);
				setState(221);
				expression(0);
				setState(222);
				match(Comma);
				setState(223);
				expression(0);
				setState(224);
				match(Greater);
				}
				break;
			case 10:
				{
				_localctx = new ExpressionLValueContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(226);
				lValue();
				}
				break;
			case 11:
				{
				_localctx = new ExpressionConstantContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(227);
				constant();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(265);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,17,_ctx) ) {
					case 1:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(230);
						if (!(precpred(_ctx, 18))) throw new FailedPredicateException(this, "precpred(_ctx, 18)");
						setState(231);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 7516192768L) != 0) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(232);
						expression(19);
						}
						break;
					case 2:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(233);
						if (!(precpred(_ctx, 17))) throw new FailedPredicateException(this, "precpred(_ctx, 17)");
						setState(234);
						match(Minus);
						setState(235);
						expression(18);
						}
						break;
					case 3:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(236);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(237);
						match(Plus);
						setState(238);
						expression(17);
						}
						break;
					case 4:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(239);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(240);
						_la = _input.LA(1);
						if ( !(_la==ShiftLeft || _la==ShiftRight) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(241);
						expression(16);
						}
						break;
					case 5:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(242);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(243);
						_la = _input.LA(1);
						if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 3932160L) != 0) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(244);
						expression(15);
						}
						break;
					case 6:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(245);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(246);
						_la = _input.LA(1);
						if ( !(_la==Equal || _la==NotEqual) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(247);
						expression(14);
						}
						break;
					case 7:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(248);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(249);
						match(BitwiseAnd);
						setState(250);
						expression(13);
						}
						break;
					case 8:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(251);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(252);
						match(BitwiseXor);
						setState(253);
						expression(12);
						}
						break;
					case 9:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(254);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(255);
						match(BitwiseOr);
						setState(256);
						expression(11);
						}
						break;
					case 10:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(257);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(258);
						match(BooleanOr);
						setState(259);
						expression(10);
						}
						break;
					case 11:
						{
						_localctx = new ExpressionBinaryContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(260);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(261);
						match(BooleanAnd);
						setState(262);
						expression(9);
						}
						break;
					case 12:
						{
						_localctx = new ExpressionUnaryPostfixContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(263);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(264);
						_la = _input.LA(1);
						if ( !(_la==PlusPlus || _la==MinusMinus) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						break;
					}
					} 
				}
				setState(269);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExpressionsContext extends ParserRuleContext {
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<TerminalNode> Comma() { return getTokens(LSLParser.Comma); }
		public TerminalNode Comma(int i) {
			return getToken(LSLParser.Comma, i);
		}
		public ExpressionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterExpressions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitExpressions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitExpressions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpressionsContext expressions() throws RecognitionException {
		ExpressionsContext _localctx = new ExpressionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_expressions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(278);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (((_la) & ~0x3f) == 0 && ((1L << _la) & 16889358603015168L) != 0) {
				{
				setState(270);
				expression(0);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==Comma) {
					{
					{
					setState(271);
					match(Comma);
					setState(272);
					expression(0);
					}
					}
					setState(277);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class LValueContext extends ParserRuleContext {
		public List<TerminalNode> Identifier() { return getTokens(LSLParser.Identifier); }
		public TerminalNode Identifier(int i) {
			return getToken(LSLParser.Identifier, i);
		}
		public TerminalNode Dot() { return getToken(LSLParser.Dot, 0); }
		public LValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterLValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitLValue(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitLValue(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LValueContext lValue() throws RecognitionException {
		LValueContext _localctx = new LValueContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_lValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(280);
			match(Identifier);
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				{
				setState(281);
				match(Dot);
				setState(282);
				match(Identifier);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ConstantContext extends ParserRuleContext {
		public TerminalNode IntegerConstant() { return getToken(LSLParser.IntegerConstant, 0); }
		public TerminalNode FloatingConstant() { return getToken(LSLParser.FloatingConstant, 0); }
		public TerminalNode StringConstant() { return getToken(LSLParser.StringConstant, 0); }
		public ConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).enterConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LSLListener ) ((LSLListener)listener).exitConstant(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LSLVisitor ) return ((LSLVisitor<? extends T>)visitor).visitConstant(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConstantContext constant() throws RecognitionException {
		ConstantContext _localctx = new ConstantContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_constant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 15762598695796736L) != 0) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 9:
			return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 18);
		case 1:
			return precpred(_ctx, 17);
		case 2:
			return precpred(_ctx, 16);
		case 3:
			return precpred(_ctx, 15);
		case 4:
			return precpred(_ctx, 14);
		case 5:
			return precpred(_ctx, 13);
		case 6:
			return precpred(_ctx, 12);
		case 7:
			return precpred(_ctx, 11);
		case 8:
			return precpred(_ctx, 10);
		case 9:
			return precpred(_ctx, 9);
		case 10:
			return precpred(_ctx, 8);
		case 11:
			return precpred(_ctx, 19);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001:\u0120\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0005\u0000\u001d\b\u0000\n\u0000\f"+
		"\u0000 \t\u0000\u0001\u0000\u0001\u0000\u0005\u0000$\b\u0000\n\u0000\f"+
		"\u0000\'\t\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003"+
		"\u0001-\b\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0003\u00022\b\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002:\b\u0002\n\u0002\f\u0002=\t\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0005\u0004G\b\u0004\n\u0004\f\u0004J\t\u0004\u0003\u0004L\b\u0004\u0001"+
		"\u0005\u0001\u0005\u0001\u0005\u0005\u0005Q\b\u0005\n\u0005\f\u0005T\t"+
		"\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001"+
		"\u0006\u0005\u0006\\\b\u0006\n\u0006\f\u0006_\t\u0006\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001"+
		"\u0007\u0005\u0007i\b\u0007\n\u0007\f\u0007l\t\u0007\u0001\u0007\u0001"+
		"\u0007\u0001\b\u0001\b\u0001\b\u0005\bs\b\b\n\b\f\bv\t\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0003\b}\b\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0003"+
		"\b\u008b\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0003\b\u0095\b\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\b\u0001\b\u0001\b\u0003\b\u00b2\b\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003"+
		"\t\u00e5\b\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0005\t\u010a\b\t\n\t\f\t\u010d\t\t\u0001\n\u0001\n\u0001\n\u0005\n"+
		"\u0112\b\n\n\n\f\n\u0115\t\n\u0003\n\u0117\b\n\u0001\u000b\u0001\u000b"+
		"\u0001\u000b\u0003\u000b\u011c\b\u000b\u0001\f\u0001\f\u0001\f\u0000\u0001"+
		"\u0012\r\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018"+
		"\u0000\t\u0002\u0000\u0001\u000122\u0003\u0000\u001a\u001d##&\'\u0001"+
		"\u0000+0\u0001\u0000\u001e \u0001\u0000\u0018\u0019\u0001\u0000\u0012"+
		"\u0015\u0001\u0000\u0016\u0017\u0002\u0000\u001b\u001b\u001d\u001d\u0001"+
		"\u000035\u0145\u0000\u001e\u0001\u0000\u0000\u0000\u0002(\u0001\u0000"+
		"\u0000\u0000\u00041\u0001\u0000\u0000\u0000\u0006@\u0001\u0000\u0000\u0000"+
		"\bK\u0001\u0000\u0000\u0000\nM\u0001\u0000\u0000\u0000\fW\u0001\u0000"+
		"\u0000\u0000\u000eb\u0001\u0000\u0000\u0000\u0010\u00b1\u0001\u0000\u0000"+
		"\u0000\u0012\u00e4\u0001\u0000\u0000\u0000\u0014\u0116\u0001\u0000\u0000"+
		"\u0000\u0016\u0118\u0001\u0000\u0000\u0000\u0018\u011d\u0001\u0000\u0000"+
		"\u0000\u001a\u001d\u0003\u0002\u0001\u0000\u001b\u001d\u0003\u0004\u0002"+
		"\u0000\u001c\u001a\u0001\u0000\u0000\u0000\u001c\u001b\u0001\u0000\u0000"+
		"\u0000\u001d \u0001\u0000\u0000\u0000\u001e\u001c\u0001\u0000\u0000\u0000"+
		"\u001e\u001f\u0001\u0000\u0000\u0000\u001f!\u0001\u0000\u0000\u0000 \u001e"+
		"\u0001\u0000\u0000\u0000!%\u0003\n\u0005\u0000\"$\u0003\f\u0006\u0000"+
		"#\"\u0001\u0000\u0000\u0000$\'\u0001\u0000\u0000\u0000%#\u0001\u0000\u0000"+
		"\u0000%&\u0001\u0000\u0000\u0000&\u0001\u0001\u0000\u0000\u0000\'%\u0001"+
		"\u0000\u0000\u0000()\u0005\u000b\u0000\u0000),\u00052\u0000\u0000*+\u0005"+
		"+\u0000\u0000+-\u0003\u0012\t\u0000,*\u0001\u0000\u0000\u0000,-\u0001"+
		"\u0000\u0000\u0000-.\u0001\u0000\u0000\u0000./\u0005(\u0000\u0000/\u0003"+
		"\u0001\u0000\u0000\u000002\u0005\u000b\u0000\u000010\u0001\u0000\u0000"+
		"\u000012\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u000034\u00052\u0000"+
		"\u000045\u0005\f\u0000\u000056\u0003\b\u0004\u000067\u0005\r\u0000\u0000"+
		"7;\u0005\u0010\u0000\u00008:\u0003\u0010\b\u000098\u0001\u0000\u0000\u0000"+
		":=\u0001\u0000\u0000\u0000;9\u0001\u0000\u0000\u0000;<\u0001\u0000\u0000"+
		"\u0000<>\u0001\u0000\u0000\u0000=;\u0001\u0000\u0000\u0000>?\u0005\u0011"+
		"\u0000\u0000?\u0005\u0001\u0000\u0000\u0000@A\u0005\u000b\u0000\u0000"+
		"AB\u00052\u0000\u0000B\u0007\u0001\u0000\u0000\u0000CH\u0003\u0006\u0003"+
		"\u0000DE\u0005)\u0000\u0000EG\u0003\u0006\u0003\u0000FD\u0001\u0000\u0000"+
		"\u0000GJ\u0001\u0000\u0000\u0000HF\u0001\u0000\u0000\u0000HI\u0001\u0000"+
		"\u0000\u0000IL\u0001\u0000\u0000\u0000JH\u0001\u0000\u0000\u0000KC\u0001"+
		"\u0000\u0000\u0000KL\u0001\u0000\u0000\u0000L\t\u0001\u0000\u0000\u0000"+
		"MN\u0005\u0001\u0000\u0000NR\u0005\u0010\u0000\u0000OQ\u0003\u000e\u0007"+
		"\u0000PO\u0001\u0000\u0000\u0000QT\u0001\u0000\u0000\u0000RP\u0001\u0000"+
		"\u0000\u0000RS\u0001\u0000\u0000\u0000SU\u0001\u0000\u0000\u0000TR\u0001"+
		"\u0000\u0000\u0000UV\u0005\u0011\u0000\u0000V\u000b\u0001\u0000\u0000"+
		"\u0000WX\u0005\u0002\u0000\u0000XY\u00052\u0000\u0000Y]\u0005\u0010\u0000"+
		"\u0000Z\\\u0003\u000e\u0007\u0000[Z\u0001\u0000\u0000\u0000\\_\u0001\u0000"+
		"\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^`\u0001"+
		"\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`a\u0005\u0011\u0000\u0000"+
		"a\r\u0001\u0000\u0000\u0000bc\u00052\u0000\u0000cd\u0005\f\u0000\u0000"+
		"de\u0003\b\u0004\u0000ef\u0005\r\u0000\u0000fj\u0005\u0010\u0000\u0000"+
		"gi\u0003\u0010\b\u0000hg\u0001\u0000\u0000\u0000il\u0001\u0000\u0000\u0000"+
		"jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000km\u0001\u0000\u0000"+
		"\u0000lj\u0001\u0000\u0000\u0000mn\u0005\u0011\u0000\u0000n\u000f\u0001"+
		"\u0000\u0000\u0000o\u00b2\u0005(\u0000\u0000pt\u0005\u0010\u0000\u0000"+
		"qs\u0003\u0010\b\u0000rq\u0001\u0000\u0000\u0000sv\u0001\u0000\u0000\u0000"+
		"tr\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000uw\u0001\u0000\u0000"+
		"\u0000vt\u0001\u0000\u0000\u0000w\u00b2\u0005\u0011\u0000\u0000xy\u0005"+
		"\u000b\u0000\u0000y|\u00052\u0000\u0000z{\u0005+\u0000\u0000{}\u0003\u0012"+
		"\t\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000\u0000}~\u0001\u0000"+
		"\u0000\u0000~\u00b2\u0005(\u0000\u0000\u007f\u0080\u0005*\u0000\u0000"+
		"\u0080\u0081\u00052\u0000\u0000\u0081\u00b2\u0005(\u0000\u0000\u0082\u0083"+
		"\u0005\u0003\u0000\u0000\u0083\u0084\u00052\u0000\u0000\u0084\u00b2\u0005"+
		"(\u0000\u0000\u0085\u0086\u0005\u0002\u0000\u0000\u0086\u0087\u0007\u0000"+
		"\u0000\u0000\u0087\u00b2\u0005(\u0000\u0000\u0088\u008a\u0005\u0004\u0000"+
		"\u0000\u0089\u008b\u0003\u0012\t\u0000\u008a\u0089\u0001\u0000\u0000\u0000"+
		"\u008a\u008b\u0001\u0000\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000"+
		"\u008c\u00b2\u0005(\u0000\u0000\u008d\u008e\u0005\u0005\u0000\u0000\u008e"+
		"\u008f\u0005\f\u0000\u0000\u008f\u0090\u0003\u0012\t\u0000\u0090\u0091"+
		"\u0005\r\u0000\u0000\u0091\u0094\u0003\u0010\b\u0000\u0092\u0093\u0005"+
		"\u0006\u0000\u0000\u0093\u0095\u0003\u0010\b\u0000\u0094\u0092\u0001\u0000"+
		"\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000\u0095\u00b2\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0005\u0007\u0000\u0000\u0097\u0098\u0005\f\u0000"+
		"\u0000\u0098\u0099\u0003\u0012\t\u0000\u0099\u009a\u0005\r\u0000\u0000"+
		"\u009a\u009b\u0003\u0010\b\u0000\u009b\u00b2\u0001\u0000\u0000\u0000\u009c"+
		"\u009d\u0005\b\u0000\u0000\u009d\u009e\u0003\u0010\b\u0000\u009e\u009f"+
		"\u0005\u0007\u0000\u0000\u009f\u00a0\u0005\f\u0000\u0000\u00a0\u00a1\u0003"+
		"\u0012\t\u0000\u00a1\u00a2\u0005\r\u0000\u0000\u00a2\u00a3\u0005(\u0000"+
		"\u0000\u00a3\u00b2\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\t\u0000\u0000"+
		"\u00a5\u00a6\u0005\f\u0000\u0000\u00a6\u00a7\u0003\u0014\n\u0000\u00a7"+
		"\u00a8\u0005(\u0000\u0000\u00a8\u00a9\u0003\u0012\t\u0000\u00a9\u00aa"+
		"\u0005(\u0000\u0000\u00aa\u00ab\u0003\u0014\n\u0000\u00ab\u00ac\u0005"+
		"\r\u0000\u0000\u00ac\u00ad\u0003\u0010\b\u0000\u00ad\u00b2\u0001\u0000"+
		"\u0000\u0000\u00ae\u00af\u0003\u0012\t\u0000\u00af\u00b0\u0005(\u0000"+
		"\u0000\u00b0\u00b2\u0001\u0000\u0000\u0000\u00b1o\u0001\u0000\u0000\u0000"+
		"\u00b1p\u0001\u0000\u0000\u0000\u00b1x\u0001\u0000\u0000\u0000\u00b1\u007f"+
		"\u0001\u0000\u0000\u0000\u00b1\u0082\u0001\u0000\u0000\u0000\u00b1\u0085"+
		"\u0001\u0000\u0000\u0000\u00b1\u0088\u0001\u0000\u0000\u0000\u00b1\u008d"+
		"\u0001\u0000\u0000\u0000\u00b1\u0096\u0001\u0000\u0000\u0000\u00b1\u009c"+
		"\u0001\u0000\u0000\u0000\u00b1\u00a4\u0001\u0000\u0000\u0000\u00b1\u00ae"+
		"\u0001\u0000\u0000\u0000\u00b2\u0011\u0001\u0000\u0000\u0000\u00b3\u00b4"+
		"\u0006\t\uffff\uffff\u0000\u00b4\u00b5\u0005\f\u0000\u0000\u00b5\u00b6"+
		"\u0003\u0012\t\u0000\u00b6\u00b7\u0005\r\u0000\u0000\u00b7\u00e5\u0001"+
		"\u0000\u0000\u0000\u00b8\u00b9\u0005\u000e\u0000\u0000\u00b9\u00ba\u0003"+
		"\u0014\n\u0000\u00ba\u00bb\u0005\u000f\u0000\u0000\u00bb\u00e5\u0001\u0000"+
		"\u0000\u0000\u00bc\u00bd\u0005\f\u0000\u0000\u00bd\u00be\u0005\u000b\u0000"+
		"\u0000\u00be\u00bf\u0005\r\u0000\u0000\u00bf\u00e5\u0003\u0012\t\u0015"+
		"\u00c0\u00c1\u0007\u0001\u0000\u0000\u00c1\u00e5\u0003\u0012\t\u0014\u00c2"+
		"\u00c3\u0003\u0016\u000b\u0000\u00c3\u00c4\u0007\u0002\u0000\u0000\u00c4"+
		"\u00c5\u0003\u0012\t\u0007\u00c5\u00e5\u0001\u0000\u0000\u0000\u00c6\u00c7"+
		"\u00052\u0000\u0000\u00c7\u00c8\u0005\f\u0000\u0000\u00c8\u00c9\u0003"+
		"\u0014\n\u0000\u00c9\u00ca\u0005\r\u0000\u0000\u00ca\u00e5\u0001\u0000"+
		"\u0000\u0000\u00cb\u00cc\u0005\n\u0000\u0000\u00cc\u00cd\u0005\f\u0000"+
		"\u0000\u00cd\u00ce\u0003\u0012\t\u0000\u00ce\u00cf\u0005\r\u0000\u0000"+
		"\u00cf\u00e5\u0001\u0000\u0000\u0000\u00d0\u00d1\u0005\u0012\u0000\u0000"+
		"\u00d1\u00d2\u0003\u0012\t\u0000\u00d2\u00d3\u0005)\u0000\u0000\u00d3"+
		"\u00d4\u0003\u0012\t\u0000\u00d4\u00d5\u0005)\u0000\u0000\u00d5\u00d6"+
		"\u0003\u0012\t\u0000\u00d6\u00d7\u0005\u0014\u0000\u0000\u00d7\u00e5\u0001"+
		"\u0000\u0000\u0000\u00d8\u00d9\u0005\u0012\u0000\u0000\u00d9\u00da\u0003"+
		"\u0012\t\u0000\u00da\u00db\u0005)\u0000\u0000\u00db\u00dc\u0003\u0012"+
		"\t\u0000\u00dc\u00dd\u0005)\u0000\u0000\u00dd\u00de\u0003\u0012\t\u0000"+
		"\u00de\u00df\u0005)\u0000\u0000\u00df\u00e0\u0003\u0012\t\u0000\u00e0"+
		"\u00e1\u0005\u0014\u0000\u0000\u00e1\u00e5\u0001\u0000\u0000\u0000\u00e2"+
		"\u00e5\u0003\u0016\u000b\u0000\u00e3\u00e5\u0003\u0018\f\u0000\u00e4\u00b3"+
		"\u0001\u0000\u0000\u0000\u00e4\u00b8\u0001\u0000\u0000\u0000\u00e4\u00bc"+
		"\u0001\u0000\u0000\u0000\u00e4\u00c0\u0001\u0000\u0000\u0000\u00e4\u00c2"+
		"\u0001\u0000\u0000\u0000\u00e4\u00c6\u0001\u0000\u0000\u0000\u00e4\u00cb"+
		"\u0001\u0000\u0000\u0000\u00e4\u00d0\u0001\u0000\u0000\u0000\u00e4\u00d8"+
		"\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000\u0000\u00e4\u00e3"+
		"\u0001\u0000\u0000\u0000\u00e5\u010b\u0001\u0000\u0000\u0000\u00e6\u00e7"+
		"\n\u0012\u0000\u0000\u00e7\u00e8\u0007\u0003\u0000\u0000\u00e8\u010a\u0003"+
		"\u0012\t\u0013\u00e9\u00ea\n\u0011\u0000\u0000\u00ea\u00eb\u0005\u001c"+
		"\u0000\u0000\u00eb\u010a\u0003\u0012\t\u0012\u00ec\u00ed\n\u0010\u0000"+
		"\u0000\u00ed\u00ee\u0005\u001a\u0000\u0000\u00ee\u010a\u0003\u0012\t\u0011"+
		"\u00ef\u00f0\n\u000f\u0000\u0000\u00f0\u00f1\u0007\u0004\u0000\u0000\u00f1"+
		"\u010a\u0003\u0012\t\u0010\u00f2\u00f3\n\u000e\u0000\u0000\u00f3\u00f4"+
		"\u0007\u0005\u0000\u0000\u00f4\u010a\u0003\u0012\t\u000f\u00f5\u00f6\n"+
		"\r\u0000\u0000\u00f6\u00f7\u0007\u0006\u0000\u0000\u00f7\u010a\u0003\u0012"+
		"\t\u000e\u00f8\u00f9\n\f\u0000\u0000\u00f9\u00fa\u0005$\u0000\u0000\u00fa"+
		"\u010a\u0003\u0012\t\r\u00fb\u00fc\n\u000b\u0000\u0000\u00fc\u00fd\u0005"+
		"&\u0000\u0000\u00fd\u010a\u0003\u0012\t\f\u00fe\u00ff\n\n\u0000\u0000"+
		"\u00ff\u0100\u0005%\u0000\u0000\u0100\u010a\u0003\u0012\t\u000b\u0101"+
		"\u0102\n\t\u0000\u0000\u0102\u0103\u0005\"\u0000\u0000\u0103\u010a\u0003"+
		"\u0012\t\n\u0104\u0105\n\b\u0000\u0000\u0105\u0106\u0005!\u0000\u0000"+
		"\u0106\u010a\u0003\u0012\t\t\u0107\u0108\n\u0013\u0000\u0000\u0108\u010a"+
		"\u0007\u0007\u0000\u0000\u0109\u00e6\u0001\u0000\u0000\u0000\u0109\u00e9"+
		"\u0001\u0000\u0000\u0000\u0109\u00ec\u0001\u0000\u0000\u0000\u0109\u00ef"+
		"\u0001\u0000\u0000\u0000\u0109\u00f2\u0001\u0000\u0000\u0000\u0109\u00f5"+
		"\u0001\u0000\u0000\u0000\u0109\u00f8\u0001\u0000\u0000\u0000\u0109\u00fb"+
		"\u0001\u0000\u0000\u0000\u0109\u00fe\u0001\u0000\u0000\u0000\u0109\u0101"+
		"\u0001\u0000\u0000\u0000\u0109\u0104\u0001\u0000\u0000\u0000\u0109\u0107"+
		"\u0001\u0000\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109"+
		"\u0001\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u0013"+
		"\u0001\u0000\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u0113"+
		"\u0003\u0012\t\u0000\u010f\u0110\u0005)\u0000\u0000\u0110\u0112\u0003"+
		"\u0012\t\u0000\u0111\u010f\u0001\u0000\u0000\u0000\u0112\u0115\u0001\u0000"+
		"\u0000\u0000\u0113\u0111\u0001\u0000\u0000\u0000\u0113\u0114\u0001\u0000"+
		"\u0000\u0000\u0114\u0117\u0001\u0000\u0000\u0000\u0115\u0113\u0001\u0000"+
		"\u0000\u0000\u0116\u010e\u0001\u0000\u0000\u0000\u0116\u0117\u0001\u0000"+
		"\u0000\u0000\u0117\u0015\u0001\u0000\u0000\u0000\u0118\u011b\u00052\u0000"+
		"\u0000\u0119\u011a\u00051\u0000\u0000\u011a\u011c\u00052\u0000\u0000\u011b"+
		"\u0119\u0001\u0000\u0000\u0000\u011b\u011c\u0001\u0000\u0000\u0000\u011c"+
		"\u0017\u0001\u0000\u0000\u0000\u011d\u011e\u0007\b\u0000\u0000\u011e\u0019"+
		"\u0001\u0000\u0000\u0000\u0016\u001c\u001e%,1;HKR]jt|\u008a\u0094\u00b1"+
		"\u00e4\u0109\u010b\u0113\u0116\u011b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}