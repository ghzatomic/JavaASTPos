/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class ForStatementEntity {
	
	public static String FOR = "for";
	
	private Integer codigo;

	private BlockEntity corpo;
	
	private ExpressionStatementEntity inicializador;
	
	private ExpressionStatementEntity condicao;
	
	private ExpressionStatementEntity atualizador;

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

	public ExpressionStatementEntity getInicializador() {
		return inicializador;
	}

	public void setInicializador(ExpressionStatementEntity inicializador) {
		this.inicializador = inicializador;
	}

	public ExpressionStatementEntity getCondicao() {
		return condicao;
	}

	public void setCondicao(ExpressionStatementEntity condicao) {
		this.condicao = condicao;
	}

	public ExpressionStatementEntity getAtualizador() {
		return atualizador;
	}

	public void setAtualizador(ExpressionStatementEntity atualizador) {
		this.atualizador = atualizador;
	}
	
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		String inicializador = "";
		String condicao = "";
		String atualizador = "";
		if (this.getInicializador() != null){
			inicializador = this.getInicializador().toString();
		}
		if (this.getCondicao() != null){
			condicao = this.getCondicao().toString();
		}
		if (this.getAtualizador() != null){
			atualizador = this.getAtualizador().toString();
		}
		ret.append(ForStatementEntity.FOR+"("+inicializador+";"+condicao+";"+atualizador+")");
		if (this.getCorpo() != null){
			ret.append(this.getCorpo().toString());
		}
		return ret.toString();
	}

}
