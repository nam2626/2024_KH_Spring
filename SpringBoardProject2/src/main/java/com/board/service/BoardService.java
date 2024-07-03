package com.board.service;

import java.util.Comparator;
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

	public int insertBoardCommentLike(int cno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("cno", cno);
		return mapper.insertBoardCommentLike(map);
	}

	public int deleteBoardCommentLike(int cno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("cno", cno);
		return mapper.deleteBoardCommentLike(map);
	}
	public int insertBoardCommentHate(int cno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("cno", cno);
		return mapper.insertBoardCommentHate(map);
	}
	
	public int deleteBoardCommentHate(int cno, String boardMemberId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", boardMemberId);
		map.put("cno", cno);
		return mapper.deleteBoardCommentHate(map);
	}

	public int insertBoardComment(BoardCommentDTO dto) {
		return mapper.insertBoardComment(dto);
	}

	public int deleteBoardComment(int cno) {
		return mapper.deleteBoardComment(cno);
	}

	public int deleteBoard(int bno) {
		return mapper.deleteBoard(bno);
	}

	public int updateBoardCount(int bno) {
		return mapper.updateBoardCount(bno);
	}

	public int getBoardNo() {
		return mapper.getBoardNo();
	}

	public int insertBoard(BoardDTO dto) {
		return mapper.insertBoard(dto);
	}

	public int insertBoardFile(FileDTO fileDTO) {
		return mapper.insertBoardFile(fileDTO);
	}

	public FileDTO selectFile(int bno, int fno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("fno", fno);
		return mapper.selectBoardFile(map);
	}

	public int selectFileNo() {
		return mapper.selectFileNo();
	}

	public int insertImageFile(FileDTO fileDTO) {
		return mapper.insertImageFile(fileDTO);
	}

	public FileDTO selectImageFile(int fno) {
		return mapper.selectImageFile(fno);
	}

	public int updateBoard(BoardDTO dto) {
		return mapper.updateBoard(dto);
	}

	public int deleteBoardFileList(int bno, int[] fnoList) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bno", bno);
		map.put("array", fnoList); 
		return mapper.deleteBoardFileList(map);
	}

	public List<Integer> selectBoardFileNumbers(int boardNo) {
		return mapper.selectBoardFileNumbers(boardNo);
	}
	
	
}






