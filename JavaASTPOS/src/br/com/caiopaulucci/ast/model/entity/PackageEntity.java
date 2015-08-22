/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

import br.com.generalpurposeobjects.model.entity.PadraoEntity;

/**
 * @author Caio Paulucci
 *
 */
public class PackageEntity extends PadraoEntity {

	private Integer codigo;
	
	private String nome;
	
	private List<PackageEntity> listaDePacotesFilhos;
	
	private List<ClassEntity> classes;

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

	public List<ClassEntity> getClasses() {
		return classes;
	}

	public void setClasses(List<ClassEntity> classes) {
		this.classes = classes;
	}

	public List<PackageEntity> getListaDePacotesFilhos() {
		return listaDePacotesFilhos;
	}

	public void setListaDePacotesFilhos(List<PackageEntity> listaDePacotesFilhos) {
		this.listaDePacotesFilhos = listaDePacotesFilhos;
	}
	
	
}
