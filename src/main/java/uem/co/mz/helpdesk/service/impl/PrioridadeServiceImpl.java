package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import uem.co.mz.helpdesk.dao.PrioridadeDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.model.Prioridade;
import uem.co.mz.helpdesk.service.PrioridadeService;

@Service("prioridadeService")
public class PrioridadeServiceImpl extends GenericServiceImpl<Prioridade> implements PrioridadeService{
	
	@Autowired
	private PrioridadeDao priDao;

	@Override
	public List<Prioridade> buscarPrioridades() {
		// TODO Auto-generated method stub
		return priDao.buscarPrioridades();
	}

}
