package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.member.dto.BoardMemberDTO;
import com.member.service.MemberService;

@SpringBootTest
class SpringApiMemberApplicationTests {
	
	@Autowired
	private MemberService service;
	
	@Test
	public void insertTest() {
		BoardMemberDTO dto = new BoardMemberDTO();
		dto.setBoardMemberId("TESTID");
		dto.setBoardMemberName("테스트");
		dto.setBoardMemberNick("테스트닉네임");
		dto.setBoardMemberPasswd("123456");
		dto.setBoardMemberGrade(1);
		
		int result = 0;
		try {
			result = service.insertMember(dto);
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals(1, result,"데이터 추가 테스트 실패");
		
	}

	
	@Test
	public void deleteTest() {
		assertEquals(1, service.deleteMember("TESTID"),"데이터 삭제 테스트 실패");
	}
}










