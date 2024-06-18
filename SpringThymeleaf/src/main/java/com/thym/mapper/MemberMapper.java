package com.thym.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.thym.dto.BoardMemberDTO;

@Mapper
public interface MemberMapper {

	BoardMemberDTO login(Map<String, Object> map);

}




