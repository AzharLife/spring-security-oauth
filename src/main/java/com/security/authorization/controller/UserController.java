package com.security.authorization.controller;

import java.security.Principal;
import java.util.List;

import com.security.authorization.dto.UserDTO;
import com.security.authorization.dto.UserRolesDTO;
import com.security.authorization.exceptions.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.security.authorization.services.UserRolesService;
import com.security.authorization.services.UserService;

@RestController
@RequestMapping(value = "/authorization")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRolesService userRolesService;
	
	@RequestMapping(value = "/registration", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	public UserDTO registrationUser(@RequestBody UserDTO userDto)
			throws CustomValidationException {
		
		
		UserDTO savedUserDTO = userService.registerUser(userDto);
		return savedUserDTO;
	}
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	public ResponseEntity<UserDTO> authenticationCheck(Principal principal) throws CustomValidationException{
		
		String userName=null;
		if(principal!=null)
		{
			userName=principal.getName();
		}
		
		UserDTO userInfoDTO = userService.getUserInfo(userName);
		
		return new ResponseEntity<UserDTO>(userInfoDTO,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getUserRoles", method = RequestMethod.GET)
	public ResponseEntity<List<UserRolesDTO>> getRoles(Principal principal) throws CustomValidationException {
		
		List<UserRolesDTO> userRoles = userRolesService.getUserRoles(principal);
		
		return new ResponseEntity<List<UserRolesDTO>>(userRoles, HttpStatus.OK);
	
	}
}
