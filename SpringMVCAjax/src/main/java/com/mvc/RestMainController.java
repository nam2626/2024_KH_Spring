package com.mvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMainController {
	//문자열로 전송
	@GetMapping("/main2")
	public String main() {
		return "index";
	}
}
