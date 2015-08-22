/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class LabeledStatementEntity {
	
	private Integer codigo;

	private StatementEntity corpo;
	
	private String label ;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public StatementEntity getCorpo() {
		return corpo;
	}

	public void setCorpo(StatementEntity corpo) {
		this.corpo = corpo;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(this.getLabel()+":\n");
		if (this.getCorpo() != null){
			ret.append(this.getCorpo().toString());
		}
		return ret.toString();
	}

}
