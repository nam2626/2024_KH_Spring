package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mybatis.dto.StudentDTO;

@Mapper
public interface StudentMapper {
	List<StudentDTO> selectAllStudent();
}
