/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.lang.reflect.Modifier;
import java.util.List;

import br.com.generalpurposeobjects.model.entity.PadraoEntity;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class MethodEntity extends PadraoEntity {

public Integer codigo;
	
	private String nome;
	
	private String declaracao;
	
	private String javadoc;
	
	private int modificadores;
	
	private List<VariableEntity> parametros;
	
	private List<AnnotationEntity> annotations;
	
	private String retorno;
	
	private BlockEntity corpo;
	
	private boolean construtor;
	
	public String getModificadoresString(){
		return Modifier.toString(getModificadores());
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getModificadores() {
		return modificadores;
	}

	public void setModificadores(int modificadores) {
		this.modificadores = modificadores;
	}

	public List<VariableEntity> getParametros() {
		return parametros;
	}

	public void setParametros(List<VariableEntity> parametros) {
		this.parametros = parametros;
	}

	public String getRetorno() {
		return retorno;
	}

	public void setRetorno(String retorno) {
		this.retorno = retorno;
	}

	public String getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public boolean isConstrutor() {
		return construtor;
	}

	public void setConstrutor(boolean construtor) {
		this.construtor = construtor;
	}

	public BlockEntity getCorpo() {
		return corpo;
	}

	public void setCorpo(BlockEntity corpo) {
		this.corpo = corpo;
	}

	public List<AnnotationEntity> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationEntity> annotations) {
		this.annotations = annotations;
	}
	
	public String getJavadoc() {
		return javadoc;
	}

	public void setJavadoc(String javadoc) {
		this.javadoc = javadoc;
	}

	@Override
		public String toString() {
			StringBuffer ret = new StringBuffer();
			if (this.getJavadoc() != null){
				ret.append(this.getJavadoc());
			}
			if (this.getAnnotations() != null){
				for (AnnotationEntity ann : this.getAnnotations()) {
					ret.append(ann.getAnnotation()+" ");
				}
			}
			ret.append(this.getModificadoresString().toString()+" ");
			if (!this.isConstrutor()){
				if (!Util.isNullOrEmpty(this.getRetorno())){
					ret.append(this.getRetorno().toString()+" ");
				}
			}
			ret.append(this.getNome().toString());
			String params = "";
			if (this.getParametros() != null){
				for (VariableEntity var : this.getParametros()) {
					if (!Util.isNullOrEmpty(params)){
						params+=",";
					}
					params+=var.toString();
				}
			}
			ret.append("("+params+")");
			if (this.getCorpo() != null){
				ret.append(this.getCorpo().toString());
			}
			return ret.toString();
		}
	
	
}
