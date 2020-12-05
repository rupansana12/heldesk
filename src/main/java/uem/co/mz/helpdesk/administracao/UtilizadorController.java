package uem.co.mz.helpdesk.administracao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Textbox;

import uem.co.mz.helpdesk.model.Perfil;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.PerfilService;
import uem.co.mz.helpdesk.service.UnidadeOrganicaService;
import uem.co.mz.helpdesk.service.UtilizadorService;

public class UtilizadorController extends GenericForwardComposer<Component> {

	private static final long serialVersionUID = 1L;

	@WireVariable
	private UtilizadorService utilizadorService;

	@WireVariable
	private PerfilService perfilService;
		
	@WireVariable
	private UnidadeOrganicaService _unidadeOrganicaService;

	private Utilizador utilizador;
	private Textbox tbx_nome;
	private Textbox tbx_senha;
	private Textbox txb_username;
	private Textbox tbx_novaSenha;
	private Label lbl_nome;
	private Div div_dadosUser;
	private Div div_activo;
	private Div div_alterarSenha;
	private Div div_movecoa;
	private Div div_empresa;
	
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_procurar;
	private Button btn_cancelar;

	private Combobox cbxPerfil;
	private Combobox cbx_unidade_organica;
	private Listbox lbx_utlizadores;
	
	private Checkbox chx_alterarSenha;
	private Checkbox chx_activo;
	private Checkbox chx_utilizadorMovecoa;
	private Checkbox chx_utilizadorEmpresa;
	
	private List<Perfil> _listUserRoles;
	private List<UnidadeOrganica> _listUnidOrg;
	private ListModelList<UnidadeOrganica> _listModelUnidOrg;
	private ListModelList<Utilizador> _listModelUser;
	private ListModelList<Perfil> _listModelUserRole;
	
	
	private Utilizador _selectedUser;
	
	public void setUtilizador(Utilizador utilizador) {this.utilizador = utilizador;}
	public UtilizadorService getUtilizadorService() {return utilizadorService;}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		setUtilizador(new Utilizador());
		
        preencherPerfil();
        preencherEmpresa();
	}

	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		perfilService = (PerfilService) SpringUtil.getBean("perfilService");
		utilizadorService = (UtilizadorService) SpringUtil.getBean("utilizadorService");
		_unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
	}

	public void onClick$btn_gravar(Event e) {
		Set<Perfil> setUserRole = new HashSet<>();
		setUserRole.add((Perfil)cbxPerfil.getSelectedItem().getValue());
		utilizador.setUsername(tbx_nome.getValue());
		utilizador.setPassword(tbx_senha.getValue());
		utilizador.setEnabled(true);
		utilizador.setRoles(setUserRole);
		
		if(chx_utilizadorEmpresa.isChecked()){
			Set<UnidadeOrganica> empresas = new HashSet<UnidadeOrganica>();
			empresas.add((UnidadeOrganica) cbx_unidade_organica.getSelectedItem().getValue());
			utilizador.setEmpresas(empresas);
		}
		
		utilizadorService.create(utilizador);
		Clients.showNotification("Utilizador registado com sucesso!", "info",
				null, "before_center", -1);
		limparCampos();

	}
	
	public void onClick$btn_cancelar(){
		limparCampos();
	}
	
	public void onClick$btn_procurar(){
		_listModelUser = new ListModelList<Utilizador>(utilizadorService.procurarUser(txb_username.getValue()));
		lbx_utlizadores.setModel(_listModelUser);
	}
	
	public void onClick$btn_actualizar(){
		Set<Perfil> setUserRole = new HashSet<>();
		setUserRole.add((Perfil)cbxPerfil.getSelectedItem().getValue());
		_selectedUser.setUsername(tbx_nome.getValue());
		_selectedUser.setRoles(setUserRole);
		_selectedUser.setEnabled(chx_activo.isChecked());
		if(chx_alterarSenha.isChecked()){
			String senha = utilizadorService.encriptarSenha(tbx_novaSenha.getValue());
			_selectedUser.setPassword(senha);
		}
		
		if(chx_utilizadorEmpresa.isChecked()){
			Set<UnidadeOrganica> empresas = new HashSet<UnidadeOrganica>();
			empresas.add((UnidadeOrganica) cbx_unidade_organica.getSelectedItem().getValue());
			_selectedUser.setEmpresas(empresas);
		}
		
		utilizadorService.saveOrUpdate(_selectedUser);
		
		Clients.showNotification("Utilizador Actualizado com sucesso!", "info",
				null, "before_center", -1);
		limparCampos();
		
		
	}
	
	public void onSelect$lbx_utlizadores(){
		if(_listModelUser.isSelectionEmpty()){
			_selectedUser = null;
		}else{
			div_dadosUser.setVisible(false);
			_selectedUser =  lbx_utlizadores.getSelectedItem().getValue();
			tbx_nome.setValue(_selectedUser.getUsername());
			tbx_senha.setValue(_selectedUser.getPassword());
			tbx_senha.setReadonly(true);
			cbxPerfil.setValue(_selectedUser.getRoles().iterator().next().getDesignacao());
			if(!(_selectedUser.getEmpresas().isEmpty())){
				chx_utilizadorEmpresa.setChecked(true);
				chx_utilizadorMovecoa.setChecked(false);
				div_empresa.setVisible(true);
				cbx_unidade_organica.setValue(_selectedUser.getEmpresas().iterator().next().getDesignacao());
			}
			/*chx_alterarSenha.setVisible(true);
			chx_activo.setVisible(true);*/
			div_activo.setVisible(true);
			chx_activo.setChecked(_selectedUser.getEnabled());
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
			
			
			
			
		}
		
	}
		
	public void onCheck$chx_utilizadorEmpresa(){
		if(chx_utilizadorEmpresa.isChecked()){
			chx_utilizadorMovecoa.setChecked(false);
			div_empresa.setVisible(true);
		}else{
			chx_utilizadorMovecoa.setChecked(true);
			div_empresa.setVisible(false);
		}
	}
	
	public void onCheck$chx_utilizadorMovecoa(){
		if(chx_utilizadorMovecoa.isChecked()){
			chx_utilizadorEmpresa.setChecked(false);
			div_empresa.setVisible(false);
		}else{
			chx_utilizadorEmpresa.setChecked(true);
			div_empresa.setVisible(true);
		}
	}
	
	public void onCheck$chx_alterarSenha(){
		if(chx_alterarSenha.isChecked()){
			div_alterarSenha.setVisible(true);
		}else{
			div_alterarSenha.setVisible(false);
		}
	}
	
	public void preencherPerfil(){
		String[] perfis = {"Membro"};
		_listUserRoles = perfilService.listaPerfisMenosOPerfil(perfis);
		_listModelUserRole = new ListModelList<Perfil>(_listUserRoles);
		cbxPerfil.setModel(_listModelUserRole);
		
	}
	
	public void preencherEmpresa(){
		_listUnidOrg = _unidadeOrganicaService.buscarUnidadesOrganicas();
		_listModelUnidOrg = new ListModelList<UnidadeOrganica>(_listUnidOrg);
		cbx_unidade_organica.setModel(_listModelUnidOrg);
		
	}
	
	private void limparCampos() {
		tbx_nome.setRawValue(null);
		tbx_senha.setRawValue(null);
		cbxPerfil.setSelectedItem(null);
		cbxPerfil.setRawValue(null);
		cbx_unidade_organica.setSelectedItem(null);
		cbx_unidade_organica.setRawValue(null);
		tbx_senha.setReadonly(false);
		div_alterarSenha.setVisible(false);
		/*chx_alterarSenha.setVisible(false);
		chx_activo.setVisible(false);*/
		chx_alterarSenha.setChecked(false);
		chx_activo.setChecked(false);
		div_activo.setVisible(false);
		div_empresa.setVisible(false);
		chx_utilizadorMovecoa.setChecked(true);
		chx_utilizadorEmpresa.setChecked(false);
		_selectedUser = null;
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
		div_dadosUser.setVisible(false);
		
	}

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUser(Utilizador utilizador) {
		this.utilizador = utilizador;
	}
	
	   public boolean isValidEmailAddress(String email) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

}
