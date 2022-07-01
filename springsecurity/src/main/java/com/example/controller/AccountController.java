/**
 * 
 */
package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kadam.sachin
 *
 */
@RestController
public class AccountController {

	@GetMapping("/myAccount")
	public String getAccountDetails() {
		return "Account details";
	}
}
