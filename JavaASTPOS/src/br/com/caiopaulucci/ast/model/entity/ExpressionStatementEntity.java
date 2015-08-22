/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class ExpressionStatementEntity {
	
	private Integer codigo;

	private String expressao;
	
	public ExpressionStatementEntity(String expr) {
		setExpressao(expr);
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getExpressao() {
		return expressao;
	}

	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}
	
	@Override
	public String toString() {
		return this.getExpressao();
	}

}
