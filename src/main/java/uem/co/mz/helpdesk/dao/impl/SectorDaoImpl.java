package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.SectorDao;
import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.Sector;
import uem.co.mz.helpdesk.model.UnidadeOrganica;

@Repository
public class SectorDaoImpl extends GenericDaoImpl<Sector> implements SectorDao{

	public SectorDaoImpl() {
		super(Sector.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Sector> burcarSectores() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT s FROM Sector s join fetch s.departamento d join fetch d.unidadeOrganica uo order by s.designacao asc ");
		return query.list();
	}

	@Override
	public List<Sector> burcarSectoresPorDepartamento(
			Departamento departamento) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT s FROM Sector s join fetch s.departamento d join fetch d.unidadeOrganica uo "
				+ "where d=:departamento order by s.designacao asc ");
		query.setParameter("departamento", departamento);
		return query.list();
	}

	@Override
	public List<Sector> burcarSectoresPorUnidadeOrganica(
			UnidadeOrganica unidadeOrganica) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("SELECT s FROM Sector s join fetch s.departamento d join fetch d.unidadeOrganica uo "
				+ "where uo=:unidadeOrganica order by s.designacao asc ");
		query.setParameter("unidadeOrganica", unidadeOrganica);
		return query.list();
	}

}
