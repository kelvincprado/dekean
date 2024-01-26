package br.com.trabalho.IsiLanguage.main;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import br.com.trabalho.IsiLanguage.exceptions.IsiSemanticException;
import br.com.trabalho.IsiLanguage.parser.IsiLangLexer;
import br.com.trabalho.IsiLanguage.parser.IsiLangParser;

public class MainClass {
	public static void main(String[] args) {
		try { 
			IsiLangLexer lexer;
			IsiLangParser parser;
			
			lexer = new IsiLangLexer(CharStreams.fromFileName("codigo.txt"));
			CommonTokenStream tokenStream = new CommonTokenStream(lexer);
			parser = new IsiLangParser(tokenStream);
			parser.prog();
			System.out.println("Compilou");
			parser.exibeComandos();
			//parser.generateCode();
			parser.exibeWarnings();
			
		}
		catch(IsiSemanticException ex) {
			System.err.println("Erro Semantico - " + ex.getMessage());
		}
		catch(Exception ex) {
			System.err.println("ERROR " + ex.getMessage());
		}
	}
}

