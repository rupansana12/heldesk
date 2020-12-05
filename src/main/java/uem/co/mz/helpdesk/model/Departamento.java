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
@Table(name = "param_departamento")
@Access(AccessType.FIELD)
public class Departamento extends IdEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "sigla")
	private String sigla;
	
	@Column(name = "designacao")
	private String designacao;
	
	@Column(name = "extensao")
	private String extensao;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidade_organica_id")
	private UnidadeOrganica unidadeOrganica;

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

	public UnidadeOrganica getUnidadeOrganica() {
		return unidadeOrganica;
	}

	public void setUnidadeOrganica(UnidadeOrganica unidadeOrganica) {
		this.unidadeOrganica = unidadeOrganica;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

}
