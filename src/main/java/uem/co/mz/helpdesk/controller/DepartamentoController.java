package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.DepartamentoService;
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

public class DepartamentoController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Textbox txb_sigla;
	private Textbox txb_extensao;
	private Departamento _departamento;
	private Combobox cbx_unidade_organica;
	private Listbox lbx_departamentos;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private UnidadeOrganicaService _unidadeOrganicaService;
	
	@WireVariable
	private DepartamentoService _departamentoSevice;
	
	private List<Departamento> listDep = new ArrayList<Departamento>();

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_departamentoSevice = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		burcarDepartamentos();
		buscarUnidadesOrganicas();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		txb_sigla.setRawValue(null);
		txb_extensao.setRawValue(null);
		cbx_unidade_organica.setRawValue(null);
		lbx_departamentos.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void burcarDepartamentos() {
		listDep =  _departamentoSevice.burcarDepartamentos();
		lbx_departamentos.setModel(new ListModelList<Departamento>(listDep));
	}
	
	private void buscarUnidadesOrganicas(){
		cbx_unidade_organica.setModel(new ListModelList<UnidadeOrganica>(_unidadeOrganicaService.buscarUnidadesOrganicas()));
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_departamento.setDesignacao(txb_designacao.getValue());
		_departamento.setSigla(txb_sigla.getValue());
		_departamento.setExtensao(txb_extensao.getValue());
		_departamentoSevice.update(_departamento);
		burcarDepartamentos();
		showNotifications("Departamento actualizado com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Departamento pr = new Departamento();
		pr.setDesignacao(txb_designacao.getValue());
		pr.setSigla(txb_sigla.getValue());
		pr.setExtensao(txb_extensao.getValue());
		pr.setUnidadeOrganica((UnidadeOrganica)cbx_unidade_organica.getSelectedItem().getValue());
		_departamentoSevice.create(pr);
		burcarDepartamentos();
		showNotifications("Departamento registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_departamentos(Event e){
		
		_departamento = (Departamento) lbx_departamentos.getSelectedItem().getValue();
		//txb_designacao.setConstraint("");
		txb_designacao.setValue(_departamento.getDesignacao());
		txb_sigla.setValue(_departamento.getSigla());
		txb_extensao.setValue(_departamento.getExtensao());
		//txb_designacao.setConstraint("no empty : Não pode ser vazio nem conter apenas espaços em branco!");
		cbx_unidade_organica.setValue(_departamento.getUnidadeOrganica().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Departamentos");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBase.jrxml", listDep, mapaParam, win);
	}

}
