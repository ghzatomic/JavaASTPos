/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.IfStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class IfInterpreter {

	public static IfStatementEntity transformaIf(IfStatement ifStmt,File originalSource){
		IfStatementEntity ret = new IfStatementEntity();
		
		Object stmt = ifStmt.getStructuralProperty(IfStatement.THEN_STATEMENT_PROPERTY) ;
		ret.setThenBlock(BlockInterpreter.transformaEmBlock(stmt, originalSource));
		
		stmt = ifStmt.getStructuralProperty(IfStatement.ELSE_STATEMENT_PROPERTY) ;
		ret.setElseBlock(BlockInterpreter.transformaEmBlock(stmt,originalSource));
		
		ret.setCondicao(ExpressionInterpreter.transformaExpression((Expression)ifStmt.getStructuralProperty(IfStatement.EXPRESSION_PROPERTY),originalSource));
		return ret;
	}
	
	public static BlockEntity transformaIfInBlock(IfStatement forStmt,File originalSource){
		IfStatementEntity ifSt = transformaIf(forStmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmt = new StatementEntity();
		stmt.setIfStatement(ifSt);
		ret.getStatementList().add(stmt);
		return ret;
		
	}
	
}
