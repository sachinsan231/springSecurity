/**
 * 
 */
package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * This class implements security based on deprecated {@link WebSecurityConfigurerAdapter}
 * @author kadam.sachin
 *
 */
@SuppressWarnings("deprecation")
//@Configuration
public class SecurityConfigBackup extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*
		 * http.authorizeHttpRequests() .antMatchers("/myBalance").authenticated()
		 * .antMatchers("/myCards").authenticated()
		 * .antMatchers("/myLoans").authenticated() .antMatchers("/contact").permitAll()
		 * .antMatchers("/notices").permitAll() .and() .formLogin().and() .httpBasic();
		 */
		
		http.authorizeHttpRequests().anyRequest()
			.denyAll()
			.and()
			.formLogin()
			.and()
			.httpBasic();
	}

}
