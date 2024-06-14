package com.mybatis.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
	
	
}



