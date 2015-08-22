/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class EnhancedForStatementEntity {
	
	private Integer codigo;

	private ExpressionStatementEntity expressao;
	
	private BlockEntity corpo;
	
	private VariableEntity parametro;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public BlockEntity getCorpo() {
		return corpo;
	}

	public void setCorpo(BlockEntity corpo) {
		this.corpo = corpo;
	}

	public ExpressionStatementEntity getExpressao() {
		return expressao;
	}

	public void setExpressao(ExpressionStatementEntity expressao) {
		this.expressao = expressao;
	}

	public VariableEntity getParametro() {
		return parametro;
	}

	public void setParametro(VariableEntity parametro) {
		this.parametro = parametro;
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(ForStatementEntity.FOR+"("+this.getParametro()+" : "+this.getExpressao()+")");
		if (this.getCorpo() != null){
			ret.append(this.getCorpo().toString());
		}
		return ret.toString();
	}

}
