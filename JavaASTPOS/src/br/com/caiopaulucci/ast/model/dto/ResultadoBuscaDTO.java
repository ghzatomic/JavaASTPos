package br.com.caiopaulucci.ast.model.dto;

public class ResultadoBuscaDTO {

	private String funcao;
	
	private String objeto;
	
	private Integer linha;
	
	private String pai;

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public String getObjeto() {
		return objeto;
	}

	public void setObjeto(String objeto) {
		this.objeto = objeto;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	
	@Override
	public String toString() {
		return this.getObjeto()+"."+this.getFuncao();
	}

	public String getPai() {
		return pai;
	}

	public void setPai(String pai) {
		this.pai = pai;
	}
	
}
