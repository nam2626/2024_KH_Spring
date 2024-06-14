package com.mybatis.service;

import org.springframework.stereotype.Service;

import com.mybatis.mapper.StudentMapper;

@Service
public class StudentService {

	private StudentMapper mapper;

	public StudentService(StudentMapper mapper) {
		this.mapper = mapper;
	}
	
	
}




