package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface DepartamentoService extends GenericService<Departamento>{
	
	public List<Departamento> burcarDepartamentos();
	public List<Departamento> burcarDepartamentosPorUnidadeOrganica(UnidadeOrganica unidadeOrganica);

}
