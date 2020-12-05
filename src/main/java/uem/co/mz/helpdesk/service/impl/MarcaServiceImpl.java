package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.MarcaDao;
import uem.co.mz.helpdesk.model.Marca;
import uem.co.mz.helpdesk.service.MarcaService;

@Service("marcaService")
public class MarcaServiceImpl extends GenericServiceImpl<Marca> implements MarcaService{
	
	@Autowired
	private MarcaDao macDao;

	@Override
	public List<Marca> buscarMarcas() {
		// TODO Auto-generated method stub
		return macDao.buscarMarcas();
	}

}
