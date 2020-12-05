package uem.co.mz.helpdesk.controller.solicitacao;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Marca;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.SolicitacaoEquipamento;
import uem.co.mz.helpdesk.model.TipoEquipamento;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.MarcaService;
import uem.co.mz.helpdesk.service.PrioridadeService;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.service.SolicitacaoService;
import uem.co.mz.helpdesk.service.TipoEquipamentoService;
import uem.co.mz.helpdesk.service.UtilizadorService;

public class RegistarSolicitacaoController extends GenericForwardComposer{
	
	private Datebox dtx_dataRegisto;
	private Combobox cbx_tipo_equipamnto;
	private Combobox cbx_marca,cbx_tecnico;
	private Combobox cbx_prioridade;
	private Combobox cbx_estado;
	private Textbox tbx_equipamento;
	private Textbox tbx_referencia;
	private Textbox tbx_descricao_problema;
	private Textbox tbx_observacao;
	private Doublebox dbx_quantidade,dbx_valor_pagar;
	private Listbox lbx_solicitacoes;
	private Listbox lbx_solicitacoesRegistadas;
	private Window win;
	private Button btn_adicionar;
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	
	@WireVariable
	private TipoEquipamentoService _tipoEquipamentoService;
	
	@WireVariable
	private MarcaService _marcaService;
	
	@WireVariable
	private UtilizadorService _utilizadorService;
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	@WireVariable
	private SolicitacaoService _solicitacaoService;
	
	@WireVariable
	private SolicitacaoEquipamentoService _solicitacaoEquipamentoService;
	
	private List<Prioridade> listPri ;
	private ListModelList<Prioridade> listModPri;
	
	private List<Utilizador> listUtil ;
	private ListModelList<Utilizador> listModUtil;
	
	private List<Marca> listMarca ;
	private ListModelList<Marca> listModMarca;
	
	private List<TipoEquipamento> listTipEqui ;
	private ListModelList<TipoEquipamento> listModTipEqui;
	
	private List<Solicitacao> listSol ;
	private ListModelList<Solicitacao> listModSol;
	
	private Solicitacao sol;
	private SolicitacaoEquipamento solEqu = null;
	
	private Cliente cliente = null;
	
	private List<SolicitacaoEquipamento> listSolEqu = new ArrayList<SolicitacaoEquipamento>();
	
	public Cliente getCliente() { return cliente;}
	public void setCliente(Cliente cliente) { this.cliente = cliente;}
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		_utilizadorService = (UtilizadorService) SpringUtil.getBean("utilizadorService");
		_prioridadeService = (PrioridadeService) SpringUtil.getBean("prioridadeService");
		_marcaService = (MarcaService) SpringUtil.getBean("marcaService");
		_tipoEquipamentoService = (TipoEquipamentoService) SpringUtil.getBean("tipoEquipamentoService");
		_solicitacaoService = (SolicitacaoService) SpringUtil.getBean("solicitacaoService");
		_solicitacaoEquipamentoService = (SolicitacaoEquipamentoService) SpringUtil.getBean("solicitacaoEquipamentoService");
		
		cliente = (Cliente) Executions.getCurrent().getArg().get("cliente");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		listarPrioridades();
		listarUtilizadoresTecnicos();
		buscarSolicitacoes();
		dtx_dataRegisto.setValue(new Date());
	}
	
	public void onClick$btn_adicionar(){
		int index =0;
		if (solEqu == null){
			solEqu = new SolicitacaoEquipamento();
			solEqu = preencherDadosDoEquipamento(solEqu);
			listSolEqu.add(solEqu);
		}else{
			solEqu = lbx_solicitacoes.getSelectedItem().getValue();
			if(listSolEqu.contains(solEqu)){
				 index = listSolEqu.indexOf(solEqu);
				 preencherDadosDoEquipamento(solEqu);
				 listSolEqu.set(index, solEqu);
			}
		}		
		preencherListaDeEquipamentosASolicitar(listSolEqu);		
		//cbx_tipo_equipamnto.setRawValue(null);
		cbx_tecnico.setRawValue(null);
		cbx_prioridade.setRawValue(null);
		cbx_estado.setRawValue(null);
		tbx_equipamento.setRawValue(null);
		tbx_referencia.setRawValue(null);
		tbx_observacao.setRawValue(null);
		tbx_descricao_problema.setRawValue(null);
		dbx_quantidade.setRawValue(null);
		solEqu = null;
	}
	
	private SolicitacaoEquipamento preencherDadosDoEquipamento(SolicitacaoEquipamento solEqu){
		solEqu.setEstadoEquipamento(cbx_estado.getSelectedItem()==null ? null : cbx_estado.getSelectedItem().getValue());
		solEqu.setUtilizador(cbx_tecnico.getSelectedItem()==null ? null : cbx_tecnico.getSelectedItem().getValue());
		//solEqu.setModelo(tbx_modelo.getValue());
		solEqu.setObservacoes(tbx_observacao.getValue());
		solEqu.setDescricaoProblema(tbx_descricao_problema.getValue());
		solEqu.setQuantidade(dbx_quantidade.getValue()==null ? 1.0 : dbx_quantidade.getValue());
		solEqu.setReferencia(tbx_referencia.getValue());
		solEqu.setSolicitacao(sol==null ? null : sol);
		solEqu.setValorPagar(dbx_valor_pagar.getValue()==null ? 1.0 : dbx_valor_pagar.getValue());
		solEqu.setEquipamento(tbx_equipamento.getValue());
		//solEqu.setTipoEquipamento(cbx_tipo_equipamnto.getSelectedItem()==null ? null : cbx_tipo_equipamnto.getSelectedItem().getValue());
		return solEqu;
	}
	
	public void onClick$btn_gravar(){
		
		if(lbx_solicitacoes.getItems()==null || lbx_solicitacoes.getItems().isEmpty()){
			showNotifications("Adicione o(s) equipamento(s) na lista", "warning");
			return;
		}else{
			sol = new Solicitacao();
			sol.setCliente(cliente);
			sol.setPrioridade(cbx_prioridade.getSelectedItem()==null ? null : cbx_prioridade.getSelectedItem().getValue());			
			sol.setDataRegisto(dtx_dataRegisto.getValue()==null ? null : dtx_dataRegisto.getValue());
			sol.setEstado("PENDENTE");		
			sol = _solicitacaoService.create(sol);		
			
			for(Listitem li : lbx_solicitacoes.getItems()){
				SolicitacaoEquipamento solEqu = li.getValue();
				solEqu.setSolicitacao(sol);
				_solicitacaoEquipamentoService.create(solEqu);
			}
			showNotifications("Solicitacao registada com sucesso", "info");
			limpaCampos();
			buscarSolicitacoes();
		}
	}
	
	public void onClick$btn_actualizar(){
		if(lbx_solicitacoes.getItems()==null || lbx_solicitacoes.getItems().isEmpty()){
			showNotifications("Adicione o(s) equipamento(s) na lista", "warning");
			return;
		}else{
			
			for(SolicitacaoEquipamento solEqu : sol.getEquipamentosSolicitados()){
				_solicitacaoEquipamentoService.remove(solEqu);
			}
			
			for(Listitem li : lbx_solicitacoes.getItems()){
				SolicitacaoEquipamento equipamentos = li.getValue();
				
				SolicitacaoEquipamento solEqu = new SolicitacaoEquipamento();
				solEqu.setEstadoEquipamento(equipamentos.getEstadoEquipamento());
				solEqu.setUtilizador(equipamentos.getUtilizador());
				solEqu.setObservacoes(equipamentos.getObservacoes());
				solEqu.setDescricaoProblema(equipamentos.getDescricaoProblema());
				solEqu.setQuantidade(equipamentos.getQuantidade());
				solEqu.setReferencia(equipamentos.getReferencia());
				solEqu.setSolicitacao(equipamentos.getSolicitacao());
				//solEqu.setTipoEquipamento(equipamentos.getTipoEquipamento());
				solEqu.setSolicitacao(sol);
				_solicitacaoEquipamentoService.create(solEqu);
			}
			limpaCampos();
			buscarSolicitacoes();
			showNotifications("Solicitacao actualizada com sucesso", "info");
		}
	}
	
	public void onClick$btn_cancelar(){
		limpaCampos();
	}
	
	private void limpaCampos(){
		dtx_dataRegisto.setValue(new Date());
		dbx_valor_pagar.setRawValue(null);
		cbx_tecnico.setRawValue(null);
		cbx_prioridade.setRawValue(null);
		cbx_estado.setRawValue(null);
		tbx_equipamento.setRawValue(null);
		tbx_referencia.setRawValue(null);
		tbx_observacao.setRawValue(null);
		tbx_descricao_problema.setRawValue(null);
		dbx_quantidade.setRawValue(null);
		lbx_solicitacoes.getItems().clear();
		listSol.clear();
		sol = null;
		solEqu = null;
		btn_actualizar.setVisible(false);
		btn_gravar.setVisible(true);
	}
	
	private void buscarSolicitacoes(){
		listSol = _solicitacaoService.buscarSolicitacoes();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
	}
	
	public void onSelect$lbx_solicitacoesRegistadas(){
		listSolEqu = new ArrayList<SolicitacaoEquipamento>();
		sol = lbx_solicitacoesRegistadas.getSelectedItem().getValue();
		
		for(SolicitacaoEquipamento solEqu : sol.getEquipamentosSolicitados()){
			listSolEqu.add(solEqu);
		}
		dtx_dataRegisto.setValue(sol.getDataRegisto());
		cbx_prioridade.setValue(sol.getPrioridade() == null ? null : sol.getPrioridade().getDesignacao());
		preencherListaDeEquipamentosASolicitar(listSolEqu);
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onSelect$lbx_solicitacoes(){
		solEqu = lbx_solicitacoes.getSelectedItem().getValue();
		cbx_tecnico.setValue(solEqu.getUtilizador().getUsername());
		cbx_estado.setValue(solEqu.getEstadoEquipamento());
		tbx_referencia.setValue(solEqu.getReferencia());
		dbx_valor_pagar.setValue(solEqu.getValorPagar());
		tbx_observacao.setValue(solEqu.getObservacoes());
		tbx_descricao_problema.setValue(solEqu.getDescricaoProblema());
		dbx_quantidade.setValue(solEqu.getQuantidade());
		
		
	}
	
	public void onClickRemoverSolicitacaoEquipamento(ForwardEvent e){
		
		SolicitacaoEquipamento solEqu = (SolicitacaoEquipamento) e.getData();		
		listSolEqu.remove(solEqu);			
		
		preencherListaDeEquipamentosASolicitar(listSolEqu);
		_solicitacaoEquipamentoService.remove(solEqu);
		buscarSolicitacoes();
	}
	
	private void preencherListaDeEquipamentosASolicitar(List<SolicitacaoEquipamento> listSolEqu){
		lbx_solicitacoes.setModel(new ListModelList<SolicitacaoEquipamento>(listSolEqu));
	}
	
	private void listarPrioridades() {
		listPri = _prioridadeService.buscarPrioridades();
		listModPri = new ListModelList<Prioridade>(listPri);
		cbx_prioridade.setModel(listModPri);
	}
	
	private void listarUtilizadoresTecnicos() {
		listUtil = _utilizadorService.buscarUtilizador();
		listModUtil = new ListModelList<Utilizador>(listUtil);
		cbx_tecnico.setModel(listModUtil);
	}
	
	private void buscarTiposDeEquipamentos() {
		listTipEqui = _tipoEquipamentoService.buscarTiposDeEquipamentos();
		listModTipEqui = new ListModelList<TipoEquipamento>(listTipEqui);
		cbx_tipo_equipamnto.setModel(listModTipEqui);
	}
	
	private void listarMarcas() {
		listMarca = _marcaService.buscarMarcas();
		listModMarca = new ListModelList<Marca>(listMarca);
		cbx_marca.setModel(listModMarca);
	}
	
	
	public void onClick$btn_imprimir(Event e) throws JRException{
			
			Map<String, Object> mapaParam = new HashMap<String, Object>();	
			final Execution ex = Executions.getCurrent();
			InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
	        mapaParam.put("imagemLogo", inputV);
	        mapaParam.put("listNome", "Lista de Clientes");
			MasterRep.imprimir("/WEB-INF/reportParam/reportSolicitacao.jrxml", listSol, mapaParam, win);
		}
	
	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, tbx_observacao,
				"before_center", 4000, true);
	}

}
