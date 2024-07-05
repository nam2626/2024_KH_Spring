package com.member.service;

import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.member.dto.MemberDTO;
import com.member.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public MemberDTO login(String id, String pwd) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		map.put("pass", pwd);
		return mapper.login(map);
	}

	public int insertMember(MemberDTO dto) {
		return mapper.insertMember(dto);
	}
	
	
}
