package controle;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionListener;

import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.SelectableDataModel;
import org.primefaces.model.SortOrder;
import org.primefaces.model.TreeNode;
import org.primefaces.model.mindmap.DefaultMindmapNode;
import org.primefaces.model.mindmap.MindmapNode;

import br.com.caiopaulucci.ast.eclipseAST.LeitorDeProjeto;
import br.com.caiopaulucci.ast.model.dto.ResultadoBuscaDTO;
import br.com.caiopaulucci.ast.model.entity.ClassEntity;
import br.com.caiopaulucci.ast.model.entity.MethodEntity;
import br.com.caiopaulucci.ast.model.entity.PackageEntity;
import br.com.caiopaulucci.ast.model.entity.SourceEntity;
import br.com.caiopaulucci.ast.model.entity.StatementEntity;
import br.com.caiopaulucci.ast.observadores.localizadores.LocalizadorObserver;
import br.com.caiopaulucci.ast.util.ObserverUtil;
import br.com.generalpurposeobjects.util.Util;
import datamodel.CustomLazyDataModel;

@ManagedBean(name="arvoreSourceView")
@ViewScoped
public class ArvoreSourceView implements Serializable {

	private TreeNode rootTreeSources = null;

	private LeitorDeProjeto leitorDeProjeto = null;
	
	private TreeNode selectedNode;
	
	private String objNome;
	
	private String funcaoNome;
	
	private List<ResultadoBuscaDTO> resultados = null;
	
	private ResultadoDataModel resultadoDataModel = null;
	
	private MindmapNode mmRoot = null;
	
	private MindmapNode mmSelectedNode;
	
	@PostConstruct
	public void init(){
		
		ObserverUtil.addObservadorAoLocalizador(new LocalizadorObserver(){
			@Override
			public void update(Observable o, Object arg) {
				if (arg instanceof ResultadoBuscaDTO){
					ResultadoBuscaDTO argumento = (ResultadoBuscaDTO) arg;
					String criterio = "";
					if (Util.isNotNullOrEmpty(getObjNome())){
						criterio=getObjNome()+".";
					}
					if (Util.isNotNullOrEmpty(getFuncaoNome())){
						criterio+=getFuncaoNome();
					}
					if (Util.isNotNullOrEmpty(criterio)){
						if (argumento.toString().contains(criterio)){
							getResultados().add(argumento);
						}
					}
					
				}
			}
		});
		
	}
	
	public void onClickMetodo(ActionListener evento){
		System.out.println("Clicou !");
	}
	
	protected void refreshResultadosListDataModel(){
		ResultadoDataModel dataModel = new ResultadoDataModel() {
			
			private static final long serialVersionUID = 1L;

			@Override
			 public List<ResultadoBuscaDTO> loadData(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
				return getResultados();
			}
		};
		try {
			dataModel.setRowCount(getResultados().size());
		} catch (Exception e) {
			e.printStackTrace();
		} 
		this.setResultadoDataModel(dataModel);
	}
	
	private List<TreeNode> montaArvoreDeSources(TreeNode root){
		List<TreeNode> listaDeSources = new ArrayList<TreeNode>();
		List<SourceEntity> listaDeEntidadesSource = getLeitorDeProjeto().executaLeituraDeSources();
		for (SourceEntity sourceEntity : listaDeEntidadesSource) {
			TreeNode sourceNode = new DefaultTreeNode("source",sourceEntity,root);
			MindmapNode n = new DefaultMindmapNode(sourceEntity.getNome());
			n.setSelectable(true);
			this.getMmRoot().addNode(n);
			sourceNode.setExpanded(true);
			montaEstruturaDePacotes(sourceEntity, sourceNode,n);
			listaDeSources.add(sourceNode);
		}
		return listaDeSources;
	}
	
	public void executaLeitura(){
		this.getResultados().clear();
		List<TreeNode> listaDeSources = new ArrayList<TreeNode>();
		List<SourceEntity> listaDeEntidadesSource = getLeitorDeProjeto().executaLeituraDeSources();
		refreshResultadosListDataModel();
	}
	
	private void montaEstruturaDePacotes(SourceEntity source,TreeNode root,MindmapNode nPai){
		if (source.getPacotes() != null){
			for (PackageEntity pacote : source.getPacotes()) {
				MindmapNode n = new DefaultMindmapNode(pacote.getNome(),pacote);
				n.setSelectable(true);
				nPai.addNode(n);
				TreeNode noPai = new DefaultTreeNode("pacote",pacote,root);
				noPai.setExpanded(true);
				if (pacote.getListaDePacotesFilhos() != null){
					for (PackageEntity pacoteFilho : pacote.getListaDePacotesFilhos()) {
						montaEstruturaDePacotes(pacoteFilho, noPai,n);
						noPai.setExpanded(true);
					}
				}
			}
		}
	}
	
	private void montaEstruturaDePacotes(PackageEntity pacote ,TreeNode root,MindmapNode nPai){
		if (pacote != null){
			TreeNode noPai = new DefaultTreeNode("pacote",pacote,root);
			if (pacote.getClasses() != null){
				for (ClassEntity classe : pacote.getClasses()) {
					TreeNode noClasse = new DefaultTreeNode("classe",classe,noPai);
					MindmapNode n = new DefaultMindmapNode(classe.getNome(),classe);
					n.setSelectable(true);
					nPai.addNode(n);
					noClasse.setExpanded(true);
					montaEstruturaDeFuncoes(classe, noClasse,n);
				}
			}
			if (pacote.getListaDePacotesFilhos() != null){
				for (PackageEntity pacoteFilho : pacote.getListaDePacotesFilhos()) {
					MindmapNode n = new DefaultMindmapNode(pacote.getNome(),pacoteFilho);
					n.setSelectable(true);
					nPai.addNode(n);
					montaEstruturaDePacotes(pacoteFilho, noPai,n);
					noPai.setExpanded(true);
				}
			}
		}
	}
	
	private void montaEstruturaDeFuncoes(ClassEntity classe,TreeNode noClasse ,MindmapNode nPai){
		if (classe.getMetodos() != null){
			for (MethodEntity metodo : classe.getMetodos()) {
				TreeNode noMetodo = new DefaultTreeNode("metodo",metodo,noClasse);
				MindmapNode n = new DefaultMindmapNode(metodo.getNome(),metodo);
				n.setSelectable(true);
				nPai.addNode(n);
				if (metodo != null){
					montaEstruturaDeMetodo(metodo, n);
				}
				
				noMetodo.setExpanded(false);
			}
		}
	}
	
	private void montaEstruturaDeMetodo(MethodEntity metodo ,MindmapNode nPai){
		int x = 1;
		if (metodo.getCorpo() != null){
			if (metodo.getCorpo().getStatementList() != null){
				for (StatementEntity stmt : metodo.getCorpo().getStatementList()) {
					MindmapNode n = new DefaultMindmapNode("Expressão "+x,stmt);
					n.setSelectable(true);
					nPai.addNode(n);
					x++;
				}
			}
			
		}
		
	}
	
	public void onNodeDblselect(SelectEvent event) {
        this.mmSelectedNode = (MindmapNode) event.getObject();        
    }
	
	public TreeNode getRootTreeSources() {
		if (rootTreeSources == null){
			rootTreeSources = new DefaultTreeNode("Root",null);
			montaArvoreDeSources(rootTreeSources);
		}
		return rootTreeSources;
	}

	public void setRootTreeSources(TreeNode rootTreeSources) {
		this.rootTreeSources = rootTreeSources;
	}

	public LeitorDeProjeto getLeitorDeProjeto() {
		if (leitorDeProjeto == null){
			leitorDeProjeto = new LeitorDeProjeto();
		}
		return leitorDeProjeto;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public String getCodigoSelectedNode(){
		if (this.getSelectedNode() != null) {
			if (this.getSelectedNode().getData() instanceof ClassEntity) {
				return ((ClassEntity) this.getSelectedNode().getData())
						.toString();
			}else if (this.getSelectedNode().getData() instanceof MethodEntity) {
				return ((MethodEntity) this.getSelectedNode().getData())
						.toString();
			}
		}
		return "";
	}
	
	public void displaySelectedClass(NodeSelectEvent event) {  
        if(this.getSelectedNode() != null) {
        	if (this.getSelectedNode().getData() instanceof ClassEntity){
        		//System.out.println(((ClassEntity)this.getSelectedNode().getData()).toString());
        	}else if (this.getSelectedNode().getData() instanceof MethodEntity){
        		this.setFuncaoNome(((MethodEntity)this.getSelectedNode().getData()).getNome());
        	}
        } 
    }

	
	private static abstract class ResultadoDataModel extends CustomLazyDataModel<ResultadoBuscaDTO> implements SelectableDataModel<ResultadoBuscaDTO>{
		
		private static final long serialVersionUID = 1L;

		@Override
		public ResultadoBuscaDTO getRowData(String rowKey) {
			List<ResultadoBuscaDTO> objetos = (List<ResultadoBuscaDTO>) getWrappedData();  
	        
	        for(ResultadoBuscaDTO obj : objetos) {  
	            if(String.valueOf(obj.toString()).equals(rowKey))  
	                return obj;  
	        } 
			return null;
		}
		
		@Override
		public Object getRowKey(ResultadoBuscaDTO arg0) {
			return arg0;
		}

	
	}
	
	public String getObjNome() {
		return objNome;
	}

	public void setObjNome(String objNome) {
		this.objNome = objNome;
	}

	public String getFuncaoNome() {
		return funcaoNome;
	}

	public void setFuncaoNome(String funcaoNome) {
		this.funcaoNome = funcaoNome;
	}

	public List<ResultadoBuscaDTO> getResultados() {
		if (resultados == null){
			resultados = new ArrayList<ResultadoBuscaDTO>();
		}
		return resultados;
	}

	public void setResultados(List<ResultadoBuscaDTO> resultados) {
		this.resultados = resultados;
	}

	public ResultadoDataModel getResultadoDataModel() {
		if (resultadoDataModel == null){
			refreshResultadosListDataModel();
		}
		return resultadoDataModel;
	}

	public void setResultadoDataModel(ResultadoDataModel resultadoDataModel) {
		this.resultadoDataModel = resultadoDataModel;
	}

	public MindmapNode getMmRoot() {
		if (mmRoot == null){
			mmRoot = new DefaultMindmapNode("Sistema");
			mmRoot.setSelectable(false);
		}
		return mmRoot;
	}

	public void setMmRoot(MindmapNode mmRoot) {
		this.mmRoot = mmRoot;
	}  

	public void onNodeSelect(SelectEvent event) {
        MindmapNode node = (MindmapNode) event.getObject();
         
        /*if(label.equals("NS(s)")) {
            for(int i = 0; i < 25; i++) {
                node.addNode(new DefaultMindmapNode("ns" + i + ".google.com", "Namespace " + i + " of Google", "82c542", false));
            }
        }
        else if(label.equals("IPs")) {
            for(int i = 0; i < 18; i++) {
                node.addNode(new DefaultMindmapNode("1.1.1."  + i, "IP Number: 1.1.1." + i, "fce24f", false));
            } 
        }
        else if(label.equals("Malware")) {
            for(int i = 0; i < 18; i++) {
                String random = UUID.randomUUID().toString();
                node.addNode(new DefaultMindmapNode("Malware-"  + random, "Malicious Software: " + random, "3399ff", false));
            }
        }   */
    }

	public MindmapNode getMmSelectedNode() {
		return mmSelectedNode;
	}

	public void setMmSelectedNode(MindmapNode mmSelectedNode) {
		this.mmSelectedNode = mmSelectedNode;
	}
	
	

}
