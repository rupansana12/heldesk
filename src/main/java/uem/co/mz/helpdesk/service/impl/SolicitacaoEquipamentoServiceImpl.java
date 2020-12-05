package uem.co.mz.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.SolicitacaoEquipamentoDao;
import uem.co.mz.helpdesk.service.SolicitacaoEquipamentoService;
import uem.co.mz.helpdesk.model.SolicitacaoEquipamento;

@Service("solicitacaoEquipamentoService")
public class SolicitacaoEquipamentoServiceImpl extends GenericServiceImpl<SolicitacaoEquipamento> implements SolicitacaoEquipamentoService{

	@Autowired
	private SolicitacaoEquipamentoDao solEquDao;
	
}
