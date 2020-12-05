package uem.co.mz.helpdesk.dao.impl;

import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.SolicitacaoEquipamentoDao;
import uem.co.mz.helpdesk.model.SolicitacaoEquipamento;

@Repository
public class SolicitacaoEquipamentoDaoImpl extends GenericDaoImpl<SolicitacaoEquipamento> implements SolicitacaoEquipamentoDao{

	public SolicitacaoEquipamentoDaoImpl( ) {
		super(SolicitacaoEquipamento.class);
		// TODO Auto-generated constructor stub
	}

}
