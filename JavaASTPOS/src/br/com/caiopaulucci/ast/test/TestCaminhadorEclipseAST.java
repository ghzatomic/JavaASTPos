/**
 * 
 */
package br.com.caiopaulucci.ast.test;

import java.util.List;
import java.util.Observable;

import br.com.caiopaulucci.ast.eclipseAST.LeitorDeProjeto;
import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;
import br.com.caiopaulucci.ast.model.entity.SourceEntity;
import br.com.caiopaulucci.ast.observadores.localizadores.LocalizadorObserver;
import br.com.caiopaulucci.ast.util.ObserverUtil;
/**
 * @author Caio Paulucci
 *
 */
public class TestCaminhadorEclipseAST {
	
	public static void main(String[] args) {
		LeitorDeProjeto leitor = new LeitorDeProjeto();
		/*IPath projectDotProjectFile = new Path(leitor.getProjeto().getConfiguracao().getCaminhoProjeto() + "/.project");
		
		IWorkspace ws = ResourcesPlugin.getWorkspace();
		try {
			IProjectDescription projectDescription = ws.loadProjectDescription(projectDotProjectFile);
			IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
			IProject m_project = root.getProject(projectDescription.getName());
			JavaCapabilityConfigurationPage.createProject(m_project, projectDescription.getLocationURI(),	null);
			IJavaProject javaProject = null;
			IPackageFragmentRoot srcFolder = null;
		
			// Create the project
			if (!m_project.exists()) {
				m_project.create(null);
			}
			m_project.open(null);
			javaProject = JavaCore.create(m_project);
		 
			IProjectDescription description = m_project.getDescription();
			description.setNatureIds(new String[] { JavaCore.NATURE_ID });
		 
			m_project.setDescription(description, null);
		 
			// need to check to make sure this is right JRE
			IClasspathEntry[] buildPath = {
					JavaCore.newSourceEntry(m_project.getFullPath().append("src")),};
				// set the classpath
			javaProject.setRawClasspath(buildPath, m_project.getFullPath()
					.append("bin"), null);
				// create the src folder
			IFolder folder = m_project.getFolder("src");
			if (!folder.exists())
				folder.create(true, true, null);
				srcFolder = javaProject.getPackageFragmentRoot(folder);
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		
		
		
		ObserverUtil.addObservadorAoLocalizador(new LocalizadorObserver(){
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof ResultadoBuscaDTO){
					ResultadoBuscaDTO argumento = (ResultadoBuscaDTO) arg;
					if (argumento.getPai() != null){
						if (argumento.getFuncao().contains("executaAcao")){
							System.out.println(argumento.getPai()+" - "+argumento.getLinha());
						}
					}
					
				}
			}
		});
		
		List<SourceEntity> lista = leitor.executaLeituraDeSources();
		/*SearchPattern pattern=SearchPattern.createPattern("",IJavaSearchConstants.TYPE,IJavaSearchConstants.TYPE,SearchPattern.R_EXACT_MATCH);
		IJavaSearchScope scope = SearchEngine.createJavaSearchScope((IJavaElement[])leitor.getIJavaElements().toArray(), true);
		
		SearchRequestor requestor = new SearchRequestor() {
			public void acceptSearchMatch(SearchMatch match) {
				System.out.println(match.getElement());
			}
		};
		
		SearchEngine searchEngine = new SearchEngine();
		try {
			searchEngine.search(pattern,
					new SearchParticipant[] { SearchEngine
							.getDefaultSearchParticipant() }, scope,
					requestor, null);
		} catch (Exception e) {
			System.out.println("exception");
			e.printStackTrace();
		}*/
		
		
		
		for (SourceEntity sourceEntity : lista) {
			System.out.println(sourceEntity);
		}
	}
	
}
