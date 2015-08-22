/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;

/**
 * @author Caio Paulucci
 *
 */
public enum StatementType {

	TRY(TryStatement.class,TryStatementEntity.class),
	DO_WHILE(DoStatement.class,DoWhileStatementEntity.class),
	WHILE(WhileStatement.class,WhileStatementEntity.class),
	IF(IfStatement.class,IfStatementEntity.class),
	FOR(ForStatement.class,ForStatementEntity.class),
	FOR_EACH(EnhancedForStatement.class,EnhancedForStatementEntity.class),
	RETURN(ReturnStatement.class,ExpressionStatementEntity.class),
	EXPRESSION(ExpressionStatement.class,ExpressionStatementEntity.class),
	VARIABLE_DECLARATION(VariableDeclarationStatement.class,ExpressionStatementEntity.class),
	BLOCK(Block.class,BlockEntity.class),
	LABELEDBLOCK(LabeledStatement.class,BlockEntity.class),
	BREAK(BreakStatement.class,BreakStatementEntity.class),
	SWITCH(SwitchStatement.class,SwitchStatementEntity.class);
	

	private Class classe;
	private Class tipo;
	
	private StatementType(Class stmtType,Class atipo){
		classe = stmtType;
		tipo = atipo;
	}
	
	public boolean isInstanceOf(Class classeInst){
		for(StatementType stmtInt : StatementType.values()){
			if (classe == classeInst){
				return true;
			}
		}
		return false;
	}
	
	public static Class getTipoEntityByEnum(StatementType tipo){
		return tipo.getTipo();
	}

	public Class getClasse() {
		return classe;
	}

	public Class getTipo() {
		return tipo;
	}

	public static StatementType getEnumByClasse(Class classeInst) {
		for (StatementType stmtInt : StatementType.values()) {
			if (stmtInt.getClasse().equals(classeInst)) {
				return stmtInt;
			}
		}
		return null;
	}
	
	/*public boolean getTipoEntity(Class classeInst,Class tipo){
		for(StatementType stmtInt : StatementType.values()){
			if (classe == classeInst){
				return true;
			}
		}
		return false;
	}*/
	
}
