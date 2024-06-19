package com.thym.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.thym.dto.BoardMemberDTO;
import com.thym.mapper.MemberMapper;

@Service
public class MemberService {
	private MemberMapper mapper;

	public MemberService(MemberMapper mapper) {
		this.mapper = mapper;
	}

	public BoardMemberDTO login(String id, String passwd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		map.put("pass", passwd);
		
		return mapper.login(map);
	}

	public List<BoardMemberDTO> selectAllMember() {
		return mapper.selectAllMember();
	}

	public BoardMemberDTO selectMember(String id) {
		return mapper.selectMember(id);
	}

	public int updateMember(BoardMemberDTO dto) {
		return mapper.updateMember(dto);
	}

	public int deleteMember(String id) {
		return mapper.deleteMember(id);
	}
	
}











