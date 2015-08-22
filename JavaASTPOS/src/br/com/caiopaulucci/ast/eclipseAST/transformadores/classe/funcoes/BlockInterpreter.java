/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class BlockInterpreter {

	public static BlockEntity transformaBloco(Block corpo,File originalSource){
		if (corpo != null){
			BlockEntity ret = new BlockEntity();
			List<Statement> statements = (List<Statement>) corpo.getStructuralProperty(Block.STATEMENTS_PROPERTY);
			for (Statement statement : statements) {
				ret.addStatement(StatementInterpreter.transformaStatement(statement,originalSource));
			}
			return ret;
		}else{
			return null;
		}
	}
	
	public static BlockEntity transformaBloco(ReturnStatement corpo,File originalSource){
		if (corpo != null){
			BlockEntity ret = new BlockEntity();
			if (ret.getStatementList() == null){
				ret.setStatementList(new ArrayList<StatementEntity>());
			}
			StatementEntity stmtReturn = new StatementEntity();
			stmtReturn.setExpressao(StatementInterpreter.transformaStatementToExpressionStatement(corpo,originalSource));
			return ret;
		}else{
			return null;
		}
	}
	
	public static BlockEntity transformaBloco(ExpressionStatement corpo,File originalSource){
		if (corpo != null){
			BlockEntity ret = new BlockEntity();
			if (ret.getStatementList() == null){
				ret.setStatementList(new ArrayList<StatementEntity>());
			}
			StatementEntity stmtReturn = new StatementEntity();
			stmtReturn.setExpressao(ExpressionInterpreter.transformaExpression(corpo,originalSource));
			ret.getStatementList().add(stmtReturn);
			return ret;
		}else{
			return null;
		}
	}
	
	public static BlockEntity transformaEmBlock(Object stmt,File originalSource){
		if (stmt == null){
			return null;
		}
		BlockEntity ret = new BlockEntity();
		if (stmt instanceof Block){
			ret = BlockInterpreter.transformaBloco((Block)stmt,originalSource);
		}else if (stmt instanceof ReturnStatement){
			ret = BlockInterpreter.transformaBloco((ReturnStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof IfStatement){
			ret = IfInterpreter.transformaIfInBlock((IfStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof ForStatement){
			ret = ForInterpreter.transformaForInBlock((ForStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof EnhancedForStatement){
			ret = ForInterpreter.transformaForInBlock((EnhancedForStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof SwitchStatement){
			ret = SwitchCaseInterpreter.transformaSwitchInBlock((SwitchStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof TryStatement){
			ret = TryInterpreter.transformaTryInBlock((TryStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof WhileStatement){
			ret = WhileInterpreter.transformaWhileInBlock((WhileStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof DoStatement){
			ret = WhileInterpreter.transformaWhileInBlock((DoStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}else if (stmt instanceof ExpressionStatement){
			ret = ExpressionInterpreter.transformaExpressionToBlock((ExpressionStatement)stmt,originalSource);
			ret.setTemChaves(false);
		}
		return ret;
	}
	
}
