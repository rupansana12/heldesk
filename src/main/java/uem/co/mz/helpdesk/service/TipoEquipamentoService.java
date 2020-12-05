package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoEquipamento;

public interface TipoEquipamentoService extends GenericService<TipoEquipamento>{

	public List<TipoEquipamento> buscarTiposDeEquipamentos();
	
}
