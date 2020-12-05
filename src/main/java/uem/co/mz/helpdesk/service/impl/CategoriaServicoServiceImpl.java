package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.service.CategoriaServicoService;
import uem.co.mz.helpdesk.dao.CategoriaServicoDao;
import uem.co.mz.helpdesk.model.CategoriaServico;

@Service("categoriaServicoService")
public class CategoriaServicoServiceImpl extends GenericServiceImpl<CategoriaServico> implements CategoriaServicoService{
	
	@Autowired
	private CategoriaServicoDao catSerDao;

	@Override
	public List<CategoriaServico> buscarCategoriasDosServicos() {
		// TODO Auto-generated method stub
		return catSerDao.buscarCategoriasDosServicos();
	}

}
