package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.PrioridadeDao;
import uem.co.mz.helpdesk.model.Prioridade;

@Repository
public class PrioridadeDaoImpl extends GenericDaoImpl<Prioridade> implements PrioridadeDao{

	public PrioridadeDaoImpl( ) {
		super(Prioridade.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Prioridade> buscarPrioridades() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select pri from Prioridade pri order by pri.designacao asc");
		return query.list();
	}

}
