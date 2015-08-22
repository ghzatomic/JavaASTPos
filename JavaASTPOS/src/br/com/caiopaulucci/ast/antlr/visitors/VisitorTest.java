package br.com.caiopaulucci.ast.antlr.visitors;

import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaBaseListener;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaBaseVisitor;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.CompilationUnitContext;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.MemberDeclarationContext;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.MethodDeclarationContext;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.TypeDeclarationContext;

public class VisitorTest extends JavaBaseVisitor<Boolean>{
	
	
	protected JavaParser parser = null;
	
	public VisitorTest(JavaParser parser) {
		this.parser = parser;
	}
	
	@Override
	public Boolean visit(ParseTree tree) {
		/*if (tree instanceof CompilationUnitContext){
			CompilationUnitContext arvore = (CompilationUnitContext) tree;
			System.out.println(parser.getTokenStream().getText(arvore.getChild(1).));
		}*/
		
		//System.out.println(tree.toStringTree());
		return super.visit(tree);
	}
	
	@Override
	public Boolean visitCompilationUnit(CompilationUnitContext ctx) {
		TokenStream ts = parser.getTokenStream();
		for (TypeDeclarationContext tipo : ctx.typeDeclaration()) {
			System.out.println(ts.getText(tipo.start,tipo.stop));
		}
		System.out.println(ts.getText(ctx.packageDeclaration().getSourceInterval()));
		return super.visitCompilationUnit(ctx);
	}

	@Override
	public Boolean visitMethodDeclaration(MethodDeclarationContext ctx) {
		System.out.println(ctx.getText());
		return super.visitMethodDeclaration(ctx);
	}
	
}
