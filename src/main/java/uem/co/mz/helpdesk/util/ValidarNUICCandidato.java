package uem.co.mz.helpdesk.util;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.service.ClienteService;

public class ValidarNUICCandidato{
	
	@Autowired
	
	ApplicationContext context = ContextLoader
	.getCurrentWebApplicationContext();
	private ClienteService _clienteService = context.getBean(ClienteService.class);
	
	public boolean nuicEhValido(Cliente condutor, String distrito){
	

		NuicUtil nuicUtil = new NuicUtil();	
		 Calendar dataNascimento = Calendar.getInstance();
		dataNascimento.setTime(condutor.getCreated());

		
	   String	NUIC = nuicUtil.gerarNUIC("",condutor.getNome(), dataNascimento, distrito);
		
		//String	NUIC = nuicUtil.gerarNUIC(candidato.getNuit());

	   /*Cliente cand =  _clienteService.getCondutorPorNUIC(NUIC);
		if (cand == null) {
			condutor.setNUIC(NUIC);
		return true;
		}
*/
		return false;

	}

}
