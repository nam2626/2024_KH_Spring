package com.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.mybatis.dto.StudentDTO;

@Mapper
public interface StudentMapper {
	List<StudentDTO> selectAllStudent();
	int deleteStudent(String stdNo);
	int updateStudent(StudentDTO dto);
	int insertStudent(StudentDTO dto);
	List<StudentDTO> selectStudent(Map<String, Object> map);
}





