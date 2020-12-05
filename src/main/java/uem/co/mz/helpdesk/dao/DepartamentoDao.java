package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface DepartamentoDao extends GenericDao<Departamento>{

	public List<Departamento> burcarDepartamentos();
	public List<Departamento> burcarDepartamentosPorUnidadeOrganica(UnidadeOrganica unidadeOrganica);
	
}
