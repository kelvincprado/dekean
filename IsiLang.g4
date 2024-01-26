grammar IsiLang;

@header{
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
}

@members{
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
}

prog	: 	'<?dekean' decl bloco  '?>'
			{  	program.setVarTable(symbolTable);
				program.setComandos(stack.pop());
			}
		;
		
decl    :  (declaravar)+ ;
        
declaravar	:	tipo ID  {
					_varName = _input.LT(-1).getText();
					_varValue = null;
					symbol = new IsiVariable(_varName, _tipo, _varValue);
					if (!symbolTable.exists(_varName)){
						symbolTable.add(symbol);	
					}
					else{
						throw new IsiSemanticException("Símbolo "+_varName+" já declarado");
					}
				}
				(  
					VIR 
					ID {
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
				)* 
			SC
		;
	
tipo   	: 'numero' 	{ _tipo = IsiVariable.NUMBER;  }
		| 'texto'  	{ _tipo = IsiVariable.TEXT;  }
		| 'logico'  { _tipo = IsiVariable.LOGICO;  }
		;
        
bloco	:	{ 	curThread = new ArrayList<AbstractCommand>(); 
				stack.push(curThread);  
			}
			(cmd)+
		;
		
cmd		:  cmdleitura 
		|  decl
		|  cmdescrita 
		|  cmdattrib
		|  cmdselecao
		
		|  cmdloop  
		;
		
cmdleitura	:	'leia'
				AP
				ID 
				{ 
					verificaID(_input.LT(-1).getText());
					_readID = _input.LT(-1).getText();
                } 
				FP 
				SC 				
				{
					IsiVariable var = (IsiVariable)symbolTable.get(_readID);
					CommandLeitura cmd = new CommandLeitura(_readID, var);
					stack.peek().add(cmd);
					var.setAttributed(true);
				}   
			;
			
cmdescrita	:	'escreva' 
				AP 
				//ID 
				//{ verificaID(_input.LT(-1).getText());
				//	_writeID = _input.LT(-1).getText();
				//} 
				texto 
				FP 
				SC
				//{
				//	CommandEscrita cmd = new CommandEscrita(_writeID);
				//	stack.peek().add(cmd);
				//	IsiVariable var = (IsiVariable)symbolTable.get(_writeID);
				//	var.setAttributed(true);
				//}
				;
			
texto	:	(TEXT
			{		
				_varValue = _input.LT(-1).getText();
				_varValue = _varValue.replaceAll( "\"", "");             //Retira todos os caracters "~" da String
				//System.out.print(_varValue);
			}
			| ID
			{ 
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
			)*
			;
			
			
cmdattrib	: 
				ID 	{ verificaID(_input.LT(-1).getText());
						_exprID = _input.LT(-1).getText();
					} 
				ATTR { _exprContent = ""; } 
				expr
				SC
				
				{	
					IsiVariable var = (IsiVariable)symbolTable.get(_exprID);
					
					if (var.getType() != _exprType) {
						throw new IsiSemanticException(String.format("Variable %s expects a %s but received a %s instead", var.getName(), typeDict[var.getType()], typeDict[_exprType]));
					}
					CommandAtribuicao cmd = new CommandAtribuicao(_exprID, _exprContent);
					stack.peek().add(cmd);
					var.setAttributed(true);
				}
			;
			
			
cmdselecao  :  'se' AP
                    (ID | NUMBER)    { _exprDecision = _input.LT(-1).getText(); }
                    OPREL { _exprDecision += _input.LT(-1).getText(); }
                    (ID | NUMBER) {_exprDecision += _input.LT(-1).getText(); }
                    FP 
                    ACH 
                    { 	curThread = new ArrayList<AbstractCommand>(); 
						stack.push(curThread);
                    }
                    (cmd)+ 
                    
                    FCH 
                    {
						listaTrue = stack.pop();	
                    } 
					('senao' 
					ACH
					{
						curThread = new ArrayList<AbstractCommand>();
						stack.push(curThread);
					} 
					(cmd+) 
					FCH
					{
						listaFalse = stack.pop();
						CommandDecisao cmd = new CommandDecisao(_exprDecision, listaTrue, listaFalse);
						stack.peek().add(cmd);
					}
				)?
            ;


cmdloop     :     'enquanto' AP 
                expr {_exprWhile = _input.LT(-1).getText();} 
                OPREL {_exprWhile += _input.LT(-1).getText();}
                expr {_exprWhile += _input.LT(-1).getText();}
                FP 
                ACH 
                {     curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
                }
                (cmd)+
				FCH
				{
					listaLoop = stack.pop();
					CommandEnquanto cmd = new CommandEnquanto(_exprWhile, listaLoop);
					stack.peek().add(cmd);
                }

            | 'faca' 
                ACH
                {     curThread = new ArrayList<AbstractCommand>(); 
                    stack.push(curThread);
				}
				(cmd)+ 
				FCH
				'enquanto' AP
				expr {_exprWhile = _input.LT(-1).getText();}
				OPREL{_exprWhile += _input.LT(-1).getText();}
				expr {_exprWhile += _input.LT(-1).getText();}
				FP
				SC
					{   listaLoop = stack.pop();
						CommandFacaEnquanto cmd = new CommandFacaEnquanto(_exprWhile, listaLoop);
						stack.peek().add(cmd);
					}
            ;

expr		:	
			NUMBER
			{
				_exprContent += _input.LT(-1).getText();
				_exprType = 0;
			}
			|
			termo (
				
				OP  { 	_exprContent += _input.LT(-1).getText();
						_exprType = 0;
					}
				termo
				)*
			|
			
				TEXT { _exprContent += _input.LT(-1).getText();
						_exprType = 1;
						}
			|
				LOGICO { _exprContent += _input.LT(-1).getText();
						_exprType = 2;
						}
			;
			
termo		:
			NUMBER
			{
				_exprContent += _input.LT(-1).getText();
				
			}
			|
			ID { verificaID(_input.LT(-1).getText());
				_exprContent += _input.LT(-1).getText();
			} 
			;	
	
AP	: '(';
	
FP	: ')';

SC	: '&';
	
OP	: '+' | '-' | '*' | '/';
	
ATTR: ':=';
	
VIR : ',';
	
ACH : '[';
	
FCH : ']';

OPREL 	: '>' | '<' | '>=' | '<=' | '==' | '!=';

ID		: [a-z]([a-z] | [A-Z] | [0-9])*;
	
NUMBER	: [0-9]+ ('.' [0-9]+)?;
		
WS		: (' ' | '\t' | '\n' | '\r' | '%'([a-z] | [A-Z] | [0-9] | ';' | ' ' | '\t' | '\n' | '=' | '/' | '*' | '-'| '+' | '(' | ')' | '{' | '}' | '>' | '<' | ',')*'%' | '%'([a-z] | [A-Z] | [0-9] | ';' | ' ' | '=' | '/'| '*' | '-'| '+' | '(' | ')' | '{' | '}' | '>' | '<' | '\t' | '\n' | ',')*'\r') -> skip;


//TEXT: ["]([a-z] | [A-Z] | [0-9] | ' ')*["];
TEXT: '"' ([a-z] | [A-Z] | [0-9] | ' ' | ',' | ':' | '!' |'?')* '"';

LOGICO : 'Verdadeiro' | 'Falso';
