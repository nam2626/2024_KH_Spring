package com.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.mybatis.dto.MajorDTO;

@Mapper
public interface MajorMapper {

	List<MajorDTO> selectAllMajor();

}
