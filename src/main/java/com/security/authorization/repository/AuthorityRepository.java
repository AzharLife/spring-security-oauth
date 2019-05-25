package com.security.authorization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.security.authorization.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
	public Authority findByAuthorityName(String authorityName);
	
	@Query("select authority.authorityName from roles authority")
	public String[] findByDeletedflagFalse();
}
