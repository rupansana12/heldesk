package uem.co.mz.helpdesk.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence. * ;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.google.common.base.Objects;

@Entity
@Table(name="admin_utilizador")
@Access(AccessType.FIELD)
public class Utilizador extends IdEntity implements UserDetails{

private static final long serialVersionUID = -9077942665643035482L;
	
	
	public Utilizador(String username, String password, boolean enabled, Set<Perfil> roles) {
	super();
	this.username = username;
	this.password = password;
	this.enabled = enabled;
	this.roles = roles;
}

	/*@NotNull(message = "Utilizador nÃ£o pode ser vazio")
	@NotEmpty(message = "Utilizador nÃ£o pode conter apenas espaÃ§os em branco")
	@Size(max = 50, message = "Utilizador nÃ£o deve ter mais de 50 caracteres")*/
	@Column(name = "username", length = 50, unique = true)
	private String username;

	/*@NotNull(message = "Senha nÃ£o pode ser vazia")
	@NotEmpty(message = "Senha nÃ£o pode conter apenas espaÃ§os em branco")*/
	@Column(columnDefinition = "LONGTEXT")
	private String password;

	@Column(name = "enabled")
	private boolean enabled;
	
	@Transient
	private String planPass = "";

	@ManyToMany(fetch = FetchType.EAGER)
	//@Fetch(FetchMode.JOIN)
	@JoinTable(name = "admin_utilizador_perfil", joinColumns = @JoinColumn(name = "utilizador_id", nullable = false, updatable = false), inverseJoinColumns = @JoinColumn(name = "perfil_id", nullable = false, updatable = false))
	private Set<Perfil> roles = new HashSet<Perfil>();

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "admin_utilizador_empresa", joinColumns = @JoinColumn(name = "utilizador_id", nullable = false, updatable = true), inverseJoinColumns = @JoinColumn(name = "empresa_id", nullable = false, updatable = true))
	private Set<UnidadeOrganica> empresas = new HashSet<UnidadeOrganica>();
	
	@Override
	public String toString() {
		return String.format("%s(id=%d, username=%s, password=%s, enabled=%b)",
				this.getClass().getSimpleName(), this.getId(),
				this.getUsername(), this.getPassword(), this.getEnabled());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;

		if (o instanceof Utilizador) {
			final Utilizador other = (Utilizador) o;
			return Objects.equal(getId(), other.getId())
					&& Objects.equal(getUsername(), other.getUsername())
					&& Objects.equal(getPassword(), other.getPassword())
					&& Objects.equal(getEnabled(), other.getEnabled());
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getId(), getUsername(), getPassword(),
				getEnabled());
	}

	@Transient
	public Set<Permissoes> getPermissions() {
		Set<Permissoes> perms = new HashSet<Permissoes>();
		for (Perfil role : roles) {
			perms.addAll(role.getPermissoes());
		}
		return perms;
	}
	
	public Utilizador() {
		this.enabled = true;
	}

	@Transient
	public Collection<GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		authorities.addAll((Collection<? extends GrantedAuthority>) getRoles());
		authorities.addAll((Collection<? extends GrantedAuthority>) getPermissions());
		return authorities;
	}
	
	public boolean isAccountNonExpired() {
		// return true = account is valid / not expired
		return true;
	}

	public boolean isAccountNonLocked() {
		// return true = account is not locked
		return true;
	}

	public boolean isCredentialsNonExpired() {
		// return true = password is valid / not expired
		return true;
	}

	public boolean isEnabled() {
		return this.getEnabled();
	}
	
	public void SetPasswordEncripted(String senha) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		this.password = passwordEncoder.encode(senha);
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getPlanPass() {
		return planPass;
	}

	public void setPlanPass(String planPass) {
		this.planPass = planPass;
	}

	public Set<Perfil> getRoles() {
		return roles;
	}

	public void setRoles(Set<Perfil> roles) {
		this.roles = roles;
	}

	public Set<UnidadeOrganica> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(Set<UnidadeOrganica> empresas) {
		this.empresas = empresas;
	}
	
	/*@Transient
	public Set<Permissoes> getPermissoes() {
		Set<Permissoes> perms = new HashSet<Permissoes>();
		for (Perfil role : roles) {
			perms.addAll(role.getPermissoes());
		}
		return perms;
	}*/
}
