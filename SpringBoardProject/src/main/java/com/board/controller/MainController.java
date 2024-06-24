package com.board.controller;

import org.springframework.stereotype.Controller;

import com.board.service.BoardService;
import com.board.service.MemberService;

@Controller
public class MainController {
	private BoardService boardService;
	private MemberService memberService;

	public MainController(BoardService boardService, MemberService memberService) {
		this.boardService = boardService;
		this.memberService = memberService;
	}
	
	
	
}
