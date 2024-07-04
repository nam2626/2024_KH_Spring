package com.member.controller;

import java.sql.SQLException;
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
			service.insertMember(dto);
			map.put("msg", "회원 등록 성공");
			map.put("result", true);
		} catch (Exception e) {
			map.put("msg", "회원 등록 실패");
			map.put("result", false);
		}
		return map;
	}
}






