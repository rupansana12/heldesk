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
@Table (name="solicitacao_equipamento")
@Access (AccessType.FIELD)
public class SolicitacaoEquipamento extends IdEntity{

	private static final long serialVersionUID = 1L;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "solicitacao_id")
	private Solicitacao solicitacao;
		
	@Column (name = "equipamento")
	private String equipamento;
	
	@Column (name = "quantidade")
	private double quantidade;
	
	@Column (name = "valor_pagar")
	private double valorPagar;
	
	@Column (name = "referencia")
	private String referencia;
	
	@Column (name = "estado_equipamento")
	private String estadoEquipamento;
	
	@Column (name = "observacoes")
	private String observacoes;
	
	@Column (name = "descricao_problema")
	private String descricaoProblema;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "utilizador_id")
	private Utilizador utilizador;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "marca_id")
	private Marca marca;
	
	@Column (name = "modelo")
	private String modelo;*/
	

	public Solicitacao getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Solicitacao solicitacao) {
		this.solicitacao = solicitacao;
	}

	/*public TipoEquipamento getTipoEquipamento() {
		return tipoEquipamento;
	}

	public void setTipoEquipamento(TipoEquipamento tipoEquipamento) {
		this.tipoEquipamento = tipoEquipamento;
	}*/

	/*public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}*/

	public double getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(double quantidade) {
		this.quantidade = quantidade;
	}

	/*public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}*/

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getEstadoEquipamento() {
		return estadoEquipamento;
	}

	public void setEstadoEquipamento(String estadoEquipamento) {
		this.estadoEquipamento = estadoEquipamento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}
	
	

	public Utilizador getUtilizador() {
		return utilizador;
	}

	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	public double getValorPagar() {
		return valorPagar;
	}

	public void setValorPagar(double valorPagar) {
		this.valorPagar = valorPagar;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descricaoProblema == null) ? 0 : descricaoProblema.hashCode());
		result = prime * result + ((equipamento == null) ? 0 : equipamento.hashCode());
		result = prime * result + ((estadoEquipamento == null) ? 0 : estadoEquipamento.hashCode());
		result = prime * result + ((observacoes == null) ? 0 : observacoes.hashCode());
		long temp;
		temp = Double.doubleToLongBits(quantidade);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((referencia == null) ? 0 : referencia.hashCode());
		result = prime * result + ((solicitacao == null) ? 0 : solicitacao.hashCode());
		result = prime * result + ((utilizador == null) ? 0 : utilizador.hashCode());
		temp = Double.doubleToLongBits(valorPagar);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SolicitacaoEquipamento other = (SolicitacaoEquipamento) obj;
		if (descricaoProblema == null) {
			if (other.descricaoProblema != null)
				return false;
		} else if (!descricaoProblema.equals(other.descricaoProblema))
			return false;
		if (equipamento == null) {
			if (other.equipamento != null)
				return false;
		} else if (!equipamento.equals(other.equipamento))
			return false;
		if (estadoEquipamento == null) {
			if (other.estadoEquipamento != null)
				return false;
		} else if (!estadoEquipamento.equals(other.estadoEquipamento))
			return false;
		if (observacoes == null) {
			if (other.observacoes != null)
				return false;
		} else if (!observacoes.equals(other.observacoes))
			return false;
		if (Double.doubleToLongBits(quantidade) != Double.doubleToLongBits(other.quantidade))
			return false;
		if (referencia == null) {
			if (other.referencia != null)
				return false;
		} else if (!referencia.equals(other.referencia))
			return false;
		if (solicitacao == null) {
			if (other.solicitacao != null)
				return false;
		} else if (!solicitacao.equals(other.solicitacao))
			return false;
		if (utilizador == null) {
			if (other.utilizador != null)
				return false;
		} else if (!utilizador.equals(other.utilizador))
			return false;
		if (Double.doubleToLongBits(valorPagar) != Double.doubleToLongBits(other.valorPagar))
			return false;
		return true;
	}

	

	
	
}
