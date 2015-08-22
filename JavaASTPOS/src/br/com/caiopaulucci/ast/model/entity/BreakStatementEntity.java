/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class BreakStatementEntity {
	
	private Integer codigo;

	private String expressao;
	
	private String labelOpcional;
	
	public BreakStatementEntity(String expr) {
		setExpressao(expr);
	}
	
	public BreakStatementEntity() {
		// TODO Auto-generated constructor stub
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
	
	public String getLabelOpcional() {
		return labelOpcional;
	}

	public void setLabelOpcional(String labelOpcional) {
		this.labelOpcional = labelOpcional;
	}

	@Override
	public String toString() {
		return getExpressao()+(Util.isNullOrEmpty(this.getLabelOpcional())?"":" "+this.getLabelOpcional());
	}

}
