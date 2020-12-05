package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.CategoriaServico;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.CategoriaServicoService;
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

public class CategoriaServicoController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private CategoriaServico _categoriaServico;
	private Listbox lbx_categorias_servicos;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private CategoriaServicoService _categoriaServicoService;
	
	private List<CategoriaServico> listCatSer ;
	private ListModelList<CategoriaServico> listModCatSer;

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_categoriaServicoService = (CategoriaServicoService) SpringUtil.getBean("categoriaServicoService");
		
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		buscarCategoriasDosServicos();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		lbx_categorias_servicos.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void buscarCategoriasDosServicos() {
		listCatSer = _categoriaServicoService.buscarCategoriasDosServicos();
		listModCatSer = new ListModelList<CategoriaServico>(listCatSer);
		lbx_categorias_servicos.setModel(listModCatSer);
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_categoriaServico.setDesignacao(txb_designacao.getValue());
		_categoriaServicoService.update(_categoriaServico);
		buscarCategoriasDosServicos();
		showNotifications("Categoria de Servico actualizada com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		CategoriaServico pa = new CategoriaServico();
		pa.setDesignacao(txb_designacao.getValue());
		_categoriaServicoService.create(pa);
		buscarCategoriasDosServicos();
		showNotifications("Categoria de Servico registada com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_categorias_servicos(Event e){
		
		_categoriaServico = (CategoriaServico) lbx_categorias_servicos.getSelectedItem().getValue();
		txb_designacao.setValue(_categoriaServico.getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Categorias de Servicos");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBaseDesig.jrxml", listCatSer, mapaParam, win);
	}
}