/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class CatchStatementEntity {
	
	public static String CATCH="catch";
	
	private Integer codigo;

	private VariableEntity excessao;
	
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

	public VariableEntity getExcessao() {
		return excessao;
	}

	public void setExcessao(VariableEntity excessao) {
		this.excessao = excessao;
	}
	
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(CATCH+"("+this.getExcessao().toString()+")");
		ret.append(this.getCorpo().toString());
		return ret.toString();
	}
	
}
