package uem.co.mz.helpdesk.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table (name="param_tipo_cliente")
@Access (AccessType.FIELD)
public class TipoCliente extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	public TipoCliente(){
		super();
	}
	
	public TipoCliente(String designacao) {
		super();
		this.designacao = designacao;
	}
	
	@Column (name = "designacao")
	private String designacao;
	
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public TipoCliente tipoDeClienteTodos(){		
		return new TipoCliente("TODOS");
		
	}
}
