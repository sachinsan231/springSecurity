package com.example.config;

import java.util.Arrays;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import com.example.constant.SecurityConstants;
import com.example.filter.AuthoritiesLoggingAfterFilter;
import com.example.filter.JWTSessionGeneratorFilter;
import com.example.filter.JWTSessionValidatorFilter;
import com.example.filter.RequestValidationBeforeFilter;

/**
 * This class implements security based on latest security 5.7 version. using {@link EnableWebSecurity} and {@link SecurityFilterChain}
 * 
 * @author kadam.sachin
 *
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, securedEnabled = true)
public class SecurityConfig {
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		//http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // added to remove JSESSION from request
        .cors().configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList(SecurityConstants.JWT_HEADER));
                        config.setMaxAge(3600L);
                        return config;
                    }
                })
        		//.and().csrf().ignoringAntMatchers("/contact").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and() /** disable CSRF as we can use JWT to authenticate and authorize request **/
        		.and().csrf().disable()
        		.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTSessionValidatorFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTSessionGeneratorFilter(), BasicAuthenticationFilter.class)
        		.authorizeHttpRequests((auth) -> auth
						/*
						 * .antMatchers("/myAccount").hasAuthority("WRITE")
						 * .antMatchers("/myBalance").hasAuthority("READ")
						 * .antMatchers("/myLoans").hasAuthority("READ")
						 */
        				.antMatchers("/myAccount").hasRole("USER")
        				.antMatchers("/myBalance").hasRole("Admin")
        				.antMatchers("/myLoans").hasAnyRole("USER","ADMIN")
                        .antMatchers("/myCards").authenticated()
                        .antMatchers("/notices", "/contact").permitAll()
                ).httpBasic(Customizer.withDefaults());
        return http.build();
	}
	
	
	/*
	 * @SuppressWarnings("deprecation")
	 * 
	 * @Bean public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
	 * UserDetails admin =
	 * User.withDefaultPasswordEncoder().username("admin").password("12345").
	 * authorities("admin").build(); UserDetails user =
	 * User.withDefaultPasswordEncoder().username("user").password("12345").
	 * authorities("read").build(); return new InMemoryUserDetailsManager(admin,
	 * user); }
	 */

	/*
	 * @Bean public UserDetailsService userDetailsService(DataSource dataSource) {
	 * return new JdbcUserDetailsManager(dataSource); }
	 */
	
	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
