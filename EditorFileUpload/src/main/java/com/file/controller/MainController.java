package com.file.controller;

import java.io.File;
import java.io.IOException;

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
			@RequestParam(value="file") MultipartFile[] file) throws IllegalStateException, IOException {
		
		//업로드할 기본 경로 지정 및 없으면 생성
		File root = new File("c:\\fileupload");
		if(!root.exists())
			root.mkdirs();
		
		for(int i=0;i<file.length;i++) {
			System.out.println(file[i].getSize() + " " + file[i].getOriginalFilename());
			//파일 사이즈 체크 해서 0이면 업로드가 안된 항목
			if(file[i].getSize() == 0)
				continue;
			//파일 쓰기
			//업로드할 경로 설정
			File f = new File(root, file[i].getOriginalFilename());
			file[i].transferTo(f);//실제 파일 쓰기를 수행
			
		}
		
		return null;
	}
}






