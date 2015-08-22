/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.interpretadores.metodo;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.variavel.VariableInterpreter;
import br.com.caiopaulucci.ast.model.entity.MethodEntity;
import br.com.caiopaulucci.ast.model.entity.VariableEntity;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class ConstructorInterpreter {
	public static MethodEntity transformar(MethodDeclaration node,File originalSource){
		MethodEntity methodEntity = MethodInterpreter.transformar(node,originalSource);
		methodEntity.setConstrutor(true);
		return methodEntity;
	}
}
