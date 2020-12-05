package uem.co.mz.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.ResultadoManutencaoDao;
import uem.co.mz.helpdesk.model.ResultadoManutencao;
import uem.co.mz.helpdesk.model.Solicitacao;
import uem.co.mz.helpdesk.service.ResultadoManutencaoService;

@Service("resultadoManutencaoService")
public class ResultadoManutencaoServiceImpl extends GenericServiceImpl<ResultadoManutencao> implements ResultadoManutencaoService{

	@Autowired
	private ResultadoManutencaoDao resManDao;

	@Override
	public ResultadoManutencao buscarResultadoManutencaoporSolicitacao(
			Solicitacao solicitacao) {
		// TODO Auto-generated method stub
		return resManDao.buscarResultadoManutencaoporSolicitacao(solicitacao);
	}
	
}
