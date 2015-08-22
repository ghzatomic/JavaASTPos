/**
 * 
 */
package br.com.caiopaulucci.ast;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.com.caiopaulucci.ast.model.entity.ProjectConfigurationEntity;
import br.com.generalpurposeobjects.util.Util;

/**
 * @author Caio Paulucci
 *
 */
public class ProjectConfigurator {

	private static final Logger logger = Logger.getLogger(ProjectConfigurator.class);
	
	public static String NOME_CONFIG_PADRAO = "projectConf";
	
	public static String PATH_CONFIG_PADRAO;
	
	public static Properties PADRAO_CONF;
	
	static{
		start();
	}
	
	public static void start(){
		PATH_CONFIG_PADRAO = ProjectConfigurator.class.getProtectionDomain().getCodeSource().getLocation().getPath().replace("%5c", "")+ ".." + File.separator + NOME_CONFIG_PADRAO+".properties";
		File arquivoConf = new File(PATH_CONFIG_PADRAO);
		if (arquivoConf.exists()){
			try {
				PADRAO_CONF.load(new FileInputStream(arquivoConf));
			} catch (Exception e) {
				logger.error(e);
			} 
		}else{
			ClassLoader classLoader = ProjectConfigurator.class.getClassLoader();
			InputStream stream = classLoader.getResourceAsStream(NOME_CONFIG_PADRAO+".properties");
			PADRAO_CONF = new Properties();
			try {
				PADRAO_CONF.load(stream);
			} catch (IOException e) {
				logger.error(e);
			}
		}
	}
	
	public ProjectConfigurationEntity toEntity(){
		ProjectConfigurationEntity ret = new ProjectConfigurationEntity();
		ret.setCaminhoProjeto(this.getCaminhoProjeto());
		ret.setExtensoesAceitas(this.getExtensoesAceitas());
		ret.setNomeProjeto(this.getNomeProjeto());
		ret.setNomesAceitos(this.getNomesAceitos());
		ret.setPastasDeFontes(this.getPastasDeFontes());
		//ret.set(this.get());
		return ret;
	}
	
	public ProjectConfigurator() {
		this.setNomeProjeto(PADRAO_CONF.getProperty("nome_projeto"));
		this.setCaminhoProjeto(PADRAO_CONF.getProperty("caminho_projeto"));
		
		String localNomesAceitos = PADRAO_CONF.getProperty("nomes_aceitos");
		ArrayList<String> nomesAceitos = new ArrayList<String>();
		if (!Util.isNullOrEmpty(localNomesAceitos)){
			String[] splited = localNomesAceitos.split(",");
			for (String splitedIterator : splited) {
				nomesAceitos.add(splitedIterator.trim());
			}
		}
		this.setNomesAceitos(nomesAceitos);
		
		String localExtAceitas = PADRAO_CONF.getProperty("extensoes_aceitas");
		ArrayList<String> extAceitas = new ArrayList<String>();
		if (!Util.isNullOrEmpty(localExtAceitas)){
			String[] splited = localExtAceitas.split(",");
			for (String splitedIterator : splited) {
				extAceitas.add(splitedIterator.trim().replace(".", ""));
			}
		}
		this.setExtensoesAceitas(extAceitas);
		
		String localPastasDeFontes = PADRAO_CONF.getProperty("pastas_fontes");
		ArrayList<String> pastasDeFontes = new ArrayList<String>();
		if (!Util.isNullOrEmpty(localPastasDeFontes)){
			String[] splited = localPastasDeFontes.split(",");
			for (String splitedIterator : splited) {
				pastasDeFontes.add(splitedIterator.trim());
			}
		}else{
			/*if (!Util.isNullOrEmpty(this.getCaminhoProjeto())){
				IWorkspaceRoot root = //new ;
				IProject project = root.getProject(getCaminhoProjeto());
				if (project.exists()){
					try {
						project.open(null);
						IJavaProject javaProject = JavaCore.create(project);
						IClasspathEntry[] classPaths = javaProject.getResolvedClasspath(true);
						for (IClasspathEntry iClasspathEntry : classPaths) {
							System.out.println(iClasspathEntry.getSourceAttachmentPath().toFile().getAbsolutePath());
						}
					} catch (CoreException e) {
						logger.error(e);
					}
				}else{
					logger.error("Projeto não existe");
				}
			}else{
				logger.error("Caminho inválido");
			}*/
		}
		this.setPastasDeFontes(pastasDeFontes);
			
	}
	
	private String nomeProjeto;
	
	private List<String> pastasDeFontes;
	
	private List<String> nomesAceitos;
	
	private List<String> extensoesAceitas;
	
	private String caminhoProjeto;

	public String getNomeProjeto() {
		return nomeProjeto;
	}

	public void setNomeProjeto(String nomeProjeto) {
		this.nomeProjeto = nomeProjeto;
	}

	public List<String> getPastasDeFontes() {
		return pastasDeFontes;
	}

	public void setPastasDeFontes(List<String> pastasDeFontes) {
		this.pastasDeFontes = pastasDeFontes;
	}

	public List<String> getNomesAceitos() {
		return nomesAceitos;
	}

	public void setNomesAceitos(List<String> nomesAceitos) {
		this.nomesAceitos = nomesAceitos;
	}

	public List<String> getExtensoesAceitas() {
		return extensoesAceitas;
	}

	public void setExtensoesAceitas(List<String> extensoesAceitas) {
		this.extensoesAceitas = extensoesAceitas;
	}

	public String getCaminhoProjeto() {
		return caminhoProjeto;
	}

	public void setCaminhoProjeto(String caminhoProjeto) {
		if (this.caminhoProjeto != null){
			if (!this.caminhoProjeto.endsWith(File.separator)){
				this.caminhoProjeto = this.caminhoProjeto+ File.separator;
			}
		}
		this.caminhoProjeto = caminhoProjeto;
	}
	

}
