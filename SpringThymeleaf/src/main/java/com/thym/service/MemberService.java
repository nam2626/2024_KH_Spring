package com.thym.service;

import org.springframework.stereotype.Service;

import com.thym.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}
	
}
