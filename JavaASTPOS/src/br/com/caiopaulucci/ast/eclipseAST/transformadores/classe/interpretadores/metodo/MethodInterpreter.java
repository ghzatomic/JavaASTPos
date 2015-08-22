/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.funcoes.BlockInterpreter;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors.CommentVisitor;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel.VariableInterpreter;
import br.com.caiopaulucci.ast.model.entity.AnnotationEntity;
import br.com.caiopaulucci.ast.model.entity.MethodEntity;
import br.com.caiopaulucci.ast.model.entity.VariableEntity;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class MethodInterpreter {
	
	
	public static MethodEntity transformar(MethodDeclaration node,File originalSource){
		MethodEntity methodEntity = new MethodEntity();
		
		int intModificadores = node.getModifiers();
		if (node.getJavadoc()!= null){
			methodEntity.setJavadoc(node.getJavadoc().toString());
		}
		Object modificadores = node.getStructuralProperty(MethodDeclaration.MODIFIERS2_PROPERTY);
		if (modificadores != null){
			List<Object> listaDeModificadores = (List<Object>) modificadores;
			for (Object object : listaDeModificadores) {
				if (object instanceof MarkerAnnotation){
					if (methodEntity.getAnnotations() == null){
						methodEntity.setAnnotations(new ArrayList<AnnotationEntity>());
					}
					methodEntity.getAnnotations().add(AnnotationInterpreter.transformar((MarkerAnnotation)object));
				}
			}
		}
		//methodEntity.setFonteInicial(node.toString());
		methodEntity.setCorpo(BlockInterpreter.transformaBloco(node.getBody(),originalSource));
		methodEntity.setModificadores(intModificadores);
		methodEntity.setNome(node.getName().getIdentifier());
		methodEntity.setConstrutor(node.isConstructor());
		if (node.getReturnType2() != null){
			methodEntity.setRetorno(node.getReturnType2().toString());
		}
		String declaracao = methodEntity.getModificadoresString()
				+ ((!Util.isNullOrEmpty(methodEntity.getRetorno()) ? " "
						+ methodEntity.getRetorno() : "") + " "
						+ methodEntity.getNome());
		List<VariableEntity> listaDeParametros = new ArrayList<VariableEntity>();
		String parametrosStr = "";
		for (Object parameter : node.parameters()) {
			SingleVariableDeclaration variableDeclaration = (SingleVariableDeclaration) parameter;
			VariableEntity parametro = VariableInterpreter.transformar(variableDeclaration,originalSource);
			listaDeParametros.add(parametro);
			if (!Util.isNullOrEmpty(parametrosStr)){
				parametrosStr+=",";
			}
			String dimensao = "";
			for(int i=0;i<parametro.getDimensaoExtra();i++){
				dimensao+="[]";
			}
			parametrosStr+=parametro.getTipo()+" "+parametro.getNome()+dimensao;
		}
		methodEntity.setParametros(listaDeParametros);
		methodEntity.setDeclaracao(declaracao+"("+parametrosStr+")");
		return methodEntity;
	}
	
	
}
