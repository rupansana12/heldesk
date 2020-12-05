package uem.co.mz.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.LogDao;
import uem.co.mz.helpdesk.model.Log;
import uem.co.mz.helpdesk.service.LogService;

@Service("logService")
public class LogServiceImpl extends GenericServiceImpl<Log>implements LogService {

	@Autowired
	private LogDao logDao;
	
}
