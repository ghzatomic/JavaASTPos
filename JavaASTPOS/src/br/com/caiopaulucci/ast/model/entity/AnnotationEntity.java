/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

/**
 * @author Caio Paulucci
 *
 */
public class AnnotationEntity {

	private Integer codigo;
	
	public String annotation;

	public AnnotationEntity() {
		// TODO Auto-generated constructor stub
	}
	
	public AnnotationEntity(String st) {
		this.setAnnotation(st);
	}
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
	
}
