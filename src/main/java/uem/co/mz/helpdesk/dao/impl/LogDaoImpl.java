package uem.co.mz.helpdesk.dao.impl;

import uem.co.mz.helpdesk.dao.LogDao;
import uem.co.mz.helpdesk.model.Log;

import org.springframework.stereotype.Repository;

@Repository("logDao")
public class LogDaoImpl extends GenericDaoImpl<Log> implements LogDao {

	public LogDaoImpl( ) {
		super(LogDao.class);
		// TODO Auto-generated constructor stub
	}

}
