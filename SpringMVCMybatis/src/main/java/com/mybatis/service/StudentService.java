package com.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.dto.StudentDTO;
import com.mybatis.mapper.StudentMapper;

@Service
public class StudentService {

	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		this.mapper = mapper;
	}

	public List<StudentDTO> selectAllStudent() {
		return mapper.selectAllStudent();
	}

	public int deleteStudent(String stdNo) {
		return mapper.deleteStudent(stdNo);
	}
	
	
}




