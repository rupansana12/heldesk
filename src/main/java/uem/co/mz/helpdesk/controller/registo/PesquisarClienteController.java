package uem.co.mz.helpdesk.controller.registo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;

import org.springframework.security.core.userdetails.User;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.ClienteService;
import uem.co.mz.helpdesk.service.TipoClienteService;

public class PesquisarClienteController extends GenericForwardComposer{
	
	private Textbox tbx_nome;
	private Combobox cbx_tipo_cliente;
	private Button btn_procurar;
	private Label lbl_total;
		
	private Listbox lbx_clientes;
	
	private Include conteudo; 
	
	private Window win_pesquisar_condutor;
	
	@WireVariable
	private ClienteService clienteService;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	private List<Cliente> clientes;
	private ListModelList<Cliente> clientesModel;
	
	private Utilizador user;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
		clienteService = (ClienteService)SpringUtil.getBean("clienteService");
		user = (Utilizador) Executions.getCurrent().getDesktop().getSession().getAttribute("ss_utilizador");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		
		buscarTiposDeClientes();
		
	}
	
	public void onClick$btn_procurar(){
		
		clientes = clienteService.buscarClientesPorNomeETipo(tbx_nome.getValue(), 
				(TipoCliente)((((TipoCliente)cbx_tipo_cliente.getSelectedItem().getValue()).getDesignacao().equalsIgnoreCase("TODOS") 
				|| cbx_tipo_cliente.getSelectedItem()==null) ? null :  cbx_tipo_cliente.getSelectedItem().getValue()));
		  
		listarClientes(clientes);
		tbx_nome.setRawValue(null);
	}	
	
	private void buscarTiposDeClientes( ){
		cbx_tipo_cliente.setModel(new ListModelList<TipoCliente>(_tipoClienteService.buscarTipoDeClientes()));
	}
		
	private void listarClientes(List<Cliente> clientes){
		clientesModel = new ListModelList<>(clientes);
		lbx_clientes.setModel(clientesModel);
		lbl_total.setValue(""+clientes.size());
	}
	
	public void onRegistarSolicitacao(ForwardEvent e) throws JRException{
		Map<String, Object> mapaParam = new HashMap<String, Object>();
		Cliente c = (Cliente) e.getData();
		//imprimir(c);
		
		mapaParam.put("cliente", c);
		conteudo.getChildren().clear();
		Executions.createComponents("/WEB-INF/solicitacao/registar_solicitacao.zul", conteudo, mapaParam);
		
	}
	
	public void imprimir(Cliente cliente) throws JRException {


		List<Cliente> condutores = new ArrayList<Cliente>();
		//condutores.add(cliente);
		final Execution ex = Executions.getCurrent();

		Map<String, Object> mapaParam = new HashMap<String, Object>();

		/*mapaParam.put("NumeroCandidato", _candidato.getCodigo());
		mapaParam.put("NomeApelido", _candidato.getNome() + " " + _candidato.getApelido());
		mapaParam.put("Genero", _candidato.getGenero());
		mapaParam.put("Nacionalidade", _candidato.getPaisNascimento());*/
		
		InputStream inputV = ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");
		mapaParam.put("imagemLogo", inputV);
		if (condutores.size() == 0) {
			Clients.showNotification("Sem dados da Candidatura por imprimir", "info", win_pesquisar_condutor, "middle_center",
					44000);
			return;
		}

		//mapaParam.put("listContas", listContas);
		// alert("listContas"+ listContas);
		String urlReport="/WEB-INF/reportParam/condutor.jrxml";
		MasterRep.imprimir(urlReport, condutores, mapaParam, win_pesquisar_condutor);
		
}

}
