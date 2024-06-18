package com.thym.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
		//전체 회원 정보를 조회해서 request영역에 저장
		List<BoardMemberDTO> list = service.selectAllMember();
		view.addObject("list",list);		
		view.setViewName("main");
		return view;
	}
	
	@GetMapping("/member/{id}")
	public ModelAndView memberView(@PathVariable String id,
			ModelAndView view) {
		//회원아이디값 확인
		System.out.println(id);
		//회원정보 조회해서 request 영역에 저장
		BoardMemberDTO dto = service.selectMember(id);
		
		view.addObject("dto",dto);		
		view.setViewName("member_view");
		return view;
	}
	
	@PostMapping("/member/update")
	public String memberUpdate(BoardMemberDTO dto) {
//		System.out.println(dto);
		int result = service.updateMember(dto);
		System.out.println(result);
		
		return "redirect:/main";
	}
}









