package uem.co.mz.helpdesk.administracao;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.spring.SpringUtil;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import uem.co.mz.helpdesk.model.Permissoes;
import uem.co.mz.helpdesk.service.PermissoesService;

public class PermissaoController extends GenericForwardComposer{
	
	private Listbox lbx_permissoes;
	
	ListModelList<Permissoes> _permissions;
	
	List<Permissoes> listPer = new ArrayList<Permissoes>();
	
	@WireVariable
	PermissoesService perSer ;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doBeforeComposeChildren(comp);
		
		perSer = (PermissoesService) SpringUtil.getBean("permissoesService");
		listPer = perSer.getAll();
	}
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		visualizarPermissoes();
	}
	
	
	private void visualizarPermissoes() {
		
		_permissions = new ListModelList<Permissoes>(listPer);
		//System.out.println(""+_permissions);
		//System.out.println("_______________________________________________________________________________________________________________");
		lbx_permissoes.setModel(_permissions);
	}

	public List<Permissoes> getListPer() {
		return listPer;
	}

	public void setListPer(List<Permissoes> listPer) {
		this.listPer = listPer;
	}
	

}
