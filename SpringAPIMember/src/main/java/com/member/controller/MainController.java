package com.member.controller;

import org.springframework.web.bind.annotation.RestController;

import com.member.service.MemberService;

@RestController
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}
	
	
}
