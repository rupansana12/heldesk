package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.CategoriaServicoDao;
import uem.co.mz.helpdesk.model.CategoriaServico;

@Repository
public class CategoriaServicoDaoImpl extends GenericDaoImpl<CategoriaServico> implements CategoriaServicoDao{

	public CategoriaServicoDaoImpl() {
		super(CategoriaServico.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<CategoriaServico> buscarCategoriasDosServicos() {
		Query query = getCurrentSession().createQuery("select cat from CategoriaServico cat order by cat.designacao asc ");
		return query.list();
	}

}
