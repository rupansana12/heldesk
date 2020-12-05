package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Perfil;

public interface PerfilService extends GenericService<Perfil>{
	
	public List<Perfil> listaPerfil();
	public List<Perfil> listaPerfisMenosOPerfil(String[] perfis);
	public Perfil listaUmPerfil(String des);

}
