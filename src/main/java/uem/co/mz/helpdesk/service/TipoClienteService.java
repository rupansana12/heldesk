package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoCliente;

public interface TipoClienteService extends GenericService<TipoCliente>{

	public List<TipoCliente> buscarTipoDeClientes();
	
}
