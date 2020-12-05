package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.TipoClienteService;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class TipoClienteController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private TipoCliente _tipoCliente;
	private Listbox lbx_tipos_clientes;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	private List<TipoCliente> listTipCli ;
	private ListModelList<TipoCliente> listModTipCli;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		buscarTipoDeClientes();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		lbx_tipos_clientes.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void buscarTipoDeClientes() {
		listTipCli = _tipoClienteService.buscarTipoDeClientes();
		listModTipCli = new ListModelList<TipoCliente>(listTipCli);
		lbx_tipos_clientes.setModel(listModTipCli);
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_tipoCliente.setDesignacao(txb_designacao.getValue());
		_tipoClienteService.update(_tipoCliente);
		buscarTipoDeClientes();
		showNotifications("Tipo de Cliente actualizado com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		TipoCliente pa = new TipoCliente();
		pa.setDesignacao(txb_designacao.getValue());
		_tipoClienteService.create(pa);
		buscarTipoDeClientes();
		showNotifications("Tipo de Cliente registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_tipos_clientes(Event e){
		
		_tipoCliente = (TipoCliente) lbx_tipos_clientes.getSelectedItem().getValue();
		txb_designacao.setValue(_tipoCliente.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Tipos de Clientes");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBaseDesig.jrxml", listTipCli, mapaParam, win);
	}
}