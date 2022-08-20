package com.company.auth.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin(origins = "*")
public class TestController {

	@GetMapping("/")
	public String test() {
		return "<h1>Server is Running</h1>";
	}

}
