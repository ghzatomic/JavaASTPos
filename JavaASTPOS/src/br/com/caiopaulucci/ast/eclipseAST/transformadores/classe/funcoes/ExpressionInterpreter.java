/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.InfixExpression;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors.ExpressionStatementVisitor;
import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.BreakStatementEntity;
import br.com.caiopaulucci.ast.model.entity.ExpressionStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class ExpressionInterpreter {

	public static ExpressionStatementEntity transformaExpression(ExpressionStatement stmt,File originalSource){
		return transformaASTNodeToExpression(stmt, originalSource);
		
	}
	
	public static ExpressionStatementEntity transformaASTNodeToExpression(ASTNode stmt,File originalSource){
		CompilationUnit compilationUnit = null;
		if (stmt.getRoot() instanceof CompilationUnit){
			compilationUnit = (CompilationUnit) stmt.getRoot();
		}
		if (compilationUnit != null){
			ExpressionStatementVisitor visitor = new ExpressionStatementVisitor(compilationUnit, originalSource);
			stmt.accept(visitor);
			if (stmt != null){
				return new ExpressionStatementEntity(visitor.getRetornoFull());
			}else{
				return new ExpressionStatementEntity("");
			}
		}
		return new ExpressionStatementEntity("");
		
	}
	
	public static BlockEntity transformaExpressionToBlock(ExpressionStatement stmt,File originalSource){
		ExpressionStatementEntity exprSt = transformaExpression(stmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmtEntity = new StatementEntity();
		stmtEntity.setExpressao(exprSt);
		ret.getStatementList().add(stmtEntity);
		return ret;
	}
	
	public static ExpressionStatementEntity transformaExpression(InfixExpression stmt,File originalSource){
		return transformaASTNodeToExpression(stmt, originalSource);
	}
	
	
	public static ExpressionStatementEntity transformaExpression(Expression stmt,File originalSource){
		//return transformaASTNodeToExpression(stmt, originalSource);
		if (stmt != null){
			return new ExpressionStatementEntity(stmt.toString());
		}else{
			return new ExpressionStatementEntity("");
		}
		
	}
	
	public static BreakStatementEntity transformaBreak(BreakStatement stmt,File originalSource){
		return new BreakStatementEntity("break");
	}
	
}
