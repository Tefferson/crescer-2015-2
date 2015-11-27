package br.com.cwi.crescer.lavanderia.security.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Embeddable
public class AuthoritiesId implements Serializable {

	private static final long serialVersionUID = -787120684702755884L;

	@Column(name="authority")
	private String authority;
	@OneToOne
	@JoinColumn(name = "username")
	private Users user;

	public AuthoritiesId() {
	}

	public AuthoritiesId(Users user, String authority) {
		this.user = user;
		this.authority = authority;
	}

	public String getAuthority() {
		return authority;
	}

	public Users getUser() {
		return user;
	}

	@Override
	public int hashCode() {
		return (user + authority).hashCode();
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof AuthoritiesId) {
			AuthoritiesId authoritiesId = (AuthoritiesId) obj;
			return authoritiesId.getAuthority().equals(authority) && authoritiesId.getUser().equals(user);
		}

		return false;
	}

}