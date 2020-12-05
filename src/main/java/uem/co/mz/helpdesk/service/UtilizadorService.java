package uem.co.mz.helpdesk.service;

import java.util.List;

import uem.co.mz.helpdesk.model.Utilizador;

public interface UtilizadorService extends GenericService<Utilizador>{
	
	public Utilizador getUser(String login);
	
	public Utilizador buscarUtilizador(String user);
	
	public List<Utilizador> buscarUtilizador();
	
	public Utilizador criarUtilizador(Utilizador utilizador);
	
	public Utilizador actualizarUtilizador(Utilizador utilizador);
	
	public Utilizador login(String user, String pass);
	
	public String encriptarSenha(String senha);
	
	public List<Utilizador> listarUtilizador();
	public List<Utilizador> procurarUser(String username);

}
