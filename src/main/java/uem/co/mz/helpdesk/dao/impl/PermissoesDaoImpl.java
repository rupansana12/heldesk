package uem.co.mz.helpdesk.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.PermissoesDao;
import uem.co.mz.helpdesk.model.Permissoes;

@Repository
public class PermissoesDaoImpl extends GenericDaoImpl<Permissoes> implements PermissoesDao{

	public PermissoesDaoImpl() {
		super(Permissoes.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Permissoes> listaPermissoesNaoSelecionadas(Set<Permissoes> per){
		List<Long> plis = new ArrayList<Long>();
		for( Permissoes p : per){
			plis.add(p.getId());
		}
		Query query = getCurrentSession().createQuery("from Permissoes p where p.id not in (:plis)");
		query.setParameterList("plis", plis);
		return query.list();
		
	}
	

}
