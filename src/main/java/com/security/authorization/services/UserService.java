package com.security.authorization.services;

import com.security.authorization.dto.UserDTO;
import com.security.authorization.exceptions.CustomValidationException;

public interface UserService {
	public UserDTO registerUser(UserDTO userDto) throws CustomValidationException;
	
	public UserDTO getUserInfo(String username) throws CustomValidationException;
}
