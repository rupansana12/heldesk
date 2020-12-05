package uem.co.mz.helpdesk.controller.solicitacao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional.TxType;

import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.sun.corba.se.impl.encoding.CodeSetConversion.BTCConverter;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.ResultadoManutencao;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.SolicitacaoEquipamento;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.service.PrioridadeService;
import uem.co.mz.helpdesk.service.ResultadoManutencaoService;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.service.SolicitacaoService;
import uem.co.mz.helpdesk.service.TipoClienteService;
import uem.co.mz.helpdesk.service.TipoEquipamentoService;

public class ReportarSolicitacaoController extends GenericForwardComposer{
	
	private Combobox cbx_tipo_cliente;
	private Combobox cbx_prioridade;
	private Combobox cbx_estado;
	private Textbox tbx_nome;
	private Textbox tbx_observacoes;
	private Textbox tbx_estado;
	private Datebox dtx_dataRegisto;
	private Radio rdx_resolvido;
	private Radio rdx_parcialmente_resolvido;
	private Radio rdx_nao_resolvido;
	
	private Doublebox dbx_valor_pagar;
	private Textbox tbx_observacao;
	
	private Button btn_cancelar;
	private Button btn_gravar;
	private Button btn_actualizar;
	
	private Listbox lbx_solicitacoes;
	private Listbox lbx_solicitacoesRegistadas;
	private Window win_reportar_solicitacao;
	private Include conteudo;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	@WireVariable
	private SolicitacaoService _solicitacaoService;
	
	@WireVariable
	private SolicitacaoEquipamentoService _solicitacaoEquipamentoService;
	
	@WireVariable
	private ResultadoManutencaoService _resultadoManutencaoService;
	
	private List<Prioridade> listPri ;
	private ListModelList<Prioridade> listModPri;
	
	private List<TipoCliente> listCli ;
	private ListModelList<TipoCliente> listModCli;
	
	private List<Solicitacao> listSol ;
	private List<Solicitacao> listSolReg ;
	private ListModelList<Solicitacao> listModSol;
	
	private Solicitacao sol;
	private ResultadoManutencao resMan = null;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		_prioridadeService = (PrioridadeService) SpringUtil.getBean("prioridadeService");
		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
		_solicitacaoService = (SolicitacaoService) SpringUtil.getBean("solicitacaoService");
		_solicitacaoEquipamentoService = (SolicitacaoEquipamentoService) SpringUtil.getBean("solicitacaoEquipamentoService");
		_resultadoManutencaoService = (ResultadoManutencaoService) SpringUtil.getBean("resultadoManutencaoService");
		sol = (Solicitacao) Executions.getCurrent().getArg().get("solicitacao");
		listSolReg = (List<Solicitacao>) Executions.getCurrent().getArg().get("listSolReg");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		buscarDetalhesDaSolicitacao(sol);
		resMan = _resultadoManutencaoService.buscarResultadoManutencaoporSolicitacao(sol);
		cbx_tipo_cliente.setValue(sol.getCliente()==null ? "" : sol.getCliente().getNome());
		cbx_prioridade.setValue(sol.getPrioridade().getDesignacao());
		dtx_dataRegisto.setValue(sol.getDataRegisto());
		tbx_estado.setValue("SOLICITACAO "+sol.getEstado());
		for(SolicitacaoEquipamento solEqu : sol.getEquipamentosSolicitados()){
			tbx_observacao.setValue(solEqu.getObservacoes());
			dbx_valor_pagar.setValue(solEqu.getValorPagar());
		
		}
		if(resMan!=null){
			if(resMan.getEstado().equalsIgnoreCase(rdx_resolvido.getLabel())){
				rdx_resolvido.setChecked(true);
				rdx_nao_resolvido.setChecked(false);
				rdx_parcialmente_resolvido.setChecked(false);
				rdx_nao_resolvido.setChecked(false);
				
			}else if(resMan.getEstado().equalsIgnoreCase(rdx_nao_resolvido.getLabel())){
				rdx_nao_resolvido.setChecked(true);
				rdx_parcialmente_resolvido.setChecked(false);
			}else{
				rdx_nao_resolvido.setChecked(false);
				rdx_parcialmente_resolvido.setChecked(true);
				rdx_resolvido.setChecked(false);
			}
			tbx_observacoes.setValue(resMan.getObservacoes());
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
		}
	}
	
	private SolicitacaoEquipamento preencherDadosDoEquipamento(SolicitacaoEquipamento solEqu){
		solEqu.setObservacoes(tbx_observacao.getValue());
		solEqu.setValorPagar(dbx_valor_pagar.getValue()==null ? 1.0 : dbx_valor_pagar.getValue());
		return solEqu;
	}
	public void onClick$btn_gravar(){
		String estado = rdx_resolvido.isChecked() ? "RESOLVIDA" : rdx_parcialmente_resolvido.isChecked() ? "PARCIALMENTE RESOLVIDA" : "NAO RESOLVIDA";
		if(resMan==null){
			resMan = new ResultadoManutencao();
		}
		for(SolicitacaoEquipamento solEqu : sol.getEquipamentosSolicitados()){
			solEqu.setObservacoes(tbx_observacao.getValue());
			solEqu.setValorPagar(dbx_valor_pagar.getValue()==null ? 1.0 : dbx_valor_pagar.getValue());
		
		}
		resMan.setEstado(estado);
		resMan.setSolicitacao(sol);
		resMan.setObservacoes(tbx_observacoes.getValue());
		_resultadoManutencaoService.saveOrUpdate(resMan);
		sol.setEstado(estado);
		sol = _solicitacaoService.update(sol);
		limpaCampos();
		showNotifications("Solicitacao "+(rdx_resolvido.isChecked() ? "RESOLVIDA" : rdx_parcialmente_resolvido.isChecked() ? "PARCIALMENTE RESOLVIDA" : "NAO RESOLVIDA"), "info");
		listarSolicitacoesRegistadas(listSolReg, sol);
		win_reportar_solicitacao.detach();
	}
	
	public void onClick$btn_actualizar(){
		String estado = rdx_resolvido.isChecked() ? "RESOLVIDA" : rdx_parcialmente_resolvido.isChecked() ? "PARCIALMENTE RESOLVIDA" : "NAO RESOLVIDA";
		if(resMan==null){
			resMan = new ResultadoManutencao();
		}
		
		for(SolicitacaoEquipamento solEqu : sol.getEquipamentosSolicitados()){
			solEqu.setObservacoes(tbx_observacao.getValue());
			solEqu.setValorPagar(dbx_valor_pagar.getValue()==null ? 1.0 : dbx_valor_pagar.getValue());
		
		}
		resMan.setEstado(estado);
		resMan.setSolicitacao(sol);
		resMan.setObservacoes(tbx_observacoes.getValue());
		_resultadoManutencaoService.saveOrUpdate(resMan);
		sol.setEstado(estado);
		sol = _solicitacaoService.update(sol);
		limpaCampos();
		showNotifications("Solicitacao "+(rdx_resolvido.isChecked() ? "RESOLVIDA" : rdx_parcialmente_resolvido.isChecked() ? "PARCIALMENTE RESOLVIDA" : "NAO RESOLVIDA"), "info");
		listarSolicitacoesRegistadas(listSolReg, sol);
		win_reportar_solicitacao.detach();
	}
	
	public void onClick$btn_cancelar(){
		limpaCampos();
	}
	
	private void limpaCampos(){
		tbx_observacoes.setRawValue(null);
		rdx_nao_resolvido.setChecked(false);
		rdx_parcialmente_resolvido.setChecked(false);
		rdx_resolvido.setChecked(true);
		
		if(resMan!=null){
			btn_actualizar.setVisible(true);
			btn_gravar.setVisible(false);
		}else {
			btn_actualizar.setVisible(false);
			btn_gravar.setVisible(true);
		}
	}
	
	private void buscarDetalhesDaSolicitacao(Solicitacao solicitacao){
		listSol = _solicitacaoService.buscarDetalhesDaSolicitacao(solicitacao);
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoes.setModel(listModSol);
		
	}
	
	private void listarSolicitacoesRegistadas(List<Solicitacao> listSolReg, Solicitacao solicitacao){
		if(listSolReg.contains(solicitacao)){
			int index = listSolReg.indexOf(solicitacao);
			listSolReg.set(index, solicitacao);
		}
		lbx_solicitacoes.setModel(new ListModelList<Solicitacao>( listSolReg));
	}
	public void showNotifications(String message, String type) {

		//Clients.showNotification(message, type, txb_designacao,
			//	"before_center", 4000, true);
	}

}
