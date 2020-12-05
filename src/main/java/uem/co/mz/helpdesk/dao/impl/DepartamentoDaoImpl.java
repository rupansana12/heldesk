package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.DepartamentoDao;
import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

@Repository
public class DepartamentoDaoImpl extends GenericDaoImpl<Departamento> implements DepartamentoDao{

	public DepartamentoDaoImpl() {
		super(Departamento.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Departamento> burcarDepartamentos() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT d FROM Departamento d join fetch d.unidadeOrganica uo order by d.designacao asc ");
		return query.list();
	}

	@Override
	public List<Departamento> burcarDepartamentosPorUnidadeOrganica(
			UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT d FROM Departamento d join fetch d.unidadeOrganica uo where uo=:unidadeOrganica order by d.designacao asc ");
		query.setParameter("unidadeOrganica", unidadeOrganica);
		return query.list();
	}

}
