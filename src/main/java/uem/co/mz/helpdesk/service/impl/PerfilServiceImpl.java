package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.PerfilDao;
import uem.co.mz.helpdesk.model.Perfil;
import uem.co.mz.helpdesk.service.PerfilService;

@Service("perfilService")
public class PerfilServiceImpl extends GenericServiceImpl<Perfil> implements PerfilService {
	
	@Autowired
	private PerfilDao perDao;

	//@Override
	public List<Perfil> listaPerfil() {
		// TODO Auto-generated method stub
		return perDao.listaPerfil();
	}
	
	//@Override
	public Perfil listaUmPerfil(String des) {
		// TODO Auto-generated method stub
		return perDao.listaUmPerfil(des);
	}

	@Override
	public List<Perfil> listaPerfisMenosOPerfil(String[] perfis) {
		// TODO Auto-generated method stub
		return perDao.listaPerfisMenosOPerfil(perfis);
	}

}
