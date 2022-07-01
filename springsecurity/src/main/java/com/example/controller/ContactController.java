package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author kadam.sachin
 *
 */

@RestController
public class ContactController {
	
	@GetMapping("/contact")
	public String getContactDetails() {
		return "contact details";
	}
}
