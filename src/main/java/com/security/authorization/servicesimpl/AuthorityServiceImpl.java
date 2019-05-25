package com.security.authorization.servicesimpl;

import java.util.ArrayList;
import java.util.List;

import com.security.authorization.exceptions.CustomValidationException;
import com.security.authorization.exceptions.ErrorCode;
import com.security.authorization.repository.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorization.dto.AuthorityDTO;
import com.security.authorization.model.Authority;
import com.security.authorization.services.AuthorityService;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	@Autowired
	private AuthorityRepository authorityRepository;

	@Override
	public Authority getAuthorityByName(String authorityName)
			throws CustomValidationException {
		Authority authority = authorityRepository
				.findByAuthorityName(authorityName);
		if (authority == null) {
			throw new CustomValidationException(ErrorCode.SCF_VD_019);
		}
		return authority;
	}
	
	@Override
	public List<AuthorityDTO> getAllRoles() throws CustomValidationException {
		String[] roleslist= authorityRepository.findByDeletedflagFalse();
		List<AuthorityDTO> authorityDTOs = new ArrayList<AuthorityDTO>();
		for(String arr  : roleslist){
			AuthorityDTO authorityDTO = new AuthorityDTO();
			authorityDTO.setRoleName(arr);
			authorityDTOs.add(authorityDTO);
		}
		return authorityDTOs;
	}

}
