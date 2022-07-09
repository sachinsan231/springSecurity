package com.example.filter;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.StringUtils;

public class RequestValidationBeforeFilter implements Filter{
	
	private static String AUTHENTICATION_SCHEME_BASIC = "Basic";
	private Charset charset = StandardCharsets.UTF_8;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String header = httpServletRequest.getHeader(AUTHORIZATION);
		if(header != null) {
			header = header.trim();
			if(StringUtils.startsWithIgnoreCase(header, AUTHENTICATION_SCHEME_BASIC)) {
				byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
				byte[] decode = Base64.getDecoder().decode(base64Token);
				String token = new String(decode, charset);
				int delim = token.indexOf(":");
				if (delim == -1) {
					throw new BadCredentialsException("Invalid basic authentication token");
				}
				String email = token.substring(0, delim);
				if(email.toLowerCase().contains("test")) {
				httpServletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					return;
				}
			}
		}
		chain.doFilter(request, response);
	}

}
