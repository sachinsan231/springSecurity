package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class implements security based on latest security 5.7 version. using {@link EnableWebSecurity} and {@link SecurityFilterChain}
 * 
 * @author kadam.sachin
 *
 */
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
		http.authorizeRequests()
			.antMatchers("/myBalance").authenticated()
			.antMatchers("/myCards").authenticated()
			.antMatchers("/myLoans").authenticated()
			.antMatchers("/notices").permitAll()
			.antMatchers("/contact").permitAll()
			.and().formLogin().and().httpBasic();
			
		return http.build();
	}
	
	
	@SuppressWarnings("deprecation")
	@Bean
	public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
		UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("12345").authorities("admin").build();
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("12345").authorities("read").build();
		return new InMemoryUserDetailsManager(admin, user);
	}

}
