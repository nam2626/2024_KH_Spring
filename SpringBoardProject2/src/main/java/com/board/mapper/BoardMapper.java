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
	int insertBoardLike(Map<String, Object> map);
	int deleteBoardLike(Map<String, Object> map);
	int selectBoardLikeCount(int bno);
	int insertBoardHate(Map<String, Object> map);
	int deleteBoardHate(Map<String, Object> map);
	int selectBoardHateCount(int bno);
	int insertBoardCommentLike(Map<String, Object> map);
	int deleteBoardCommentLike(Map<String, Object> map);
	int insertBoardCommentHate(Map<String, Object> map);
	int deleteBoardCommentHate(Map<String, Object> map);
	int insertBoardComment(BoardCommentDTO dto);
	int deleteBoardComment(int cno);
	int deleteBoard(int bno);
	int updateBoardCount(int bno);
	int getBoardNo();
	int insertBoard(BoardDTO dto);
	int insertBoardFile(FileDTO fileDTO);
	FileDTO selectBoardFile(Map<String, Object> map);
	int selectFileNo();
	int insertImageFile(FileDTO fileDTO);
	FileDTO selectImageFile(int fno);
	int updateBoard(BoardDTO dto);
	int deleteBoardFileList(Map<String, Object> map);
	List<Integer> selectBoardFileNumbers(int boardNo);

}





