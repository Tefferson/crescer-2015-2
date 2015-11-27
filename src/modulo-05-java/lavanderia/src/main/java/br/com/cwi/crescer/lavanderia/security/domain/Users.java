package br.com.cwi.crescer.lavanderia.security.domain;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users {

	@Id
	@Column(name = "Username", length = 100)
	private String username;

	@Column(name = "Password", length = 32)
	@Basic(optional = false)
	private String password;

	@Column(name = "Enabled")
	@Basic(optional = false)
	private Boolean enabled;

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

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	@Override
	public int hashCode() {
		return (username + password + enabled).hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Users) {
			Users users = (Users) obj;
			return users.getEnabled() == enabled && users.getUsername().equals(username)
					&& users.getPassword().equals(password);
		}

		return false;
	}

}
