package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Cards;
import com.example.model.Customer;
import com.example.repository.CardsRepository;

/**
 * 
 * @author kadam.sachin
 *
 */

@RestController
public class CardsController {

	@Autowired
	CardsRepository cardsRepository;
	
	@GetMapping("/myCards/{customerId}")
	public List<Cards> getCardDetails(@PathVariable(name ="customerId") int customerId) {
		List<Cards> cards = cardsRepository.findByCustomerId(customerId);
		if(cards.size() > 0) {
			return cards;
		}
		return null;
	}
	
	@PostMapping("/myCards")
	public List<Cards> getCardDetails(@RequestBody Customer customer) {
		List<Cards> cards = cardsRepository.findByCustomerId(customer.getId());
		if (cards != null ) {
			return cards;
		}else {
			return null;
		}
	}
}
