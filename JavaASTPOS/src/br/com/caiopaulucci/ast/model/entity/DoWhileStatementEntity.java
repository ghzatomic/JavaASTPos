/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class DoWhileStatementEntity {
	
	public static String DO_WHILE = "do";
	
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
		ret.append(DO_WHILE);
		ret.append(this.getCorpo().toString());
		ret.append(WhileStatementEntity.WHILE_DECLARATION+"("+this.getExpressao().toString()+");");
		return ret.toString();
	}
	
}
