/*package com.security.scf.authorization.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.security.scf.farmer.config.SimpleCorsFilter;
import com.security.scf.farmer.dto.PostalCodeDTO;
import com.security.scf.farmer.exception.CustomValidationException;

@Component
public class MasterServiceCalls {
	
	
	@Value("${masterService_url}")
	private String masterServiceUrl;
	
	@Autowired
	private RestTemplate restTemplate;

	HttpHeaders headers = new HttpHeaders();
	
	public StateCountryInfoDTO getPostalCode(Long postalId) throws CustomValidationException
	{
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.set("Authorization", SimpleCorsFilter.token);
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		UriComponentsBuilder builder;
		
		String restUrl = masterServiceUrl + "/postalDetails";

		builder = UriComponentsBuilder.fromHttpUrl(restUrl).queryParam(
				"postalId", postalId);

		HttpEntity<PostalCodeDTO> postalResponse = restTemplate
				.exchange(builder.build().encode().toUri(),
						HttpMethod.GET, entity, PostalCodeDTO.class);
	
		
		PostalCodeDTO postalCodeDTO= null;
		if (postalResponse.getBody() != null) {
			postalCodeDTO = postalResponse.getBody();
		}
		
		return postalCodeDTO;
	}

}
*/