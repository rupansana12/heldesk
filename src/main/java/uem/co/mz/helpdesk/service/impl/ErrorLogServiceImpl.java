package uem.co.mz.helpdesk.service.impl;

import uem.co.mz.helpdesk.dao.ErrorLogDao;
import uem.co.mz.helpdesk.model.ErrorLog;
import uem.co.mz.helpdesk.service.ErrorLogService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("errorLogService")
public class ErrorLogServiceImpl extends GenericServiceImpl<ErrorLog> implements ErrorLogService{
	
	@Autowired
	private ErrorLogDao errLogDao;

}
