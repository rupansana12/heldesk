package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.Perfil;

public interface PerfilDao extends GenericDao<Perfil>{

	public List<Perfil> listaPerfil();
	
	public Perfil listaUmPerfil(String des);
	
	public List<Perfil> listaPerfisMenosOPerfil(String[] perfis);
	
}
