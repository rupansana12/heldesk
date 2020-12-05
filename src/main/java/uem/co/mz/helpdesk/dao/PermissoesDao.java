package uem.co.mz.helpdesk.dao;

import java.util.List;
import java.util.Set;

import uem.co.mz.helpdesk.model.Permissoes;

public interface PermissoesDao extends GenericDao<Permissoes>{
	
	public List<Permissoes> listaPermissoesNaoSelecionadas(Set<Permissoes> per);

}
