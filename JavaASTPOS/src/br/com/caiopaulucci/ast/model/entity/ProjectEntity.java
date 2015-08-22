/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.util.Date;
import java.util.List;

/**
 * @author Caio Paulucci
 *
 */
public class ProjectEntity {

	private Integer codigo;
	
	private String nome;
	private String descricao;
	private Date dataDeAnalise;
	private Date ultimaModificacao;
	
	private ProjectConfigurationEntity configuracao;
	
	private List<SourceEntity> sources;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDataDeAnalise() {
		return dataDeAnalise;
	}

	public void setDataDeAnalise(Date dataDeAnalise) {
		this.dataDeAnalise = dataDeAnalise;
	}

	public Date getUltimaModificacao() {
		return ultimaModificacao;
	}

	public void setUltimaModificacao(Date ultimaModificacao) {
		this.ultimaModificacao = ultimaModificacao;
	}

	public ProjectConfigurationEntity getConfiguracao() {
		return configuracao;
	}

	public void setConfiguracao(ProjectConfigurationEntity configuracao) {
		this.configuracao = configuracao;
	}

	public List<SourceEntity> getSources() {
		return sources;
	}

	public void setSources(List<SourceEntity> sources) {
		this.sources = sources;
	}
	
}

