package uem.co.mz.helpdesk.dao;

import java.util.List;

import uem.co.mz.helpdesk.model.Utilizador;

public interface UtilizadorDao extends GenericDao<Utilizador>{
	
	public Utilizador buscarUtilizador(String user);
	
	public List<Utilizador> buscarUtilizador();
	
	public Utilizador login(String user, String pass);
	
	public Utilizador getUser(String login);
	
	public List<Utilizador> listarUtilizador();
	
	public List<Utilizador> procurarUser(String username);

}
