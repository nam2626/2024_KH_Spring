package com.thym.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import com.thym.dto.BoardMemberDTO;
import com.thym.dto.GradeDTO;
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
	
	@GetMapping("/member/register/view")
	public String registerView() {
		return "member_register";
	}
	
	@PostMapping("/member/insert")
	public String registerMember(BoardMemberDTO dto, HttpSession session,
			HttpServletResponse response) throws IOException {
		System.out.println(dto);
		//모든 데이터가 정상인지 체크
		response.setContentType("text/html;charset=utf-8");
		if(dto.getBoardMemberId().isEmpty() || dto.getBoardMemberName().isEmpty() || dto.getBoardMemberNick().isEmpty() || dto.getBoardMemberPasswd().isEmpty()) {
			response.getWriter().println("<script>"
					+ "alert('잘못된 데이터가 있습니다');"
					+ "history.back();"
					+ "</script>");
		}else{
			//DB에 등록 작업 시작
			try {
				service.insertMember(dto);
				dto.setBoardMemberPasswd(null);
				session.setAttribute("user", dto);
				response.getWriter().println("<script>"
						+ "alert('회원가입 성공, 메인페이지로 이동합니다.');"
						+ "location.href='/main';"
						+ "</script>");
			}catch (Exception e) {
				//회원 가입 실패시 경고창 띄우고 이전페이지로 이동
				response.getWriter().println("<script>"
						+ "alert('회원 가입에 실패하였습니다.\\n데이터를 확인하세요.');"
						+ "history.back();"
						+ "</script>");
			}
		}
		return null;
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/grade")
	public ModelAndView gradeMain(ModelAndView view) {
		//전체 회원 등급을 조회
		List<GradeDTO> list = service.selectAllGrade();
		//request영역에 전체 회원 등급 리스트를 저장
		view.addObject("list", list);
		view.setViewName("grade_main");
		return view;
	}
	
	@PutMapping("/grade/update")
	public ResponseEntity<String> updateGrade(GradeDTO dto){
		Map<String, Object> map = new HashMap<String, Object>();
		
		System.out.println(dto);
		//수정 작업
		int result = service.updateGrade(dto);
		
		//수정 결과 메세지
		if(result == 0)
			map.put("result", "등급정보 수정 실패");
		else
			map.put("result", "등급정보 수정 성공");
			
		return new ResponseEntity(map, HttpStatus.OK);
	}
	
	@PostMapping("/grade/add")
	public ResponseEntity<String> insertGrade(GradeDTO dto) {
		System.out.println(dto);
		
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.insertGrade(dto);
			map.put("msg", "회원등급 등록 성공");
		}catch(Exception e) {
			map.put("msg", "회원등급 등록 실패");
		}
		
		return new ResponseEntity(null,HttpStatus.OK);
	}
	
}









