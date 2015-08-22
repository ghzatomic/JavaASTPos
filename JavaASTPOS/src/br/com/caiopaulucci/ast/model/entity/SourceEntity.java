/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class SourceEntity {

	private Integer codigo;
	private String nome;
	private String relativePath;
	
	private List<PackageEntity> pacotes;
	
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
	public String getRelativePath() {
		return relativePath;
	}
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	public List<PackageEntity> getPacotes() {
		return pacotes;
	}
	public void setPacotes(List<PackageEntity> pacotes) {
		this.pacotes = pacotes;
	}
	
}
