// Generated from D:/GUC/Semester 10/Compilers Lab - ACL/Tasks/Task 9/Task9Sol/Task9/grammar/Task9.g4 by ANTLR 4.13.1
package csen1002.main.task9;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class Task9Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ZERO=1, ONE=2, HASH=3;
	public static final int
		RULE_s = 0, RULE_f = 1, RULE_t = 2, RULE_n = 3, RULE_d = 4;
	private static String[] makeRuleNames() {
		return new String[] {
			"s", "f", "t", "n", "d"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'0'", "'1'", "'#'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "ZERO", "ONE", "HASH"
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
	public String getGrammarFileName() { return "Task9.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		/**
		 * Compares two integer numbers
		 *
		 * @param x the first number to compare
		 * @param y the second number to compare
		 * @return 1 if x is equal to y, and 0 otherwise
		 */
		public static int equals(int x, int y) {
		    return x == y ? 1 : 0;
		}

	public Task9Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SContext extends ParserRuleContext {
		public int check;
		public FContext f;
		public FContext f() {
			return getRuleContext(FContext.class,0);
		}
		public SContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_s; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterS(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitS(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitS(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SContext s() throws RecognitionException {
		SContext _localctx = new SContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_s);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			((SContext)_localctx).f = f();
			((SContext)_localctx).check =  ((SContext)_localctx).f.check * ((SContext)_localctx).f.m;
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
	public static class FContext extends ParserRuleContext {
		public int check;
		public int m;
		public DContext d;
		public TContext t;
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public TContext t() {
			return getRuleContext(TContext.class,0);
		}
		public FContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_f; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterF(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitF(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitF(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FContext f() throws RecognitionException {
		FContext _localctx = new FContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_f);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(13);
			((FContext)_localctx).d = d(1,1);
			setState(14);
			((FContext)_localctx).t = t(2,((FContext)_localctx).d.l);
			 ((FContext)_localctx).check = ((FContext)_localctx).d.check*((FContext)_localctx).t.check; ((FContext)_localctx).m = ((FContext)_localctx).t.m;
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
	public static class TContext extends ParserRuleContext {
		public int r;
		public int l;
		public int check;
		public int m;
		public NContext n;
		public TerminalNode HASH() { return getToken(Task9Parser.HASH, 0); }
		public NContext n() {
			return getRuleContext(NContext.class,0);
		}
		public TContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public TContext(ParserRuleContext parent, int invokingState, int r, int l) {
			super(parent, invokingState);
			this.r = r;
			this.l = l;
		}
		@Override public int getRuleIndex() { return RULE_t; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterT(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitT(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitT(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TContext t(int r,int l) throws RecognitionException {
		TContext _localctx = new TContext(_ctx, getState(), r, l);
		enterRule(_localctx, 4, RULE_t);
		try {
			setState(22);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case HASH:
				enterOuterAlt(_localctx, 1);
				{
				setState(17);
				match(HASH);
				setState(18);
				((TContext)_localctx).n = n(_localctx.r,1,_localctx.l);
				((TContext)_localctx).check = ((TContext)_localctx).n.check; ((TContext)_localctx).m = ((TContext)_localctx).n.m;
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				((TContext)_localctx).check = 1; ((TContext)_localctx).m = 1;
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
	public static class NContext extends ParserRuleContext {
		public int r;
		public int c;
		public int l;
		public int check;
		public int m;
		public DContext d;
		public TContext t;
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public TContext t() {
			return getRuleContext(TContext.class,0);
		}
		public NContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public NContext(ParserRuleContext parent, int invokingState, int r, int c, int l) {
			super(parent, invokingState);
			this.r = r;
			this.c = c;
			this.l = l;
		}
		@Override public int getRuleIndex() { return RULE_n; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterN(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitN(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitN(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NContext n(int r,int c,int l) throws RecognitionException {
		NContext _localctx = new NContext(_ctx, getState(), r, c, l);
		enterRule(_localctx, 6, RULE_n);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			((NContext)_localctx).d = d(_localctx.r,1);
			setState(25);
			((NContext)_localctx).t = t(_localctx.r+1, _localctx.l);
			((NContext)_localctx).check = ((NContext)_localctx).d.check * ((NContext)_localctx).t.check;  ((NContext)_localctx).m =  equals(((NContext)_localctx).d.l, _localctx.l) * ((NContext)_localctx).t.m;
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
	public static class DContext extends ParserRuleContext {
		public int r;
		public int c;
		public int check;
		public int l;
		public DContext d1;
		public TerminalNode ZERO() { return getToken(Task9Parser.ZERO, 0); }
		public DContext d() {
			return getRuleContext(DContext.class,0);
		}
		public TerminalNode ONE() { return getToken(Task9Parser.ONE, 0); }
		public DContext(ParserRuleContext parent, int invokingState) { super(parent, invokingState); }
		public DContext(ParserRuleContext parent, int invokingState, int r, int c) {
			super(parent, invokingState);
			this.r = r;
			this.c = c;
		}
		@Override public int getRuleIndex() { return RULE_d; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).enterD(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Task9Listener ) ((Task9Listener)listener).exitD(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Task9Visitor ) return ((Task9Visitor<? extends T>)visitor).visitD(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DContext d(int r,int c) throws RecognitionException {
		DContext _localctx = new DContext(_ctx, getState(), r, c);
		enterRule(_localctx, 8, RULE_d);
		try {
			setState(40);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(28);
				match(ZERO);
				setState(29);
				((DContext)_localctx).d1 = d(r,c+1);
				((DContext)_localctx).l = ((DContext)_localctx).d1.l; ((DContext)_localctx).check = (1-equals(c,r))* ((DContext)_localctx).d1.check;
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				match(ONE);
				setState(33);
				((DContext)_localctx).d1 = d(r,c+1);
				((DContext)_localctx).l = ((DContext)_localctx).d1.l; ((DContext)_localctx).check = equals(c,r)* ((DContext)_localctx).d1.check;
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(36);
				match(ZERO);
				((DContext)_localctx).l = c; ((DContext)_localctx).check = 1-equals(c,r);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(38);
				match(ONE);
				((DContext)_localctx).l = c; ((DContext)_localctx).check = equals(c,r);
				}
				break;
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

	public static final String _serializedATN =
		"\u0004\u0001\u0003+\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0001"+
		"\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003"+
		"\u0002\u0017\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0003"+
		"\u0004)\b\u0004\u0001\u0004\u0000\u0000\u0005\u0000\u0002\u0004\u0006"+
		"\b\u0000\u0000)\u0000\n\u0001\u0000\u0000\u0000\u0002\r\u0001\u0000\u0000"+
		"\u0000\u0004\u0016\u0001\u0000\u0000\u0000\u0006\u0018\u0001\u0000\u0000"+
		"\u0000\b(\u0001\u0000\u0000\u0000\n\u000b\u0003\u0002\u0001\u0000\u000b"+
		"\f\u0006\u0000\uffff\uffff\u0000\f\u0001\u0001\u0000\u0000\u0000\r\u000e"+
		"\u0003\b\u0004\u0000\u000e\u000f\u0003\u0004\u0002\u0000\u000f\u0010\u0006"+
		"\u0001\uffff\uffff\u0000\u0010\u0003\u0001\u0000\u0000\u0000\u0011\u0012"+
		"\u0005\u0003\u0000\u0000\u0012\u0013\u0003\u0006\u0003\u0000\u0013\u0014"+
		"\u0006\u0002\uffff\uffff\u0000\u0014\u0017\u0001\u0000\u0000\u0000\u0015"+
		"\u0017\u0006\u0002\uffff\uffff\u0000\u0016\u0011\u0001\u0000\u0000\u0000"+
		"\u0016\u0015\u0001\u0000\u0000\u0000\u0017\u0005\u0001\u0000\u0000\u0000"+
		"\u0018\u0019\u0003\b\u0004\u0000\u0019\u001a\u0003\u0004\u0002\u0000\u001a"+
		"\u001b\u0006\u0003\uffff\uffff\u0000\u001b\u0007\u0001\u0000\u0000\u0000"+
		"\u001c\u001d\u0005\u0001\u0000\u0000\u001d\u001e\u0003\b\u0004\u0000\u001e"+
		"\u001f\u0006\u0004\uffff\uffff\u0000\u001f)\u0001\u0000\u0000\u0000 !"+
		"\u0005\u0002\u0000\u0000!\"\u0003\b\u0004\u0000\"#\u0006\u0004\uffff\uffff"+
		"\u0000#)\u0001\u0000\u0000\u0000$%\u0005\u0001\u0000\u0000%)\u0006\u0004"+
		"\uffff\uffff\u0000&\'\u0005\u0002\u0000\u0000\')\u0006\u0004\uffff\uffff"+
		"\u0000(\u001c\u0001\u0000\u0000\u0000( \u0001\u0000\u0000\u0000($\u0001"+
		"\u0000\u0000\u0000(&\u0001\u0000\u0000\u0000)\t\u0001\u0000\u0000\u0000"+
		"\u0002\u0016(";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}