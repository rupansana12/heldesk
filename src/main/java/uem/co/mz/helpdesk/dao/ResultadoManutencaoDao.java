package uem.co.mz.helpdesk.dao;

import uem.co.mz.helpdesk.model.ResultadoManutencao;
import uem.co.mz.helpdesk.model.Solicitacao;

public interface ResultadoManutencaoDao extends GenericDao<ResultadoManutencao>{

	public ResultadoManutencao buscarResultadoManutencaoporSolicitacao(Solicitacao solicitacao);
	
}
