/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class SwitchStatementEntity {
	
	public static String SWITCH = "switch";
	
	private Integer codigo;

	private ExpressionStatementEntity expressao;
	
	private List<SwitchCaseConditionStatementEntity> condicoes;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<SwitchCaseConditionStatementEntity> getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(List<SwitchCaseConditionStatementEntity> condicoes) {
		this.condicoes = condicoes;
	}

	public ExpressionStatementEntity getExpressao() {
		return expressao;
	}

	public void setExpressao(ExpressionStatementEntity expressao) {
		this.expressao = expressao;
	}
	

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(SWITCH+"("+this.getExpressao()+"){\n");
		if (this.getCondicoes() != null){
			for (SwitchCaseConditionStatementEntity condicao : this.getCondicoes()) {
				ret.append(condicao.toString());
			}
		}
		ret.append("}");
		return ret.toString();
	}
	
}
