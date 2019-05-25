package com.security.authorization.servicesimpl;

import java.util.HashSet;
import java.util.Set;

import com.security.authorization.exceptions.CustomValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.security.authorization.config.SimpleCorsFilter;
import com.security.authorization.dto.GeneralDTO;
import com.security.authorization.dto.StateCountryInfoDTO;
import com.security.authorization.dto.UserDTO;
import com.security.authorization.dto.UsersBuyingStationWarehouseDTO;
import com.security.authorization.model.Authority;
import com.security.authorization.model.User;
import com.security.authorization.repository.UserRepository;
import com.security.authorization.services.AuthorityService;
import com.security.authorization.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private AuthorityService authorityService;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${MasterService_url}")
	private String masterServiceURL;
	
	@Override
	public UserDTO registerUser(UserDTO userDTO) throws CustomValidationException {
		User user = new User();
		user.setActivated(true);
		user.setEmail(userDTO.getEmail());
		user.setUsername(userDTO.getUsername());
		user.setFirstName(userDTO.getFirstName());
		user.setLastName(userDTO.getLastName());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		Authority authority = authorityService.getAuthorityByName(userDTO
				.getUserRoleName());
		Set<Authority> authorities = new HashSet<Authority>();
		authorities.add(authority);
		user.setAuthorities(authorities);
		User savedUsers = userRepository.save(user);
		UserDTO savedUserDTO = new UserDTO();
		if(savedUsers != null) {
			savedUserDTO.setFirstName(savedUsers.getFirstName());
			savedUserDTO.setLastName(savedUsers.getLastName());
			savedUserDTO.setUsername(savedUsers.getUsername());
			savedUserDTO.setActivated(savedUsers.isActivated());
			savedUserDTO.setEmail(savedUsers.getEmail());
			savedUserDTO.setPassword(null);
			savedUserDTO.setUserRoleName(userDTO.getUserRoleName());
			
			UsersBuyingStationWarehouseDTO usersBuyingStationWarehouseDTO = new UsersBuyingStationWarehouseDTO();
			usersBuyingStationWarehouseDTO.setUsername(savedUsers.getUsername());
			usersBuyingStationWarehouseDTO.setBuyingStationId(userDTO.getBuyingStationId());
			usersBuyingStationWarehouseDTO.setWarehouseId(userDTO.getWarehouseId());
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", SimpleCorsFilter.token);
			HttpEntity<?> entity = new HttpEntity<>(usersBuyingStationWarehouseDTO,headers); 
			String restUrl = masterServiceURL  + "/usersMapping";
			UriComponentsBuilder builder1 = UriComponentsBuilder
						.fromHttpUrl(restUrl);

			HttpEntity<GeneralDTO> userMapResponse = restTemplate.exchange(builder1.build()
			.encode().toUri(), HttpMethod.POST, entity, GeneralDTO.class ); // what
			
			String message = null;
			if (userMapResponse != null) {
				message=userMapResponse.getBody().getMessage();
			}
			
			if(message == "Error") {
				
			}
			else if(message == "Success") {
				savedUserDTO.setBuyingStationId(userDTO.getBuyingStationId());
				savedUserDTO.setWarehouseId(userDTO.getWarehouseId());
			}
			
		}
		return userDTO;
	}

	
	public UserDTO getUserInfo(String username) throws CustomValidationException{
		
		UserDTO userDTO = new UserDTO();
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", SimpleCorsFilter.token);

		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		StateCountryInfoDTO stateCountryDetailsDTO = null;
		
		
		String restUrl = masterServiceURL  + "/stateCountryDetails";

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(restUrl)
				.queryParam("username",username);

		HttpEntity<StateCountryInfoDTO> stateCountryResponse = restTemplate.exchange(builder.build().encode().toUri(),
						HttpMethod.GET, entity, StateCountryInfoDTO.class);

		if (stateCountryResponse.getBody() != null) {
			stateCountryDetailsDTO = stateCountryResponse.getBody();
		}
	


		if (stateCountryDetailsDTO != null) {
			userDTO.setStateId(stateCountryDetailsDTO.getStateId());
			userDTO.setStateName(stateCountryDetailsDTO.getStateName());
			userDTO.setCountryName(stateCountryDetailsDTO.getCountryName());
			userDTO.setCountryCallingCode(stateCountryDetailsDTO.getCountryCallingCode());
			userDTO.setCountryIsoCode(stateCountryDetailsDTO.getCountryIsoCode());
		}

		return userDTO;
	}
	
	
	
	
	
	
	private boolean isEmptyString(Long buyingStationId) {
		// TODO Auto-generated method stub
		return false;
	}
}
