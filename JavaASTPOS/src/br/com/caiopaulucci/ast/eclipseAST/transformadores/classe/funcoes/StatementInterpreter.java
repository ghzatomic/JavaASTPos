/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.List;

import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors.CommentVisitor;
import br.com.caiopaulucci.ast.model.entity.ExpressionStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementType;

/**
 * @author Caio Paulucci
 *
 */
public class StatementInterpreter {
 
	public static StatementEntity transformaStatement(Statement stmt,File originalSource){
		StatementEntity stmtRet = new StatementEntity();
		CompilationUnit compilationUnit = null;
		if (stmt.getRoot() instanceof CompilationUnit){
			compilationUnit = (CompilationUnit) stmt.getRoot();
		}
		if (compilationUnit != null){
			/*int commentPos = compilationUnit.firstLeadingCommentIndex(stmt);
			if (commentPos != -1){
				Comment comment=(Comment) compilationUnit.getCommentList().get(commentPos);
				CommentVisitor commentVisitor = new CommentVisitor(compilationUnit, originalSource,comment,stmt,true);
				comment.accept(commentVisitor);
				stmtRet.setComentariosSuperiores(commentVisitor.getRetornoFull());
			}
			int commentPosEnd = compilationUnit.lastTrailingCommentIndex(stmt);
			if (commentPosEnd != -1){
				Comment comment=(Comment) compilationUnit.getCommentList().get(commentPosEnd);
				CommentVisitor commentVisitor = new CommentVisitor(compilationUnit, originalSource,comment,stmt,false);
				comment.accept(commentVisitor);
				if (commentVisitor.isNoFim()){
					stmtRet.setComentariosFim(commentVisitor.getRetornoFull());
				}else{
					stmtRet.setComentariosPosteriores(commentVisitor.getRetornoFull());
				}
			}*/
		}
		
		StatementType tipo = StatementType.getEnumByClasse(stmt.getClass());
		if (tipo != null){
			if (tipo.equals(StatementType.TRY)){
				stmtRet.setTryStatement(TryInterpreter.transformaTry((TryStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.DO_WHILE)){
				stmtRet.setDoWhile(WhileInterpreter.transformaDoWhile((DoStatement) stmt,originalSource));
			}else if (tipo.equals(StatementType.WHILE)){
				stmtRet.setWhileStatement(WhileInterpreter.transformaWhile((WhileStatement) stmt,originalSource));
			}else if (tipo.equals(StatementType.FOR)){
				stmtRet.setForSimples(ForInterpreter.transformaFor((ForStatement) stmt,originalSource));
			}else if (tipo.equals(StatementType.FOR_EACH)){
				stmtRet.setForComposto(ForInterpreter.transformaFor((EnhancedForStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.SWITCH)){
				stmtRet.setSwitchStatement(SwitchCaseInterpreter.transformaSwitch((SwitchStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.IF)){
				stmtRet.setIfStatement(IfInterpreter.transformaIf((IfStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.EXPRESSION)){
				stmtRet.setExpressao(ExpressionInterpreter.transformaExpression((ExpressionStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.RETURN)){
				stmtRet.setExpressao(transformaStatementToExpressionStatement((ReturnStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.VARIABLE_DECLARATION)){
				stmtRet.setExpressao(transformaStatementToExpressionStatement((VariableDeclarationStatement)stmt,originalSource));
			}else if (tipo.equals(StatementType.BLOCK)){
				stmtRet.setBloco(BlockInterpreter.transformaBloco((Block)stmt,originalSource));
			}
		}
		return stmtRet;
	}
	
	public static ExpressionStatementEntity transformaStatementToExpressionStatement(ReturnStatement stmt,File originalSource){
		if (stmt != null){
			return new ExpressionStatementEntity(stmt.toString());
		}else{
			return new ExpressionStatementEntity("");
		}
	}
	
	public static ExpressionStatementEntity transformaStatementToExpressionStatement(VariableDeclarationStatement stmt,File originalSource){
		if (stmt != null){
			return new ExpressionStatementEntity(stmt.toString());
		}else{
			return new ExpressionStatementEntity("");
		}
	}
	
}
