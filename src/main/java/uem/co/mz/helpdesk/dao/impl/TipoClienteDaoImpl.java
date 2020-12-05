package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.TipoClienteDao;
import uem.co.mz.helpdesk.model.TipoCliente;

@Repository
public class TipoClienteDaoImpl extends GenericDaoImpl<TipoCliente> implements TipoClienteDao{

	public TipoClienteDaoImpl( ) {
		super(TipoCliente.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TipoCliente> buscarTipoDeClientes() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select tip from TipoCliente tip order by tip .designacao asc");
		return query.list();
	}

}
