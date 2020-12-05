package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.Marca;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.MarcaService;
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

public class MarcaController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Marca _marcas;
	private Listbox lbx_marcas;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private MarcaService _marcaService;
	
	private List<Marca> listMarca ;
	private ListModelList<Marca> listModMarca;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_marcaService = (MarcaService) SpringUtil.getBean("marcaService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listarMarcas();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		lbx_marcas.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void listarMarcas() {
		listMarca = _marcaService.buscarMarcas();
		listModMarca = new ListModelList<Marca>(listMarca);
		lbx_marcas.setModel(listModMarca);
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_marcas.setDesignacao(txb_designacao.getValue());
		_marcaService.update(_marcas);
		listarMarcas();
		showNotifications("Marca actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Marca pa = new Marca();
		pa.setDesignacao(txb_designacao.getValue());
		_marcaService.create(pa);
		listarMarcas();
		showNotifications("Marca registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_marcas(Event e){
		
		_marcas = (Marca) lbx_marcas.getSelectedItem().getValue();
		txb_designacao.setValue(_marcas.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Marcas");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBaseDesig.jrxml", listMarca, mapaParam, win);
	}
}