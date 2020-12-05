package uem.co.mz.helpdesk.report;

public class CondutorReportBean {
	
	private String designacao;
	private String designacao1;
	private String designacao2;
	private String designacao3;
	private String quantidade;
	private String quantidade1;
	private String quantidade2;
	private String quantidade3;
	
	public CondutorReportBean(String designacao, String designacao1, String designacao2, String designacao3, String quantidade,
			String quantidade1, String quantidade2, String quantidade3) { 				super();
		this.designacao = designacao; this.designacao1 = designacao1; this.designacao2 = designacao2; this.designacao3 = designacao3;
		this.quantidade = quantidade; this.quantidade1 = quantidade1; this.quantidade2 = quantidade2; this.quantidade3 = quantidade3;
	}
	
	public String getDesignacao() {
		return designacao;
	}
	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}
	public String getDesignacao1() {
		return designacao1;
	}
	public void setDesignacao1(String designacao1) {
		this.designacao1 = designacao1;
	}
	public String getDesignacao2() {
		return designacao2;
	}
	public void setDesignacao2(String designacao2) {
		this.designacao2 = designacao2;
	}
	public String getDesignacao3() {
		return designacao3;
	}
	public void setDesignacao3(String designacao3) {
		this.designacao3 = designacao3;
	}
	public String getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(String quantidade) {
		this.quantidade = quantidade;
	}
	public String getQuantidade1() {
		return quantidade1;
	}
	public void setQuantidade1(String quantidade1) {
		this.quantidade1 = quantidade1;
	}
	public String getQuantidade2() {
		return quantidade2;
	}
	public void setQuantidade2(String quantidade2) {
		this.quantidade2 = quantidade2;
	}
	public String getQuantidade3() {
		return quantidade3;
	}
	public void setQuantidade3(String quantidade3) {
		this.quantidade3 = quantidade3;
	}

}
