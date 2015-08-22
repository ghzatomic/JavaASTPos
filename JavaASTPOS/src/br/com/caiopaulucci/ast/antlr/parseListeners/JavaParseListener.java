package br.com.caiopaulucci.ast.antlr.parseListeners;

import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.RuleNode;

import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaBaseListener;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.MethodDeclarationContext;

public class JavaParseListener extends JavaBaseListener {

	protected JavaParser parser = null;
	
	public JavaParseListener(JavaParser parser) {
		this.parser = parser;
	}
	
	@Override
	public void exitMethodDeclaration(MethodDeclarationContext ctx) {
		//System.out.println(ctx.methodBody());
		 String fullSignature = 
				 parser.getTokenStream().getText(ctx);
		 System.out.println(fullSignature);
		super.exitMethodDeclaration(ctx);
	}
	
}
