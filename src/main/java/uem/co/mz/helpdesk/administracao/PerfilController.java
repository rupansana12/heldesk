package uem.co.mz.helpdesk.administracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.A;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.co.mz.helpdesk.model.Perfil;
import uem.co.mz.helpdesk.model.Permissoes;
import uem.co.mz.helpdesk.service.PerfilService;
import uem.co.mz.helpdesk.service.PermissoesService;
import uem.co.mz.helpdesk.util.ClientNotification;

public class PerfilController extends GenericForwardComposer {
	
	@WireVariable 
	private PerfilService perSer;
	
	@WireVariable 
	private  PermissoesService permSer;
	
	private Perfil per;
	private Perfil selPer;
	
	private List<Perfil> listPer;
	private ListModelList<Perfil> listModPer;
	
	private List<Permissoes> listPerm = new ArrayList<Permissoes>();
	private Set<Permissoes> setPerm = new HashSet<Permissoes>();
	private ListModelList<Permissoes> listModPerm;
	private ListModelList<Permissoes> listModPermPer;
	private ListModelList<Permissoes> listModPermGetAll;
	
	private List<Permissoes> listPermPer = new ArrayList<Permissoes>();
	private Set<Permissoes> setPermPer = new HashSet<Permissoes>();

	private Textbox txtDesignacao;
	private Listheader lhrRemove;
	private Listbox lbxPermissoes;
	private Listbox lbxPermissoesPerfil;
	private Listbox lbxPerfil;
	private Combobox cbxPerfil;
	
	//private DualListbox dual_direitos;
	private A chooseAll;
	private A chooseBtn;
	private A removeBtn;
	private A removeAll;
	
	//private Button btnGravar;
	private Button btnNovoPerfil;
	private Button btnActualizar;
	private Button btnCancelar;
	
	private Window win;
	
	private ClientNotification cli = new ClientNotification();
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		perSer = (PerfilService)SpringUtil.getBean("perfilService");
		permSer = (PermissoesService)SpringUtil.getBean("permissoesService");
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		listaPerfil();
		listaPermissoes();
		listaPerfilGetAll();
		lhrRemove.setVisible(false);
		txtDesignacao.setVisible(false);
		cbxPerfil.setVisible(true);
	}
	
	
	public void onClick$chooseAll(){
		
		if(cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()==""){
			
			return;
		}else{
		ListModelList<Permissoes> listModelPermAll;
		
		ListModel<Permissoes> lp = lbxPermissoes.getModel();
		ListModelList<Permissoes> lmp = new ListModelList<Permissoes>((Collection<? extends Permissoes>) lp);
		for (Permissoes p: lmp){
			
			listPermPer.add(p);
			 setPermPer.add(p);
			 listPerm.remove(p);

		}
		selPer.setPermissoes(setPermPer);
		listModPermPer = new ListModelList<Permissoes>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);

		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		}
		
		
	}
	
	
	public void onClick$chooseBtn(){

		Set<Listitem> listSelectItens = lbxPermissoes.getSelectedItems();
		
		if (cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()=="") {
			return;
		} else {
			
		for(final Listitem li: listSelectItens){
			 Permissoes dis = (Permissoes)li.getValue();
			 
			 listPermPer.add(dis);
			 setPermPer.add(dis);

			 listPerm.remove(dis);
			 setPerm.remove(dis);
		}
		selPer.setPermissoes(setPermPer);
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		listaPermissoesPerfil();
		
		}
	}
	
	public void onClick$removeBtn(){
		
		Set<Listitem> listSelectItens = lbxPermissoesPerfil.getSelectedItems();

		if (cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()=="") {
			return;
		} else {
			
		for(final Listitem li: listSelectItens){
			
			Permissoes dis = li.getValue();
			 listPerm.add(dis);
			 setPerm.add(dis);
			 
			listPermPer.remove(dis);
			 setPermPer.remove(dis);
		}
		selPer.setPermissoes(setPermPer);
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);

		listaPermissoesPerfil();
		lbxPermissoes.clearSelection();
		
		}
		
	}

	
	public void onClick$removeAll(){
		
		if(cbxPerfil.getSelectedItem()==null && txtDesignacao.getText()==""){
			
			return;
		}else{
		ListModelList<Permissoes> listModelPermAll;
		
		ListModel<Permissoes> lp = lbxPermissoesPerfil.getModel();
		ListModelList<Permissoes> lmp = new ListModelList<Permissoes>((Collection<? extends Permissoes>) lp);
		for (Permissoes p: lmp){
			listPerm.add(p);
			listPermPer.remove(p);
			 setPermPer.remove(p);
		}
		selPer.setPermissoes(setPermPer);
		
		listModPermPer = new ListModelList<Permissoes>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPerm);

		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		//lbxPermissoes.getItems().clear();
		lbxPermissoes.setModel(listModPerm);
	}
	}
	
	/*public void onClick$btnGravar(){
		
		
		perSer.saveOrUpdate(selPer);
		cli.Msg("Permissoes criadas com sucesso", win);
		listaPerfilGetAll();
		listaPermissoes();
		limpaCampos();
		
	}*/
	
	public void onClick$btnNovoPerfil(){
		
		if(btnNovoPerfil.getLabel().equals("Novo Perfil")){
			
			txtDesignacao.setVisible(true);
			cbxPerfil.setVisible(false);
			btnNovoPerfil.setLabel("Gravar");
			//btnGravar.setVisible(false);
			selPer = new Perfil();
		}else {
			
			
			selPer.setDesignacao(txtDesignacao.getValue());
			selPer.setType("Normal");
			List<Permissoes> lisPer = new ArrayList<Permissoes>();
			Set<Permissoes> setP = new HashSet<Permissoes>();
			ListModel<Permissoes> lp = lbxPermissoesPerfil.getModel();
			
			if(lbxPermissoesPerfil.getItemCount()<1){
				
				//alert("empty");
				selPer.setPermissoes(null);
			}else{
			ListModelList<Permissoes> lmp = new ListModelList<Permissoes>((Collection<? extends Permissoes>) lp);
			for (Permissoes p: lmp){
				lisPer.add(p);
				setP.add(p);
			}
				selPer.setPermissoes(setP);
			}
			
			perSer.saveOrUpdate(selPer);
			btnNovoPerfil.setLabel("Novo Perfil");
			cli.Msg("Permissoes criadas com sucesso", win);
			listaPerfilGetAll();
			listaPermissoes();
			limpaCampos();
			
		}
		
		
	}
	
	public void onClick$btnActualizar(){
		
		perSer.saveOrUpdate(selPer);
		cli.Msg("Permissoes criadas com sucesso", win);
		listaPerfilGetAll();
		listaPermissoes();
		limpaCampos();
	}

	public void onClick$btnCancelar(){
		limpaCampos();
		btnActualizar.setVisible(false);
		//btnGravar.setVisible(true);
	}
	
	public void onSelect$lbxPermissoesPerfil(){
		
		
		
	
	
	}
	public void onSelect$lbxPerfil(){
		//listPermPer = new ArrayList<Permissoes>();
		
		//lbxPermissoesPerfil.getItems().clear();
		selPer = (Perfil)lbxPerfil.getSelectedItem().getValue();
		cbxPerfil.setValue(selPer.getDesignacao());
		btnNovoPerfil.setVisible(false);
		
		//alert(""+selPer.getDesignacao());
		listPermPer = new ListModelList<Permissoes>(selPer.getPermissoes());
		
		setPermPer = new HashSet<Permissoes>(listPermPer);
		selPer.setPermissoes(setPermPer);
		listaPermissoesPerfil();
		
		//Set<Permissoes> sper = new HashSet<Permissoes>();
		//listaPermissoes();
		if(!selPer.getPermissoes().isEmpty()){
			listPerm = permSer.listaPermissoesNaoSelecionadas(selPer.getPermissoes());
			setPerm = new HashSet<Permissoes>(listPerm);
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
		
		}else {
			listModPermGetAll.setMultiple(true);
			listPerm = new ListModelList<Permissoes>(listModPermGetAll);
			setPerm = new HashSet<Permissoes>(listPerm);
			lbxPermissoes.setModel(listModPermGetAll);
		}
		//sper.addAll(pList);
		//alert("list"+pList);
		
		/*listPerm.removeAll(selPer.getPermissoes());
		
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);*/
		
		
		
		btnActualizar.setVisible(true);
		//btnGravar.setVisible(false);
		lhrRemove.setVisible(true);
	}
	
	public void onClickRemoverPermissoes(ForwardEvent e){
		
		Permissoes p = (Permissoes) e.getData();
		
		listPerm.add(p);
		listPermPer.remove(p);
		setPermPer = new HashSet<Permissoes>(listPermPer);
		selPer.setPermissoes(setPermPer);
		//perSer.update(selPer);
		
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
		listModPermPer = new ListModelList<Permissoes>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);
		
	}
	
	public void onSelect$cbxPerfil(){
		
		//selPer = new Perfil();
		selPer = (Perfil) cbxPerfil.getSelectedItem().getValue();
		//alert(""+selPer.getDesignacao());
		lbxPermissoesPerfil.getItems().clear();
		listModPerm = new ListModelList<Permissoes>();
	}
	
	public void limpaCampos(){
		
		lbxPermissoesPerfil.getItems().clear();
		listModPerm = new ListModelList<Permissoes>();
		cbxPerfil.setRawValue(null);
		btnActualizar.setVisible(false);
		//btnGravar.setVisible(true);
		
		listPermPer = new ArrayList<Permissoes>();
		listModPerm = new ListModelList<Permissoes>();
		listModPermPer = new ListModelList<Permissoes>();
		
		listModPermGetAll.setMultiple(true);
		listPerm = new ListModelList<Permissoes>(listModPermGetAll);
		setPerm = new HashSet<Permissoes>(listPerm);
		lbxPermissoes.setModel(listModPermGetAll);
		
		lhrRemove.setVisible(false);
		lbxPermissoesPerfil.setModel(new ListModelList<Permissoes>());
		
		txtDesignacao.setRawValue(null);
		txtDesignacao.setVisible(false);
		cbxPerfil.setVisible(true);
		btnNovoPerfil.setLabel("Novo Perfil");
		btnNovoPerfil.setVisible(true);
	}
	
	public void listaPerfil(){
		listPer = perSer.getAll();
		listModPer = new ListModelList<Perfil>(listPer);
		cbxPerfil.setModel(listModPer);
		
	}
	
	public void listaPerfilGetAll(){
		listPer = perSer.getAll();
		listModPer = new ListModelList<Perfil>(listPer);
		lbxPerfil.setModel(listModPer);
		
	}
	
	public void listaPermissoes(){
		
		listPerm =  permSer.getAll();
		listModPerm = new ListModelList<Permissoes>(listPerm);
		listModPermGetAll = new ListModelList<Permissoes>(listPerm);
		listModPerm.setMultiple(true);
		lbxPermissoes.setModel(listModPerm);
		
	}
	
	public void listaPermissoesPerfil(){
		
		//listPerm = (Set<Permissoes>) permSer.getAll();
		listModPermPer = new ListModelList<Permissoes>(listPermPer);
		listModPermPer.setMultiple(true);
		lbxPermissoesPerfil.setModel(listModPermPer);
		
	}
	
	
	
	
}
