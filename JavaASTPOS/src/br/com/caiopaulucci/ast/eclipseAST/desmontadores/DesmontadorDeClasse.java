/**
 * 
 */
package br.com.caiopaulucci.ast.eclipseAST.desmontadores;

import java.io.File;
import java.io.IOException;
import java.util.Hashtable;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;

import br.com.caiopaulucci.ast.util.FileUtils;

/**
 * @author Caio Paulucci
 *
 */
public class DesmontadorDeClasse {

	private String originalSource = ""; 
	
/*	
	public Object execute() throws ExecutionException {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IWorkspaceRoot root = workspace.getRoot();
		// Get all projects in the workspace
		IProject[] projects = root.getProjects();
		// Loop over all projects
		for (IProject project : projects) {
			try {
				if (project.isNatureEnabled("org.eclipse.jdt.core.javanature")) {

					IPackageFragment[] packages = JavaCore.create(project)
							.getPackageFragments();
					// parse(JavaCore.create(project));
					for (IPackageFragment mypackage : packages) {
						if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
							for (ICompilationUnit unit : mypackage
									.getCompilationUnits()) {
								// Now create the AST for the ICompilationUnits
								CompilationUnit parse = parse(unit);
								// MethodVisitor visitor = new MethodVisitor();
								// parse.accept(visitor);

								
								 * for (MethodDeclaration method :
								 * visitor.getMethods()) {
								 * System.out.print("Method name: " +
								 * method.getName() + " Return type: " +
								 * method.getReturnType2()); }
								 

							}
						}

					}
				}
			} catch (CoreException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	*//**
     * Reads a ICompilationUnit and creates the AST DOM for manipulating the
     * Java source file
     *
     * @param unit
     * @return
     *//*

    private static CompilationUnit parse(ICompilationUnit unit) {
            ASTParser parser = ASTParser.newParser(AST.JLS3);
            parser.setKind(ASTParser.K_COMPILATION_UNIT);
            parser.setSource(unit);
            parser.setResolveBindings(true);
            return (CompilationUnit) parser.createAST(null); // parse
    }
*/	
	public CompilationUnit desmontar(File arquivoClasse,int tipo,String[] sourcePath) throws IOException{
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		this.setOriginalSource(FileUtils.readFile(arquivoClasse.getAbsolutePath()).toString());
		parser.setSource(this.getOriginalSource().toCharArray());
		parser.setKind(tipo);
		parser.setEnvironment(new String[]{}, sourcePath, null, true);
		parser.setIgnoreMethodBodies(false);
		parser.setStatementsRecovery(true);
		parser.setResolveBindings(true);
		Hashtable<String, String> options = JavaCore.getOptions();
	    options.put(JavaCore.COMPILER_DOC_COMMENT_SUPPORT, JavaCore.ENABLED);
		parser.setCompilerOptions(options);
		/*parser.setResolveBindings(true);
		parser.setIgnoreMethodBodies(false);*/

		CompilationUnit cu = (CompilationUnit) parser.createAST(null);
		
		/*List types = cu.types();
		TypeDeclaration typeDeclaration  = (TypeDeclaration) types.get(0);
		ITypeBinding binding = typeDeclaration.resolveBinding();
		*/
		return cu;
	}
	
	/*public static void main(String[] args) throws IOException {
		File rootDir = new File(System.getProperty("java.io.tmpdir"));
		ASTParser parser = ASTParser.newParser(AST.JLS3);
		parser.setEnvironment(null, null, null, true);
		parser.setResolveBindings(true);
		parser.setStatementsRecovery(true);
		parser.setBindingsRecovery(true);

		final String key = "Lp/X;";
		final IBinding[] bindings = new IBinding[2];

		String contents =
			"package p;\n" + 
			"public class X extends Y {\n" + 
			"	public int i;\n" + 
			"	public static void main(String[] args) {\n" + 
			"		int length = args.length;\n" + 
			"		System.out.println(length);\n" + 
			"	}\n" + 
			"}";
		
		File packageDir = new File(rootDir, "p");
		packageDir.mkdir();
		File file = new File(packageDir, "X.java");
		Writer writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(contents);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch(IOException e) {
					// ignore
				}
			}
		}

		String contents2 =
			"package p;\n" + 
			"public class Y {}";
		File fileY = new File(packageDir, "Y.java");
		Writer writer2 = null;
		try {
			writer2 = new BufferedWriter(new FileWriter(fileY));
			writer2.write(contents2);
		} finally {
			if (writer2 != null) {
				try {
					writer2.close();
				} catch(IOException e) {
					// ignore
				}
			}
		}

		try {
			final String canonicalPath = file.getCanonicalPath();
			final CompilationUnit[] units = new CompilationUnit[1];
	
			FileASTRequestor requestor = new FileASTRequestor() {
				public void acceptBinding(String bindingKey, IBinding binding) {
					if (key.equals(bindingKey)) {
						bindings[0] = binding;
						IBinding[] temp = createBindings(new String[] {"Ljava/lang/Object;"});
						for (int i = 0; i < temp.length; ++i) {
							bindings[i + 1] = temp[i];
						}
					}
				}
				public void acceptAST(String sourceFilePath, CompilationUnit ast) {
					if (canonicalPath.equals(sourceFilePath)) {
						units[0] = ast;
					}
				}
			};
	
			parser.setEnvironment(null, new String[] { rootDir.getCanonicalPath() }, null, true);
	
			parser.createASTs(new String[] {canonicalPath}, null, new String[] {key}, requestor, null);
	
			ITypeBinding typeBinding = (ITypeBinding) bindings[0];
			IPackageBinding packageBinding = typeBinding.getPackage();
			typeBinding = (ITypeBinding) bindings[1];
		} finally {
			file.delete();
			fileY.delete();
		}
	}*/
	
	public CompilationUnit desmontar(File arquivoClasse,String[] sourcePath) throws IOException{
		return this.desmontar(arquivoClasse,ASTParser.K_COMPILATION_UNIT,sourcePath);
	}

	public String getOriginalSource() {
		return originalSource;
	}

	public void setOriginalSource(String originalSource) {
		this.originalSource = originalSource;
	}
	
}
