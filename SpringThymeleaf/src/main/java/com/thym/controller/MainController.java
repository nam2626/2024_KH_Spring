package com.thym.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.apache.tomcat.util.digester.DocumentProperties.Charset;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.thym.dto.BoardMemberDTO;
import com.thym.service.MemberService;

import jakarta.servlet.http.HttpServletResponse;
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
	
	@GetMapping("/member/delete/{id}")
	public String memberDelete(@PathVariable String id, 
			HttpServletResponse response) throws IOException {
//		System.out.println(id);
		//데이터 삭제 작업
		response.setContentType("text/html;charset=utf-8");
		int result = service.deleteMember(id);
		//성공/실패 경고창 띄운 후, /main 으로 이동하게끔 처리
		if(result != 0)
			response.getWriter().println("<script>alert('회원정보 삭제 성공');</script>");
		else
			response.getWriter().println("<script>alert('회원정보 삭제 실패');</script>");

		response.getWriter().println("<script>location.href='/main';</script>");
		return null;
	}
}









