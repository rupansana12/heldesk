package uem.co.mz.helpdesk.administracao;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.UtilizadorService;

public class AlterarSenhaController extends GenericForwardComposer{
	
	private Textbox txtSenhaActual;
	private Textbox txtSenha;
	private Textbox txtConfirmarSenha;
	
	private Button btnGravar;
	private Button btnCancelar;
	
	private Window win_alterar_senha;
	
	private UtilizadorService utilizadorService;
	
	private Utilizador user;
	
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		utilizadorService = (UtilizadorService)SpringUtil.getBean("utilizadorService");
		user = (Utilizador) Executions.getCurrent().getDesktop().getSession().getAttribute("ss_utilizador");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
	
	}
	
	public void onClick$btnGravar(){
		
		if(txtSenha.getValue().equals(txtConfirmarSenha.getValue()) && txtSenhaActual.getValue().equals(user.getPassword())){
			
			user.setPassword(txtSenha.getText());
			utilizadorService.update(user);
			Clients.showNotification("Senha alterada com sucesso.", "error", win_alterar_senha, "middle_center", 4000);
			win_alterar_senha.detach();
		}else {
			Clients.showNotification("Caro Utilizador, queira por favor verificar as senhas.", "error", win_alterar_senha, "middle_center", 4000);
		}
	}
	
	public void onClick$btnCancelar(){
		txtConfirmarSenha.setRawValue(null);
		txtSenha.setRawValue(null);
		txtSenhaActual.setRawValue(null);
	}
	

}
