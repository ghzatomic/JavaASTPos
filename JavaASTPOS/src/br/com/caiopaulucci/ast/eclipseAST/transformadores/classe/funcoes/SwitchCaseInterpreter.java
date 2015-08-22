/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchStatement;

import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.EnhancedForStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;
import br.com.caiopaulucci.ast.model.entity.SwitchCaseConditionStatementEntity;
import br.com.caiopaulucci.ast.model.entity.SwitchCaseStatementEntity;
import br.com.caiopaulucci.ast.model.entity.SwitchStatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class SwitchCaseInterpreter {

	public static SwitchStatementEntity transformaSwitch(SwitchStatement stmt,File originalSource){
		SwitchStatementEntity ret = new SwitchStatementEntity();
		ret.setExpressao(ExpressionInterpreter.transformaExpression((Expression)stmt.getStructuralProperty(SwitchStatement.EXPRESSION_PROPERTY),originalSource));
		List<Object> listaDeStatements = (List<Object>)stmt.getStructuralProperty(SwitchStatement.STATEMENTS_PROPERTY);
		for (Object object : listaDeStatements) {
			SwitchCaseConditionStatementEntity condicao = new SwitchCaseConditionStatementEntity();
			if (object instanceof SwitchCase){
				SwitchCase stmtCast = (SwitchCase)object;
				condicao.setSwitchCase(transformaSwitchCase(stmtCast,originalSource));
			}else if (object instanceof BreakStatement){
				BreakStatement breakStmt = (BreakStatement)object;
				condicao.setBreakStatement(ExpressionInterpreter.transformaBreak(breakStmt,originalSource));
			}else{
				condicao.setBloco(BlockInterpreter.transformaEmBlock(object,originalSource));
			}
			if (ret.getCondicoes() == null){
				ret.setCondicoes(new ArrayList<SwitchCaseConditionStatementEntity>());
			}
			ret.getCondicoes().add(condicao);
		}
		return ret;
	}
	
	public static SwitchCaseStatementEntity transformaSwitchCase(SwitchCase sc,File originalSource){
		SwitchCaseStatementEntity ret = new SwitchCaseStatementEntity();
		ret.setExpressao(sc.toString());
		return ret;
	}
	
	public static BlockEntity transformaSwitchInBlock(SwitchStatement stmt,File originalSource){
		SwitchStatementEntity St = transformaSwitch(stmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmtadd = new StatementEntity();
		stmtadd.setSwitchStatement(St);
		ret.getStatementList().add(stmtadd);
		return ret;
	}
	
}
