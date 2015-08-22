/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class SwitchCaseConditionStatementEntity {
	
	private Integer codigo;

	private SwitchCaseStatementEntity switchCase;
	
	private BreakStatementEntity breakStatement;
	
	private BlockEntity bloco;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public SwitchCaseStatementEntity getSwitchCase() {
		return switchCase;
	}

	public void setSwitchCase(SwitchCaseStatementEntity switchCase) {
		this.switchCase = switchCase;
	}

	public BreakStatementEntity getBreakStatement() {
		return breakStatement;
	}

	public void setBreakStatement(BreakStatementEntity breakStatement) {
		this.breakStatement = breakStatement;
	}

	public BlockEntity getBloco() {
		return bloco;
	}

	public void setBloco(BlockEntity bloco) {
		this.bloco = bloco;
	}

	@Override
	public String toString() {
		StringBuffer ret  = new StringBuffer();
		boolean jatem = false;
		if (this.getSwitchCase() != null){
			ret.append(this.getSwitchCase().toString());
			jatem = true;
		}
		if (this.getBloco() != null){
			if (jatem){
				ret.append("\n");
			}
			ret.append(this.getBloco().toString());
			jatem = true;
		}
		if (this.getBreakStatement() != null){
			if (jatem){
				ret.append("\n");
			}
			ret.append(this.getBreakStatement().toString()+";");
			jatem = true;
		}
		return ret.toString();
	}
	
}
