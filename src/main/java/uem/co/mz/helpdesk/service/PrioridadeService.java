package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Prioridade;

public interface PrioridadeService extends GenericService<Prioridade>{
	
	public List<Prioridade> buscarPrioridades();

}
