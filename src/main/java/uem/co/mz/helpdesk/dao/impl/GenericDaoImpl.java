package uem.co.mz.helpdesk.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import uem.co.mz.helpdesk.dao.GenericDao;
import uem.co.mz.helpdesk.model.IdEntity;

public abstract class GenericDaoImpl<T extends IdEntity> implements GenericDao<T> {
	
	@Autowired
	private SessionFactory sessionFactory;
	@SuppressWarnings("rawtypes")
	private Class type;
	
	@SuppressWarnings("rawtypes")
	public GenericDaoImpl(Class clazz){
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		type = (Class<T>) pt.getActualTypeArguments()[0];
	}
	
	public void saveOrUpdate(T t) {
		getCurrentSession().saveOrUpdate(t);
	}
	
	public T create(T t) {
		getCurrentSession().saveOrUpdate(t);
		return t;
	}
	
	public T update(T t) {
		getCurrentSession().saveOrUpdate(t);
		return t;
	}
	
	public void remove(T t) {
		getCurrentSession().delete(t);	
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return (List<T>) getCurrentSession().createQuery("FROM "+type.getName()).list();
	}
	
	@SuppressWarnings("unchecked")
	public List<T> getAllOrder(String desc) {
		return (List<T>) getCurrentSession().createQuery("FROM "+type.getName()+" c order by c."+desc+" asc").list();
	}
	
	public long count(String desc) {	
		return (long) getCurrentSession().createQuery("select count(c."+desc+") FROM "+type.getName()+ " c" ).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T findById(long id) {	
		return (T) getCurrentSession().createQuery("FROM "+type.getName()+ " where id  = " +id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public T findByLike(String desc, String val) {	
		return (T) getCurrentSession().createQuery("FROM "+type.getName()+ " c where c."+desc+"  like '%" +val+"%' asc").list();
	}
	
	@SuppressWarnings("unchecked")
	public T last(String desc) {
		return (T) getCurrentSession().createQuery("FROM "+type.getName()+" c  order by c."+desc+" desc").list().get(0);	
	}
	
	@SuppressWarnings("unchecked")
	public T first(String desc) {
		return (T) getCurrentSession().createQuery("FROM "+type.getName()+" c order by c."+desc+" asc").list().get(0);
	}
	
	protected Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}


}
