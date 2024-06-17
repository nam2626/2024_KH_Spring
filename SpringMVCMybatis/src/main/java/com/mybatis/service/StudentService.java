package com.mybatis.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public int updateStudent(StudentDTO dto) {
		return mapper.updateStudent(dto);
	}

	public int insertStudent(StudentDTO dto) {
		return mapper.insertStudent(dto);
	}

	public List<StudentDTO> selectStudent(String kind, String search) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kind", kind);
		map.put("search", search);
		
		return mapper.selectStudent(map);
	}
	
	
}




