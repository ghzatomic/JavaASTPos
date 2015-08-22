package br.com.caiopaulucci.ast.eclipseAST;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import br.com.caiopaulucci.ast.ProjectConfigurator;
import br.com.caiopaulucci.ast.descobridores.CaminhadorDePasta;
import br.com.caiopaulucci.ast.eclipseAST.desmontadores.DesmontadorDeClasse;
import br.com.caiopaulucci.ast.eclipseAST.transformadores.classe.ClassASTTransformer;
import br.com.caiopaulucci.ast.interfaces.InterfaceDeAcao;
import br.com.caiopaulucci.ast.model.entity.ClassEntity;
import br.com.caiopaulucci.ast.model.entity.PackageEntity;
import br.com.caiopaulucci.ast.model.entity.ProjectEntity;
import br.com.caiopaulucci.ast.model.entity.SourceEntity;
import br.com.generalpurposeobjects.util.Util;

public class LeitorDeProjeto {

	private ProjectEntity projeto = null;
	
	private List<CompilationUnit> compilationUnits = null;
	
	public LeitorDeProjeto() {
		ProjectConfigurator projectConfigurator = new ProjectConfigurator();
		getProjeto().setConfiguracao(projectConfigurator.toEntity());
	}
	
	public List<SourceEntity> executaLeituraDeSources(){
		
		CaminhadorDePasta caminhadorDePasta = new CaminhadorDePasta(getProjeto().getConfiguracao());
		
		final List<String> pathsSeparados = new ArrayList<String>();
		
		caminhadorDePasta.caminhar(null,new InterfaceDeAcao() {
			@Override
			public void executarAcao(File arquivo,String rootPath) {
				String path = arquivo.getAbsolutePath();
				path = path.replace(rootPath, "");
				if(!pathsSeparados.contains(path)){
					pathsSeparados.add(path);
				}
			}
		},true);
		
		final List<SourceEntity> sourceList = new ArrayList<SourceEntity>();
		
		for (String sourceFolder : getProjeto().getConfiguracao().getPastasDeFontes()) {
			SourceEntity sourceEntity = new SourceEntity();
			sourceFolder = sourceFolder.replace(getProjeto().getConfiguracao().getCaminhoProjeto(), "");
			sourceEntity.setRelativePath(getProjeto().getConfiguracao().getCaminhoProjeto());
			sourceEntity.setNome(sourceFolder);
			List<PackageEntity> packageEntityList = criaArvoreDePacotes(pathsSeparados,sourceFolder);
			sourceEntity.setPacotes(packageEntityList);
			sourceList.add(sourceEntity);
		}

		List<String> listaDePaths = new ArrayList<String>();

		caminhadorDePasta.caminhar(new InterfaceDeAcao() {
			@Override
			public void executarAcao(File arquivo,String rootPath) {
				try {
					String path = arquivo.getAbsolutePath();
					path = path.replace(rootPath, "").substring(1);
					listaDePaths.add(arquivo.getAbsolutePath());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
					
		});
		
		/*final List<IBinding> bindings = new ArrayList<IBinding>();
		
		final List<CompilationUnit> units = new ArrayList<CompilationUnit>();
		for (String path : listaDePaths) {
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setEnvironment(null, null, null, true);
			parser.setResolveBindings(true);
			parser.setStatementsRecovery(true);
			parser.setBindingsRecovery(true);
			
			FileASTRequestor requestor = new FileASTRequestor() {
				public void acceptBinding(String bindingKey, IBinding binding) {
					System.out.println("Bind key : "+bindingKey);
					bindings.add(binding);
				}
				public void acceptAST(String sourceFilePath, CompilationUnit ast) {
					units.add(ast);
				}
			};
			
			parser.setEnvironment(null, new String[] { getProjeto().getConfiguracao().getCaminhoProjeto() }, null, true);
			
			parser.createASTs(new String[] {path}, null, new String[] {""}, requestor, null);
		}
		System.out.println("Aki");*/
		
		/*for (SourceEntity sourceFolder : sourceList) {
			for (PackageEntity pacote : sourceFolder.getPacotes()) {
				ASTParser parser = ASTParser.newParser(AST.JLS3);
				parser.setEnvironment(null, null, null, true);
				parser.setResolveBindings(true);
				parser.setStatementsRecovery(true);
				parser.setBindingsRecovery(true);
				final List<IBinding> bindings = new ArrayList<IBinding>();
				
				final List<CompilationUnit> units = new ArrayList<CompilationUnit>();
				
				FileASTRequestor requestor = new FileASTRequestor() {
					public void acceptBinding(String bindingKey, IBinding binding) {
						System.out.println("Bind key : "+bindingKey);
						bindings.add(binding);
					}
					public void acceptAST(String sourceFilePath, CompilationUnit ast) {
						units.add(ast);
					}
				};
				
				parser.setEnvironment(null, new String[] { getProjeto().getConfiguracao().getCaminhoProjeto() }, null, true);
				
				parser.createASTs(new String[] {sourceFolder.getRelativePath()}, null, new String[] {pacote.getNome()}, requestor, null);
			}
			
		}
		System.out.println("Aki");*/
		
		
		/*for (String path : listaDePaths) {
			
			ASTParser parser = ASTParser.newParser(AST.JLS3);
			parser.setEnvironment(null, null, null, true);
			parser.setResolveBindings(true);
			parser.setStatementsRecovery(true);
			parser.setBindingsRecovery(true);
			final List<IBinding> bindings = new ArrayList<IBinding>();
			
			final List<CompilationUnit> units = new ArrayList<CompilationUnit>();
			
			FileASTRequestor requestor = new FileASTRequestor() {
				public void acceptBinding(String bindingKey, IBinding binding) {
					System.out.println("Bind key : "+bindingKey);
				}
				public void acceptAST(String sourceFilePath, CompilationUnit ast) {
					units.add(ast);
				}
			};
			
			parser.setEnvironment(null, new String[] { getProjeto().getConfiguracao().getCaminhoProjeto() }, null, true);
			
			parser.createASTs(new String[] {path}, null, new String[] {"xx"}, requestor, null);
			
		}*/
		
		caminhadorDePasta.caminhar(new InterfaceDeAcao() {

			@Override
			public void executarAcao(File arquivo,String rootPath) {
				try {
					String path = arquivo.getAbsolutePath();
					path = path.replace(rootPath, "").substring(1);
					DesmontadorDeClasse desmontadorDeClasse = new DesmontadorDeClasse();
					final CompilationUnit cu = desmontadorDeClasse
							.desmontar(arquivo,new String[]{rootPath});
					ClassASTTransformer transformador = new ClassASTTransformer(
							arquivo);
					cu.accept(transformador);
					getCompilationUnits().add(cu);
					if (transformador.getClass() != null) {
						//System.out.println(transformador.getClasse().toString());
						SourceEntity sourceEntity = null;
						for(SourceEntity srcEntity : sourceList){
							if (path.startsWith(srcEntity.getNome())){
								sourceEntity = srcEntity;
							}
						}
						if (sourceEntity != null){
							PackageEntity pacoteEncontrado = procuraPacoteByName(sourceEntity.getPacotes(), cu.getPackage().getStructuralProperty(PackageDeclaration.NAME_PROPERTY).toString());
							if (pacoteEncontrado != null){
								if (pacoteEncontrado.getClasses() == null){
									pacoteEncontrado.setClasses(new LinkedList<ClassEntity>());
								}
								pacoteEncontrado.getClasses().add(transformador.getClasse());
							}
						}
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		return sourceList;
	}
	
	private List<PackageEntity> criaArvoreDePacotes(List<String> pathsSeparados,String sourceFolder){
		List<PackageEntity> packageEntityList = new ArrayList<PackageEntity>();
		for (String path : pathsSeparados) {
			if (Util.isNullOrEmpty(path)){
				continue;
			}
			if (path.startsWith(File.separator)){
				path = path.substring(1,path.length());
			}
			if (!path.startsWith(sourceFolder)){
				continue;
			}else if (path.equals(sourceFolder)){
				continue;
			}else if (path.startsWith(sourceFolder)){
				path = path.substring(sourceFolder.length()+1);
			}
			path = path.replace(File.separator, ".");
			PackageEntity pacoteEncontrado = procuraPacoteByName(packageEntityList, path);
			if (pacoteEncontrado == null){
				PackageEntity pacoteAdd = new PackageEntity();
				pacoteAdd.setNome(path);
				packageEntityList.add(pacoteAdd);
			}else{
				if (pacoteEncontrado.getListaDePacotesFilhos() == null){
					pacoteEncontrado.setListaDePacotesFilhos(new ArrayList<PackageEntity>());
				}
				PackageEntity pacoteAdd = new PackageEntity();
				String nome = path.substring(path.indexOf(pacoteEncontrado.getNome())+pacoteEncontrado.getNome().length());
				pacoteAdd.setNome(nome.substring(1));
				pacoteEncontrado.getListaDePacotesFilhos().add(pacoteAdd);
			}
		}
		
		return packageEntityList;
	}
	
	private void organizaEstrutura(Map<PackageDeclaration,ClassEntity> mapDeClasses){
		for (Map.Entry<PackageDeclaration, ClassEntity> elemento : mapDeClasses.entrySet()) {
			System.out.println(elemento.getKey().toString());
		}
	}

	private PackageEntity procuraPacoteByName(List<PackageEntity> listaDePacotes, String pacoteProcurado){
		if (listaDePacotes == null){
			return null;
		}
		String[] pacoteProcuradoSplited = pacoteProcurado.split("\\.");
		
		PackageEntity pacotePai = null;
		for (String pacoteProcuradoSplitedItem : pacoteProcuradoSplited) {
			for (PackageEntity packageEntity : listaDePacotes) {
				if (pacoteProcuradoSplitedItem.equals(packageEntity.getNome())){
					pacotePai = packageEntity;
					break;
				}
			}
			if (pacotePai != null){
				PackageEntity filho = procuraPacoteByName(pacotePai.getListaDePacotesFilhos(), pacoteProcurado);
				if (filho != null){
					return filho;
				}else{
					return pacotePai;
				}
			}
		}
		
		return null;
	}
	
	public ProjectEntity getProjeto() {
		if (projeto == null){
			projeto = new ProjectEntity();
		}
		return projeto;
	}

	public List<CompilationUnit> getCompilationUnits() {
		if(compilationUnits == null){
			compilationUnits = new ArrayList<CompilationUnit>();
		}
		return compilationUnits;
	}

	public void setCompilationUnits(List<CompilationUnit> compilationUnits) {
		this.compilationUnits = compilationUnits;
	}

	
}
