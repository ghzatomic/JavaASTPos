package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors;

import java.io.File;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.ConstructorInterpreter;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.MethodInterpreter;
import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;
import br.com.caiopaulucci.ast.model.entity.MethodEntity;
import br.com.caiopaulucci.ast.util.ObserverUtil;
import br.com.generalpurposeobjects.util.Util;

public class MethodDeclarationVisitor extends ASTVisitor {

	MethodEntity entity = new MethodEntity();
	
	private File originalSource ;
	
	public MethodDeclarationVisitor(File originalSource) {
		this.originalSource = originalSource;
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		MethodEntity metodo = null;
		if (node.isConstructor()){
			metodo = ConstructorInterpreter.transformar(node,originalSource);
		}else{
			metodo = MethodInterpreter.transformar(node,originalSource);
		}
		setEntity(metodo);
		
		/*ResultadoBuscaDTO resultado = new ResultadoBuscaDTO();
		resultado.setFuncao(metodo.getNome());
		resultado.setObjeto(node.getParent().getStructuralProperty(TypeDeclaration.NAME_PROPERTY)+"");
	
		ObserverUtil.notificaObservers(resultado);*/
		
		return false;
	}

	public MethodEntity getEntity() {
		return entity;
	}

	public void setEntity(MethodEntity entity) {
		this.entity = entity;
	}
	
}
