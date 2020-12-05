package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.Prioridade;

public interface PrioridadeDao extends GenericDao<Prioridade>{

	public List<Prioridade> buscarPrioridades();
	
}
