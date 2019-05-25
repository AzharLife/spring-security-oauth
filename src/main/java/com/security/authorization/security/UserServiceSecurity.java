package com.security.authorization.security;

import java.util.ArrayList;
import java.util.Collection;

import com.security.authorization.model.Authority;
import com.security.authorization.model.User;
import com.security.authorization.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserServiceSecurity implements UserDetailsService {

	private final Logger log = LoggerFactory
			.getLogger(UserServiceSecurity.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(final String login) {
		log.debug("Authenticating {}", login);
		String lowercaseLogin = login.toLowerCase();
		User userFromDatabase;
		if (lowercaseLogin.contains("@")) {
			userFromDatabase = userRepository.findByEmail(lowercaseLogin);
		} else {
			userFromDatabase = userRepository
					.findByUsernameCaseInsensitive(lowercaseLogin);
		}

		if (userFromDatabase == null) {
			throw new UsernameNotFoundException("User " + lowercaseLogin
					+ " was not found in the database");
		} else if (!userFromDatabase.isActivated()) {
			throw new UserNotActivatedException("User " + lowercaseLogin
					+ " is not activated");
		}

		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority authority : userFromDatabase.getAuthorities()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(
					authority.getAuthorityName());
			grantedAuthorities.add(grantedAuthority);
		}

		/*return new org.springframework.security.core.userdetails.User(
				userFromDatabase.getUsername(), userFromDatabase.getPassword(),
				grantedAuthorities);*/
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(userFromDatabase.getUsername(),
				userFromDatabase.getPassword(), grantedAuthorities);
		return userDetails;

	}

}
