package uem.co.mz.helpdesk.report;

import uem.co.mz.helpdesk.model.IdEntity;

public class CondutorExportBean extends IdEntity{
	
	private String codigo;
	private String nome;
	private String apelido;
	private String dataCriacao;
	private String empresa;
	private String provincia;
	private String tipoCarta;
	private String categoriaMembro;
	private String situacao;
	
	public CondutorExportBean(){
		
	}
	
	public CondutorExportBean(String codigo, String nome, String apelido, String dataCriacao, String empresa,
			String provincia, String tipoCarta, String categoriaMembro, String situacao) { 				super();
		this.codigo = codigo; this.nome = nome; this.apelido = apelido; this.dataCriacao = dataCriacao; this.empresa = empresa;
		this.provincia = provincia; this.tipoCarta = tipoCarta; this.categoriaMembro = categoriaMembro; this.situacao = situacao;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getEmpresa() {
		return empresa;
	}

	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getTipoCarta() {
		return tipoCarta;
	}

	public void setTipoCarta(String tipoCarta) {
		this.tipoCarta = tipoCarta;
	}

	public String getSituacao() {
		return situacao ;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getCategoriaMembro() {
		return categoriaMembro;
	}

	public void setCategoriaMembro(String categoriaMembro) {
		this.categoriaMembro = categoriaMembro;
	}
	
}
