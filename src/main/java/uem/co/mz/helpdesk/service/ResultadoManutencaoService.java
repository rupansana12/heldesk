package uem.co.mz.helpdesk.service;

import uem.co.mz.helpdesk.model.ResultadoManutencao;
import uem.co.mz.helpdesk.model.Solicitacao;

public interface ResultadoManutencaoService extends GenericService<ResultadoManutencao>{
	
	public ResultadoManutencao buscarResultadoManutencaoporSolicitacao(Solicitacao solicitacao);

}
