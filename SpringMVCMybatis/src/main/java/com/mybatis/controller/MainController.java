package com.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.mybatis.service.StudentService;

@Controller
public class MainController {
	
	private StudentService studentService;
	
	public MainController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public String index() {
		return "main";
	}
	
	@GetMapping("/addStdView")
	public String addStudentView() {
		return "student_register";
	}
	
}





