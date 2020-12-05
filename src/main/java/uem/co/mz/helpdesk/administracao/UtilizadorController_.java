package uem.co.mz.helpdesk.administracao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.DefaultTreeModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Window;

import uem.co.mz.helpdesk.model.Perfil;
import uem.co.mz.helpdesk.model.Permissoes;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.PerfilService;
import uem.co.mz.helpdesk.service.PermissoesService;
import uem.co.mz.helpdesk.service.UtilizadorService;
import uem.co.mz.helpdesk.util.ClientNotification;


public class UtilizadorController_ extends GenericForwardComposer{

	private Textbox txtUtilizador;
	private Textbox txtSenha;
	private Textbox txtConfirmarSenha;
	private Checkbox chx_activo;
	private Checkbox chx_alterarSenha;
	private Listbox lbxUtilizador;
	private Combobox cbxPerfil;//private Listbox lbxPerfil;
	
	private org.zkoss.zhtml.Div divConfirmarSenha;
	private org.zkoss.zhtml.Div divSenha;
	//private Tree tree;
	
	private Button btnProcurar;
	private Button btnGravar;
	private Button btnActualizar;
	private Button btnCancelar;
	
	private Window win;
	
	private Utilizador uti;
	private Utilizador selUti;
	
	private Permissoes perm;
	private Perfil per;
	private Perfil selPer;
	
	private List<Perfil> listPer;
	private ListModelList<Perfil> listModPer;
	
	private List<Utilizador> listUti;
	private ListModelList<Utilizador> listModUti;
	
	private ClientNotification cli = new ClientNotification();
	
	@WireVariable
	private UtilizadorService utiSer;
	
	@WireVariable 
	private  PermissoesService permSer;
	
	@WireVariable 
	private  PerfilService perSer;
	
	TreeModel<Perfil> treeModPer;
	DefaultTreeModel<Perfil> defTreeModPer;
	

	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		utiSer = (UtilizadorService)SpringUtil.getBean("utilizadorService");
		perSer = (PerfilService)SpringUtil.getBean("perfilService");
		permSer = (PermissoesService)SpringUtil.getBean("permissoesService");
	
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		//listaUtilizador();
		listaPerfil();
		
		
		
	}
	
	public void onClick$btnProcurar(){
		
		if(txtUtilizador.getText()!=""){
			
			Utilizador uti	= utiSer.buscarUtilizador(txtUtilizador.getText());
			
			if(uti==null){
				return;
				}
			else {
				List<Utilizador> lu = new ArrayList<Utilizador>();
				lu.add(uti);
				lbxUtilizador.setModel(new ListModelList<Utilizador>(lu));
			}
			
		}else {
			return;
		}
		
		
	}
	
	public void onClick$btnGravar(){
		
		if(btnGravar.getLabel().equals("Novo")){
			
			divConfirmarSenha.setVisible(true);
			divSenha.setVisible(true);
			txtConfirmarSenha.setRawValue(null);
			txtSenha.setRawValue(null);
			
			cbxPerfil.setVisible(true);
			btnGravar.setLabel("Gravar");
			
			
		}else{
			
			if (txtConfirmarSenha.getText().equals(txtSenha.getText())) {
				
				uti = new Utilizador();
				uti.setUsername(txtUtilizador.getValue());
				uti.setPassword(txtSenha.getValue());
				uti.setPlanPass(txtSenha.getValue());
				uti.setEnabled(chx_activo.isChecked());
				
				Perfil p = cbxPerfil.getSelectedItem().getValue();
				Set<Perfil> set = new HashSet<Perfil>();
				set.add(p);
				uti.setRoles(set);
				
				utiSer.criarUtilizador(uti);
				cli.Msg("Utilizador registada com Sucesso", win);
				limpaCampos();
				
				listaUtilizador();
			}else{
				
				cli.Msg("Verifique a senha", win);
			}
		
		
		
		}
	}
	
	public void onClick$btnActualizar(){
		
		selUti.setUsername(txtUtilizador.getValue());
		
		if (txtConfirmarSenha.getText().equals(txtSenha.getText())) {
			selUti.setPassword(txtSenha.getValue());
		}else{
			
			cli.Msg("Verifique a senha", win);
			return;
		}
		//uti.setPlanPass(txtSenha.getValue());
		selUti.setEnabled(chx_activo.isChecked());
		
		Perfil p = cbxPerfil.getSelectedItem().getValue();
		Set<Perfil> set = new HashSet<Perfil>();
		set.add(p);
		selUti.setRoles(set);
		
		utiSer.actualizarUtilizador(selUti);
		cli.Msg("Utilizador registada com Sucesso", win);
		limpaCampos();
		
		listaUtilizador();
	}

	public void onClick$btnCancelar(){
	
		limpaCampos();
	}
	
	public void onSelect$lbxUtilizador(){		
		selUti = (Utilizador)lbxUtilizador.getSelectedItem().getValue();
		txtUtilizador.setValue(selUti.getUsername());
		txtSenha.setValue("***********");
		txtConfirmarSenha.setValue("***********");
		chx_activo.setChecked(selUti.isEnabled());
		
		 Set<Perfil> setPer = selUti.getRoles();
		 List<Perfil> lisPer = new ListModelList<Perfil>(setPer);
		 //alert(""+lisPer.iterator().next().getDesignacao());
		 cbxPerfil.setValue(lisPer.get(0).getDesignacao());
		
		//divConfirmarSenha.setVisible(true);
		//lbxPerfil
		
		cbxPerfil.setVisible(true);
		
		
		
	}
	
	public void onCheck$chx_alterarSenha(){
		
		if(txtUtilizador.getText()!=""){
			divConfirmarSenha.setVisible(true);
			txtSenha.setRawValue(null);
			divSenha.setVisible(true);
			txtConfirmarSenha.setRawValue(null);
			
			btnActualizar.setVisible(true);
			btnGravar.setVisible(false);
			
		}
	}
	
	public void limpaCampos(){		
		txtUtilizador.setRawValue(null);
		txtConfirmarSenha.setRawValue(null);
		txtSenha.setRawValue(null);
		chx_activo.setChecked(true);
		chx_alterarSenha.setChecked(false);
		btnActualizar.setVisible(false);
		btnGravar.setVisible(true);
		btnGravar.setLabel("Novo");
		divConfirmarSenha.setVisible(false);
		divSenha.setVisible(false);
		//txtSenha.setDisabled(true);
		
		cbxPerfil.setModel(listModPer);
		cbxPerfil.setRawValue(null);
		//lbxUtilizador.getItems().clear();
		//cbxPerfil.setVisible(false);
	}
	
	public void listaUtilizador(){
		//listUti = utiSer.getAll();
		listUti = new ArrayList<Utilizador>();
		listModUti = new ListModelList<Utilizador>(listUti);
		lbxUtilizador.setModel(listModUti);
	}
	
	public void listaPerfil(){
		
		//List<Perfil> perList = perSer.getAll();
		//treeModPer =  (TreeModel<Perfil>) perList; 
		//treeModPer = (TreeModel<Perfil>) perSer.getAll();
		
		listPer = perSer.getAll();
		listModPer = new ListModelList<Perfil>(listPer);
		//listModPer.setMultiple(true);
		cbxPerfil.setModel(listModPer);
		//treeModPer =(TreeModel<Perfil>) listPer;
		//tree.setModel(treeModPer);
	}
	
}
