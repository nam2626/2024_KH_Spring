package com.mybatis.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybatis.dto.StudentDTO;
import com.mybatis.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}
	
	@DeleteMapping("/delete/{stdNo}")
//	public String deleteStudent(@PathVariable("stdNo") String stdNo) {
	public String deleteStudent(@PathVariable String stdNo) {
		System.out.println(stdNo);
		//해당 학번 삭제 작업
		int result = service.deleteStudent(stdNo);
		//삭제 결과 리턴
		String msg = "정상적으로 삭제 되었습니다.";
		if(result != 1)
			msg = "삭제시 문제가 생겼습니다.";
		return msg;
	}
	
	@PutMapping("/update/{id}")
	public Map<String, Object> updateStudent(@PathVariable String id, 
			@RequestBody StudentDTO data) {
		System.out.println(id);
		System.out.println(data);
		
		//데이터를 수정
		int result = service.updateStudent(data);
		Map<String, Object> map = new HashMap<String, Object>();
		if(result == 1) {
			map.put("resultCode", 1);
			map.put("msg", "학생정보 수정에 성공하였습니다.");
		}else {
			map.put("resultCode", 0);
			map.put("msg", "학생정보 수정에 실패하였습니다.");
		}
		return map;
	}
	
}








