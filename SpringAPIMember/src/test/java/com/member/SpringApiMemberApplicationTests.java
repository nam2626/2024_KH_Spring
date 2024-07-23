package com.member;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.member.dto.BoardMemberDTO;
import com.member.service.MemberService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
class SpringApiMemberApplicationTests {
	
	@Autowired
	private MemberService service;
	
	@BeforeAll
	//모든 메서드 실행전 한번만 실행
	public static void initAll() {
		System.out.println("전체 초기화 코드");
	}
	
	@AfterAll
	//모든 메서드 실행 후 한번만 실행
	public static void endTestAll() {
		System.out.println("테스트 종료 후 코드");
	}
	
	@BeforeEach
	//각 테스트 전 실행하는 메서드
	public void testBefore() {
		System.out.println("테스트 메서드 수행 전에 수행하는 메서드");
	}
	
	@AfterEach
	//각 테스트 전 실행하는 메서드
	public void testAfter() {
		System.out.println("테스트 메서드 수행 후에 수행하는 메서드");
	}
	
	
	@DisplayName("데이터 추가 테스트")
	@Order(1)
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
		System.out.println("데이터 추가 테스트 메서드");
		assertEquals(1, result,"데이터 추가 테스트 실패");
		
	}

	@DisplayName("데이터 삭제 테스트")
	@Order(2)
	@Test
	public void deleteTest() {
		assertEquals(1, service.deleteMember("TESTID"),"데이터 삭제 테스트 실패");
		System.out.println("데이터 삭제 테스트 메서드");

	}
}










