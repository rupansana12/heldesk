package uem.co.mz.helpdesk.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.Sector;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.DepartamentoService;
import uem.co.mz.helpdesk.service.SectorService;
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

public class SectorController extends GenericForwardComposer {

	private Textbox txb_designacao;
	private Textbox txb_sigla;
	private Textbox txb_extensao;
	private Sector _sector;
	private Combobox cbx_unidade_organica;
	private Combobox cbx_departamento;
	private Listbox lbx_sectores;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	
	@WireVariable
	private UnidadeOrganicaService _unidadeOrganicaService;
	
	@WireVariable
	private DepartamentoService _departamentoService;
	
	@WireVariable
	private SectorService _sectorSevice;
	
	private List<Sector> listSec = new ArrayList<Sector>();

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_sectorSevice = (SectorService) SpringUtil.getBean("sectorService");
		_departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		burcarSectores();
		buscarUnidadesOrganicas();
	}
	
	private void limparCampos() {
		txb_designacao.setRawValue(null);
		txb_sigla.setRawValue(null);
		txb_extensao.setRawValue(null);
		cbx_unidade_organica.setRawValue(null);
		cbx_departamento.setRawValue(null);
		lbx_sectores.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
	}

	private void burcarSectores() {
		listSec =  _sectorSevice.burcarSectores();
		lbx_sectores.setModel(new ListModelList<Sector>(listSec));
	}
	
	
	
	private void buscarUnidadesOrganicas( ){
		cbx_unidade_organica.setModel(new ListModelList<UnidadeOrganica>(_unidadeOrganicaService.buscarUnidadesOrganicas()));
	}
	
	private void buscarDepartamentosPorUnidadeOrganica(UnidadeOrganica unidadeOrganica){
		cbx_departamento.setModel(new ListModelList<Departamento>(_departamentoService.burcarDepartamentosPorUnidadeOrganica(unidadeOrganica)));
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, txb_designacao,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_sector.setDesignacao(txb_designacao.getValue());
		_sector.setSigla(txb_sigla.getValue());
		_sector.setExtensao(txb_extensao.getValue());
		_sector.setDepartamento((Departamento)cbx_departamento.getSelectedItem().getValue());
		_sectorSevice.update(_sector);
		burcarSectores();
		showNotifications("Sector actualizado com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Sector pr = new Sector();
		pr.setDesignacao(txb_designacao.getValue());
		pr.setSigla(txb_sigla.getValue());
		pr.setExtensao(txb_extensao.getValue());
		pr.setDepartamento((Departamento)cbx_departamento.getSelectedItem().getValue());
		_sectorSevice.create(pr);
		burcarSectores();
		showNotifications("Sector registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_sectores(Event e){
		
		_sector = (Sector) lbx_sectores.getSelectedItem().getValue();
		//txb_designacao.setConstraint("");
		txb_designacao.setValue(_sector.getDesignacao());
		txb_sigla.setValue(_sector.getSigla());
		txb_extensao.setValue(_sector.getExtensao());
		//txb_designacao.setConstraint("no empty : Não pode ser vazio nem conter apenas espaços em branco!");
		cbx_unidade_organica.setValue(_sector.getDepartamento().getUnidadeOrganica().getDesignacao());
		buscarDepartamentosPorUnidadeOrganica(_sector.getDepartamento().getUnidadeOrganica());
		cbx_departamento.setValue(_sector.getDepartamento().getDesignacao());
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onSelect$cbx_unidade_organica(Event e){
		cbx_departamento.setRawValue(null);
		buscarDepartamentosPorUnidadeOrganica((UnidadeOrganica)cbx_unidade_organica.getSelectedItem().getValue());
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Sectores");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBase.jrxml", listSec, mapaParam, win);
	}

}
