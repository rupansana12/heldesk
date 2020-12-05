package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.service.ClienteService;
import uem.co.mz.helpdesk.dao.ClienteDao;
import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

@Service("clienteService")
public class ClienteServiceImpl extends GenericServiceImpl<Cliente> implements ClienteService{

	@Autowired
	private ClienteDao cliDao;

	@Override
	public List<Cliente> buscarClientes() {
		// TODO Auto-generated method stub
		return cliDao.buscarClientes();
	}

	@Override
	public List<Cliente> buscarClientesPorTipo(TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		return cliDao.buscarClientesPorTipo(tipoCliente);
	}

	@Override
	public List<Cliente> buscarClientesPorNomeETipo(String nome, TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		return cliDao.buscarClientesPorNomeETipo(nome, tipoCliente);
	}

	@Override
	public List<Cliente> buscarClientesExternos() {
		// TODO Auto-generated method stub
		return cliDao.buscarClientesExternos();
	}

	@Override
	public List<Cliente> buscarClientesPorUnidadeOrganica( UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		return cliDao.buscarClientesPorUnidadeOrganica(unidadeOrganica);
	}
	
}
