package uem.co.mz.helpdesk.controller.estatistica;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Window;

import net.sf.jasperreports.engine.JRException;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.PrioridadeService;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.service.SolicitacaoService;
import uem.co.mz.helpdesk.service.TipoClienteService;

public class EstatisticaController extends GenericForwardComposer{
	

	
	
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	
	@WireVariable
	private PrioridadeService _prioridadeService;
	
	@WireVariable
	private SolicitacaoService _solicitacaoService;
	
	@WireVariable
	private SolicitacaoEquipamentoService _solicitacaoEquipamentoService;
	
	private Button btn_total_solicitacao, btn_total_solicitacaoResolvida,btn_total_solicitacaoParcialmenteResolvida, btn_total_solicitacaoPendente;
	
	private Listbox lbx_solicitacoesRegistadas;
	private List<Solicitacao> listSolicitacao= new ArrayList<Solicitacao>();
	private List<Object[]> _countBySolicitacao = new ArrayList<Object[]>();
	private  Label lbl_total_solicitacao,lbx_resolvido,lbx_pendente;
	private  Label lbl_total_solicitacaoResolvida;
	private  Label lbl_total_solicitacaoParcialmenteResolvida;
	private  Label lbl_total_solicitacaoPendente;
	private List<Solicitacao> listSol ;
	private ListModelList<Solicitacao> listModSol;
	
	private Window win;
	
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
		buscarSolicitacoes();
		listarNumSolicitacoesResolvidas();
		listarNumSolicitacoesParcialmenteResolvidas();
		listarNumSolicitacoesPendentes();
	}
	
	
	private void listarSolicitacoes(){
		List<Object[]> listF = _solicitacaoService.listarNumTodasSolicitacoes();
		//lbx_solicitacoesRegistadas.setModel(new ListModelList<Object[]>(listF));
		
		_countBySolicitacao = _solicitacaoService.listarNumTodasSolicitacoes(); 
		(lbl_total_solicitacao).setValue("Total de solicitação = "+totalTotalSolicitacoes(_countBySolicitacao));
		//lbx_solicitacoesRegistadas.setModel(new ListModelList<>(_countBySolicitacao));
		
	}
	
	
	private int totalTotalSolicitacoes(List<Object[]> listObjects){
		listSolicitacao = new ArrayList<Solicitacao>();
		int total = 0;
		for (Object[] objects :listObjects) {			
		total = total + Integer.valueOf(""+objects[1]);			
		}
		System.out.println(total+"");
		return total;
		
	}
	
	private void listarNumSolicitacoesResolvidas(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesResolvidas(); 
		(lbl_total_solicitacaoResolvida).setValue("Solicitação Resolvidas = "+totalTotalSolicitacoes(_countBySolicitacao));
			
	}
	
	private void listarNumSolicitacoesParcialmenteResolvidas(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesParcialmenteResolvidas(); 
		(lbl_total_solicitacaoParcialmenteResolvida).setValue("solicitação Não Resolvidas = "+totalTotalSolicitacoes(_countBySolicitacao));
			
	}
	
	private void listarNumSolicitacoesPendentes(){
		_countBySolicitacao = _solicitacaoService.buscarNumSolicitacoesPendentes(); 
		(lbl_total_solicitacaoPendente).setValue("Solicitação Pendentes = "+totalTotalSolicitacoes(_countBySolicitacao));
			
	}
	
	private void buscarSolicitacoes(){
		listSol = _solicitacaoService.buscarSolicitacoes();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
		
	}
	

	
	public void onClick$btn_total_solicitacao() throws JRException{
		listSol = _solicitacaoService.buscarSolicitacoes();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
			}
	
	public void onClick$btn_total_solicitacaoResolvida() throws JRException{
		listSol = _solicitacaoService.buscarSolicitacoesResolvidas();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
	}
	
	public void onClick$btn_total_solicitacaoParcialmenteResolvida() throws JRException{
		listSol = _solicitacaoService.buscarSolicitacoesParcialmenteResolvidas();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
	}
	public void onClick$btn_total_solicitacaoPendente() throws JRException{
		listSol = _solicitacaoService.buscarSolicitacoesPendentes();
		listModSol = new ListModelList<Solicitacao>(listSol);
		lbx_solicitacoesRegistadas.setModel(listModSol);
		
	}
	
	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Clientes");
		MasterRep.imprimir("/WEB-INF/reportParam/reportSolicitacao.jrxml", listSol, mapaParam, win);
	}


}
