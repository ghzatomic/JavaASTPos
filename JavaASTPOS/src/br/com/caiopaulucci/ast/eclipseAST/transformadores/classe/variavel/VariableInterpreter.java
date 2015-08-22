/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.AnnotationInterpreter;
import br.com.caiopaulucci.ast.model.entity.AnnotationEntity;
import br.com.caiopaulucci.ast.model.entity.ExpressionStatementEntity;
import br.com.caiopaulucci.ast.model.entity.VariableEntity;

/**
 * @author Caio Paulucci
 *
 */
public class VariableInterpreter {

	public static VariableEntity transformar(SingleVariableDeclaration node,File originalSource){
		VariableEntity variavel = new VariableEntity();
        variavel.setDimensaoExtra(node.getExtraDimensions());
        variavel.setTipo(node.getType().toString());
        if (node.getType().isPrimitiveType()){
        	variavel.setPrimitivo(true);
        }else{
        	variavel.setPrimitivo(false);
        }
        variavel.setModificadores(node.getModifiers());
        variavel.setNome(node.getName().getIdentifier());
        variavel.setDeclaracao(variavel.getModificadoresString()+" "+variavel.getTipo()+" "+variavel.getDeclaracao());
        return variavel;
	}
	
	public static ExpressionStatementEntity transformarToExpression(VariableDeclaration node,File originalSource){
        return new ExpressionStatementEntity(node.toString());
	}
	
	public static ExpressionStatementEntity transformarToExpression(VariableDeclarationExpression node,File originalSource){
        return new ExpressionStatementEntity(node.toString());
	}
	
	public static ExpressionStatementEntity transformarToExpression(PostfixExpression node,File originalSource){
        return new ExpressionStatementEntity(node.toString());
	}
	
	public static VariableEntity transformar(FieldDeclaration node,File originalSource){
		VariableDeclarationFragment typeDec = (VariableDeclarationFragment) ((List)node.getStructuralProperty(FieldDeclaration.FRAGMENTS_PROPERTY)).get(0);
		
		VariableEntity variavel = transformar(typeDec,originalSource);
		Object modificadores = node.getStructuralProperty(FieldDeclaration.MODIFIERS2_PROPERTY);
		if (modificadores != null){
			List<Object> listaDeModificadores = (List<Object>) modificadores;
			for (Object object : listaDeModificadores) {
				if (object instanceof MarkerAnnotation){
					if (variavel.getAnnotations() == null){
						variavel.setAnnotations(new ArrayList<AnnotationEntity>());
					}
					variavel.getAnnotations().add(AnnotationInterpreter.transformar((MarkerAnnotation)object));
				}
			}
		}
		variavel.setTipo(node.getType().toString());
        if (node.getType().isPrimitiveType()){
        	variavel.setPrimitivo(true);
        }else{
        	variavel.setPrimitivo(false);
        }
		variavel.setModificadores(node.getModifiers());
		variavel.setDeclaracao(variavel.getModificadoresString()+" "+variavel.getTipo()+" "+variavel.getDeclaracao());
        return variavel;
	} 
	
	public static VariableEntity transformar(VariableDeclarationFragment node,boolean isPrimitive,File originalSource){
		VariableEntity variavel = new VariableEntity();
        variavel.setDimensaoExtra(node.getExtraDimensions());
        if (node.getInitializer()!= null){
        	variavel.setExpressaoInicial(node.getInitializer().toString());
        }else{
        	if (!isPrimitive){
        		variavel.setExpressaoInicial("null");
        	}else{
        		variavel.setExpressaoInicial("");
        	}
        }
        variavel.setNome(node.getName().getIdentifier());
        variavel.setDeclaracao(variavel.getNome()+" = "+variavel.getExpressaoInicial());
        return variavel;
	}
	
	public static VariableEntity transformar(VariableDeclarationFragment node,File originalSource){
		VariableEntity variavel = new VariableEntity();
        variavel.setDimensaoExtra(node.getExtraDimensions());
        if (node.getInitializer()!= null){
        	variavel.setExpressaoInicial(node.getInitializer().toString());
        }else{
        	variavel.setExpressaoInicial("");
        }
        variavel.setNome(node.getName().getIdentifier());
        variavel.setDeclaracao(variavel.getNome()+" = "+variavel.getExpressaoInicial());
        return variavel;
	}
	
}
