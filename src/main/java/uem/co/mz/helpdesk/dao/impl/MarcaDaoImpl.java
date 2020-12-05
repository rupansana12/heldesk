package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.MarcaDao;
import uem.co.mz.helpdesk.model.Marca;

@Repository
public class MarcaDaoImpl extends GenericDaoImpl<Marca> implements MarcaDao{

	public MarcaDaoImpl( ) {
		super(Marca.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Marca> buscarMarcas() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select mar from Marca mar order by mar.designacao asc");
		return query.list();
	}

	
}
