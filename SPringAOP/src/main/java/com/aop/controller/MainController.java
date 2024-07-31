package com.aop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aop.dto.BoardMemberDTO;
import com.aop.dto.GradeDTO;
import com.aop.service.MemberService;

@RestController
@CrossOrigin(origins = "*" , allowedHeaders = "*" )
public class MainController {
	private MemberService service;

	public MainController(MemberService service) {
		this.service = service;
	}
	
	@PostMapping("/member/login")
	public BoardMemberDTO login(String id, String pwd) {
		return service.login(id, pwd);
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
	public Map<String, Object> insertMember(
			@RequestBody Map<String, String> param){
		System.out.println(param);
		BoardMemberDTO dto = new BoardMemberDTO();
		dto.setBoardMemberId(param.get("boardMemberId"));
		dto.setBoardMemberName(param.get("boardMemberName"));
		dto.setBoardMemberNick(param.get("boardMemberNick"));
		dto.setBoardMemberPasswd(param.get("boardMemberPasswd"));
		dto.setBoardMemberGrade(Integer.parseInt(param.get("boardMemberGrade")));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println(dto);
			service.insertMember(dto);
			map.put("msg", "회원 등록 성공");
			map.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "회원 등록 실패");
			map.put("result", false);
		}
		return map;
	}
	
	@GetMapping("/member/{id}")
	public BoardMemberDTO selectMember(@PathVariable("id") String id) {
		return service.selectMember(id);
	}
	
	@DeleteMapping("/member/delete")
	public Map<String, Object> deleteMember(String id){
		System.out.println("삭제할 아이디 : " + id);
		service.deleteMember(id);
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("msg", "회원정보 삭제 완료");
		return result;
	}
	
	@PutMapping("/member/update")
	public Map<String, Object> updateMember(@RequestBody Map<String, String> param) {
		System.out.println(param);
		BoardMemberDTO dto = new BoardMemberDTO();
		dto.setBoardMemberId(param.get("boardMemberId"));
		dto.setBoardMemberName(param.get("boardMemberName"));
		dto.setBoardMemberNick(param.get("boardMemberNick"));
		dto.setBoardMemberPasswd(param.get("boardMemberPasswd"));
		dto.setBoardMemberGrade(Integer.parseInt(param.get("boardMemberGrade")));
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			System.out.println(dto);
			service.updateMember(dto);
			map.put("msg", "회원 수정 성공");
			map.put("result", true);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "회원 수정 실패");
			map.put("result", false);
		}
		return map;
	}
	
	
}






