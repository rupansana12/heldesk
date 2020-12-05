package uem.co.mz.helpdesk.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.TipoClienteDao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.service.TipoClienteService;

@Service("tipoClienteService")
public class TipoClienteServiceImpl extends GenericServiceImpl<TipoCliente> implements TipoClienteService{
	
	@Autowired
	private TipoClienteDao tipCliDao;

	@Override
	public List<TipoCliente> buscarTipoDeClientes() {
		// TODO Auto-generated method stub
		List<TipoCliente> tiposClientes = new ArrayList<TipoCliente>();		
		tiposClientes.add(new TipoCliente("TODOS"));
		tiposClientes.addAll(tipCliDao.buscarTipoDeClientes());
		return tiposClientes;
	}

}
