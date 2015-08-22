/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel.VariableInterpreter;
import br.com.caiopaulucci.ast.model.entity.BlockEntity;
import br.com.caiopaulucci.ast.model.entity.EnhancedForStatementEntity;
import br.com.caiopaulucci.ast.model.entity.ForStatementEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;

/**
 * @author Caio Paulucci
 *
 */
public class ForInterpreter {

	public static BlockEntity transformaForInBlock(ForStatement forStmt,File originalSource){
		ForStatementEntity St = transformaFor(forStmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmt = new StatementEntity();
		stmt.setForSimples(St);
		ret.getStatementList().add(stmt);
		return ret;
	}
	
	public static BlockEntity transformaForInBlock(EnhancedForStatement forStmt,File originalSource){
		EnhancedForStatementEntity St = transformaFor(forStmt,originalSource);
		BlockEntity ret = new BlockEntity();
		if (ret.getStatementList() == null){
			ret.setStatementList(new ArrayList<StatementEntity>());
		}
		StatementEntity stmt = new StatementEntity();
		stmt.setForComposto(St);
		ret.getStatementList().add(stmt);
		return ret;
	}
	
	public static ForStatementEntity transformaFor(ForStatement forStmt,File originalSource){
		ForStatementEntity ret = new ForStatementEntity();
		Object stmt = forStmt.getStructuralProperty(ForStatement.BODY_PROPERTY) ;
		ret.setCorpo(BlockInterpreter.transformaEmBlock(stmt,originalSource));
		
		ret.setCondicao(ExpressionInterpreter.transformaExpression((Expression)forStmt.getStructuralProperty(ForStatement.EXPRESSION_PROPERTY),originalSource));
		
		List<Object> inicializador =(List<Object>)forStmt.getStructuralProperty(ForStatement.INITIALIZERS_PROPERTY);
		if (inicializador != null){
			if (inicializador.size() > 0){
				Object inicializ = inicializador.get(0);
				if (inicializ instanceof VariableDeclarationExpression){
					ret.setInicializador(VariableInterpreter.transformarToExpression((VariableDeclarationExpression)inicializ,originalSource));
				}
			}
		}
		
		List<Object> atualizador =(List<Object>)forStmt.getStructuralProperty(ForStatement.UPDATERS_PROPERTY);
		if (atualizador != null){
			if (atualizador.size() > 0){
				Object upd = atualizador.get(0);
				if (upd instanceof PostfixExpression){
					ret.setAtualizador(VariableInterpreter.transformarToExpression((PostfixExpression)upd,originalSource));
				}
			}
		}
		
		return ret;
	}
	
	public static EnhancedForStatementEntity transformaFor(EnhancedForStatement forStmt,File originalSource){
		EnhancedForStatementEntity ret = new EnhancedForStatementEntity();
	
		Object stmt = forStmt.getStructuralProperty(EnhancedForStatement.BODY_PROPERTY) ;
		ret.setCorpo(BlockInterpreter.transformaEmBlock(stmt,originalSource));
		
		ret.setExpressao(ExpressionInterpreter.transformaExpression((Expression)forStmt.getStructuralProperty(EnhancedForStatement.EXPRESSION_PROPERTY),originalSource));
		ret.setParametro(VariableInterpreter.transformar((SingleVariableDeclaration)forStmt.getStructuralProperty(EnhancedForStatement.PARAMETER_PROPERTY),originalSource));
		return ret;
	}
	
}
