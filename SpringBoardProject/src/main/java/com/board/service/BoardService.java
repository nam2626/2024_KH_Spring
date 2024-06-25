package com.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.board.dto.BoardCommentDTO;
import com.board.dto.BoardDTO;
import com.board.dto.FileDTO;
import com.board.mapper.BoardMapper;

@Service
public class BoardService {
	private BoardMapper mapper;

	public BoardService(BoardMapper mapper) {
		this.mapper = mapper;
	}

	public List<BoardDTO> selectBoardNewList(int pageNo, int pageContentEa) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageNo", pageNo);
		map.put("pageContentCount", pageContentEa);
		return mapper.selectBoardNewList(map);
	}

	public int selectBoardTotalCount() {
		return mapper.selectBoardTotalCount();
	}

	public BoardDTO selectBoard(int bno) {
		return mapper.selectBoard(bno);
	}

	public List<BoardCommentDTO> selectBoardCommentList(int bno) {
		return mapper.selectBoardCommentList(bno);
	}

	public List<FileDTO> selectBoardFileList(int bno) {
		return mapper.selectBoardFileList(bno);
	}

	public int insertBoardLike(int bno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("bno", bno);
		return mapper.insertBoardLike(map);
	}
	public int deleteBoardLike(int bno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("bno", bno);
		return mapper.deleteBoardLike(map);
	}

	public int selectBoardLikeCount(int bno) {
		return mapper.selectBoardLikeCount(bno);
	}
	public int insertBoardHate(int bno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("bno", bno);
		return mapper.insertBoardHate(map);
	}
	public int deleteBoardHate(int bno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("bno", bno);
		return mapper.deleteBoardHate(map);
	}
	
	public int selectBoardHateCount(int bno) {
		return mapper.selectBoardHateCount(bno);
	}
	
	
}






