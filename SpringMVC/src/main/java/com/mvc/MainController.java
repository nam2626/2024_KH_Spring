package com.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {

	@RequestMapping("/")
	public String loginView() {
		return "login";
	}
	
//	@PostMapping("/login.do")
//	public String login(HttpServletRequest request) {
//		String id = request.getParameter("id");
//		String pass = request.getParameter("pass");
//		
//		System.out.println(id + " " + pass);
//		
//		return "login_result";
//	}
	
//	@PostMapping("/login.do")
//	@RequestMapping("/login.do")
//	public String login(@RequestParam(name="id",defaultValue = "user") String id,
//			@RequestParam(name = "pass",defaultValue = "123456") String pass) {
//		
//		System.out.println(id + " " + pass);
//		
//		return "login_result";
//	}
	@RequestMapping("/login.do")
	public String login(String id, String pass, HttpSession session) {
		
		System.out.println(id + " " + pass);
		session.setAttribute("id", id);
		session.setAttribute("pass", pass);
		
		return "login_result";
	}
	
	@RequestMapping("/registerView.do")
	public String registerView() {
		return "member_register";
	}
	//member_register.jsp를 참고해서
	//호출경로를 지정해서, 회원가입 정보를 받아서 RegisterDTO 객체를 생성
	//생성한 객체를 request 영역에 저장하고, msg에 임의의 메세지를 저장해서
	//register_result.jsp로 이동
	@RequestMapping("/register.do")
	public ModelAndView register(RegisterDTO dto, ModelAndView view) {
		System.out.println(dto);
//		ModelAndView view = new ModelAndView();
		//request영역에 데이터를 저장
		view.addObject("dto",dto);
		view.addObject("msg","안녕하세요 - request영역");
		//이동할 페이지 설정
		view.setViewName("register_result");
		return view;
	}
}






