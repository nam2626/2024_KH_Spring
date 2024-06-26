package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	@GetMapping("/editor")
	public String editor() {
		return "editor";
	}
	
	@GetMapping("/board/write/view")
	public String boardWriteView() {
		return "board_write";
	}
	
}
