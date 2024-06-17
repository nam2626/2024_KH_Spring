package com.mybatis.service;

import org.springframework.stereotype.Service;

import com.mybatis.mapper.MajorMapper;

@Service
public class MajorService {
	private MajorMapper mapper;

	public MajorService(MajorMapper mapper) {
		this.mapper = mapper;
	}
	
	
}
