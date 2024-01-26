// Generated from IsiLang.g4 by ANTLR 4.7.1
package br.com.trabalho.IsiLanguage.parser;

	import br.com.trabalho.IsiLanguage.datastructures.IsiSymbol;
	import br.com.trabalho.IsiLanguage.datastructures.IsiVariable;
	import br.com.trabalho.IsiLanguage.datastructures.IsiSymbolTable;
	import br.com.trabalho.IsiLanguage.exceptions.IsiSemanticException;
	import br.com.trabalho.IsiLanguage.ast.IsiProgram;
	import br.com.trabalho.IsiLanguage.ast.AbstractCommand;
	import br.com.trabalho.IsiLanguage.ast.CommandLeitura;
	import br.com.trabalho.IsiLanguage.ast.CommandEscrita;
	import br.com.trabalho.IsiLanguage.ast.CommandEnquanto;
	import br.com.trabalho.IsiLanguage.ast.CommandAtribuicao;
	import br.com.trabalho.IsiLanguage.ast.CommandDecisao;
	import br.com.trabalho.IsiLanguage.ast.CommandFacaEnquanto;
	import java.util.ArrayList;
	import java.util.Stack;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class IsiLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, AP=12, FP=13, SC=14, OP=15, ATTR=16, VIR=17, ACH=18, 
		FCH=19, OPREL=20, ID=21, NUMBER=22, WS=23, TEXT=24, LOGICO=25;
	public static final int
		RULE_prog = 0, RULE_decl = 1, RULE_declaravar = 2, RULE_tipo = 3, RULE_bloco = 4, 
		RULE_cmd = 5, RULE_cmdleitura = 6, RULE_cmdescrita = 7, RULE_texto = 8, 
		RULE_cmdattrib = 9, RULE_cmdselecao = 10, RULE_cmdloop = 11, RULE_expr = 12, 
		RULE_termo = 13;
	public static final String[] ruleNames = {
		"prog", "decl", "declaravar", "tipo", "bloco", "cmd", "cmdleitura", "cmdescrita", 
		"texto", "cmdattrib", "cmdselecao", "cmdloop", "expr", "termo"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'<?dekean'", "'?>'", "'numero'", "'texto'", "'logico'", "'leia'", 
		"'escreva'", "'se'", "'senao'", "'enquanto'", "'faca'", "'('", "')'", 
		"'&'", null, "':='", "','", "'['", "']'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"AP", "FP", "SC", "OP", "ATTR", "VIR", "ACH", "FCH", "OPREL", "ID", "NUMBER", 
		"WS", "TEXT", "LOGICO"
	};
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
	public String getGrammarFileName() { return "IsiLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


		private String chave;
		private int _tipo;
		private String _varName;
		private String _varValue;
		private IsiSymbolTable symbolTable = new IsiSymbolTable();
		private IsiSymbol symbol;
		private IsiProgram program = new IsiProgram();
		private ArrayList<AbstractCommand> curThread;
		private Stack<ArrayList<AbstractCommand>> stack = new Stack<ArrayList<AbstractCommand>>();
		private String _readID;
		private String _writeID;
		private String _exprID;
		private String _exprContent;
		private String _exprDecision;
		private String _exprWhile;
		private int _exprType;
		private ArrayList<AbstractCommand> listaTrue;
		private ArrayList<AbstractCommand> listaFalse;
		private ArrayList<AbstractCommand> commandEnq;
		private ArrayList<AbstractCommand> listaLoop;

		private String[] typeDict = new String[] {"numero", "texto", "logico"};
		
		public void verificaID(String id){
			if (!symbolTable.exists(id)){
				throw new IsiSemanticException("Simbolo "+id+" não declarado");
			}
		}
		
		public void exibeComandos(){
			for (AbstractCommand c: program.getComandos()){
				System.out.println(c);
			}
		}

		public void exibeWarnings(){ 
			for (IsiSymbol symbol: symbolTable.getAll()) {
				if(symbol instanceof IsiVariable && ((IsiVariable)symbol).getAttributed()==false) {
					System.out.println(String.format("Variável %s declarada mas nunca usada", symbol.getName()));
				}
			}
		}
		
		public void generateCode(){
			program.generateJavaCode();
		}

	public IsiLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public BlocoContext bloco() {
			return getRuleContext(BlocoContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitProg(this);
		}
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(28);
			match(T__0);
			setState(29);
			decl();
			setState(30);
			bloco();
			setState(31);
			match(T__1);
			  	program.setVarTable(symbolTable);
							program.setComandos(stack.pop());
						
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

	public static class DeclContext extends ParserRuleContext {
		public List<DeclaravarContext> declaravar() {
			return getRuleContexts(DeclaravarContext.class);
		}
		public DeclaravarContext declaravar(int i) {
			return getRuleContext(DeclaravarContext.class,i);
		}
		public DeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_decl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDecl(this);
		}
	}

	public final DeclContext decl() throws RecognitionException {
		DeclContext _localctx = new DeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_decl);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(35); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(34);
					declaravar();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(37); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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

	public static class DeclaravarContext extends ParserRuleContext {
		public TipoContext tipo() {
			return getRuleContext(TipoContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public List<TerminalNode> VIR() { return getTokens(IsiLangParser.VIR); }
		public TerminalNode VIR(int i) {
			return getToken(IsiLangParser.VIR, i);
		}
		public DeclaravarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaravar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterDeclaravar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitDeclaravar(this);
		}
	}

	public final DeclaravarContext declaravar() throws RecognitionException {
		DeclaravarContext _localctx = new DeclaravarContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declaravar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			tipo();
			setState(40);
			match(ID);

								_varName = _input.LT(-1).getText();
								_varValue = null;
								symbol = new IsiVariable(_varName, _tipo, _varValue);
								if (!symbolTable.exists(_varName)){
									symbolTable.add(symbol);	
								}
								else{
									throw new IsiSemanticException("Símbolo "+_varName+" já declarado");
								}
							
			setState(47);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VIR) {
				{
				{
				setState(42);
				match(VIR);
				setState(43);
				match(ID);

										_varName = _input.LT(-1).getText();
										_varValue = null;
										symbol = new IsiVariable(_varName, _tipo, _varValue);
										if (!symbolTable.exists(_varName)){
											symbolTable.add(symbol);	
										}
										else{
											throw new IsiSemanticException("Symbol "+_varName+" já declarado");
										}
									
				}
				}
				setState(49);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(50);
			match(SC);
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

	public static class TipoContext extends ParserRuleContext {
		public TipoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tipo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTipo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTipo(this);
		}
	}

	public final TipoContext tipo() throws RecognitionException {
		TipoContext _localctx = new TipoContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_tipo);
		try {
			setState(58);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__2:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				match(T__2);
				 _tipo = IsiVariable.NUMBER;  
				}
				break;
			case T__3:
				enterOuterAlt(_localctx, 2);
				{
				setState(54);
				match(T__3);
				 _tipo = IsiVariable.TEXT;  
				}
				break;
			case T__4:
				enterOuterAlt(_localctx, 3);
				{
				setState(56);
				match(T__4);
				 _tipo = IsiVariable.LOGICO;  
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

	public static class BlocoContext extends ParserRuleContext {
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public BlocoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_bloco; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterBloco(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitBloco(this);
		}
	}

	public final BlocoContext bloco() throws RecognitionException {
		BlocoContext _localctx = new BlocoContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_bloco);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			 	curThread = new ArrayList<AbstractCommand>(); 
							stack.push(curThread);  
						
			setState(62); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(61);
				cmd();
				}
				}
				setState(64); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
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

	public static class CmdContext extends ParserRuleContext {
		public CmdleituraContext cmdleitura() {
			return getRuleContext(CmdleituraContext.class,0);
		}
		public DeclContext decl() {
			return getRuleContext(DeclContext.class,0);
		}
		public CmdescritaContext cmdescrita() {
			return getRuleContext(CmdescritaContext.class,0);
		}
		public CmdattribContext cmdattrib() {
			return getRuleContext(CmdattribContext.class,0);
		}
		public CmdselecaoContext cmdselecao() {
			return getRuleContext(CmdselecaoContext.class,0);
		}
		public CmdloopContext cmdloop() {
			return getRuleContext(CmdloopContext.class,0);
		}
		public CmdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmd; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmd(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmd(this);
		}
	}

	public final CmdContext cmd() throws RecognitionException {
		CmdContext _localctx = new CmdContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_cmd);
		try {
			setState(72);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				cmdleitura();
				}
				break;
			case T__2:
			case T__3:
			case T__4:
				enterOuterAlt(_localctx, 2);
				{
				setState(67);
				decl();
				}
				break;
			case T__6:
				enterOuterAlt(_localctx, 3);
				{
				setState(68);
				cmdescrita();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 4);
				{
				setState(69);
				cmdattrib();
				}
				break;
			case T__7:
				enterOuterAlt(_localctx, 5);
				{
				setState(70);
				cmdselecao();
				}
				break;
			case T__9:
			case T__10:
				enterOuterAlt(_localctx, 6);
				{
				setState(71);
				cmdloop();
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

	public static class CmdleituraContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdleituraContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdleitura; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdleitura(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdleitura(this);
		}
	}

	public final CmdleituraContext cmdleitura() throws RecognitionException {
		CmdleituraContext _localctx = new CmdleituraContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_cmdleitura);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			match(T__5);
			setState(75);
			match(AP);
			setState(76);
			match(ID);
			 
								verificaID(_input.LT(-1).getText());
								_readID = _input.LT(-1).getText();
			                
			setState(78);
			match(FP);
			setState(79);
			match(SC);

								IsiVariable var = (IsiVariable)symbolTable.get(_readID);
								CommandLeitura cmd = new CommandLeitura(_readID, var);
								stack.peek().add(cmd);
								var.setAttributed(true);
							
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

	public static class CmdescritaContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TextoContext texto() {
			return getRuleContext(TextoContext.class,0);
		}
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdescritaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdescrita; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdescrita(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdescrita(this);
		}
	}

	public final CmdescritaContext cmdescrita() throws RecognitionException {
		CmdescritaContext _localctx = new CmdescritaContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cmdescrita);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			match(T__6);
			setState(83);
			match(AP);
			setState(84);
			texto();
			setState(85);
			match(FP);
			setState(86);
			match(SC);
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

	public static class TextoContext extends ParserRuleContext {
		public List<TerminalNode> TEXT() { return getTokens(IsiLangParser.TEXT); }
		public TerminalNode TEXT(int i) {
			return getToken(IsiLangParser.TEXT, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public TextoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_texto; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTexto(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTexto(this);
		}
	}

	public final TextoContext texto() throws RecognitionException {
		TextoContext _localctx = new TextoContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_texto);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID || _la==TEXT) {
				{
				setState(92);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TEXT:
					{
					setState(88);
					match(TEXT);
							
									_varValue = _input.LT(-1).getText();
									_varValue = _varValue.replaceAll( "\"", "");             //Retira todos os caracters "~" da String
									//System.out.print(_varValue);
								
					}
					break;
				case ID:
					{
					setState(90);
					match(ID);
					 
									verificaID(_input.LT(-1).getText());
									_writeID = _input.LT(-1).getText(); //se precisar escrever comente esta linha e descomente as duas a seguir
									_varValue = (symbolTable.get(_input.LT(-1).getText())).toString();
									//System.out.println(_varValue);		
									//verificaID(_input.LT(-1).getText());
									//_writeID = _input.LT(-1).getText();
									//System.out.println(_writeID);
									CommandEscrita cmd = new CommandEscrita(_writeID);
									stack.peek().add(cmd);
									IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
									var.setAttributed(true);
								
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(96);
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

	public static class CmdattribContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TerminalNode ATTR() { return getToken(IsiLangParser.ATTR, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdattribContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdattrib; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdattrib(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdattrib(this);
		}
	}

	public final CmdattribContext cmdattrib() throws RecognitionException {
		CmdattribContext _localctx = new CmdattribContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_cmdattrib);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(97);
			match(ID);
			 verificaID(_input.LT(-1).getText());
									_exprID = _input.LT(-1).getText();
								
			setState(99);
			match(ATTR);
			 _exprContent = ""; 
			setState(101);
			expr();
			setState(102);
			match(SC);
				
								IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
								
								if (var.getType() != _exprType) {
									throw new IsiSemanticException(String.format("Variable %s expects a %s but received a %s instead", var.getName(), typeDict[var.getType()], typeDict[_exprType]));
								}
								CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
								stack.peek().add(cmd);
								var.setAttributed(true);
							
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

	public static class CmdselecaoContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public List<TerminalNode> ACH() { return getTokens(IsiLangParser.ACH); }
		public TerminalNode ACH(int i) {
			return getToken(IsiLangParser.ACH, i);
		}
		public List<TerminalNode> FCH() { return getTokens(IsiLangParser.FCH); }
		public TerminalNode FCH(int i) {
			return getToken(IsiLangParser.FCH, i);
		}
		public List<TerminalNode> ID() { return getTokens(IsiLangParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(IsiLangParser.ID, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(IsiLangParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(IsiLangParser.NUMBER, i);
		}
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public CmdselecaoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdselecao; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdselecao(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdselecao(this);
		}
	}

	public final CmdselecaoContext cmdselecao() throws RecognitionException {
		CmdselecaoContext _localctx = new CmdselecaoContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_cmdselecao);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__7);
			setState(106);
			match(AP);
			setState(107);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			 _exprDecision = _input.LT(-1).getText(); 
			setState(109);
			match(OPREL);
			 _exprDecision += _input.LT(-1).getText(); 
			setState(111);
			_la = _input.LA(1);
			if ( !(_la==ID || _la==NUMBER) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			_exprDecision += _input.LT(-1).getText(); 
			setState(113);
			match(FP);
			setState(114);
			match(ACH);
			 	curThread = new ArrayList<AbstractCommand>(); 
									stack.push(curThread);
			                    
			setState(117); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(116);
				cmd();
				}
				}
				setState(119); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
			setState(121);
			match(FCH);

									listaTrue = stack.pop();	
			                    
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(123);
				match(T__8);
				setState(124);
				match(ACH);

										curThread = new ArrayList<AbstractCommand>();
										stack.push(curThread);
									
				{
				setState(127); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(126);
					cmd();
					}
					}
					setState(129); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				}
				setState(131);
				match(FCH);

										listaFalse = stack.pop();
										CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
										stack.peek().add(cmd);
									
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

	public static class CmdloopContext extends ParserRuleContext {
		public TerminalNode AP() { return getToken(IsiLangParser.AP, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode OPREL() { return getToken(IsiLangParser.OPREL, 0); }
		public TerminalNode FP() { return getToken(IsiLangParser.FP, 0); }
		public TerminalNode ACH() { return getToken(IsiLangParser.ACH, 0); }
		public TerminalNode FCH() { return getToken(IsiLangParser.FCH, 0); }
		public List<CmdContext> cmd() {
			return getRuleContexts(CmdContext.class);
		}
		public CmdContext cmd(int i) {
			return getRuleContext(CmdContext.class,i);
		}
		public TerminalNode SC() { return getToken(IsiLangParser.SC, 0); }
		public CmdloopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cmdloop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterCmdloop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitCmdloop(this);
		}
	}

	public final CmdloopContext cmdloop() throws RecognitionException {
		CmdloopContext _localctx = new CmdloopContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_cmdloop);
		int _la;
		try {
			setState(176);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__9:
				enterOuterAlt(_localctx, 1);
				{
				setState(136);
				match(T__9);
				setState(137);
				match(AP);
				setState(138);
				expr();
				_exprWhile = _input.LT(-1).getText();
				setState(140);
				match(OPREL);
				_exprWhile += _input.LT(-1).getText();
				setState(142);
				expr();
				_exprWhile += _input.LT(-1).getText();
				setState(144);
				match(FP);
				setState(145);
				match(ACH);
				     curThread = new ArrayList<AbstractCommand>(); 
				                    stack.push(curThread);
				                
				setState(148); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(147);
					cmd();
					}
					}
					setState(150); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(152);
				match(FCH);

									listaLoop = stack.pop();
									CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaLoop);
									stack.peek().add(cmd);
				                
				}
				break;
			case T__10:
				enterOuterAlt(_localctx, 2);
				{
				setState(155);
				match(T__10);
				setState(156);
				match(ACH);
				     curThread = new ArrayList<AbstractCommand>(); 
				                    stack.push(curThread);
								
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(158);
					cmd();
					}
					}
					setState(161); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__9) | (1L << T__10) | (1L << ID))) != 0) );
				setState(163);
				match(FCH);
				setState(164);
				match(T__9);
				setState(165);
				match(AP);
				setState(166);
				expr();
				_exprWhile = _input.LT(-1).getText();
				setState(168);
				match(OPREL);
				_exprWhile += _input.LT(-1).getText();
				setState(170);
				expr();
				_exprWhile += _input.LT(-1).getText();
				setState(172);
				match(FP);
				setState(173);
				match(SC);
				   listaLoop = stack.pop();
										CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprWhile, listaLoop);
										stack.peek().add(cmd);
									
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

	public static class ExprContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public List<TermoContext> termo() {
			return getRuleContexts(TermoContext.class);
		}
		public TermoContext termo(int i) {
			return getRuleContext(TermoContext.class,i);
		}
		public List<TerminalNode> OP() { return getTokens(IsiLangParser.OP); }
		public TerminalNode OP(int i) {
			return getToken(IsiLangParser.OP, i);
		}
		public TerminalNode TEXT() { return getToken(IsiLangParser.TEXT, 0); }
		public TerminalNode LOGICO() { return getToken(IsiLangParser.LOGICO, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitExpr(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_expr);
		int _la;
		try {
			setState(193);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(178);
				match(NUMBER);

								_exprContent += _input.LT(-1).getText();
								_exprType = 0;
							
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(180);
				termo();
				setState(186);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OP) {
					{
					{
					setState(181);
					match(OP);
					 	_exprContent += _input.LT(-1).getText();
											_exprType = 0;
										
					setState(183);
					termo();
					}
					}
					setState(188);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(189);
				match(TEXT);
				 _exprContent += _input.LT(-1).getText();
										_exprType = 1;
										
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(191);
				match(LOGICO);
				 _exprContent += _input.LT(-1).getText();
										_exprType = 2;
										
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

	public static class TermoContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(IsiLangParser.NUMBER, 0); }
		public TerminalNode ID() { return getToken(IsiLangParser.ID, 0); }
		public TermoContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_termo; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).enterTermo(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof IsiLangListener ) ((IsiLangListener)listener).exitTermo(this);
		}
	}

	public final TermoContext termo() throws RecognitionException {
		TermoContext _localctx = new TermoContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_termo);
		try {
			setState(199);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				setState(195);
				match(NUMBER);

								_exprContent += _input.LT(-1).getText();
								
							
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(197);
				match(ID);
				 verificaID(_input.LT(-1).getText());
								_exprContent += _input.LT(-1).getText();
							
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\33\u00cc\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\6\3&\n\3\r\3\16\3\'\3\4\3\4\3\4\3\4\3\4\3\4\7\4\60\n\4\f\4\16\4\63\13"+
		"\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\5\5=\n\5\3\6\3\6\6\6A\n\6\r\6\16\6"+
		"B\3\7\3\7\3\7\3\7\3\7\3\7\5\7K\n\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\7\n_\n\n\f\n\16\nb\13\n\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\f\6\fx\n\f\r\f\16\fy\3\f\3\f\3\f\3\f\3\f\3\f\6\f\u0082\n\f\r\f"+
		"\16\f\u0083\3\f\3\f\3\f\5\f\u0089\n\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\6\r\u0097\n\r\r\r\16\r\u0098\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\6\r\u00a2\n\r\r\r\16\r\u00a3\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\5\r\u00b3\n\r\3\16\3\16\3\16\3\16\3\16\3\16\7\16\u00bb"+
		"\n\16\f\16\16\16\u00be\13\16\3\16\3\16\3\16\3\16\5\16\u00c4\n\16\3\17"+
		"\3\17\3\17\3\17\5\17\u00ca\n\17\3\17\2\2\20\2\4\6\b\n\f\16\20\22\24\26"+
		"\30\32\34\2\3\3\2\27\30\2\u00d4\2\36\3\2\2\2\4%\3\2\2\2\6)\3\2\2\2\b<"+
		"\3\2\2\2\n>\3\2\2\2\fJ\3\2\2\2\16L\3\2\2\2\20T\3\2\2\2\22`\3\2\2\2\24"+
		"c\3\2\2\2\26k\3\2\2\2\30\u00b2\3\2\2\2\32\u00c3\3\2\2\2\34\u00c9\3\2\2"+
		"\2\36\37\7\3\2\2\37 \5\4\3\2 !\5\n\6\2!\"\7\4\2\2\"#\b\2\1\2#\3\3\2\2"+
		"\2$&\5\6\4\2%$\3\2\2\2&\'\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2(\5\3\2\2\2)*\5"+
		"\b\5\2*+\7\27\2\2+\61\b\4\1\2,-\7\23\2\2-.\7\27\2\2.\60\b\4\1\2/,\3\2"+
		"\2\2\60\63\3\2\2\2\61/\3\2\2\2\61\62\3\2\2\2\62\64\3\2\2\2\63\61\3\2\2"+
		"\2\64\65\7\20\2\2\65\7\3\2\2\2\66\67\7\5\2\2\67=\b\5\1\289\7\6\2\29=\b"+
		"\5\1\2:;\7\7\2\2;=\b\5\1\2<\66\3\2\2\2<8\3\2\2\2<:\3\2\2\2=\t\3\2\2\2"+
		">@\b\6\1\2?A\5\f\7\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2C\13\3\2\2"+
		"\2DK\5\16\b\2EK\5\4\3\2FK\5\20\t\2GK\5\24\13\2HK\5\26\f\2IK\5\30\r\2J"+
		"D\3\2\2\2JE\3\2\2\2JF\3\2\2\2JG\3\2\2\2JH\3\2\2\2JI\3\2\2\2K\r\3\2\2\2"+
		"LM\7\b\2\2MN\7\16\2\2NO\7\27\2\2OP\b\b\1\2PQ\7\17\2\2QR\7\20\2\2RS\b\b"+
		"\1\2S\17\3\2\2\2TU\7\t\2\2UV\7\16\2\2VW\5\22\n\2WX\7\17\2\2XY\7\20\2\2"+
		"Y\21\3\2\2\2Z[\7\32\2\2[_\b\n\1\2\\]\7\27\2\2]_\b\n\1\2^Z\3\2\2\2^\\\3"+
		"\2\2\2_b\3\2\2\2`^\3\2\2\2`a\3\2\2\2a\23\3\2\2\2b`\3\2\2\2cd\7\27\2\2"+
		"de\b\13\1\2ef\7\22\2\2fg\b\13\1\2gh\5\32\16\2hi\7\20\2\2ij\b\13\1\2j\25"+
		"\3\2\2\2kl\7\n\2\2lm\7\16\2\2mn\t\2\2\2no\b\f\1\2op\7\26\2\2pq\b\f\1\2"+
		"qr\t\2\2\2rs\b\f\1\2st\7\17\2\2tu\7\24\2\2uw\b\f\1\2vx\5\f\7\2wv\3\2\2"+
		"\2xy\3\2\2\2yw\3\2\2\2yz\3\2\2\2z{\3\2\2\2{|\7\25\2\2|\u0088\b\f\1\2}"+
		"~\7\13\2\2~\177\7\24\2\2\177\u0081\b\f\1\2\u0080\u0082\5\f\7\2\u0081\u0080"+
		"\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\3\2\2\2\u0085\u0086\7\25\2\2\u0086\u0087\b\f\1\2\u0087\u0089\3"+
		"\2\2\2\u0088}\3\2\2\2\u0088\u0089\3\2\2\2\u0089\27\3\2\2\2\u008a\u008b"+
		"\7\f\2\2\u008b\u008c\7\16\2\2\u008c\u008d\5\32\16\2\u008d\u008e\b\r\1"+
		"\2\u008e\u008f\7\26\2\2\u008f\u0090\b\r\1\2\u0090\u0091\5\32\16\2\u0091"+
		"\u0092\b\r\1\2\u0092\u0093\7\17\2\2\u0093\u0094\7\24\2\2\u0094\u0096\b"+
		"\r\1\2\u0095\u0097\5\f\7\2\u0096\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098"+
		"\u0096\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\25"+
		"\2\2\u009b\u009c\b\r\1\2\u009c\u00b3\3\2\2\2\u009d\u009e\7\r\2\2\u009e"+
		"\u009f\7\24\2\2\u009f\u00a1\b\r\1\2\u00a0\u00a2\5\f\7\2\u00a1\u00a0\3"+
		"\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4"+
		"\u00a5\3\2\2\2\u00a5\u00a6\7\25\2\2\u00a6\u00a7\7\f\2\2\u00a7\u00a8\7"+
		"\16\2\2\u00a8\u00a9\5\32\16\2\u00a9\u00aa\b\r\1\2\u00aa\u00ab\7\26\2\2"+
		"\u00ab\u00ac\b\r\1\2\u00ac\u00ad\5\32\16\2\u00ad\u00ae\b\r\1\2\u00ae\u00af"+
		"\7\17\2\2\u00af\u00b0\7\20\2\2\u00b0\u00b1\b\r\1\2\u00b1\u00b3\3\2\2\2"+
		"\u00b2\u008a\3\2\2\2\u00b2\u009d\3\2\2\2\u00b3\31\3\2\2\2\u00b4\u00b5"+
		"\7\30\2\2\u00b5\u00c4\b\16\1\2\u00b6\u00bc\5\34\17\2\u00b7\u00b8\7\21"+
		"\2\2\u00b8\u00b9\b\16\1\2\u00b9\u00bb\5\34\17\2\u00ba\u00b7\3\2\2\2\u00bb"+
		"\u00be\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00c4\3\2"+
		"\2\2\u00be\u00bc\3\2\2\2\u00bf\u00c0\7\32\2\2\u00c0\u00c4\b\16\1\2\u00c1"+
		"\u00c2\7\33\2\2\u00c2\u00c4\b\16\1\2\u00c3\u00b4\3\2\2\2\u00c3\u00b6\3"+
		"\2\2\2\u00c3\u00bf\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4\33\3\2\2\2\u00c5"+
		"\u00c6\7\30\2\2\u00c6\u00ca\b\17\1\2\u00c7\u00c8\7\27\2\2\u00c8\u00ca"+
		"\b\17\1\2\u00c9\u00c5\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\35\3\2\2\2\22"+
		"\'\61<BJ^`y\u0083\u0088\u0098\u00a3\u00b2\u00bc\u00c3\u00c9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}