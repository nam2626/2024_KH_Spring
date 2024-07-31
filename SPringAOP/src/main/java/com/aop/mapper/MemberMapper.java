package com.aop.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.aop.dto.BoardMemberDTO;
import com.aop.dto.GradeDTO;

@Mapper
public interface MemberMapper {

	BoardMemberDTO login(Map<String, Object> map);
	List<BoardMemberDTO> selectAllMember();
	BoardMemberDTO selectMember(String id);
	int updateMember(BoardMemberDTO dto);
	int deleteMember(String id);
	int insertMember(BoardMemberDTO dto);
	List<GradeDTO> selectAllGrade();
	int updateGrade(GradeDTO dto);
	int insertGrade(GradeDTO dto);
	int deleteGrade(int gradeNo);
	List<BoardMemberDTO> selectGradeMember(int[] gradeNo);

}




