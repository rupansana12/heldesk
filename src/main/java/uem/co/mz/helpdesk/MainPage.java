package uem.co.mz.helpdesk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.spring.SpringUtil;
import org.zkoss.zhtml.Ol;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;

import uem.co.mz.helpdesk.model.Cliente;
import uem.co.mz.helpdesk.model.Perfil;
import uem.co.mz.helpdesk.model.Utilizador;
import uem.co.mz.helpdesk.service.ClienteService;
import uem.co.mz.helpdesk.service.UtilizadorService;
import uem.co.mz.helpdesk.service.impl.LogServiceImpl;

public class MainPage extends GenericForwardComposer{
	
	protected List<String> links;
	
	//@Wire("#mainlayout")
	private Div target;

	@Wire("#breadcrumb")
	private Ol ol;

	@WireVariable
	private LogServiceImpl logService;
	
	protected Authentication authentication = SecurityContextHolder
			.getContext().getAuthentication();

	@Wire
	private Image imgPfl;

	@Wire("#sidebar #imgPflSide")
	private Image imgPflSide;

	// SideBar Menus

	private Include conteudo; 
	
	private String initPage;

	private String hoursPage;

	private String recruitPage;

	private String perfomPage;

	private String leavePage;

	private String trainPage;

	private String recPage;

	private String aproovPage;

	private String morePage;
	
	public Utilizador loggeduser;
	
	public Cliente cliente;
	
	
	@WireVariable
	private UtilizadorService utilizadorService;
	
	@WireVariable
	private ClienteService condutorService;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
	
		utilizadorService = (UtilizadorService) SpringUtil.getBean("utilizadorService");
		condutorService = (ClienteService)SpringUtil.getBean("clienteService");
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		//alert(authentication.getName());
		menuReset();
		setInitPage("active");
		loggeduser = utilizadorService.getUser(authentication.getName());
		Executions.getCurrent().getDesktop().getSession()
				.setAttribute("ss_utilizador", loggeduser);
		
		
		
		if(((Perfil)loggeduser.getRoles().iterator().next()).getId()==2L){
			cliente = null;//////////////
			//cliente = condutorService.carregarDadosBasicosDoCondutorPorUser(loggeduser);
			homeCondutor();
		}/*else {
			homeAdministrator();
		}*/
		final HashMap<String, Object> map = new HashMap<String, Object>();

		//Selectors.wireComponents(view, this, false);

		//target.getChildren().clear();

		/////////map.put("target", target);
		////////////map.put("breadcrumb", ol);
		/////////////////////////Executions.createComponents("dashboard.zul", target, map);

		links = new ArrayList<String>();

		if (target != null) {
			//imgPflSide.invalidate();
		}
	}
	
	
	
	public void homeCondutor() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		//map.put("target", target);
		//map.put("breadcrumb", ol);
		//map.put("condutorActualizar", condutor);
		conteudo.setAttribute("condutorView", cliente);
		//conteudo.setSrc("/WEB-INF/registo/dadosBasicos.zul");
		//Executions.createComponents("/WEB-INF/registo/dadosBasicos.zul", conteudo, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		//////////drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
		
	}
	
	
	public void homeAdministrator() {
		final HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("target", target);
		map.put("breadcrumb", ol);
		target.getChildren().clear();
		//Executions.createComponents("/WEB-INF/registo/dadosBasicos.zul", target, map);

		links = new ArrayList<String>();
		links.add("Inicio");
		//////////drawnBreadcrumb("fa fa-sort", "Pagina Inicial", links);

		menuReset();
		setInitPage("active");
		
	}
	
	
	
	private void menuReset() {

		setInitPage("");
		setHoursPage("");
		setRecruitPage("");
		setRecPage("");
		setPerfomPage("");
		setLeavePage("");
		setTrainPage("");
		setAproovPage("");
		setMorePage("");
	}
	
	public String getInitPage() {
		return initPage;
	}

	public void setInitPage(String initPage) {
		this.initPage = initPage;
	}

	public String getHoursPage() {
		return hoursPage;
	}

	public void setHoursPage(String hoursPage) {
		this.hoursPage = hoursPage;
	}

	public String getRecruitPage() {
		return recruitPage;
	}

	public void setRecruitPage(String recruitPage) {
		this.recruitPage = recruitPage;
	}

	public String getPerfomPage() {
		return perfomPage;
	}

	public void setPerfomPage(String perfomPage) {
		this.perfomPage = perfomPage;
	}

	public String getLeavePage() {
		return leavePage;
	}

	public void setLeavePage(String leavePage) {
		this.leavePage = leavePage;
	}

	public String getTrainPage() {
		return trainPage;
	}

	public void setTrainPage(String trainPage) {
		this.trainPage = trainPage;
	}

	public String getRecPage() {
		return recPage;
	}

	public void setRecPage(String recPage) {
		this.recPage = recPage;
	}

	public String getAproovPage() {
		return aproovPage;
	}

	public void setAproovPage(String aproovPage) {
		this.aproovPage = aproovPage;
	}

	public String getMorePage() {
		return morePage;
	}

	public void setMorePage(String morePage) {
		this.morePage = morePage;
	}

}
