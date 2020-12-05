package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.IdEntity;

public interface GenericDao<T extends IdEntity> {
	
	public List<T> getAll();
	
	public List<T> getAllOrder(String desc);
	
	public T findByLike(String desc, String val);
	
	public void saveOrUpdate(T t);
	
	public T create(T t);
	
	public T update(T t);
	
	public T findById(long id);
	
	public void remove(T t);
	
	public long count(String desc);

    public T first(String desc);
    
    public T last(String desc);

}
