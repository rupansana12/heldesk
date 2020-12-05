package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface ClienteService extends GenericService<Cliente>{
	
	public List<Cliente> buscarClientes();
	public List<Cliente> buscarClientesPorNomeETipo(String nome, TipoCliente tipoCliente);
	public List<Cliente> buscarClientesPorTipo(TipoCliente tipoCliente);
	public List<Cliente> buscarClientesExternos();
	public List<Cliente> buscarClientesPorUnidadeOrganica(UnidadeOrganica unidadeOrganica);

}
