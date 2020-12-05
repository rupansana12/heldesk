package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.PrioridadeService;
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

public class PrioridadeController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Prioridade _prioridade;
	private Listbox lbx_prioridades;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	private List<Prioridade> listPri ;
	private ListModelList<Prioridade> listModPri;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_prioridadeService = (PrioridadeService) SpringUtil.getBean("prioridadeService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		listarPrioridades();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		lbx_prioridades.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void listarPrioridades() {
		listPri = _prioridadeService.buscarPrioridades();
		listModPri = new ListModelList<Prioridade>(listPri);
		lbx_prioridades.setModel(listModPri);
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_prioridade.setDesignacao(txb_designacao.getValue());
		_prioridadeService.update(_prioridade);
		listarPrioridades();
		showNotifications("Prioridade actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Prioridade pa = new Prioridade();
		pa.setDesignacao(txb_designacao.getValue());
		_prioridadeService.create(pa);
		listarPrioridades();
		showNotifications("Prioridade registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_prioridades(Event e){
		
		_prioridade = (Prioridade) lbx_prioridades.getSelectedItem().getValue();
		txb_designacao.setValue(_prioridade.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Prioridades");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBaseDesig.jrxml", listPri, mapaParam, win);
	}
}