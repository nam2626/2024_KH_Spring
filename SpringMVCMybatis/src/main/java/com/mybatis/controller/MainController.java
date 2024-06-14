package com.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.dto.StudentDTO;
import com.mybatis.service.StudentService;

@Controller
public class MainController {
	
	private StudentService studentService;
	
	public MainController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/")
	public ModelAndView index(ModelAndView view) {
		//전체 학생정보를 읽어옴
		List<StudentDTO> studentList = studentService.selectAllStudent(); 
		//request 영역에 studentList 저장
		view.addObject("studentList", studentList);
		//main.jsp 로 이동하게끔 처리
		view.setViewName("main");
		System.out.println(studentList);
		return view;
	}
	
	@GetMapping("/addStdView")
	public String addStudentView() {
		return "student_register";
	}
	
}





