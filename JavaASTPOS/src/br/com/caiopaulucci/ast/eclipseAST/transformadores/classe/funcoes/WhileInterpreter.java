/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.DoWhileStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;
import br.com.caiopaulucci.ast.model.entity.TryStatementEntity;
import br.com.caiopaulucci.ast.model.entity.WhileStatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class WhileInterpreter {

	public static DoWhileStatementEntity transformaDoWhile(DoStatement doStmt,File originalSource){
		DoWhileStatementEntity ret = new DoWhileStatementEntity();
		Object stmt = doStmt.getStructuralProperty(DoStatement.BODY_PROPERTY) ;
		ret.setCorpo(BlockInterpreter.transformaEmBlock(stmt,originalSource));
		ret.setExpressao(ExpressionInterpreter.transformaExpression(doStmt.getExpression(),originalSource));
		return ret;
	}
	
	public static WhileStatementEntity transformaWhile(WhileStatement doStmt,File originalSource){
		WhileStatementEntity ret = new WhileStatementEntity();
		Object stmt = doStmt.getStructuralProperty(WhileStatement.BODY_PROPERTY) ;
		ret.setCorpo(BlockInterpreter.transformaEmBlock(stmt,originalSource));
		ret.setExpressao(ExpressionInterpreter.transformaExpression(doStmt.getExpression(),originalSource));
		return ret;
	}
	
	public static BlockEntity transformaWhileInBlock(WhileStatement stmt,File originalSource){
		WhileStatementEntity St = transformaWhile(stmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmtadd = new StatementEntity();
		stmtadd.setWhileStatement(St);
		ret.getStatementList().add(stmtadd);
		return ret;
	}
	
	public static BlockEntity transformaWhileInBlock(DoStatement stmt,File originalSource){
		DoWhileStatementEntity St = transformaDoWhile(stmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmtadd = new StatementEntity();
		stmtadd.setDoWhile(St);
		ret.getStatementList().add(stmtadd);
		return ret;
	}
	
}
