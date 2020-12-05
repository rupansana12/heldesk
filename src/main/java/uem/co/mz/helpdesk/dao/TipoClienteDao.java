package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoCliente;

public interface TipoClienteDao extends GenericDao<TipoCliente>{

	public List<TipoCliente> buscarTipoDeClientes();
	
}
