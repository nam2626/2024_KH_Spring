package com.file.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/board/write")
	public String boardWrite(String title, String content, String writer,
			@RequestParam(value="file") MultipartFile[] file) {
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getSize() + " " + file[i].getOriginalFilename());
		}
		
		return null;
	}
}






