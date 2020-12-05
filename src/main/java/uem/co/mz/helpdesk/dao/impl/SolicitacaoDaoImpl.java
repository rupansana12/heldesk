package uem.co.mz.helpdesk.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.SolicitacaoDao;
import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.Utilizador;

@Repository
public class SolicitacaoDaoImpl extends GenericDaoImpl<Solicitacao> implements SolicitacaoDao{

	public SolicitacaoDaoImpl( ) {
		super(Solicitacao.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Solicitacao> buscarSolicitacoes() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq order by s.created ");
		return query.list();
	}
	
	@Override
	public List<Solicitacao> buscarDetalhesDaSolicitacao(Solicitacao solicitacao) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq where s=:solicitacao order by s.created ");
			query.setParameter("solicitacao", solicitacao);
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesDoCliente(Cliente cliente) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq "
				+ " where cli=:cliente order by s.created ");
		query.setParameter("cliente", cliente);
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesPorData(Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq "
				+ "where s.created between :dataInicial and :dataFinal order by s.created ");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoes(Prioridade prioridade, TipoCliente tipoCliente, String estado, String nrReferencia, Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		
		String parmData = dataInicial==null ? "" : " s.created between :dataInicial and :dataFinal ";
		//String parmTip = tipoCliente==null ? "" : " and tipCli=:tipoCliente ";
		String parmPri = prioridade==null ? "" : " and pri=:prioridade ";
		String parmEst = estado==null || estado.isEmpty() ? "" : " and s.estado=:estado ";
		String parmRef = nrReferencia==null || nrReferencia.isEmpty() || nrReferencia=="" ? "" : " and equi.referencia=:nrReferencia ";
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli LEFT JOIN FETCH cli.tipoCliente tipCli JOIN FETCH "
				+ " s.equipamentosSolicitados equi LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq "
				+ "where "+ parmData+" "+parmEst+" "+parmRef+" "+parmPri+" "+" order by s.created ");
		//query.setParameter("tipoCliente", tipoCliente);
		if(dataInicial!=null){
			query.setParameter("dataInicial", dataInicial);
			query.setParameter("dataFinal", dataFinal); }
		if(prioridade!=null){
			query.setParameter("prioridade", prioridade); }
		if(estado!=null){
			query.setParameter("estado", estado); }	
		if(nrReferencia!=null){
			query.setParameter("nrReferencia", nrReferencia); }
		if(tipoCliente!=null){
			//query.setParameter("tipoCliente", tipoCliente); 
			}
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesDoCliente(String nomeCliente, Date dataInicial, Date dataFinal) {
		// TODO Auto-generated method stub
		
		String parmData = dataInicial==null ? "" : " and s.created between :dataInicial and :dataFinal ";
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq "
				+ " where cli.nome like :nomeCliente "+ parmData+" order by s.created ");
		query.setParameter("nomeCliente", "%"+nomeCliente+"%");
		query.setParameter("dataInicial", dataInicial);
		query.setParameter("dataFinal", dataFinal);
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoes(Utilizador _user) {
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq where equi.utilizador=:user order by s.created ");
			query.setParameter("user", _user);
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesResolvidas() {
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq where s.estado=:solicitacao order by s.created ");
		query.setParameter("solicitacao", "RESOLVIDA");
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesParcialmenteResolvidas() {
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq where s.estado=:solicitacao order by s.created ");
		query.setParameter("solicitacao", "PARCIALMENTE RESOLVIDA");
		return query.list();
	}

	@Override
	public List<Solicitacao> buscarSolicitacoesPendentes() {
		Query query = getCurrentSession().createQuery("select DISTINCT s from Solicitacao s LEFT JOIN FETCH s.cliente cli JOIN FETCH s.equipamentosSolicitados equi "
				+ " LEFT JOIN FETCH s.prioridade pri LEFT JOIN FETCH equi.utilizador eq where s.estado=:solicitacao order by s.created ");
		query.setParameter("solicitacao", "PENDENTE");
		return query.list();
	}

	@Override
	public List<Object[]> listarNumTodasSolicitacoes() {
		Query query = getCurrentSession().createQuery("select sol.estado, "
				+ "count(sol.id) "
				+ "from Solicitacao sol "
				+ "group by sol.estado");	
		return query.list();
	}

	@Override
	public List<Object[]> buscarNumSolicitacoesResolvidas() {
		Query query = getCurrentSession().createQuery("select sol.estado, "
				+ " count(sol.id) "
				+ "from Solicitacao sol "
				+ "where sol.estado =:sol "
				+ "group by sol.estado");
				
		query.setParameter("sol", "RESOLVIDA");
		return query.list();
	}
	
	

	@Override
	public List<Object[]> buscarNumSolicitacoesParcialmenteResolvidas() {
		Query query = getCurrentSession().createQuery("select sol.estado, "
				+ "count(sol.id) "
				+ "from Solicitacao sol "
				+ "where sol.estado =:sol "
				+ "group by sol.estado");
				
		query.setParameter("sol", "PARCIALMENTE RESOLVIDA");
		return query.list();
	}

	@Override
	public List<Object[]> buscarNumSolicitacoesPendentes() {
		Query query = getCurrentSession().createQuery("select sol.estado, "
				+ " count(sol.id) "
				+ "from Solicitacao sol "
				+ "where sol.estado =:sol "
				+ "group by sol.estado");
				
		query.setParameter("sol", "PENDENTE");
		return query.list();
	}

}
