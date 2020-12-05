package uem.co.mz.helpdesk.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.NotificacaoDao;
import uem.co.mz.helpdesk.model.Notificacao;
import uem.co.mz.helpdesk.service.NotificacaoService;

@Service("notificacaoService")
public class NotificacaoServiceImpl extends GenericServiceImpl<Notificacao> implements NotificacaoService{

	@Autowired
	private NotificacaoDao notDao;
	
}
