/**
 * 
 */
package br.com.caiopaulucci.ast.model.entity;

import java.lang.reflect.Modifier;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.text.edits.MalformedTreeException;

import br.com.caiopaulucci.ast.eclipseAST.codeformatter.FormatadorDeCodigo;

/**
 * @author Caio Paulucci
 *
 */
public class ClassEntity {

	private Integer codigo;
	
	private String nome;

	private String declaracao;
	
	private int modificadores;
	
	private String fonteInicial;
	
	private List<VariableEntity> variaveis;
	
	private List<MethodEntity> metodos;

	private List<String> imports;
	
	private List<AnnotationEntity> annotations;
	
	private String superClass;
	
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
	
	public String getModificadoresString(){
		return Modifier.toString(getModificadores());
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

	public List<VariableEntity> getVariaveis() {
		return variaveis;
	}

	public void setVariaveis(List<VariableEntity> variaveis) {
		this.variaveis = variaveis;
	}

	public List<MethodEntity> getMetodos() {
		return metodos;
	}

	public void setMetodos(List<MethodEntity> metodos) {
		this.metodos = metodos;
	}

	public String getDeclaracao() {
		return declaracao;
	}

	public void setDeclaracao(String declaracao) {
		this.declaracao = declaracao;
	}

	public String getSuperClass() {
		return superClass;
	}

	public void setSuperClass(String superClass) {
		this.superClass = superClass;
	}

	public List<String> getImports() {
		return imports;
	}

	public void setImports(List<String> imports) {
		this.imports = imports;
	}

	public List<AnnotationEntity> getAnnotations() {
		return annotations;
	}

	public void setAnnotations(List<AnnotationEntity> annotations) {
		this.annotations = annotations;
	}
	
	@Override
	public String toString() {
		return toString(true);
	}
	
	public String toString(boolean formata) {
		StringBuilder sb = new StringBuilder();
		
		if (getAnnotations() != null){
			for (AnnotationEntity ann : getAnnotations()) {
				sb.append(ann.getAnnotation()+" ");
			}
		}
		sb.append(getDeclaracao());
		sb.append("{\n");
		if (getVariaveis() != null){
			for (VariableEntity variavel : getVariaveis()) {
				sb.append(""+variavel.toString()+";");
			}
		}
		if (getMetodos() != null){
			for (MethodEntity metodo : getMetodos()) {
				sb.append(""+metodo.toString());
			}
		}
		sb.append("\n}");
		if (formata){
			try {
				return FormatadorDeCodigo.formata(sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
				return sb.toString();
			}
		}else{
			return sb.toString();
		}
	}
	
}
