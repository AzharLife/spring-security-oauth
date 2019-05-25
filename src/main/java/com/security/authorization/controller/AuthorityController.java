package com.security.authorization.controller;

import java.util.List;

import com.security.authorization.dto.AuthorityDTO;
import com.security.authorization.exceptions.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.authorization.services.AuthorityService;

@RestController
@RequestMapping(value = "/authorization")
public class AuthorityController {
	
	@Autowired
	private AuthorityService authorityService;
	
	@RequestMapping(value="/authorities",method=RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<AuthorityDTO>> getallroles() throws CustomValidationException {
		List<AuthorityDTO> list = authorityService.getAllRoles();
		return new ResponseEntity<List<AuthorityDTO>>(list,HttpStatus.OK);
	}
}
