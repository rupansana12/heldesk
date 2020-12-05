package uem.co.mz.helpdesk.controller.solicitacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional.TxType;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Input;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.PrioridadeService;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.service.SolicitacaoService;
import uem.co.mz.helpdesk.service.TipoClienteService;
import uem.co.mz.helpdesk.service.TipoEquipamentoService;

public class ListarSolicitacaoController extends GenericForwardComposer{
	
	private Combobox cbx_tipo_cliente;
	//private Combobox cbx_marca;
	private Combobox cbx_prioridade;
	private Combobox cbx_estado;
	private Textbox tbx_numeroReferencia;
	private Datebox dtx_dataInicial;
	private Datebox dtx_dataFinal;
	private Label lbl_total;
	
	private Listbox lbx_solicitacoesRegistadas;
	private Window win_listar_solicitacao;
	private Include conteudo;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	@WireVariable
	private SolicitacaoService _solicitacaoService;
	
	@WireVariable
	private SolicitacaoEquipamentoService _solicitacaoEquipamentoService;
	
	private List<Prioridade> listPri ;
	private ListModelList<Prioridade> listModPri;
	
	private List<TipoCliente> listCli ;
	private ListModelList<TipoCliente> listModCli;
	
	private List<Solicitacao> listSol ;
	private ListModelList<Solicitacao> listModSol;
	
	private List<Solicitacao> listSolicitacao= new ArrayList<Solicitacao>();
	
	private List<Object[]> _countBySolicitacao = new ArrayList<Object[]>();
	private  Label lbl_total_solicitacao;
	private  Label lbl_total_solicitacaoResolvida;
	private  Label lbl_total_solicitacaoParcialmenteResolvida;
	private  Label lbl_total_solicitacaoPendente;
	
	private Utilizador _user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		_prioridadeService = (PrioridadeService) SpringUtil.getBean("prioridadeService");
		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
		_solicitacaoService = (SolicitacaoService) SpringUtil.getBean("solicitacaoService");
		_solicitacaoEquipamentoService = (SolicitacaoEquipamentoService) SpringUtil.getBean("solicitacaoEquipamentoService");
		_user = (Utilizador) Executions.getCurrent().getDesktop().getSession().getAttribute("ss_utilizador");
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		listarPrioridades();
		buscarTiposDeClientes();
		
		//buscarSolicitacoes();
		buscarSolicitacoes(_user);
		System.out.println(_user.getUsername());
		dtx_dataInicial.setValue(new Date());
		dtx_dataFinal.setValue(new Date());
		cbx_tipo_cliente.setSelectedIndex(-1);
	}
	
	public void onClick$btn_procurar(){
		Prioridade prioridade = cbx_prioridade.getSelectedItem() == null ? null : cbx_prioridade.getSelectedItem().getValue();
		TipoCliente tipoCliente = cbx_tipo_cliente.getSelectedItem().getId() == null ? null : cbx_tipo_cliente.getSelectedItem().getValue();
		String estado = cbx_estado.getSelectedItem() == null ? null : cbx_estado.getSelectedItem().getValue();
		Date dataInicial = dtx_dataInicial.getValue() == null ? null : dtx_dataInicial.getValue();
		Date dataFinal = dtx_dataFinal.getValue() == null ? null : dtx_dataFinal.getValue();
		String nrReferencia = tbx_numeroReferencia.getValue() == null || tbx_numeroReferencia.getValue().isEmpty() || tbx_numeroReferencia.getValue()=="" ? null : tbx_numeroReferencia.getValue();
		listSol = _solicitacaoService.buscarSolicitacoes(prioridade, tipoCliente, estado, nrReferencia, dataInicial, dataFinal);
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		lbl_total.setValue(""+listSol.size());
		//alert("p "+ prioridde +" tp"+ tipoCliente +" es"+ estado +" nr "+ nrReferencia +" di"+ dataInicial +" df "+ dataFinal); 
		//cbx_tipo_cliente.setRawValue(null);
		//cbx_marca.setRawValue(null);
		cbx_prioridade.setRawValue(null);
		cbx_estado.setRawValue(null);
		tbx_numeroReferencia.setRawValue(null);
		dtx_dataInicial.setValue(new Date());
		dtx_dataFinal.setValue(new Date());
	}	
	
	public void onReportar(ForwardEvent e) throws JRException{
		Map<String, Object> mapaParam = new HashMap<String, Object>();
		Solicitacao sol = (Solicitacao) e.getData();
		//imprimir(c);
		
		mapaParam.put("solicitacao", sol);
		mapaParam.put("listSolReg", listSol);
		//conteudo.getChildren().clear();
		Executions.createComponents("/WEB-INF/solicitacao/reportar_solicitacao.zul", win_listar_solicitacao, mapaParam);
		
	}
	
	private void listarPrioridades() {
		listPri = _prioridadeService.buscarPrioridades();
		listModPri = new ListModelList<Prioridade>(listPri);
		cbx_prioridade.setModel(listModPri);
	}
	
	private void buscarTiposDeClientes() {
		listCli = _tipoClienteService.buscarTipoDeClientes();
		listModCli = new ListModelList<TipoCliente>(listCli);
		cbx_tipo_cliente.setModel(listModCli);
	}
	
	private void buscarSolicitacoes(){
		listSol = _solicitacaoService.buscarSolicitacoes();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
	}
	
	private void buscarSolicitacoes(Utilizador user){
		listSol = _solicitacaoService.buscarSolicitacoes(user);
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
	}
	
	public void showNotifications(String message, String type) {

	}
	
	
	private void listarSolicitacoes(){
		List<Object[]> listF = _solicitacaoService.listarNumTodasSolicitacoes();
		lbx_solicitacoesRegistadas.setModel(new ListModelList<Object[]>(listF));		
		_countBySolicitacao = _solicitacaoService.listarNumTodasSolicitacoes (); 
		(lbl_total_solicitacao).setValue(""+totalTotalSolicitacoes(_countBySolicitacao));
		lbx_solicitacoesRegistadas.setModel(new ListModelList<>(_countBySolicitacao));	
	}
	
	private int totalTotalSolicitacoes(List<Object[]> listObjects){
		listSolicitacao = new ArrayList<Solicitacao>();
		int total = 0;
		for (Object[] objects :listObjects) {			
		total = total + Integer.valueOf(""+objects[1]);			
		}		
		return total;
	}
	
	private void listarNumSolicitacoesResolvidas(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesResolvidas(); 
		(lbl_total_solicitacaoResolvida).setValue(""+totalTotalSolicitacoes(_countBySolicitacao));
			
	}
	
	private void listarNumSolicitacoesParcialmenteResolvidas(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesParcialmenteResolvidas(); 
		(lbl_total_solicitacaoParcialmenteResolvida).setValue(""+totalTotalSolicitacoes(_countBySolicitacao));
			
	}
	
	private void listarNumSolicitacoesPendentes(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesPendentes(); 
		(lbl_total_solicitacaoPendente).setValue(""+totalTotalSolicitacoes(_countBySolicitacao));
			
	}


}
