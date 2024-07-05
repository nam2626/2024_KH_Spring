package com.member.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.member.dto.MemberDTO;

@Mapper
public interface MemberMapper {

	MemberDTO login(HashMap<String, String> map);
	int insertMember(MemberDTO dto);

}
