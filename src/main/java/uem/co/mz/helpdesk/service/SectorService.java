package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.Sector;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

public interface SectorService extends GenericService<Sector>{


	public List<Sector> burcarSectores();
	public List<Sector> burcarSectoresPorDepartamento(Departamento departamento);
	public List<Sector> burcarSectoresPorUnidadeOrganica(UnidadeOrganica unidadeOrganica);
	
}
