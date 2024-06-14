package com.mybatis.controller;

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
	
	
	
}



