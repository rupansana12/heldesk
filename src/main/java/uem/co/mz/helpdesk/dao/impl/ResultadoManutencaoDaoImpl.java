package uem.co.mz.helpdesk.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.ResultadoManutencaoDao;
import uem.co.mz.helpdesk.model.ResultadoManutencao;
import uem.co.mz.helpdesk.model.Solicitacao;

@Repository
public class ResultadoManutencaoDaoImpl extends GenericDaoImpl<ResultadoManutencao> implements ResultadoManutencaoDao{

	public ResultadoManutencaoDaoImpl( ) {
		super(ResultadoManutencao.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ResultadoManutencao buscarResultadoManutencaoporSolicitacao( Solicitacao solicitacao) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select rm from ResultadoManutencao rm join fetch rm.solicitacao s where s=:solicitacao ");
		query.setParameter("solicitacao", solicitacao);
		return (ResultadoManutencao) query.uniqueResult();
	}

}
