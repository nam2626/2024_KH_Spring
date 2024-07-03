package com.member.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.dto.BoardMemberDTO;
import com.member.service.MemberService;

@RestController
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}
	
	@GetMapping("/member/list")
	public List<BoardMemberDTO> selectAllMember(){
		return service.selectAllMember();
	}
	
}






