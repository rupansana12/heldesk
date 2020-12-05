package uem.co.mz.helpdesk.dao.impl;

import uem.co.mz.helpdesk.dao.ErrorLogDao;
import uem.co.mz.helpdesk.model.ErrorLog;

import org.springframework.stereotype.Repository;

@Repository
public class ErrorLogDaoImpl extends GenericDaoImpl<ErrorLog> implements ErrorLogDao{

	public ErrorLogDaoImpl( ) {
		super(ErrorLog.class);
		// TODO Auto-generated constructor stub
	}

}
