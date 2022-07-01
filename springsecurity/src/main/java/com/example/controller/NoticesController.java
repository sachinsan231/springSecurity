package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @author kadam.sachin
 *
 */
@RestController
public class NoticesController {

	@GetMapping("/notices")
	public String getNoticeDetails() {
		return "notices details";
	}
}
