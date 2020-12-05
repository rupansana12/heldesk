package uem.co.mz.helpdesk.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "param_unidade_organica")
@Access(AccessType.FIELD)
public class UnidadeOrganica extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "designacao")
	private String designacao;
	
	@Column(name = "extensao")
	private String extensao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipo_cliente_id")
	private TipoCliente tipoCliente;

	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public TipoCliente getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
