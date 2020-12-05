package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.SectorDao;
import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.Sector;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.service.SectorService;

@Service("sectorService")
public class SectorServiceImpl extends GenericServiceImpl<Sector> implements SectorService{
	
	@Autowired
	private SectorDao secDao;

	@Override
	public List<Sector> burcarSectores() {
		// TODO Auto-generated method stub
		return secDao.burcarSectores();
	}

	@Override
	public List<Sector> burcarSectoresPorDepartamento(
			Departamento departamento) {
		// TODO Auto-generated method stub
		return secDao.burcarSectoresPorDepartamento(departamento);
	}

	@Override
	public List<Sector> burcarSectoresPorUnidadeOrganica(
			UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		return secDao.burcarSectoresPorUnidadeOrganica(unidadeOrganica);
	}

}
