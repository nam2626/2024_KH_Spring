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
		//삭제 결과 리턴
		return "삭제기능 호출";
	}
	
	
	
}



