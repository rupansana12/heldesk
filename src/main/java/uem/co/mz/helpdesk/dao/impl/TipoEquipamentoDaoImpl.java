package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.TipoEquipamentoDao;
import uem.co.mz.helpdesk.model.TipoEquipamento;

@Repository
public class TipoEquipamentoDaoImpl extends GenericDaoImpl<TipoEquipamento> implements TipoEquipamentoDao{

	public TipoEquipamentoDaoImpl( ) {
		super(TipoEquipamento.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<TipoEquipamento> buscarTiposDeEquipamentos() {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("select tip from TipoEquipamento tip order by tip.designacao asc ");
		return query.list();
	}

}
