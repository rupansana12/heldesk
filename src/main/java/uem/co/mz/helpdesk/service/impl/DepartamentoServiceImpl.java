package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.DepartamentoDao;
import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.service.DepartamentoService;

@Service("departamentoService")
public class DepartamentoServiceImpl extends GenericServiceImpl<Departamento> implements DepartamentoService{

	@Autowired
	private DepartamentoDao depDao;

	@Override
	public List<Departamento> burcarDepartamentos() {
		// TODO Auto-generated method stub
		return depDao.burcarDepartamentos();
	}

	@Override
	public List<Departamento> burcarDepartamentosPorUnidadeOrganica(
			UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		return depDao.burcarDepartamentosPorUnidadeOrganica(unidadeOrganica);
	}
	
}
