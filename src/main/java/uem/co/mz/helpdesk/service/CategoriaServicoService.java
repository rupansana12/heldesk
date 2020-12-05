package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.CategoriaServico;

public interface CategoriaServicoService extends GenericService<CategoriaServico>{

	public List<CategoriaServico> buscarCategoriasDosServicos();
	
}
