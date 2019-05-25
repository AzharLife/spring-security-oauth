package com.security.authorization.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;

@Entity(name = "roles")
public class Authority {

	@Id
	@Size(min = 0, max = 50)
	@Column(name = "role_name")
	private String authorityName;

	public String getAuthorityName() {
		return authorityName;
	}

	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Authority authority = (Authority) o;

		if (!authorityName.equals(authority.authorityName))
			return false;

		return true;
	}

	@Override
	public int hashCode() {
		return authorityName.hashCode();
	}

	@Override
	public String toString() {
		return "Authority{" + "name='" + authorityName + '\'' + '}';
	}

}
