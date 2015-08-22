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
public class VariableEntity extends PadraoEntity {

	private Integer codigo;
	
	private String nome;
	
	private String declaracao;
	
	private String tipo;
	
	private String expressaoInicial;
	
	private boolean primitivo;
	
	private int modificadores;
	
	private int dimensaoExtra;
	
	private String fonteInicial;
	
	private List<AnnotationEntity> annotations;

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

	public String getFonteInicial() {
		return fonteInicial;
	}

	public void setFonteInicial(String fonteInicial) {
		this.fonteInicial = fonteInicial;
	}
	
	public String getModificadoresString(){
		return Modifier.toString(getModificadores());
	}

	public int getDimensaoExtra() {
		return dimensaoExtra;
	}

	public void setDimensaoExtra(int dimensaoExtra) {
		this.dimensaoExtra = dimensaoExtra;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isPrimitivo() {
		return primitivo;
	}

	public void setPrimitivo(boolean primitivo) {
		this.primitivo = primitivo;
	}

	public String getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getExpressaoInicial() {
		return expressaoInicial;
	}

	public void setExpressaoInicial(String expressaoInicial) {
		this.expressaoInicial = expressaoInicial;
	}
	
	public List<AnnotationEntity> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationEntity> annotations) {
		this.annotations = annotations;
	}

	@Override
	public String toString() {
		StringBuffer ret = new StringBuffer();
		if (this.getAnnotations() != null){
			for (AnnotationEntity ann : this.getAnnotations()) {
				ret.append(ann.getAnnotation()+" ");
			}
		}
		if (getModificadores() != 0){
			ret.append(getModificadoresString()+" ");
		}
		if (!Util.isNullOrEmpty(getTipo())){
			ret.append(getTipo()+" ");
		}
		ret.append(this.getNome());
		if (this.getDimensaoExtra() != 0){
			for (int i=0; i<this.getDimensaoExtra() ; i++) {
				ret.append("[]");
			}
		}
		if (!Util.isNullOrEmpty(this.getExpressaoInicial())){
			ret.append(" = "+this.getExpressaoInicial());
		}
		return ret.toString();
	}
	
}
