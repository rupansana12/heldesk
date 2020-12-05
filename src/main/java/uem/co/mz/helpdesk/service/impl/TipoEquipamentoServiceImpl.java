package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.TipoEquipamentoDao;
import uem.co.mz.helpdesk.model.TipoEquipamento;
import uem.co.mz.helpdesk.service.TipoEquipamentoService;

@Service("tipoEquipamentoService")
public class TipoEquipamentoServiceImpl extends GenericServiceImpl<TipoEquipamento> implements TipoEquipamentoService{
	
	@Autowired
	private TipoEquipamentoDao tipEqDao;

	@Override
	public List<TipoEquipamento> buscarTiposDeEquipamentos() {
		// TODO Auto-generated method stub
		return tipEqDao.buscarTiposDeEquipamentos();
	}

}
