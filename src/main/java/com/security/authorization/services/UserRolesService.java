package com.security.authorization.services;

import java.security.Principal;
import java.util.List;

import com.security.authorization.dto.UserRolesDTO;
import com.security.authorization.exceptions.CustomValidationException;

public interface UserRolesService {

	public List<UserRolesDTO> getUserRoles(Principal principal) throws CustomValidationException;
}
