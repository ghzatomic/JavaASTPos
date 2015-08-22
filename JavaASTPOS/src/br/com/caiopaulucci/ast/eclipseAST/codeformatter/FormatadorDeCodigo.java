package br.com.caiopaulucci.ast.eclipseAST.codeformatter;

import org.eclipse.jdt.core.formatter.CodeFormatter;
import org.eclipse.jdt.internal.formatter.DefaultCodeFormatter;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.TextEdit;

public class FormatadorDeCodigo {

	public static String formata(String code) throws MalformedTreeException, BadLocationException{
		CodeFormatter codeFormatter = new DefaultCodeFormatter();
		TextEdit te = codeFormatter.format(CodeFormatter.K_UNKNOWN, code, 0,code.length(),0,null);
		IDocument dc = new Document(code);
		te.apply(dc);
		return dc.get();
	}
	
}
