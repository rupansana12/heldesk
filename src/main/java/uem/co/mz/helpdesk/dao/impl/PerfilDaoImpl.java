package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.PerfilDao;
import uem.co.mz.helpdesk.model.Perfil;

@Repository
public class PerfilDaoImpl extends GenericDaoImpl<Perfil> implements PerfilDao{

	public PerfilDaoImpl() {
		super(Perfil.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Perfil> listaPerfil(){
		
		Query query = getCurrentSession().createQuery("from Perfil p ");
		return query.list();
	}
	
	public Perfil listaUmPerfil(String des){
		
		Query query = getCurrentSession().createQuery("from Perfil p where p.designacao =:des");
		query.setParameter("des", des);
		return (Perfil) query.uniqueResult();
	}

	@Override
	public List<Perfil> listaPerfisMenosOPerfil(String[] perfis) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery("from Perfil p where p.designacao not in (:perfis)");
		query.setParameterList("perfis", perfis);
		return query.list();
	}

}
