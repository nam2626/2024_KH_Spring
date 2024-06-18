package com.thym.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thym.dto.BoardMemberDTO;
import com.thym.service.MemberService;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;

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
	
	@PostMapping("/login")
	public String login(String id, String passwd, HttpSession session) {
		BoardMemberDTO dto = service.login(id,passwd);
		session.setAttribute("user", dto);
		return "redirect:/main";
	}
	
	@GetMapping("/main")
	public ModelAndView main(ModelAndView view) {
		view.setViewName("main");
		return view;
	}
}









