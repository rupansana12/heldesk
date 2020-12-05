package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.TipoClienteService;
import uem.co.mz.helpdesk.service.UnidadeOrganicaService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class UnidadeOrganicaController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Textbox txb_sigla;
	private Textbox txb_extensao;
	private UnidadeOrganica _unidadeOrganica;
	private Combobox cbx_tipos_clientes;
	private Listbox lbx_unidades_organicas;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	@WireVariable
	private UnidadeOrganicaService _unidadeOrganicaSevice;
	
	private List<UnidadeOrganica> listUniOrg = new ArrayList<UnidadeOrganica>();

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_unidadeOrganicaSevice = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		burcarUnidadesOrganicas();
		listarPaises();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		txb_sigla.setRawValue(null);
		txb_extensao.setRawValue(null);
		cbx_tipos_clientes.setRawValue(null);
		lbx_unidades_organicas.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void burcarUnidadesOrganicas() {
		listUniOrg =  _unidadeOrganicaSevice.buscarUnidadesOrganicas();
		lbx_unidades_organicas.setModel(new ListModelList<UnidadeOrganica>(listUniOrg));
	}
	
	private void listarPaises(){
		cbx_tipos_clientes.setModel(new ListModelList<TipoCliente>(_tipoClienteService.buscarTipoDeClientes()));
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_unidadeOrganica.setDesignacao(txb_designacao.getValue());
		_unidadeOrganica.setSigla(txb_sigla.getValue());
		_unidadeOrganica.setExtensao(txb_extensao.getValue());
		_unidadeOrganicaSevice.update(_unidadeOrganica);
		burcarUnidadesOrganicas();
		showNotifications("Unidade Organica actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		UnidadeOrganica pr = new UnidadeOrganica();
		pr.setDesignacao(txb_designacao.getValue());
		pr.setSigla(txb_sigla.getValue());
		pr.setExtensao(txb_extensao.getValue());
		pr.setTipoCliente((TipoCliente)cbx_tipos_clientes.getSelectedItem().getValue());
		_unidadeOrganicaSevice.create(pr);
		burcarUnidadesOrganicas();
		showNotifications("Unidade Organica registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_unidades_organicas(Event e){
		
		_unidadeOrganica = (UnidadeOrganica) lbx_unidades_organicas.getSelectedItem().getValue();
		//txb_designacao.setConstraint("");
		txb_designacao.setValue(_unidadeOrganica.getDesignacao());
		txb_sigla.setValue(_unidadeOrganica.getSigla());
		txb_extensao.setValue(_unidadeOrganica.getExtensao());
		//txb_designacao.setConstraint("no empty : Não pode ser vazio nem conter apenas espaços em branco!");
		cbx_tipos_clientes.setValue(_unidadeOrganica.getTipoCliente().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Unidades Organicas");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBase.jrxml", listUniOrg, mapaParam, win);
	}

}
