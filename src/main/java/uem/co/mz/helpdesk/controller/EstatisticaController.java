package uem.co.mz.helpdesk.controller;

import java.util.ArrayList;
import java.util.List;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.PrioridadeService;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.service.SolicitacaoService;
import uem.co.mz.helpdesk.service.TipoClienteService;

public class EstatisticaController extends GenericForwardComposer{
	

	
	private Listbox lbx_solicitacoesRegistadas;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	@WireVariable
	private SolicitacaoService _solicitacaoService;
	
	@WireVariable
	private SolicitacaoEquipamentoService _solicitacaoEquipamentoService;
	
	
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
		
		_solicitacaoService = (SolicitacaoService) SpringUtil.getBean("solicitacaoService");
		_solicitacaoEquipamentoService = (SolicitacaoEquipamentoService) SpringUtil.getBean("solicitacaoEquipamentoService");
		_user = (Utilizador) Executions.getCurrent().getDesktop().getSession().getAttribute("ss_utilizador");
		
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
	
		listarSolicitacoes();
		listarNumSolicitacoesResolvidas();
		listarNumSolicitacoesParcialmenteResolvidas();
		listarNumSolicitacoesPendentes();
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
