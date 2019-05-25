package com.security.authorization.exceptions;

import org.springframework.stereotype.Component;

@Component
public class Response {

	private Integer statusCode;
	
	private String description;
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}



	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Response(){
		
	}
}
