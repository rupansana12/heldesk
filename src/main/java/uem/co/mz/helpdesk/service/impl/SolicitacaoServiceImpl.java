package uem.co.mz.helpdesk.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.SolicitacaoDao;
import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.SolicitacaoService;

@Service("solicitacaoService")
public class SolicitacaoServiceImpl extends GenericServiceImpl<Solicitacao> implements SolicitacaoService{

	@Autowired
	private SolicitacaoDao solDao;

	@Override
	public List<Solicitacao> buscarSolicitacoes() {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoes();
	}

	@Override
	public List<Solicitacao> buscarDetalhesDaSolicitacao(Solicitacao solicitacao) {
		// TODO Auto-generated method stub
		return solDao.buscarDetalhesDaSolicitacao(solicitacao);
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesDoCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesDoCliente(cliente);
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesPorData(Date dataInicial,
			Date dataFinal) {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesPorData(dataInicial, dataFinal);
	}

	@Override
	public List<Solicitacao> buscarSolicitacoes(Prioridade prioridade, TipoCliente tipoCliente, String estado, String nrReferencia,
			Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoes(prioridade, tipoCliente, estado, nrReferencia, dataInicial, dataFinal);
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesDoCliente(String nomeCliente, Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesDoCliente(nomeCliente, dataInicial, dataFinal);
	}

	@Override
	public List<Solicitacao> buscarSolicitacoes(Utilizador _user) {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoes(_user);
	}

	@Override
	public List<Object[]> listarNumTodasSolicitacoes() {
		// TODO Auto-generated method stub
		return solDao.listarNumTodasSolicitacoes();
	}

	@Override
	public List<Object[]> buscarNumSolicitacoesResolvidas() {
		// TODO Auto-generated method stub
		return solDao.buscarNumSolicitacoesResolvidas();
	}

	@Override
	public List<Object[]> buscarNumSolicitacoesParcialmenteResolvidas() {
		// TODO Auto-generated method stub
		return solDao.buscarNumSolicitacoesParcialmenteResolvidas();
	}

	@Override
	public List<Object[]> buscarNumSolicitacoesPendentes() {
		// TODO Auto-generated method stub
		return solDao.buscarNumSolicitacoesPendentes();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesResolvidas() {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesResolvidas();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesParcialmenteResolvidas() {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesParcialmenteResolvidas();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesPendentes() {
		// TODO Auto-generated method stub
		return solDao.buscarSolicitacoesPendentes();
	}
	
}
