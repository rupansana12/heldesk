package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface UnidadeOrganicaDao extends GenericDao<UnidadeOrganica>{

	public List<UnidadeOrganica> burcarUnidadesOrganicas();
	public List<UnidadeOrganica> burcarUnidadesOrganicasPorTipoDeCliente(TipoCliente tipoCliente);
	
}
