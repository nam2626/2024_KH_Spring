package com.mybatis.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mybatis.dto.MajorDTO;
import com.mybatis.dto.StudentDTO;
import com.mybatis.service.MajorService;
import com.mybatis.service.StudentService;

@Controller
public class MainController {
	
	private StudentService studentService;
	private MajorService majorService;
	
	public MainController(StudentService studentService, MajorService majorService) {
		this.studentService = studentService;
		this.majorService = majorService;
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
	public ModelAndView addStudentView(ModelAndView view) {
		//전체 학과명과 학과번호를 조회해서 request영역에 저장
		//1. MajorDTO 작성
		//2. MajorMapper 작성
		//3. MajorService 작성 <-- MajorMapper 역주입
		//4. MainController <-- MajorService 역주입
		//5. 전체 학과정보 읽어옴 <-- List<MajorDTO>
		List<MajorDTO> list = majorService.selectAllMajor();
		System.out.println(list);
		//6. addObject로 전체 학과정보 저장
		view.addObject("majorList", list);
		//7. setViewName 이동할 페이지 student_register로 지정
		view.setViewName("student_register");
		//8. 리턴할 객체는 ModelAndView
		return view;
	}
	
}





