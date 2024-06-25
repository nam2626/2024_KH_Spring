package com.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.board.dto.BoardCommentDTO;
import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;

@Mapper
public interface BoardMapper {

	List<BoardDTO> selectBoardNewList(Map<String, Object> map);
	int selectBoardTotalCount();
	BoardDTO selectBoard(int bno);
	List<BoardCommentDTO> selectBoardCommentList(int bno);
	List<FileDTO> selectBoardFileList(int bno);

}
