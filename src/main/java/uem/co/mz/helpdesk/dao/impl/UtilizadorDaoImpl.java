package uem.co.mz.helpdesk.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.UtilizadorDao;
import uem.co.mz.helpdesk.model.Utilizador;

@Repository
public class UtilizadorDaoImpl extends GenericDaoImpl<Utilizador> implements UtilizadorDao{

	public UtilizadorDaoImpl() {
		super(Utilizador.class);
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	//@Override
	public Utilizador getUser(String login) {
		Query query = getCurrentSession().createQuery(" SELECT u FROM Utilizador u left join fetch u.empresas emp where u.username =:login ");
		query.setParameter("login", login);
		return (Utilizador) DataAccessUtils.singleResult(query.list());
		//return (Utilizador) query.uniqueResult();
	}
	
	public Utilizador buscarUtilizador(String user){
		
		
		Query query = getCurrentSession().createQuery("from Utilizador u left join fetch u.empresas emp where u.username like :user order by u.username asc");
		query.setParameter("user","%"+user+"%");
		return (Utilizador) query.uniqueResult();
	}
	
	public Utilizador login(String user, String pass){
		
		Query query = getCurrentSession().createQuery("from Utilizador u left join fetch u.empresas emp where u.username=:user and u.password=:pass and u.enabled=true");
		query.setParameter("user",user);
		query.setParameter("pass",pass);
		return (Utilizador) query.uniqueResult();
		
	}
	
	public List<Utilizador> listarUtilizador(){
		Query query = getCurrentSession().createQuery(" from Utilizador u left join fetch u.empresas emp ");
		return query.list();
	}

	@Override
	public List<Utilizador> procurarUser(String username) {
		// TODO Auto-generated method stub
		Query query = getCurrentSession().createQuery(" SELECT u FROM Utilizador u left join fetch u.empresas emp where u.username like :username ");
		query.setParameter("username", "%"+username+"%");
		return query.list();
	}

	@Override
	public List<Utilizador> buscarUtilizador() {
		Query query = getCurrentSession().createQuery("SELECT u from Utilizador u left join fetch u.roles utRol where utRol.id =:user order by u.username asc");
		query.setParameter("user",Long.parseLong("2"));
		return query.list();
	}
	
}
