package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import uem.co.mz.helpdesk.dao.GenericDao;
import uem.co.mz.helpdesk.model.IdEntity;
import uem.co.mz.helpdesk.service.GenericService;

@Transactional
abstract class GenericServiceImpl<T extends IdEntity> implements GenericService<T> {
	
	@Autowired
	protected GenericDao<T> specificDao;
	
	public List<T> getAll(){
		return specificDao.getAll();
	}

	public List<T> getAllOrder(String desc){
		return specificDao.getAllOrder(desc);
	}

	public T findByLike(String desc, String val){
		return specificDao.findByLike(desc, val);
	}

	public void saveOrUpdate(T t){
		specificDao.saveOrUpdate(t);
	}

	public T findById(long id){
		return specificDao.findById(id);
	}
	
	public T create(T t){
		return specificDao.create(t);
	}
	
	public T update(T t){
		
		return specificDao.update(t);
	}
			
	
	public void remove(T t){
		specificDao.remove(t);
	}

	public long count(String desc){
		return specificDao.count(desc);
	}

	public T first(String desc){
		return specificDao.first(desc);
	}

	public T last(String desc){
		return specificDao.last(desc);
	}
	
}
