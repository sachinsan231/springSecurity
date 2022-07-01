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
public class BalanceController {

	@GetMapping("/myBalance")
	public double getBalance() {
		return 20000d;
	}
	
}
