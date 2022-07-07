package com.example.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.model.Customer;
import com.example.model.SecurityCustomer;
import com.example.repository.CustomerRepository;

@Service
public class CustomUserDetails implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<Customer> customer = customerRepository.findByEmail(username);
		if(customer == null || customer.size() == 0 ) {
			throw new UsernameNotFoundException("User Details not found for customer : "+username);
		}
		return new SecurityCustomer(customer.get(0));
	}

}
