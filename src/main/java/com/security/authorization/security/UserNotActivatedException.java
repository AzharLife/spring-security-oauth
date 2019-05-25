package com.security.authorization.security;

import org.springframework.security.core.AuthenticationException;

public class UserNotActivatedException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -730900989727532553L;

	public UserNotActivatedException(String msg, Throwable t) {
		super(msg, t);
	}

	public UserNotActivatedException(String msg) {
		super(msg);
	}
}
