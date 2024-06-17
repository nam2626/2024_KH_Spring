package com.mybatis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mybatis.dto.MajorDTO;
import com.mybatis.mapper.MajorMapper;

@Service
public class MajorService {
	private MajorMapper mapper;

	public MajorService(MajorMapper mapper) {
		this.mapper = mapper;
	}

	public List<MajorDTO> selectAllMajor() {
		return mapper.selectAllMajor();
	}
	
	
}
