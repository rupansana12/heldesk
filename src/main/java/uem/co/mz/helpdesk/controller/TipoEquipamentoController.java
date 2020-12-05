package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.TipoEquipamento;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.TipoEquipamentoService;
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

public class TipoEquipamentoController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private TipoEquipamento _tipoEquipamento;
	private Listbox lbx_tipos_equipamentos;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private TipoEquipamentoService _tipoEquipamentoService;
	
	private List<TipoEquipamento> listTipEqui ;
	private ListModelList<TipoEquipamento> listModTipEqui;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_tipoEquipamentoService = (TipoEquipamentoService) SpringUtil.getBean("tipoEquipamentoService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		buscarTiposDeEquipamentos();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		lbx_tipos_equipamentos.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void buscarTiposDeEquipamentos() {
		listTipEqui = _tipoEquipamentoService.buscarTiposDeEquipamentos();
		listModTipEqui = new ListModelList<TipoEquipamento>(listTipEqui);
		lbx_tipos_equipamentos.setModel(listModTipEqui);
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_tipoEquipamento.setDesignacao(txb_designacao.getValue());
		_tipoEquipamentoService.update(_tipoEquipamento);
		buscarTiposDeEquipamentos();
		showNotifications("Tipo de Equipamento actualizado com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		TipoEquipamento pa = new TipoEquipamento();
		pa.setDesignacao(txb_designacao.getValue());
		_tipoEquipamentoService.create(pa);
		buscarTiposDeEquipamentos();
		showNotifications("Tipo de Equipamento registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_tipos_equipamentos(Event e){
		
		_tipoEquipamento = (TipoEquipamento) lbx_tipos_equipamentos.getSelectedItem().getValue();
		txb_designacao.setValue(_tipoEquipamento.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Tipos de Equipamentos");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBaseDesig.jrxml", listTipEqui, mapaParam, win);
	}
}