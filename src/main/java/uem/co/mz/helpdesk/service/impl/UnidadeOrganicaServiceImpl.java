package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.UnidadeOrganicaDao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.service.UnidadeOrganicaService;

@Service("unidadeOrganicaService")
public class UnidadeOrganicaServiceImpl extends GenericServiceImpl<UnidadeOrganica> implements UnidadeOrganicaService{

	@Autowired
	private UnidadeOrganicaDao uniOrgDao;

	@Override
	public List<UnidadeOrganica> buscarUnidadesOrganicas() {
		// TODO Auto-generated method stub
		return uniOrgDao.burcarUnidadesOrganicas();
	}

	@Override
	public List<UnidadeOrganica> buscarUnidadesOrganicasPorTipoDeCliente(
			TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		return uniOrgDao.burcarUnidadesOrganicasPorTipoDeCliente(tipoCliente);
	}
	
}
