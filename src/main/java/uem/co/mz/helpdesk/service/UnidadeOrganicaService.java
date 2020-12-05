package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface UnidadeOrganicaService extends GenericService<UnidadeOrganica>{

	public List<UnidadeOrganica> buscarUnidadesOrganicas();
	public List<UnidadeOrganica> buscarUnidadesOrganicasPorTipoDeCliente(TipoCliente tipoCliente);
	
}
