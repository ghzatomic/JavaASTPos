package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors;

import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.search.IJavaSearchConstants;
import org.eclipse.jdt.core.search.SearchPattern;
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;

import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;
import br.com.caiopaulucci.ast.util.FileUtils;
import br.com.caiopaulucci.ast.util.ObserverUtil;

public class ExpressionStatementVisitor extends ASTVisitor {

    CompilationUnit compilationUnit;

    private String[] source;
    
    private String fullSource;
    
    private String retornoFull = "";
    
    public ExpressionStatementVisitor(CompilationUnit compilationUnit, File sourceFile) {

        super();
        this.compilationUnit = compilationUnit;
        try {
        	this.fullSource = FileUtils.readFile(sourceFile.getAbsolutePath()).toString();
			this.source = fullSource.split("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    /* (non-Javadoc)
     * @see org.eclipse.jdt.core.dom.ASTVisitor#visit(org.eclipse.jdt.core.dom.ExpressionStatement)
     */
    @Override
    public boolean visit(ExpressionStatement node) {
    	int startLineNumberComment = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
        int endLineNumberComment = compilationUnit.getLineNumber(node.getStartPosition() + node.getLength()) - 1;
        
        StringBuffer ret = new StringBuffer();
        for (int lineCount = startLineNumberComment ; lineCount<= endLineNumberComment; lineCount++) {
            ret.append(source[lineCount].trim());
        }
        this.retornoFull = ret.toString();
        //System.out.println(node);
        ResultadoBuscaDTO resultado = new ResultadoBuscaDTO();
        if (node.getExpression() instanceof MethodInvocation){
        	resultado.setFuncao(node.getExpression().getStructuralProperty(MethodInvocation.NAME_PROPERTY)+"");
        	if (node.getExpression().getStructuralProperty(MethodInvocation.EXPRESSION_PROPERTY) instanceof ClassInstanceCreation){
        		resultado.setObjeto(((ClassInstanceCreation)node.getExpression().getStructuralProperty(MethodInvocation.EXPRESSION_PROPERTY)).getStructuralProperty(ClassInstanceCreation.TYPE_PROPERTY)+"");
        	}
        	resultado.setLinha(compilationUnit.getLineNumber(node.getStartPosition()));
        	resultado.setPai(((TypeDeclaration)compilationUnit.types().get(0)).getName().getIdentifier());
        	
        	
        }
		ObserverUtil.notificaObservers(resultado);
    	return super.visit(node);
    }
    
    protected IType getType(ICompilationUnit cu, String name) throws JavaModelException {
		IType[] types= cu.getAllTypes();
		for (int i= 0; i < types.length; i++)
			if (types[i].getTypeQualifiedName('.').equals(name) ||
			    types[i].getElementName().equals(name))
				return types[i];
		return null;
	}

    public void preVisit(ASTNode node) {

    }

	public String getRetornoFull() {
		return retornoFull;
	}

}