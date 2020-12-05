package uem.co.mz.helpdesk.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name="admin_perfil")
public class Perfil extends IdEntity implements Serializable, GrantedAuthority{

	private static final long serialVersionUID = -4954680229830230243L;

	@Column(name = "perf_designacao", length = 50, unique=true,updatable=true)
	private String designacao;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "admin_permissoes_perfil", joinColumns = @JoinColumn(name = "Perfil_id"), inverseJoinColumns = @JoinColumn(name = "Permissoes_id"))
	private Set<Permissoes> permissoes;

	private String type;

	public Perfil() {
		this.type = "Normal";
	}
	
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
		if (!(obj instanceof Perfil)) {
			return false;
		}
		Perfil that = (Perfil) obj;
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

	public Set<Permissoes> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(Set<Permissoes> permissoes) {
		this.permissoes = permissoes;
	}
	
	public String getAuthority() {
		// TODO Auto-generated method stub
		return getDesignacao();
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	/*@Override
	public String toString() {
		
		return designacao.toString();
	}*/
}
