package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.Marca;

public interface MarcaDao extends GenericDao<Marca>{
	
	public List<Marca> buscarMarcas();
	
}
