/**
 * 
 */
package br.com.caiopaulucci.ast.test;

import java.io.File;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import antlr.collections.AST;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaLexer;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser;
import br.com.caiopaulucci.ast.antlr.javaInterpreter.JavaParser.CompilationUnitContext;
import br.com.caiopaulucci.ast.antlr.parseListeners.JavaParseListener;
import br.com.caiopaulucci.ast.antlr.visitors.VisitorTest;
import br.com.caiopaulucci.ast.descobridores.CaminhadorDePasta;
import br.com.caiopaulucci.ast.interfaces.InterfaceDeAcao;
import br.com.caiopaulucci.ast.util.FileUtils;

/**
 * @author Caio Paulucci
 *
 */
public class TestCaminhadorAntlr {
	
	public static void main(String[] args) {
		new CaminhadorDePasta().caminhar(new InterfaceDeAcao() {
			
			@Override 
			public void executarAcao(File arquivo,String rootPath) {
				try {
					System.out.println("Arquivo : "+arquivo.getName()+" ----------------------------");
					String sourceOriginal = FileUtils.readFile(arquivo.getAbsolutePath()).toString();
					JavaLexer lexer = new JavaLexer(new ANTLRFileStream(arquivo.getAbsolutePath()));
					CommonTokenStream tokens = new CommonTokenStream(lexer);
					JavaParser parser = new JavaParser(tokens);
					//parser.setTrace(true);
					//parser.addParseListener(new JavaParseListener(parser));
					parser.setBuildParseTree(true);
					parser.setTrimParseTree(false);
					parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
					
					//ParseTreeWalker.DEFAULT.walk(new JavaParseListener(parser), parser.compilationUnit());
					
					CompilationUnitContext t = parser.compilationUnit();
					VisitorTest visitor = new VisitorTest(parser);
					visitor.visitCompilationUnit(t);
					//t.accept(visitor);
					//t.inspect(parser);
					//System.out.println(t.toStringTree(parser));
					System.out.println("------------------------------------------------------------");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
