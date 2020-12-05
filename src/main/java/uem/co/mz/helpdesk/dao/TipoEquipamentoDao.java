package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.TipoEquipamento;

public interface TipoEquipamentoDao extends GenericDao<TipoEquipamento>{

	public List<TipoEquipamento> buscarTiposDeEquipamentos();
	
}
