/**
 * 
 */
package br.com.caiopaulucci.ast.descobridores;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;

import br.com.caiopaulucci.ast.ProjectConfigurator;
import br.com.caiopaulucci.ast.eclipseAST.desmontadores.DesmontadorDeClasse;
import br.com.caiopaulucci.ast.interfaces.InterfaceDeAcao;
import br.com.caiopaulucci.ast.model.entity.ProjectConfigurationEntity;

/**
 * @author Caio Paulucci
 *
 */
public class CaminhadorDePasta {

	ProjectConfigurationEntity configuracao = null;
	
	public CaminhadorDePasta() {
		
	}
	
	public CaminhadorDePasta(ProjectConfigurationEntity configuracao) {
		this.setConfiguracao(configuracao);
	}
	
	public void caminhar(InterfaceDeAcao acao){
		caminhar(null,acao);
	}
	
	private void caminhar(File root,InterfaceDeAcao acao,boolean pastaDeFontesProcessada,boolean somentePastas){
		if (root == null){
			root = new File(getConfiguracao().getCaminhoProjeto());
		}
		
		for (File filho : root.listFiles()) {
			if (filho.isDirectory()){
				if (!pastaDeFontesProcessada){
					if (getConfiguracao().getPastasDeFontes().contains(filho.getName())){
						if(somentePastas){
							acao.executarAcao(filho,getConfiguracao().getCaminhoProjeto());
						}
						caminhar(filho,acao,true,somentePastas);
					}
				}else{
					if(somentePastas){
						acao.executarAcao(filho,getConfiguracao().getCaminhoProjeto());
					}
					caminhar(filho,acao,true,somentePastas);
				}
				
			}else if (filho.isFile()){
				if(!somentePastas){
					if (getConfiguracao().getExtensoesAceitas().contains(FilenameUtils.getExtension(filho.getName()))){
						acao.executarAcao(filho,getConfiguracao().getCaminhoProjeto());
					}
				}
			}
		}
		
	}
	
	public void caminhar(File root,InterfaceDeAcao acao){
		caminhar(root, acao, false,false);
	}
	
	public void caminhar(File root,InterfaceDeAcao acao,boolean somentePastas){
		caminhar(root, acao, false,somentePastas);
	}
	
	public ProjectConfigurationEntity getConfiguracao() {
		if (configuracao == null){
			configuracao = new ProjectConfigurator().toEntity();
		}
		return configuracao;
	}

	private void setConfiguracao(ProjectConfigurationEntity configuracao) {
		this.configuracao = configuracao;
	}
	
	
	
}
