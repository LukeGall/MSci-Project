// Generated from /home/luke/Level-5-Project/Mungo/mungo-tools/stmungo/src/main/antlr/Scribble.g4 by ANTLR 4.8

package genantlr;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ScribbleLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, MODULEKW=11, IMPORTKW=12, TYPEKW=13, PROTOCOLKW=14, GLOBALKW=15, 
		EXPLICITKW=16, AUXKW=17, LOCALKW=18, ROLEKW=19, SELFKW=20, SIGKW=21, INSTANTIATESKW=22, 
		ASKW=23, FROMKW=24, TOKW=25, CHOICEKW=26, ATKW=27, ORKW=28, RECKW=29, 
		CONTINUEKW=30, PARKW=31, ANDKW=32, INTERRUPTIBLEKW=33, WITHKW=34, BYKW=35, 
		THROWSKW=36, CATCHESKW=37, DOKW=38, CHOICENAMEKW=39, CONNECTKW=40, DISCONNECTKW=41, 
		ACCEPTKW=42, WRAPKW=43, WHITESPACE=44, COMMENT=45, LINE_COMMENT=46, EXTIDENTIFIER=47, 
		INT=48, FLOAT=49, BOOL=50, IDENTIFIER=51, COMMA=52, COLON=53, SEMICOLON=54, 
		CURLYLEFT=55, CURLYRIGHT=56, ROUNDLEFT=57, ROUNDRIGHT=58, ANGLELEFT=59, 
		ANGLERIGHT=60, AT=61;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "MODULEKW", "IMPORTKW", "TYPEKW", "PROTOCOLKW", "GLOBALKW", "EXPLICITKW", 
			"AUXKW", "LOCALKW", "ROLEKW", "SELFKW", "SIGKW", "INSTANTIATESKW", "ASKW", 
			"FROMKW", "TOKW", "CHOICEKW", "ATKW", "ORKW", "RECKW", "CONTINUEKW", 
			"PARKW", "ANDKW", "INTERRUPTIBLEKW", "WITHKW", "BYKW", "THROWSKW", "CATCHESKW", 
			"DOKW", "CHOICENAMEKW", "CONNECTKW", "DISCONNECTKW", "ACCEPTKW", "WRAPKW", 
			"WHITESPACE", "COMMENT", "LINE_COMMENT", "SYMBOL", "EXTIDENTIFIER", "LETTER", 
			"DIGIT", "UNDERSCORE", "INT", "FLOAT", "BOOL", "IDENTIFIER", "COMMA", 
			"COLON", "SEMICOLON", "CURLYLEFT", "CURLYRIGHT", "ROUNDLEFT", "ROUNDRIGHT", 
			"ANGLELEFT", "ANGLERIGHT", "AT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'.'", "'['", "']'", "'='", "'<='", "'>='", "'!'", "'&'", "'|'", 
			"'reset'", "'module'", "'import'", "'type'", "'protocol'", "'global'", 
			"'explicit'", "'aux'", "'local'", "'role'", "'self'", "'sig'", "'instantiates'", 
			"'as'", "'from'", "'to'", "'choice'", "'at'", "'or'", "'rec'", "'continue'", 
			"'par'", "'and'", "'interruptible'", "'with'", "'by'", "'throws'", "'catches'", 
			"'do'", "'choicename'", "'connect'", "'disconnect'", "'accept'", "'wrap'", 
			null, null, null, null, null, null, null, null, "','", "':'", "';'", 
			"'{'", "'}'", "'('", "')'", "'<'", "'>'", "'@'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, "MODULEKW", 
			"IMPORTKW", "TYPEKW", "PROTOCOLKW", "GLOBALKW", "EXPLICITKW", "AUXKW", 
			"LOCALKW", "ROLEKW", "SELFKW", "SIGKW", "INSTANTIATESKW", "ASKW", "FROMKW", 
			"TOKW", "CHOICEKW", "ATKW", "ORKW", "RECKW", "CONTINUEKW", "PARKW", "ANDKW", 
			"INTERRUPTIBLEKW", "WITHKW", "BYKW", "THROWSKW", "CATCHESKW", "DOKW", 
			"CHOICENAMEKW", "CONNECTKW", "DISCONNECTKW", "ACCEPTKW", "WRAPKW", "WHITESPACE", 
			"COMMENT", "LINE_COMMENT", "EXTIDENTIFIER", "INT", "FLOAT", "BOOL", "IDENTIFIER", 
			"COMMA", "COLON", "SEMICOLON", "CURLYLEFT", "CURLYRIGHT", "ROUNDLEFT", 
			"ROUNDRIGHT", "ANGLELEFT", "ANGLERIGHT", "AT"
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


	public ScribbleLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Scribble.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	@Override
	public void action(RuleContext _localctx, int ruleIndex, int actionIndex) {
		switch (ruleIndex) {
		case 43:
			WHITESPACE_action((RuleContext)_localctx, actionIndex);
			break;
		case 44:
			COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		case 45:
			LINE_COMMENT_action((RuleContext)_localctx, actionIndex);
			break;
		}
	}
	private void WHITESPACE_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 0:
			skip();
			break;
		}
	}
	private void COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 1:
			skip();
			break;
		}
	}
	private void LINE_COMMENT_action(RuleContext _localctx, int actionIndex) {
		switch (actionIndex) {
		case 2:
			skip();
			break;
		}
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2?\u01e6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34"+
		"\3\34\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3 \3 \3 \3 \3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3$\3$\3$\3%\3%\3%\3%\3%\3%\3"+
		"%\3&\3&\3&\3&\3&\3&\3&\3&\3\'\3\'\3\'\3(\3(\3(\3(\3(\3(\3(\3(\3(\3(\3"+
		"(\3)\3)\3)\3)\3)\3)\3)\3)\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3*\3+\3+\3+\3"+
		"+\3+\3+\3+\3,\3,\3,\3,\3,\3-\6-\u0171\n-\r-\16-\u0172\3-\3-\3.\3.\3.\3"+
		".\7.\u017b\n.\f.\16.\u017e\13.\3.\3.\3.\3.\3.\3/\3/\3/\3/\7/\u0189\n/"+
		"\f/\16/\u018c\13/\3/\5/\u018f\n/\3/\3/\3/\3\60\3\60\5\60\u0196\n\60\3"+
		"\61\3\61\3\61\5\61\u019b\n\61\3\61\3\61\3\61\7\61\u01a0\n\61\f\61\16\61"+
		"\u01a3\13\61\3\61\3\62\3\62\3\63\3\63\3\64\3\64\3\65\6\65\u01ad\n\65\r"+
		"\65\16\65\u01ae\3\66\6\66\u01b2\n\66\r\66\16\66\u01b3\3\66\3\66\6\66\u01b8"+
		"\n\66\r\66\16\66\u01b9\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\3\67\5"+
		"\67\u01c5\n\67\38\38\58\u01c9\n8\38\38\38\78\u01ce\n8\f8\168\u01d1\13"+
		"8\39\39\3:\3:\3;\3;\3<\3<\3=\3=\3>\3>\3?\3?\3@\3@\3A\3A\3B\3B\3\u017c"+
		"\2C\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36"+
		";\37= ?!A\"C#E$G%I&K\'M(O)Q*S+U,W-Y.[/]\60_\2a\61c\2e\2g\2i\62k\63m\64"+
		"o\65q\66s\67u8w9y:{;}<\177=\u0081>\u0083?\3\2\6\5\2\13\f\16\17\"\"\4\2"+
		"\f\f\17\17\f\2##%%((*+\60\61<<AA]_}}\177\177\4\2C\\c|\2\u01f2\2\3\3\2"+
		"\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17"+
		"\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2"+
		"\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3"+
		"\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3"+
		"\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2"+
		"=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3"+
		"\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3\2\2\2\2U\3\2\2"+
		"\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2\2a\3\2\2\2\2i\3\2\2\2\2"+
		"k\3\2\2\2\2m\3\2\2\2\2o\3\2\2\2\2q\3\2\2\2\2s\3\2\2\2\2u\3\2\2\2\2w\3"+
		"\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\2\177\3\2\2\2\2\u0081\3\2\2\2"+
		"\2\u0083\3\2\2\2\3\u0085\3\2\2\2\5\u0087\3\2\2\2\7\u0089\3\2\2\2\t\u008b"+
		"\3\2\2\2\13\u008d\3\2\2\2\r\u0090\3\2\2\2\17\u0093\3\2\2\2\21\u0095\3"+
		"\2\2\2\23\u0097\3\2\2\2\25\u0099\3\2\2\2\27\u009f\3\2\2\2\31\u00a6\3\2"+
		"\2\2\33\u00ad\3\2\2\2\35\u00b2\3\2\2\2\37\u00bb\3\2\2\2!\u00c2\3\2\2\2"+
		"#\u00cb\3\2\2\2%\u00cf\3\2\2\2\'\u00d5\3\2\2\2)\u00da\3\2\2\2+\u00df\3"+
		"\2\2\2-\u00e3\3\2\2\2/\u00f0\3\2\2\2\61\u00f3\3\2\2\2\63\u00f8\3\2\2\2"+
		"\65\u00fb\3\2\2\2\67\u0102\3\2\2\29\u0105\3\2\2\2;\u0108\3\2\2\2=\u010c"+
		"\3\2\2\2?\u0115\3\2\2\2A\u0119\3\2\2\2C\u011d\3\2\2\2E\u012b\3\2\2\2G"+
		"\u0130\3\2\2\2I\u0133\3\2\2\2K\u013a\3\2\2\2M\u0142\3\2\2\2O\u0145\3\2"+
		"\2\2Q\u0150\3\2\2\2S\u0158\3\2\2\2U\u0163\3\2\2\2W\u016a\3\2\2\2Y\u0170"+
		"\3\2\2\2[\u0176\3\2\2\2]\u0184\3\2\2\2_\u0195\3\2\2\2a\u0197\3\2\2\2c"+
		"\u01a5\3\2\2\2e\u01a7\3\2\2\2g\u01a9\3\2\2\2i\u01ac\3\2\2\2k\u01b1\3\2"+
		"\2\2m\u01c4\3\2\2\2o\u01c8\3\2\2\2q\u01d2\3\2\2\2s\u01d4\3\2\2\2u\u01d6"+
		"\3\2\2\2w\u01d8\3\2\2\2y\u01da\3\2\2\2{\u01dc\3\2\2\2}\u01de\3\2\2\2\177"+
		"\u01e0\3\2\2\2\u0081\u01e2\3\2\2\2\u0083\u01e4\3\2\2\2\u0085\u0086\7\60"+
		"\2\2\u0086\4\3\2\2\2\u0087\u0088\7]\2\2\u0088\6\3\2\2\2\u0089\u008a\7"+
		"_\2\2\u008a\b\3\2\2\2\u008b\u008c\7?\2\2\u008c\n\3\2\2\2\u008d\u008e\7"+
		">\2\2\u008e\u008f\7?\2\2\u008f\f\3\2\2\2\u0090\u0091\7@\2\2\u0091\u0092"+
		"\7?\2\2\u0092\16\3\2\2\2\u0093\u0094\7#\2\2\u0094\20\3\2\2\2\u0095\u0096"+
		"\7(\2\2\u0096\22\3\2\2\2\u0097\u0098\7~\2\2\u0098\24\3\2\2\2\u0099\u009a"+
		"\7t\2\2\u009a\u009b\7g\2\2\u009b\u009c\7u\2\2\u009c\u009d\7g\2\2\u009d"+
		"\u009e\7v\2\2\u009e\26\3\2\2\2\u009f\u00a0\7o\2\2\u00a0\u00a1\7q\2\2\u00a1"+
		"\u00a2\7f\2\2\u00a2\u00a3\7w\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7g\2\2"+
		"\u00a5\30\3\2\2\2\u00a6\u00a7\7k\2\2\u00a7\u00a8\7o\2\2\u00a8\u00a9\7"+
		"r\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7t\2\2\u00ab\u00ac\7v\2\2\u00ac\32"+
		"\3\2\2\2\u00ad\u00ae\7v\2\2\u00ae\u00af\7{\2\2\u00af\u00b0\7r\2\2\u00b0"+
		"\u00b1\7g\2\2\u00b1\34\3\2\2\2\u00b2\u00b3\7r\2\2\u00b3\u00b4\7t\2\2\u00b4"+
		"\u00b5\7q\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7q\2\2\u00b7\u00b8\7e\2\2"+
		"\u00b8\u00b9\7q\2\2\u00b9\u00ba\7n\2\2\u00ba\36\3\2\2\2\u00bb\u00bc\7"+
		"i\2\2\u00bc\u00bd\7n\2\2\u00bd\u00be\7q\2\2\u00be\u00bf\7d\2\2\u00bf\u00c0"+
		"\7c\2\2\u00c0\u00c1\7n\2\2\u00c1 \3\2\2\2\u00c2\u00c3\7g\2\2\u00c3\u00c4"+
		"\7z\2\2\u00c4\u00c5\7r\2\2\u00c5\u00c6\7n\2\2\u00c6\u00c7\7k\2\2\u00c7"+
		"\u00c8\7e\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7v\2\2\u00ca\"\3\2\2\2\u00cb"+
		"\u00cc\7c\2\2\u00cc\u00cd\7w\2\2\u00cd\u00ce\7z\2\2\u00ce$\3\2\2\2\u00cf"+
		"\u00d0\7n\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7e\2\2\u00d2\u00d3\7c\2\2"+
		"\u00d3\u00d4\7n\2\2\u00d4&\3\2\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7\7q\2"+
		"\2\u00d7\u00d8\7n\2\2\u00d8\u00d9\7g\2\2\u00d9(\3\2\2\2\u00da\u00db\7"+
		"u\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7n\2\2\u00dd\u00de\7h\2\2\u00de*"+
		"\3\2\2\2\u00df\u00e0\7u\2\2\u00e0\u00e1\7k\2\2\u00e1\u00e2\7i\2\2\u00e2"+
		",\3\2\2\2\u00e3\u00e4\7k\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6\7u\2\2\u00e6"+
		"\u00e7\7v\2\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7p\2\2\u00e9\u00ea\7v\2\2"+
		"\u00ea\u00eb\7k\2\2\u00eb\u00ec\7c\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee"+
		"\7g\2\2\u00ee\u00ef\7u\2\2\u00ef.\3\2\2\2\u00f0\u00f1\7c\2\2\u00f1\u00f2"+
		"\7u\2\2\u00f2\60\3\2\2\2\u00f3\u00f4\7h\2\2\u00f4\u00f5\7t\2\2\u00f5\u00f6"+
		"\7q\2\2\u00f6\u00f7\7o\2\2\u00f7\62\3\2\2\2\u00f8\u00f9\7v\2\2\u00f9\u00fa"+
		"\7q\2\2\u00fa\64\3\2\2\2\u00fb\u00fc\7e\2\2\u00fc\u00fd\7j\2\2\u00fd\u00fe"+
		"\7q\2\2\u00fe\u00ff\7k\2\2\u00ff\u0100\7e\2\2\u0100\u0101\7g\2\2\u0101"+
		"\66\3\2\2\2\u0102\u0103\7c\2\2\u0103\u0104\7v\2\2\u01048\3\2\2\2\u0105"+
		"\u0106\7q\2\2\u0106\u0107\7t\2\2\u0107:\3\2\2\2\u0108\u0109\7t\2\2\u0109"+
		"\u010a\7g\2\2\u010a\u010b\7e\2\2\u010b<\3\2\2\2\u010c\u010d\7e\2\2\u010d"+
		"\u010e\7q\2\2\u010e\u010f\7p\2\2\u010f\u0110\7v\2\2\u0110\u0111\7k\2\2"+
		"\u0111\u0112\7p\2\2\u0112\u0113\7w\2\2\u0113\u0114\7g\2\2\u0114>\3\2\2"+
		"\2\u0115\u0116\7r\2\2\u0116\u0117\7c\2\2\u0117\u0118\7t\2\2\u0118@\3\2"+
		"\2\2\u0119\u011a\7c\2\2\u011a\u011b\7p\2\2\u011b\u011c\7f\2\2\u011cB\3"+
		"\2\2\2\u011d\u011e\7k\2\2\u011e\u011f\7p\2\2\u011f\u0120\7v\2\2\u0120"+
		"\u0121\7g\2\2\u0121\u0122\7t\2\2\u0122\u0123\7t\2\2\u0123\u0124\7w\2\2"+
		"\u0124\u0125\7r\2\2\u0125\u0126\7v\2\2\u0126\u0127\7k\2\2\u0127\u0128"+
		"\7d\2\2\u0128\u0129\7n\2\2\u0129\u012a\7g\2\2\u012aD\3\2\2\2\u012b\u012c"+
		"\7y\2\2\u012c\u012d\7k\2\2\u012d\u012e\7v\2\2\u012e\u012f\7j\2\2\u012f"+
		"F\3\2\2\2\u0130\u0131\7d\2\2\u0131\u0132\7{\2\2\u0132H\3\2\2\2\u0133\u0134"+
		"\7v\2\2\u0134\u0135\7j\2\2\u0135\u0136\7t\2\2\u0136\u0137\7q\2\2\u0137"+
		"\u0138\7y\2\2\u0138\u0139\7u\2\2\u0139J\3\2\2\2\u013a\u013b\7e\2\2\u013b"+
		"\u013c\7c\2\2\u013c\u013d\7v\2\2\u013d\u013e\7e\2\2\u013e\u013f\7j\2\2"+
		"\u013f\u0140\7g\2\2\u0140\u0141\7u\2\2\u0141L\3\2\2\2\u0142\u0143\7f\2"+
		"\2\u0143\u0144\7q\2\2\u0144N\3\2\2\2\u0145\u0146\7e\2\2\u0146\u0147\7"+
		"j\2\2\u0147\u0148\7q\2\2\u0148\u0149\7k\2\2\u0149\u014a\7e\2\2\u014a\u014b"+
		"\7g\2\2\u014b\u014c\7p\2\2\u014c\u014d\7c\2\2\u014d\u014e\7o\2\2\u014e"+
		"\u014f\7g\2\2\u014fP\3\2\2\2\u0150\u0151\7e\2\2\u0151\u0152\7q\2\2\u0152"+
		"\u0153\7p\2\2\u0153\u0154\7p\2\2\u0154\u0155\7g\2\2\u0155\u0156\7e\2\2"+
		"\u0156\u0157\7v\2\2\u0157R\3\2\2\2\u0158\u0159\7f\2\2\u0159\u015a\7k\2"+
		"\2\u015a\u015b\7u\2\2\u015b\u015c\7e\2\2\u015c\u015d\7q\2\2\u015d\u015e"+
		"\7p\2\2\u015e\u015f\7p\2\2\u015f\u0160\7g\2\2\u0160\u0161\7e\2\2\u0161"+
		"\u0162\7v\2\2\u0162T\3\2\2\2\u0163\u0164\7c\2\2\u0164\u0165\7e\2\2\u0165"+
		"\u0166\7e\2\2\u0166\u0167\7g\2\2\u0167\u0168\7r\2\2\u0168\u0169\7v\2\2"+
		"\u0169V\3\2\2\2\u016a\u016b\7y\2\2\u016b\u016c\7t\2\2\u016c\u016d\7c\2"+
		"\2\u016d\u016e\7r\2\2\u016eX\3\2\2\2\u016f\u0171\t\2\2\2\u0170\u016f\3"+
		"\2\2\2\u0171\u0172\3\2\2\2\u0172\u0170\3\2\2\2\u0172\u0173\3\2\2\2\u0173"+
		"\u0174\3\2\2\2\u0174\u0175\b-\2\2\u0175Z\3\2\2\2\u0176\u0177\7\61\2\2"+
		"\u0177\u0178\7,\2\2\u0178\u017c\3\2\2\2\u0179\u017b\13\2\2\2\u017a\u0179"+
		"\3\2\2\2\u017b\u017e\3\2\2\2\u017c\u017d\3\2\2\2\u017c\u017a\3\2\2\2\u017d"+
		"\u017f\3\2\2\2\u017e\u017c\3\2\2\2\u017f\u0180\7,\2\2\u0180\u0181\7\61"+
		"\2\2\u0181\u0182\3\2\2\2\u0182\u0183\b.\3\2\u0183\\\3\2\2\2\u0184\u0185"+
		"\7\61\2\2\u0185\u0186\7\61\2\2\u0186\u018a\3\2\2\2\u0187\u0189\n\3\2\2"+
		"\u0188\u0187\3\2\2\2\u0189\u018c\3\2\2\2\u018a\u0188\3\2\2\2\u018a\u018b"+
		"\3\2\2\2\u018b\u018e\3\2\2\2\u018c\u018a\3\2\2\2\u018d\u018f\7\17\2\2"+
		"\u018e\u018d\3\2\2\2\u018e\u018f\3\2\2\2\u018f\u0190\3\2\2\2\u0190\u0191"+
		"\7\f\2\2\u0191\u0192\b/\4\2\u0192^\3\2\2\2\u0193\u0196\t\4\2\2\u0194\u0196"+
		"\5g\64\2\u0195\u0193\3\2\2\2\u0195\u0194\3\2\2\2\u0196`\3\2\2\2\u0197"+
		"\u019a\3\2\2\2\u0198\u019b\5c\62\2\u0199\u019b\5g\64\2\u019a\u0198\3\2"+
		"\2\2\u019a\u0199\3\2\2\2\u019b\u01a1\3\2\2\2\u019c\u01a0\5c\62\2\u019d"+
		"\u01a0\5e\63\2\u019e\u01a0\5_\60\2\u019f\u019c\3\2\2\2\u019f\u019d\3\2"+
		"\2\2\u019f\u019e\3\2\2\2\u01a0\u01a3\3\2\2\2\u01a1\u019f\3\2\2\2\u01a1"+
		"\u01a2\3\2\2\2\u01a2\u01a4\3\2\2\2\u01a3\u01a1\3\2\2\2\u01a4b\3\2\2\2"+
		"\u01a5\u01a6\t\5\2\2\u01a6d\3\2\2\2\u01a7\u01a8\4\62;\2\u01a8f\3\2\2\2"+
		"\u01a9\u01aa\7a\2\2\u01aah\3\2\2\2\u01ab\u01ad\5e\63\2\u01ac\u01ab\3\2"+
		"\2\2\u01ad\u01ae\3\2\2\2\u01ae\u01ac\3\2\2\2\u01ae\u01af\3\2\2\2\u01af"+
		"j\3\2\2\2\u01b0\u01b2\5e\63\2\u01b1\u01b0\3\2\2\2\u01b2\u01b3\3\2\2\2"+
		"\u01b3\u01b1\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4\u01b5\3\2\2\2\u01b5\u01b7"+
		"\7\60\2\2\u01b6\u01b8\5e\63\2\u01b7\u01b6\3\2\2\2\u01b8\u01b9\3\2\2\2"+
		"\u01b9\u01b7\3\2\2\2\u01b9\u01ba\3\2\2\2\u01bal\3\2\2\2\u01bb\u01bc\7"+
		"h\2\2\u01bc\u01bd\7c\2\2\u01bd\u01be\7n\2\2\u01be\u01bf\7u\2\2\u01bf\u01c5"+
		"\7g\2\2\u01c0\u01c1\7v\2\2\u01c1\u01c2\7t\2\2\u01c2\u01c3\7w\2\2\u01c3"+
		"\u01c5\7g\2\2\u01c4\u01bb\3\2\2\2\u01c4\u01c0\3\2\2\2\u01c5n\3\2\2\2\u01c6"+
		"\u01c9\5c\62\2\u01c7\u01c9\5g\64\2\u01c8\u01c6\3\2\2\2\u01c8\u01c7\3\2"+
		"\2\2\u01c9\u01cf\3\2\2\2\u01ca\u01ce\5c\62\2\u01cb\u01ce\5e\63\2\u01cc"+
		"\u01ce\5g\64\2\u01cd\u01ca\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01cc\3\2"+
		"\2\2\u01ce\u01d1\3\2\2\2\u01cf\u01cd\3\2\2\2\u01cf\u01d0\3\2\2\2\u01d0"+
		"p\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d2\u01d3\7.\2\2\u01d3r\3\2\2\2\u01d4"+
		"\u01d5\7<\2\2\u01d5t\3\2\2\2\u01d6\u01d7\7=\2\2\u01d7v\3\2\2\2\u01d8\u01d9"+
		"\7}\2\2\u01d9x\3\2\2\2\u01da\u01db\7\177\2\2\u01dbz\3\2\2\2\u01dc\u01dd"+
		"\7*\2\2\u01dd|\3\2\2\2\u01de\u01df\7+\2\2\u01df~\3\2\2\2\u01e0\u01e1\7"+
		">\2\2\u01e1\u0080\3\2\2\2\u01e2\u01e3\7@\2\2\u01e3\u0082\3\2\2\2\u01e4"+
		"\u01e5\7B\2\2\u01e5\u0084\3\2\2\2\22\2\u0172\u017c\u018a\u018e\u0195\u019a"+
		"\u019f\u01a1\u01ae\u01b3\u01b9\u01c4\u01c8\u01cd\u01cf\5\3-\2\3.\3\3/"+
		"\4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}