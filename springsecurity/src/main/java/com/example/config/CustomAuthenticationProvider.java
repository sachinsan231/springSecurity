package com.example.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.model.Authority;
import com.example.model.Customer;
import com.example.repository.CustomerRepository;

/**
 * 
 * @author kadam.sachin
 *
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider{

	@Autowired
	CustomerRepository  customerRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		List<Customer> users = customerRepository.findByEmail(userName);
		if(users.size() > 0) {
			if(passwordEncoder.matches(password, users.get(0).getPwd())) {
				List<GrantedAuthority> authorities = new ArrayList<>();
				authorities.add(new SimpleGrantedAuthority(users.get(0).getRole()));
				return new UsernamePasswordAuthenticationToken(userName,  password, getGrantedAuthorities(users.get(0).getAuthorities()));
			}else {
				throw new BadCredentialsException("Invalid password");
			}
			}else {
				throw new BadCredentialsException("Bad credentials");
			}
	}

	
	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<Authority> authorities) {
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		for (Authority auth : authorities) {
			grantedAuthorities.add(new SimpleGrantedAuthority(auth.getName()));
		}
		return grantedAuthorities;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
