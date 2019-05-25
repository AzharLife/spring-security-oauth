package com.security.authorization.config;

import com.security.authorization.security.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.security.authorization.security.CustomLogoutSuccessHandler;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

	@Autowired
	private CustomLogoutSuccessHandler customLogoutSuccessHandler;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.exceptionHandling()
				.authenticationEntryPoint(customAuthenticationEntryPoint)
				.and()
				.logout()
				.logoutUrl("/oauth/logout")
				.logoutSuccessHandler(customLogoutSuccessHandler)
				.and()
				.csrf()
				.requireCsrfProtectionMatcher(
						new AntPathRequestMatcher("/oauth/authorize"))
				.disable().headers().frameOptions().disable()
				.and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests().antMatchers("/authorization/**").permitAll()
				.antMatchers("/hello").hasAnyRole("ADMIN");
				/*.hasRole(Authority.ADMIN.name()).antMatchers("/users")
				.hasRole(Authority.ADMIN.name());*/
	}
}
