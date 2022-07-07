/**
 * 
 */
package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Accounts;
import com.example.model.Customer;
import com.example.repository.AccountsRepository;

/**
 * @author kadam.sachin
 *
 */
@RestController
public class AccountController {
	
	@Autowired
	private AccountsRepository accountsRepository;

	@GetMapping("/myAccount/{accountId}")
	public Accounts getAccountDetails(@PathVariable(name = "accountId") int accountId) {
		Accounts accounts = accountsRepository.findByCustomerId(accountId);
		if (accounts != null ) {
			return accounts;
		}else {
			return null;
		}
	}
	
	@PostMapping("/myAccount")
	public Accounts getAccountDetails(@RequestBody Customer customer) {
		Accounts accounts = accountsRepository.findByCustomerId(customer.getId());
		if (accounts != null ) {
			return accounts;
		}else {
			return null;
		}
	}
}
