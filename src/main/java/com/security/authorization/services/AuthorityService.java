package com.security.authorization.services;

import java.util.List;

import com.security.authorization.dto.AuthorityDTO;
import com.security.authorization.exceptions.CustomValidationException;
import com.security.authorization.model.Authority;

public interface AuthorityService {
	public Authority getAuthorityByName(String authorityName)
			throws CustomValidationException;
	public List<AuthorityDTO> getAllRoles() throws CustomValidationException ;
}
