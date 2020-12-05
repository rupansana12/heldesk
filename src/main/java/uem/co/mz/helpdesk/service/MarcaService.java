package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Marca;

public interface MarcaService extends GenericService<Marca>{
	
	public List<Marca> buscarMarcas();

}
