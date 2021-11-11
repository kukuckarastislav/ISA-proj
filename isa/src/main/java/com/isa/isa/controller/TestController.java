package com.isa.isa.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
	
	//@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/helloTest")
	public String greeting() {
		return "odlicno radi lepo :) ";
	}
}
