package br.com.trabalho.IsiLanguage.ast;

public class CommandAtribuicao extends AbstractCommand{
	private String id;
	private String expr;
	
	public CommandAtribuicao(String id, String expr) {
		this.id = id;
		this.expr = expr;
	}
	
	@Override
	public String generateJavaCode() {
		// TODO Auto-generated method stub
		String finalExpr=this.expr;
		if (this.expr.equals("Verdade")) {
			finalExpr="true";
		} else if (this.expr.equals("Falso")) {
			finalExpr="false";
		}
		return id + " = "+finalExpr+";";
	}

	@Override
	public String toString() {
		return "CommandAtribuicao [id=" + id + ", expr=" + expr + "]";
	}
}
