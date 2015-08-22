/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import br.com.generalpurposeobjects.model.entity.PadraoEntity;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class StatementEntity extends PadraoEntity {

	private Integer codigo;
	
	private Integer ordem;

	private String comentariosSuperiores;
	private String comentariosFim;
	private String comentariosPosteriores;
	
	private IfStatementEntity ifStatement;
	
	private TryStatementEntity tryStatement;
	
	private DoWhileStatementEntity doWhile;
	
	private EnhancedForStatementEntity forComposto;
	
	private ForStatementEntity forSimples;
	
	private SwitchStatementEntity switchStatement;
	
	private WhileStatementEntity whileStatement;

	private ExpressionStatementEntity expressao;
	
	private LabeledStatementEntity labeledStatement;
	
	private BlockEntity bloco;
	
	private BreakStatementEntity breakStatement;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public IfStatementEntity getIfStatement() {
		return ifStatement;
	}

	public void setIfStatement(IfStatementEntity ifStatement) {
		this.ifStatement = ifStatement;
	}

	public TryStatementEntity getTryStatement() {
		return tryStatement;
	}

	public void setTryStatement(TryStatementEntity tryStatement) {
		this.tryStatement = tryStatement;
	}

	public DoWhileStatementEntity getDoWhile() {
		return doWhile;
	}

	public void setDoWhile(DoWhileStatementEntity doWhile) {
		this.doWhile = doWhile;
	}

	public EnhancedForStatementEntity getForComposto() {
		return forComposto;
	}

	public void setForComposto(EnhancedForStatementEntity forComposto) {
		this.forComposto = forComposto;
	}

	public ForStatementEntity getForSimples() {
		return forSimples;
	}

	public void setForSimples(ForStatementEntity forSimples) {
		this.forSimples = forSimples;
	}

	public SwitchStatementEntity getSwitchStatement() {
		return switchStatement;
	}

	public void setSwitchStatement(SwitchStatementEntity switchStatement) {
		this.switchStatement = switchStatement;
	}

	public WhileStatementEntity getWhileStatement() {
		return whileStatement;
	}

	public void setWhileStatement(WhileStatementEntity whileStatement) {
		this.whileStatement = whileStatement;
	}

	public ExpressionStatementEntity getExpressao() {
		return expressao;
	}

	public void setExpressao(ExpressionStatementEntity expressao) {
		this.expressao = expressao;
	}
	
	public BlockEntity getBloco() {
		return bloco;
	}

	public void setBloco(BlockEntity bloco) {
		this.bloco = bloco;
	}

	public LabeledStatementEntity getLabeledStatement() {
		return labeledStatement;
	}

	public void setLabeledStatement(LabeledStatementEntity labeledStatement) {
		this.labeledStatement = labeledStatement;
	}

	public BreakStatementEntity getBreakStatement() {
		return breakStatement;
	}

	public void setBreakStatement(BreakStatementEntity breakStatement) {
		this.breakStatement = breakStatement;
	}
	
	public String getComentariosSuperiores() {
		return comentariosSuperiores;
	}

	public void setComentariosSuperiores(String comentariosSuperiores) {
		this.comentariosSuperiores = comentariosSuperiores;
	}

	public String getComentariosPosteriores() {
		return comentariosPosteriores;
	}

	public void setComentariosPosteriores(String comentariosPosteriores) {
		this.comentariosPosteriores = comentariosPosteriores;
	}

	public String getComentariosFim() {
		return comentariosFim;
	}

	public void setComentariosFim(String comentariosFim) {
		this.comentariosFim = comentariosFim;
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		if (!Util.isNullOrEmpty(this.getComentariosSuperiores())){
			ret.append(this.getComentariosSuperiores());
			ret.append("\n");
		}
		if (this.getIfStatement()!= null){
			ret.append(this.getIfStatement().toString());
		}else if (this.getTryStatement()!= null){
			ret.append(this.getTryStatement().toString());
		} else if (this.getDoWhile() != null) {
			ret.append(this.getDoWhile().toString());
		} else if (this.getWhileStatement() != null) {
			ret.append(this.getWhileStatement().toString());
		} else if (this.getForComposto() != null) {
			ret.append(this.getForComposto().toString());
		} else if (this.getForSimples() != null) {
			ret.append(this.getForSimples().toString());
		} else if (this.getSwitchStatement() != null) {
			ret.append(this.getSwitchStatement().toString());
		} else if (this.getExpressao() != null) {
			ret.append(this.getExpressao().toString());
			ret.append("\n");
		} else if (this.getBloco() != null) {
			ret.append( this.getBloco().toString());
		} else if (this.getLabeledStatement() != null) {
			ret.append( this.getLabeledStatement().toString());
		} else if (this.getBreakStatement() != null) {
			ret.append(this.getBreakStatement().toString()+";");
		}
		if (!Util.isNullOrEmpty(this.getComentariosPosteriores())){
			ret.append(this.getComentariosPosteriores());
			ret.append("\n");
		}
		return ret.toString();
	}

}
