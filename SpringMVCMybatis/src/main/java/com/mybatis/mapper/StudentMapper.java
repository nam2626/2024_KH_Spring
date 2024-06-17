package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mybatis.dto.StudentDTO;

@Mapper
public interface StudentMapper {
	List<StudentDTO> selectAllStudent();
	int deleteStudent(String stdNo);
	int updateStudent(StudentDTO dto);
	int insertStudent(StudentDTO dto);
}
