package uem.co.mz.helpdesk.service;

import java.util.Date;
import java.util.List;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.Utilizador;

public interface SolicitacaoService extends GenericService<Solicitacao>{
	
	public List<Object[]> listarNumTodasSolicitacoes();
	public List<Object[]> buscarNumSolicitacoesResolvidas();
	public List<Object[]> buscarNumSolicitacoesParcialmenteResolvidas();
	public List<Object[]> buscarNumSolicitacoesPendentes();
	
	public List<Solicitacao> buscarSolicitacoesResolvidas();
	public List<Solicitacao> buscarSolicitacoesParcialmenteResolvidas();
	public List<Solicitacao> buscarSolicitacoesPendentes();
	
	public List<Solicitacao> buscarSolicitacoes();
	public List<Solicitacao> buscarSolicitacoes(Utilizador _user);
	public List<Solicitacao> buscarDetalhesDaSolicitacao(Solicitacao solicitacao);
	public List<Solicitacao> buscarSolicitacoesDoCliente(Cliente cliente);
	public List<Solicitacao> buscarSolicitacoesPorData(Date dataInicial, Date dataFinal);
	public List<Solicitacao> buscarSolicitacoes(Prioridade prioridade, TipoCliente tipoCliente, String estado, String nrReferencia, Date dataInicial, Date dataFinal);
	public List<Solicitacao> buscarSolicitacoesDoCliente(String nomeCliente, Date dataInicial, Date dataFinal);
}
