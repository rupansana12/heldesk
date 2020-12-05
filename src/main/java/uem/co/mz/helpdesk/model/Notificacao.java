package uem.co.mz.helpdesk.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="notificacao")
@Access(AccessType.FIELD)
public class Notificacao extends IdEntity{
	
	private static final long serialVersionUID = 1L;

	@Column(name = "type")
	private String type;

	@Column(name = "msg")
	private String msg;

	@Column(name = "is_enviada")
	private boolean isEnviada;

	@Column(name = "destino_email")
	private String destinoEmail;

	@Column(name = "destino_sms")
	private String destinoSMS;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public boolean isEnviada() {
		return isEnviada;
	}

	public void setEnviada(boolean isEnviada) {
		this.isEnviada = isEnviada;
	}

	public String getDestinoEmail() {
		return destinoEmail;
	}

	public void setDestinoEmail(String destinoEmail) {
		this.destinoEmail = destinoEmail;
	}

	public String getDestinoSMS() {
		return destinoSMS;
	}

	public void setDestinoSMS(String destinoSMS) {
		this.destinoSMS = destinoSMS;
	}

}
