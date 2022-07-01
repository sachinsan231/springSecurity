package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author kadam.sachin
 *
 */

@RestController
public class LoanController {

	@GetMapping("/myLoans")
	public String getLoanDetails() {
		return "loan details";
	}
}
