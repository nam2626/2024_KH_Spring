package com.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.BoardDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardNewList(Map<String, Object> map);
	int selectBoardTotalCount();
	BoardDTO selectBoard(int bno);

}
