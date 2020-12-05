package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.UnidadeOrganicaDao;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

@Repository
public class UnidadeOrganicaDaoImpl extends GenericDaoImpl<UnidadeOrganica> implements UnidadeOrganicaDao{

	public UnidadeOrganicaDaoImpl() {
		super(UnidadeOrganica.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<UnidadeOrganica> burcarUnidadesOrganicas() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT uo FROM UnidadeOrganica uo left join fetch uo.tipoCliente tc order by uo.designacao asc ");
		return query.list();
	}

	@Override
	public List<UnidadeOrganica> burcarUnidadesOrganicasPorTipoDeCliente(
			TipoCliente tipoCliente) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT uo FROM UnidadeOrganica uo left join fetch uo.tipoCliente tc where tc =:tipoCliente order by uo.designacao asc ");
		query.setParameter("tipoCliente", tipoCliente);
		return query.list();
	}
	

}
