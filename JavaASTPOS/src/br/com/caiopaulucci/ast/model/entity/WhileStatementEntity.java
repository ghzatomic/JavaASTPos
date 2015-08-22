/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class WhileStatementEntity {
	
	public static String WHILE_DECLARATION = "while";
	
	private Integer codigo;

	private ExpressionStatementEntity expressao;
	
	private BlockEntity corpo;

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
	
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(WHILE_DECLARATION+"(");
		ret.append(getExpressao()+")");
		if (this.getCorpo() != null){
			ret.append(this.getCorpo().toString());
		}
		return ret.toString();
	}
	
}
