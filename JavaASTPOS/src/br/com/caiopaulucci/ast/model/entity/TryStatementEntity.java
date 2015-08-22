/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class TryStatementEntity {
	
	public static String TRY = "try";
	
	private Integer codigo;

	private BlockEntity corpoTry;
	
	private List<CatchStatementEntity> catchs;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public BlockEntity getCorpoTry() {
		return corpoTry;
	}

	public void setCorpoTry(BlockEntity corpoTry) {
		this.corpoTry = corpoTry;
	}

	public List<CatchStatementEntity> getCatchs() {
		return catchs;
	}

	public void setCatchs(List<CatchStatementEntity> catchs) {
		this.catchs = catchs;
	}
	
	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		ret.append(TRY);
		ret.append(this.getCorpoTry().toString());
		if (this.getCatchs() != null){
			for (CatchStatementEntity catchEntity : this.getCatchs()) {
				ret.append(catchEntity.toString());
			}
		}
		return ret.toString();
	}
	
}
