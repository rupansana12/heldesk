package uem.co.mz.helpdesk.model;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="admin_permissoes")
@Access(AccessType.FIELD)
public class Permissoes extends IdEntity implements GrantedAuthority{

	private static final long serialVersionUID = -4560054190826027253L;
	
	@Column(name = "perm_designacao", unique=true, updatable=false)
	private String designacao;
	
	@Column(name = "perm_descricao", unique=true, updatable=false)
	private String descricao;
	
	@Override
	public String toString() {
		return this.designacao;
	}

	@Override
	public int hashCode() {
		HashCodeBuilder hcb = new HashCodeBuilder();
		hcb.append(designacao);
		return hcb.toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Permissoes)) {
			return false;
		}
		Permissoes that = (Permissoes) obj;
		EqualsBuilder eb = new EqualsBuilder();
		eb.append(designacao, that.designacao);
		return eb.isEquals();
	}
	
	public String getDesignacao() {
		return designacao;
	}

	public void setDesignacao(String designacao) {
		this.designacao = designacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getAuthority() {
		// TODO Auto-generated method stub
		return getDesignacao();
	}
}
