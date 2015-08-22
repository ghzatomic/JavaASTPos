package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo.visitors;

import java.io.File;
import java.io.IOException;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.LineComment;

import br.com.caiopaulucci.ast.util.FileUtils;

public class CommentVisitor extends ASTVisitor {

    CompilationUnit compilationUnit;

    private String[] source;
    
    private Comment comentario;

    private ASTNode astNode;
    
    private String fullSource;
    
    private String retornoFull = "";
    
    private String retornoLinha = "";
    
    private String retornoBloco = "";
    
    private boolean pre = true;
    
    private boolean noFim = false;
    
    public CommentVisitor(CompilationUnit compilationUnit, File sourceFile,Comment comentario,ASTNode astNode,boolean pre) {

        super();
        this.compilationUnit = compilationUnit;
        this.comentario = comentario;
        this.astNode = astNode;
        this.pre = pre;
        try {
        	this.fullSource = FileUtils.readFile(sourceFile.getAbsolutePath()).toString();
			this.source = fullSource.split("\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

    public boolean visit(LineComment node) {
    	String lineComments = "";
    	
    	if (this.pre){
    		int startLineNumber = compilationUnit.getLineNumber(node.getStartPosition()) - 1;
            int nodeComentedStartPos = compilationUnit.getLineNumber(astNode.getStartPosition());
            for (int i = startLineNumber; i < nodeComentedStartPos-1; i++) {
    			lineComments += source[i].trim()+"\n";
    		}
    	}else{
    		/*if (compilationUnit.getLineNumber(node.getStartPosition()) == compilationUnit.getLineNumber(astNode.getStartPosition()+astNode.getLength())){
    			// Comentario na mesma linha no final
    			int startLineNumberComentario = compilationUnit.getLineNumber(node.getStartPosition())-1;
	            int nodeComentedEndPos = compilationUnit.getLineNumber(astNode.getStartPosition()+astNode.getLength())-1;
	            for (int i = startLineNumberComentario; i <= nodeComentedEndPos; i++) {
	    			lineComments += source[i].trim().substring(source[i].trim().indexOf("//"));
	    			if (i != nodeComentedEndPos){
	    				lineComments +="\n";
	    			}
	    		}
	            noFim = true;
    		}else{*/
	    		int startLineNumberComentario = compilationUnit.getLineNumber(node.getStartPosition()) -1;
	            int nodeComentedEndPos = compilationUnit.getLineNumber(astNode.getStartPosition()+astNode.getLength());
	            for (int i = startLineNumberComentario; i <= nodeComentedEndPos; i++) {
	    			lineComments += source[i].trim();
	    			if (i != nodeComentedEndPos){
	    				lineComments +="\n";
	    			}
	    		}
    		//}
    	}
        
        this.setRetornoLinha(lineComments);
        
        this.retornoFull += lineComments;
        
        return true;
    }

    public boolean visit(BlockComment node) {
    	/**
    	 * implementar a contagem de caracteres para quando for um comentario de bloco
    	 * dentro de uma funcao .. ou if .. etc ...
    	 * 
    	 * Regex de comentario : 
    	 */
    	//String regexComentario = "(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)";
        int startLineNumberComment = compilationUnit.getLineNumber(node.getStartPosition()) - 1;

        int nodeASTStart = compilationUnit.getLineNumber(astNode.getStartPosition());
        StringBuffer blockComment = new StringBuffer();
        if (!this.pre){
	        for (int lineCount = nodeASTStart ; lineCount<= startLineNumberComment; lineCount++) {
	
	            String blockCommentLine = source[lineCount].trim();
	            blockComment.append(blockCommentLine);
	            if (lineCount != nodeASTStart) {
	                blockComment.append("\n");
	            }
	        }
        }else{
	        for (int lineCount = startLineNumberComment ; lineCount<= nodeASTStart-2; lineCount++) {
	
	            String blockCommentLine = source[lineCount].trim();
	            blockComment.append(blockCommentLine);
	            if (lineCount != nodeASTStart-2) {
	                blockComment.append("\n");
	            }
	        }
        }
        
        this.setRetornoBloco(blockComment.toString());
        
        this.retornoFull += blockComment.toString();
        
        

        return true;
    }

    public void preVisit(ASTNode node) {

    }

	public String getRetornoFull() {
		return retornoFull;
	}


	public String getRetornoLinha() {
		return retornoLinha;
	}

	public void setRetornoLinha(String retornoLinha) {
		this.retornoLinha = retornoLinha;
	}

	public String getRetornoBloco() {
		return retornoBloco;
	}

	public void setRetornoBloco(String retornoBloco) {
		this.retornoBloco = retornoBloco;
	}

	public boolean isNoFim() {
		return noFim;
	}

}