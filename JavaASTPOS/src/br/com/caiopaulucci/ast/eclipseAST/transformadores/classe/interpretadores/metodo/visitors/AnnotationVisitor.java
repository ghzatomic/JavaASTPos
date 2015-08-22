package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors;

import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.MarkerAnnotation;

import br.com.caiopaulucci.ast.model.entity.AnnotationEntity;

public class AnnotationVisitor extends ASTVisitor{

	private AnnotationEntity entity = null;
	
	private String[] originalSource;
	
	@Override
	public boolean visit(MarkerAnnotation node) {
		setEntity(new AnnotationEntity(node.toString()));
		return super.visit(node);
	}

	public AnnotationEntity getEntity() {
		return entity;
	}

	public void setEntity(AnnotationEntity entity) {
		this.entity = entity;
	}
	
}
