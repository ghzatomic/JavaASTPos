/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class IfStatementEntity {
	
	public static String IF = "if";
	public static String ELSE = "else ";
	
	private Integer codigo;

	private ExpressionStatementEntity condicao;
	
	private BlockEntity thenBlock;
	
	private BlockEntity elseBlock;

	public BlockEntity getThenBlock() {
		return thenBlock;
	}

	public void setThenBlock(BlockEntity thenBlock) {
		this.thenBlock = thenBlock;
	}

	public BlockEntity getElseBlock() {
		return elseBlock;
	}

	public void setElseBlock(BlockEntity elseBlock) {
		this.elseBlock = elseBlock;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public ExpressionStatementEntity getCondicao() {
		return condicao;
	}

	public void setCondicao(ExpressionStatementEntity condicao) {
		this.condicao = condicao;
	}
	
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(IF+"("+this.getCondicao().toString()+")");
		ret.append(this.getThenBlock());
		if (this.getElseBlock() != null){
			ret.append(ELSE);
			ret.append(this.getElseBlock().toString());
		}
		return ret.toString();
	}
	
}
