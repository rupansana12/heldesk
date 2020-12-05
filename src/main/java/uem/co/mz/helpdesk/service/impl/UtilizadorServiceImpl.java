package uem.co.mz.helpdesk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import uem.co.mz.helpdesk.dao.NotificacaoDao;
import uem.co.mz.helpdesk.dao.UtilizadorDao;
import uem.co.mz.helpdesk.model.Notificacao;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.UtilizadorService;

@Service("utilizadorService")
public class UtilizadorServiceImpl extends GenericServiceImpl<Utilizador> implements UtilizadorService, UserDetailsService {
	
	@Autowired
	private UtilizadorDao utiDao;
	
	@Autowired
	private NotificacaoDao notDao;
	
	//@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return getUser(username);
	}
	
	//@Override
	public Utilizador getUser(String login) {
		// TODO Auto-generated method stub
		return utiDao.getUser(login);
	}
	
	public Utilizador buscarUtilizador(String user){
		
		return utiDao.buscarUtilizador(user);
	}
			
	public Utilizador criarUtilizador(Utilizador utilizador){
		
		utiDao.create(utilizador);
		Notificacao not = new Notificacao();
		//not.setDestinoEmail(utilizador.getEmail());
		not.setType("DADOS DE ACESSO");
		//not.setDestinoSMS(String.valueOf(utilizador.getTelefone()));
		not.setEnviada(false);
		not.setMsg("Prezado Utilizador. Seja bem vindo ao Sistema de Gestao da Foco. Dados de acesso: Utilizador: "+utilizador.getUsername()+" Senha: "+utilizador.getPlanPass()+"");
		notDao.create(not);
		return utilizador;
	}
	
	public Utilizador actualizarUtilizador(Utilizador utilizador){
		
		utiDao.update(utilizador);
		/*Notificacao not = new Notificacao();
		//not.setDestinoEmail(utilizador.getEmail());
		not.setType("DADOS DE ACESSO");
		//not.setDestinoSMS(String.valueOf(utilizador.getTelefone()));
		not.setEnviada(false);
		not.setMsg("Prezado Utilizador. Seja bem vindo ao Sistema de Gestao da Foco. Dados de acesso: Utilizador: "+utilizador.getUsername()+" Senha: "+utilizador.getPlanPass()+"");
		notDao.create(not);*/
		return utilizador;
	}
	
	public Utilizador login(String user, String pass){
		
		return utiDao.login(user, pass);
	}

	//@Override
	public String encriptarSenha(String senha){

		//BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		//String hashedPassword = passwordEncoder.encode(senha);
		
		//return hashedPassword;
		return senha;
	}

	public List<Utilizador> listarUtilizador() {
		// TODO Auto-generated method stub
		return utiDao.listarUtilizador();
	}

	@Override
	public List<Utilizador> procurarUser(String username) {
		// TODO Auto-generated method stub
		return utiDao.procurarUser(username);
	}

	@Override
	public List<Utilizador> buscarUtilizador() {
		// TODO Auto-generated method stub
		return utiDao.buscarUtilizador();
	}

}
