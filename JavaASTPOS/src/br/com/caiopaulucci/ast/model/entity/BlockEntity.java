/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.generalpurposeobjects.model.entity.PadraoEntity;

/**
 * @author Caio Paulucci
 *
 */
public class BlockEntity extends PadraoEntity {

	private Integer codigo;
	
	private boolean temChaves = true;

	private List<StatementEntity> statementList;
	
	private int ordemGenerator = 0;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public List<StatementEntity> getStatementList() {
		return statementList;
	}

	public void setStatementList(List<StatementEntity> statementList) {
		this.statementList = statementList;
	}
	
	public void addStatement(StatementEntity added){
		if (this.getStatementList() == null){
			this.setStatementList(new ArrayList<StatementEntity>());
		}
		added.setOrdem(getOrdemGen());
		this.getStatementList().add(added);
	}
	
	private Integer getOrdemGen(){
		ordemGenerator++;
		return ordemGenerator;
	}
	
	public boolean getTemChaves() {
		return temChaves;
	}

	public void setTemChaves(boolean temChaves) {
		this.temChaves = temChaves;
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		if (this.temChaves){
			ret.append("{");
		}
		if (this.getStatementList() != null){
			Collections.sort(this.getStatementList(), new Comparator<StatementEntity>() {
				@Override
				public int compare(StatementEntity o1, StatementEntity o2) {
					return o1.getOrdem().compareTo(o2.getOrdem());
				}
			});
			for (StatementEntity stm : this.getStatementList()) {
				String stmtStr = stm.toString();
				ret.append(stmtStr);
			}
		}
		if (this.temChaves){
			ret.append("}");
		}
		return ret.toString();
	}
	
}

