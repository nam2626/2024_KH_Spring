package com.member.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.member.dto.BoardMemberDTO;
import com.member.dto.GradeDTO;
import com.member.service.MemberService;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" )
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}
	
	@GetMapping("/member/list")
	public List<BoardMemberDTO> selectAllMember(){
		return service.selectAllMember();
	}
	
	@GetMapping("/grade/list")
	public List<GradeDTO> selectAllGrade(){
		return service.selectAllGrade();
	}
	
	@PostMapping("/member/insert")
	public Map<String, Object> insertMember(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "테스트 메세지");
		return map;
	}
}






