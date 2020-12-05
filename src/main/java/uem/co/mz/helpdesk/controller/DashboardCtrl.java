package uem.co.mz.helpdesk.controller;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;

import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.UtilizadorService;

public class DashboardCtrl extends GenericForwardComposer {

	private Div div_agente;
	private Div div_candidato;
	private Div div_content_out;
	private Include inc_main;


	private Session _session;

	private Utilizador _loggedUser;

	@SuppressWarnings("unused")
	private UtilizadorService _userService;

	@SuppressWarnings("unchecked")
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		_userService = (UtilizadorService) SpringUtil.getBean("utilizadorService");
	}

	@SuppressWarnings("unchecked")
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);

		_session = Executions.getCurrent().getDesktop().getSession();

		verificarUser();

		mostrarDados();
	}

	public void verificarUser() {

		_loggedUser = (Utilizador) _session.getAttribute("ss_utilizador");
	
		
	}

	private void mostrarDados() {
			preencherCampos();
	}

	private void preencherCampos() {

			div_content_out.detach();
			inc_main.setSrc("/dashboardCandidato.zul");


	}
}
