package com.thym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.thym.service.MemberService;

@Controller
public class MainController {
	
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}
}


