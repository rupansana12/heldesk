package uem.co.mz.helpdesk.controller.registo;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional.TxType;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Departamento;
import uem.co.mz.helpdesk.model.Sector;
import uem.co.mz.helpdesk.model.TipoCliente;
import uem.co.mz.helpdesk.model.UnidadeOrganica;
import uem.co.mz.helpdesk.report.MasterRep;
import uem.co.mz.helpdesk.service.ClienteService;
import uem.co.mz.helpdesk.service.DepartamentoService;
import uem.co.mz.helpdesk.service.SectorService;
import uem.co.mz.helpdesk.service.TipoClienteService;
import uem.co.mz.helpdesk.service.UnidadeOrganicaService;
import net.sf.jasperreports.engine.JRException;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ClienteController extends GenericForwardComposer {

	private Textbox tbx_nome;
	private Textbox tbx_codigo;
	private Textbox tbx_email;
	private Textbox tbx_celular;
	private Textbox tbx_celularAlternativo;
	private Cliente _cliente;
	private Combobox cbx_unidade_organica;
	private Combobox cbx_departamento;
	private Combobox cbx_sector;
	private Combobox cbx_tipo_cliente;
	private Listbox lbx_clientes;
	private Button btn_gravar;
	private Button btn_actualizar;
	private Button btn_imprimir;
	private Window win;
	private Div div_unidade_organica;
	private Div div_departamento;
	private Div div_sector;
	
	private Include conteudo; 
	
	@WireVariable
	private UnidadeOrganicaService _unidadeOrganicaService;
	
	@WireVariable
	private DepartamentoService _departamentoService;
	
	@WireVariable
	private SectorService _sectorService;
	
	@WireVariable
	private TipoClienteService _tipoClienteService;
	
	@WireVariable
	private ClienteService _clienteSevice;
	
	private List<Cliente> listCli = new ArrayList<Cliente>();

	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {

		super.doBeforeComposeChildren(comp);

		_tipoClienteService = (TipoClienteService) SpringUtil.getBean("tipoClienteService");
		_clienteSevice = (ClienteService) SpringUtil.getBean("clienteService");
		_sectorService = (SectorService) SpringUtil.getBean("sectorService");
		_departamentoService = (DepartamentoService) SpringUtil.getBean("departamentoService");
		_unidadeOrganicaService = (UnidadeOrganicaService) SpringUtil.getBean("unidadeOrganicaService");
	}

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		buscarTiposDeClientes();
		burcarClientes();
		buscarUnidadesOrganicas();
	}
	
	private void limparCampos() {
		tbx_nome.setRawValue(null);
		//tbx_codigo.setRawValue(null);
		tbx_email.setRawValue(null);
		tbx_celular.setRawValue(null);
		tbx_celularAlternativo.setRawValue(null);
		cbx_sector.setRawValue(null);
		cbx_tipo_cliente.setRawValue(null);
		cbx_unidade_organica.setRawValue(null);
		cbx_departamento.setRawValue(null);
		lbx_clientes.clearSelection();
		btn_gravar.setVisible(true);
		btn_actualizar.setVisible(false);
		div_unidade_organica.setVisible(true);
		div_unidade_organica.setVisible(true);
		div_sector.setVisible(true);
	}

	private void burcarClientes() {
		listCli =  _clienteSevice.buscarClientes();
		lbx_clientes.setModel(new ListModelList<Cliente>(listCli));
	}
	
	private void buscarTiposDeClientes( ){
		cbx_tipo_cliente.setModel(new ListModelList<TipoCliente>(_tipoClienteService.buscarTipoDeClientes()));
	}
	
	private void buscarUnidadesOrganicas( ){
		cbx_unidade_organica.setModel(new ListModelList<UnidadeOrganica>(_unidadeOrganicaService.buscarUnidadesOrganicas()));
	}
	
	private void buscarDepartamentosPorUnidadeOrganica(UnidadeOrganica unidadeOrganica){
		cbx_departamento.setModel(new ListModelList<Departamento>(_departamentoService.burcarDepartamentosPorUnidadeOrganica(unidadeOrganica)));
	}
	
	private void buscarSectoresPorUnidadeOrganica(UnidadeOrganica unidadeOrganica){
		cbx_sector.setModel(new ListModelList<Sector>(_sectorService.burcarSectoresPorUnidadeOrganica(unidadeOrganica)));
	}
	
	private void buscarSectoresPorPorDepartamento(Departamento departamento){
		cbx_sector.setModel(new ListModelList<Sector>(_sectorService.burcarSectoresPorDepartamento(departamento)));
	}

	public void showNotifications(String message, String type) {

		Clients.showNotification(message, type, tbx_nome,
				"before_center", 4000, true);
	}

	public void onClick$btn_actualizar() throws InterruptedException {
		
		_cliente.setNome(tbx_nome.getValue());
		_cliente.setTelefone(tbx_celular.getValue());
		_cliente.setTelefoneAlternativo(tbx_celularAlternativo.getValue());
		_cliente.setEmail(tbx_email.getValue());
		_cliente.setSector((Sector)(cbx_sector.getSelectedItem() == null ? null : cbx_sector.getSelectedItem().getValue()));
		_cliente.setTipoCliente(cbx_tipo_cliente.getSelectedItem().getValue());
		_clienteSevice.update(_cliente);
		burcarClientes();
		showNotifications("Cliente actualizado com sucesso!", "info");
		limparCampos();

			}

	public void onClick$btn_gravar(Event e) throws InterruptedException{

		Cliente _cliente = new Cliente();
		_cliente.setNome(tbx_nome.getValue());
		_cliente.setTelefone(tbx_celular.getValue());
		_cliente.setTelefoneAlternativo(tbx_celularAlternativo.getValue());
		_cliente.setEmail(tbx_email.getValue());
		_cliente.setSector((Sector)(cbx_sector.getSelectedItem() == null ? null : cbx_sector.getSelectedItem().getValue()));
		_cliente.setTipoCliente(cbx_tipo_cliente.getSelectedItem().getValue());
		_clienteSevice.create(_cliente);
		burcarClientes();
		showNotifications("Cliente registado com sucesso!", "info");
		limparCampos();
	}

	public void onClick$btn_cancelar(Event e) throws InterruptedException{

		limparCampos();
	
	}
	
	public void onSelect$lbx_clientes(Event e){
		
		_cliente = (Cliente) lbx_clientes.getSelectedItem().getValue();
		tbx_nome.setValue(_cliente.getNome());
		tbx_celular.setValue(_cliente.getTelefone());
		tbx_celularAlternativo.setValue(_cliente.getTelefoneAlternativo());
		tbx_email.setValue(_cliente.getEmail());
		cbx_tipo_cliente.setValue(_cliente.getTipoCliente().getDesignacao());
		
		if(((TipoCliente)cbx_tipo_cliente.getSelectedItem().getValue()).getDesignacao().equalsIgnoreCase("UEM")){
			cbx_unidade_organica.setValue(_cliente.getSector().getDepartamento().getUnidadeOrganica()==null ? null : _cliente.getSector().getDepartamento().getUnidadeOrganica().getDesignacao());
			buscarDepartamentosPorUnidadeOrganica(_cliente.getSector().getDepartamento().getUnidadeOrganica());		
			cbx_departamento.setValue(_cliente.getSector().getDepartamento()==null ? null : _cliente.getSector().getDepartamento().getDesignacao());
			buscarSectoresPorPorDepartamento(_cliente.getSector().getDepartamento());		
			cbx_sector.setValue(_cliente.getSector()==null ? null : _cliente.getSector().getDesignacao());			
			div_unidade_organica.setVisible(true);
			div_departamento.setVisible(true);
			div_sector.setVisible(true);
			
		}else{
			div_unidade_organica.setVisible(false);
			div_departamento.setVisible(false);
			div_sector.setVisible(false);
			
		}		
		
		
		btn_actualizar.setVisible(true);
		btn_gravar.setVisible(false);
	}
	
	public void onSelect$cbx_tipo_cliente(Event e){
		
		if(((TipoCliente)cbx_tipo_cliente.getSelectedItem().getValue()).getDesignacao().equalsIgnoreCase("UEM")){
			cbx_unidade_organica.setRawValue(null);
			cbx_sector.setRawValue(null);
			cbx_departamento.setRawValue(null);
			div_unidade_organica.setVisible(true);
			div_departamento.setVisible(true);
			div_sector.setVisible(true);
		}else{
			div_unidade_organica.setVisible(false);
			div_departamento.setVisible(false);
			div_sector.setVisible(false);
			
		}
	}
	
	public void onSelect$cbx_unidade_organica(Event e){
		cbx_departamento.setRawValue(null);
		cbx_sector.setRawValue(null);
		buscarDepartamentosPorUnidadeOrganica((UnidadeOrganica)cbx_unidade_organica.getSelectedItem().getValue());
	}
	
	public void onSelect$cbx_departamento(Event e){
		cbx_sector.setRawValue(null);
		buscarSectoresPorPorDepartamento(cbx_departamento.getSelectedItem().getValue());
	}
	
	public void onRegistarSolicitacao(ForwardEvent e) throws JRException{
		Map<String, Object> mapaParam = new HashMap<String, Object>();
		Cliente c = (Cliente) e.getData();
		//imprimir(c);
		
		mapaParam.put("cliente", c);
		conteudo.getChildren().clear();
		Executions.createComponents("/WEB-INF/solicitacao/registar_solicitacao.zul", conteudo, mapaParam);
		
	}

	public void onClick$btn_imprimir(Event e) throws JRException{
		
		Map<String, Object> mapaParam = new HashMap<String, Object>();	
		final Execution ex = Executions.getCurrent();
		InputStream inputV= ex.getDesktop().getWebApp().getResourceAsStream("/img/imagen.png");       
        mapaParam.put("imagemLogo", inputV);
        mapaParam.put("listNome", "Lista de Clientes");
		MasterRep.imprimir("/WEB-INF/reportParam/reportParametrizacaoBase.jrxml", listCli, mapaParam, win);
	}

}
