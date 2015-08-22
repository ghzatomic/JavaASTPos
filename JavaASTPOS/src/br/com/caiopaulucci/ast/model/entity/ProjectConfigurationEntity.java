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
public class ProjectConfigurationEntity extends PadraoEntity {

	private Integer codigo;
	
	private String nomeProjeto;
	
	//@ElementCollection
	//@CollectionTable(name="fontes", joinColumns=@JoinColumn(name="projectid"))
	private List<String> pastasDeFontes;
	
	private List<String> nomesAceitos;
	
	private List<String> extensoesAceitas;
	
	private String caminhoProjeto;

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public List<String> getPastasDeFontes() {
		return pastasDeFontes;
	}

	public void setPastasDeFontes(List<String> pastasDeFontes) {
		this.pastasDeFontes = pastasDeFontes;
	}

	public List<String> getNomesAceitos() {
		return nomesAceitos;
	}

	public void setNomesAceitos(List<String> nomesAceitos) {
		this.nomesAceitos = nomesAceitos;
	}

	public List<String> getExtensoesAceitas() {
		return extensoesAceitas;
	}

	public void setExtensoesAceitas(List<String> extensoesAceitas) {
		this.extensoesAceitas = extensoesAceitas;
	}

	public String getCaminhoProjeto() {
		return caminhoProjeto;
	}

	public void setCaminhoProjeto(String caminhoProjeto) {
		this.caminhoProjeto = caminhoProjeto;
	}
	
}
