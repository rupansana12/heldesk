package uem.co.mz.helpdesk.service;

import java.util.List;
import java.util.Set;

import uem.co.mz.helpdesk.model.Permissoes;

public interface PermissoesService extends GenericService<Permissoes>{
	
	public List<Permissoes> listaPermissoesNaoSelecionadas(Set<Permissoes> per);

}
