/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.TryStatement;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel.VariableInterpreter;
import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.CatchStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;
import br.com.caiopaulucci.ast.model.entity.TryStatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class TryInterpreter {

	public static TryStatementEntity transformaTry(TryStatement tryStatement,File originalSource){
		TryStatementEntity tryRet = new TryStatementEntity();
		tryRet.setCorpoTry(BlockInterpreter.transformaBloco((Block)tryStatement.getStructuralProperty(TryStatement.BODY_PROPERTY),originalSource));
		List<CatchClause> listaDeCatchs = (List<CatchClause>) tryStatement.getStructuralProperty(TryStatement.CATCH_CLAUSES_PROPERTY);
		for (CatchClause catchClause : listaDeCatchs) {
			if (tryRet.getCatchs() == null){
				tryRet.setCatchs(new ArrayList<CatchStatementEntity>());
			}
			tryRet.getCatchs().add(transformaCatch(catchClause,originalSource));
		}
		return tryRet;
	}
	
	public static CatchStatementEntity transformaCatch(CatchClause catchClause,File originalSource){
		CatchStatementEntity catchStatementEntity = new CatchStatementEntity();
		catchStatementEntity.setCorpo(BlockInterpreter.transformaBloco(catchClause.getBody(),originalSource));
		catchStatementEntity.setExcessao(VariableInterpreter.transformar(catchClause.getException(),originalSource));
		return catchStatementEntity;
	}
	
	
	public static BlockEntity transformaTryInBlock(TryStatement stmt,File originalSource){
		TryStatementEntity St = transformaTry(stmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmtadd = new StatementEntity();
		stmtadd.setTryStatement(St);
		ret.getStatementList().add(stmtadd);
		return ret;
	}
	
}
