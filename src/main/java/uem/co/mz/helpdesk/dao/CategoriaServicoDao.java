package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.CategoriaServico;

public interface CategoriaServicoDao extends GenericDao<CategoriaServico>{

	public List<CategoriaServico> buscarCategoriasDosServicos();
	
}
