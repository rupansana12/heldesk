package uem.co.mz.helpdesk.util;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.zkoss.zk.ui.Desktop;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.image.AImage;
import org.zkoss.spring.SpringUtil;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;

//import uem.co.mz.helpdesk.model.Condutor;
//import uem.co.mz.helpdesk.service.CondutorService;

@SuppressWarnings("rawtypes")
public class TakePhotoCtrl extends GenericForwardComposer{

	private static final long serialVersionUID = 1L;
	private Window  win5;
	private Window win_photo;
	@SuppressWarnings("unused")
	private Include inc_photo;
	private Image img_pic;
	private String fileName;
	private Media _media;
	private String PHOTO_PATH = "";
	private String CAMINHO_DA_FOTO;
	private UploadFile _uploadFile;
	private Desktop _desktop;
	private Session _session = Executions.getCurrent().getSession();
	//private Condutor _condutor;
	private Path path;
	private String pastaDeFotos;
	private String pastaDeFotos_semSeparador;
	private String caminhoCompleto;
	private String separator = "/";
	//private String separator = System.getProperty("file.separator");
	//String canoPath="";
	//String fs = File.separator;
	private org.zkoss.zhtml.Button btn_capturar, btn_cancelar, btn_enviar;
	
	@Autowired
	//private CondutorService _condutorService;
	
	@Override
	public void doBeforeComposeChildren(Component comp) throws Exception {
		super.doBeforeComposeChildren(comp);
		
		//_condutorService = (CondutorService) SpringUtil.getBean("condutorService");
		//_condutor = (Condutor) Executions.getCurrent().getArg().get("condutor");
		PHOTO_PATH = separator + "ficheiros" + separator + "foto" + separator;
		//caminhoCompleto = "C:"+PHOTO_PATH+_condutor.getCodigo()+".png";
		path = Paths.get(PHOTO_PATH);
		pastaDeFotos_semSeparador =  path.toAbsolutePath().toString();
		pastaDeFotos =  (path.toAbsolutePath().toString()).concat( separator);
		//System.out.println("pastaDeFotos : "+pastaDeFotos+" : _condutor : "+_condutor.getNome());
	}
	
	
	
	 @Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		_uploadFile = new UploadFile();
		_desktop = Executions.getCurrent().getDesktop();
		_session = _desktop.getSession();
		
		_session.setAttribute("uri_foto", caminhoCompleto);
		//_session.setAttribute("uri_foto", pastaDeFotos.concat(""+_condutor.getCodigo()).concat(".png"));
		//canoPath = fs+"ficheiros"+fs+"foto"+fs+_candidato.getCodigo()+".png";
		//System.out.println("caminho absoluto : "+pastaDeFotos.concat(""+_condutor.getCodigo()).concat(".png"));
	}
	 
	public void onClick$btn_capturar(Event e){
		//img_pic = (Image) win.getFellow("img_pic");
	//img_pic.setSrc("/img/avatar.png");

	}

	
	public void onClick$btn_enviar(){
		if(_condutor!=null){
			img_pic = (Image) win5.getFellow("img_pic");
			_condutor.setFoto(pastaDeFotos+_condutor.getCodigo()+".png");
			_condutorService.saveOrUpdate(_condutor);
			
			//CAMINHO_DA_FOTO = pastaDeFotos;
			fileName = ""+_condutor.getCodigo()+".png";
			
			
			try {
				//System.out.println(pastaDeFotos+""+fileName+" : pastaDeFotos+ +fileName ");
				AImage image = new AImage(caminhoCompleto);
				//AImage image = new AImage(pastaDeFotos+""+fileName);
				img_pic.setContent(image);
				//alert("Certo");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//img_pic.setSrc("/"+"ficheiros"+"/"+"foto"+"/"+_candidato.getCodigo()+".png");
			win_photo.detach();
			//Include inc = (Include) win5.getParent();
			//inc.invalidate();
			
		}
	
	}
	
	public void onClick$btn_cancelar(Event e){
		win_photo.detach();
	}
	
	
}