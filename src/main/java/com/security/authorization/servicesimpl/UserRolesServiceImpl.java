package com.security.authorization.servicesimpl;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import com.security.authorization.exceptions.CustomValidationException;
import com.security.authorization.repository.UserRolesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.security.authorization.dto.UserRolesDTO;
import com.security.authorization.services.UserRolesService;

@Service
public class UserRolesServiceImpl implements  UserRolesService{

	@Autowired
	private UserRolesRepository userRolesRepository;

	private static final Logger LOG = LoggerFactory.getLogger(UserRolesServiceImpl.class);
	
	@Override
	public List<UserRolesDTO> getUserRoles(Principal principal)
			throws CustomValidationException {
		
		String userName= principal.getName();
		List<Object[]> userRoles =null;
		List<UserRolesDTO> userRolesDtoList = new ArrayList<UserRolesDTO>();
		if(userName !=null)
		{
//		 userRoles = userRolesRepository.findByUserNameAndDeletedFlagFalse(userName);
		 userRoles = userRolesRepository.getByUserNameAndDeletedFlagFalse(userName);
		 
		 if(! userRoles.isEmpty())
		 {
			 for(Object[] obj : userRoles)
			 {
				 LOG.debug("Role name is "+obj[0]);
				 UserRolesDTO userRoleDto = new UserRolesDTO();
				 if(obj[0]!=null)
				 {
				 userRoleDto.setRoleName(obj[0].toString());
				 }
				 userRoleDto.setPriorityOrder((Long)obj[1]);
				 userRolesDtoList.add(userRoleDto);
			 }
		 }
		 
		}
		return userRolesDtoList;
	}
	
	
}
