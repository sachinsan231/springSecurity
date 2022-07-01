package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author kadam.sachin
 *
 */

@RestController
public class CardsController {

	@GetMapping("/myCards")
	public String getCardDetails() {
		return "Cards details";
	}
}
