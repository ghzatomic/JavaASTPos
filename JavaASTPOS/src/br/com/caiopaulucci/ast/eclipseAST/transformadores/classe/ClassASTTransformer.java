/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeMap;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors.AnnotationVisitor;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors.MethodDeclarationVisitor;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel.VariableInterpreter;
import br.com.caiopaulucci.ast.model.entity.AnnotationEntity;
import br.com.caiopaulucci.ast.model.entity.ClassEntity;
import br.com.caiopaulucci.ast.model.entity.MethodEntity;
import br.com.caiopaulucci.ast.model.entity.VariableEntity;
import br.com.caiopaulucci.ast.util.ObserverUtil;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class ClassASTTransformer extends ASTVisitor{

	private ClassEntity classe = null;
	
	private File originalSource ;
	
	public ClassASTTransformer(File originalSource) {
		this.originalSource = originalSource;
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.CompilationUnit)
	 */
	@Override
	public boolean visit(CompilationUnit node) {
		if(node.types().size() != 0){
			TypeDeclaration typeDeclaration = (TypeDeclaration)node.types().get(0);
			if (typeDeclaration.getSuperclassType() != null){
				getClasse().setSuperClass(typeDeclaration.getSuperclassType().toString());
			}	
			getClasse().setNome(typeDeclaration.getName().getIdentifier());
			getClasse().setModificadores(typeDeclaration.getModifiers());
			String declaracao = getClasse().getModificadoresString()+" class "+getClasse().getNome()+((!Util.isNullOrEmpty(getClasse().getSuperClass())?" extends "+getClasse().getSuperClass():""));
			getClasse().setDeclaracao(declaracao);
			getClasse().setFonteInicial(node.toString());
			/*for (Comment comentario : (List<Comment>) node.getCommentList()) {
				CommentVisitor commentVisitor = new CommentVisitor(node, originalSource.split("\n"));
				comentario.accept(commentVisitor);
			}*/
		}
		return super.visit(node);
	}
	
	public boolean visit(ImportDeclaration node) {
		return super.visit(node);
	}

	@Override
	public boolean visit(MarkerAnnotation node) {	
		if (getClasse().getAnnotations() == null){
			getClasse().setAnnotations(new ArrayList<AnnotationEntity>());
		}
		AnnotationVisitor visitor = new AnnotationVisitor();
		node.accept(visitor);
		getClasse().getAnnotations().add(visitor.getEntity());
		return super.visit(node);
	}
	
	@Override
	public boolean visit(MethodDeclaration node) {
		MethodDeclarationVisitor visitor = new MethodDeclarationVisitor(this.getOriginalSource());
		node.accept(visitor);
		if (getClasse().getMetodos() == null){
			getClasse().setMetodos(new ArrayList<MethodEntity>());
		}
		getClasse().getMetodos().add(visitor.getEntity());
		
		return false;
	}
	
	@Override
	public boolean visit(FieldDeclaration node) {
		if (getClasse().getVariaveis() == null){
			getClasse().setVariaveis(new ArrayList<VariableEntity>());
		}
		getClasse().getVariaveis().add(VariableInterpreter.transformar(node,originalSource));
		return false; // do not continue to avoid usage info
	}

	
	public ClassEntity getClasse() {
		if (classe == null){
			classe = new ClassEntity();
		}
		return classe;
	}

	public void setClasse(ClassEntity classe) {
		this.classe = classe;
	}

	public File getOriginalSource() {
		return originalSource;
	}

	public void setOriginalSource(File originalSource) {
		this.originalSource = originalSource;
	}
	
}
