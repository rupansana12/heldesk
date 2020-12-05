package uem.co.mz.helpdesk.dao.impl;

import org.springframework.stereotype.Repository;

import uem.co.mz.helpdesk.dao.NotificacaoDao;
import uem.co.mz.helpdesk.model.Notificacao;

@Repository
public class NotificacaoDaoImpl extends GenericDaoImpl<Notificacao> implements NotificacaoDao{

	public NotificacaoDaoImpl() {
		super(Notificacao.class);
		// TODO Auto-generated constructor stub
	}

}
