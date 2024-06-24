package com.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardDTO;
import com.board.service.BoardService;
import com.board.service.MemberService;
import com.board.vo.PaggingVO;

@Controller
public class MainController {
	private BoardService boardService;
	private MemberService memberService;

	public MainController(BoardService boardService, MemberService memberService) {
		this.boardService = boardService;
		this.memberService = memberService;
	}
	
	@GetMapping("/")
	public ModelAndView main(ModelAndView view,
			@RequestParam(defaultValue = "1") int pageNo,
			@RequestParam(defaultValue = "20") int pageContentEa) {
		//해당 페이지 게시글 목록 읽어옴
		List<BoardDTO> boardList = 
				boardService.selectBoardNewList(pageNo,pageContentEa);
		//페이징 정보도 읽어옴
		//	전체 페이지 개수 읽어옴
		int totalCount = boardService.selectBoardTotalCount();
		PaggingVO vo = new PaggingVO(totalCount, pageNo, pageContentEa);
		
		//request 영역에 저장
		view.addObject("list", boardList);
		view.addObject("pagging", vo)
		
		view.setViewName("main");
		return view;
	}
	
}







