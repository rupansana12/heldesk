package uem.co.mz.helpdesk.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.PermissoesDao;
import uem.co.mz.helpdesk.model.Permissoes;
import uem.co.mz.helpdesk.service.PermissoesService;

@Service("permissoesService")
public class PermissoesServiceImpl extends GenericServiceImpl<Permissoes> implements PermissoesService{

	@Autowired
	private PermissoesDao perDao;
	
	
	public List<Permissoes> listaPermissoesNaoSelecionadas(Set<Permissoes> per){
		
		return perDao.listaPermissoesNaoSelecionadas(per);
	}

}
