package com.mvc;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestMainController {
	//문자열로 전송
	@GetMapping("/main2")
	public String main() {
		return "index";
	}
	
	//객체 리턴
	@RequestMapping("/get")
	public RegisterDTO getRegister() {
		return new RegisterDTO("A0001", "123456", "홍길동", "남", 30);
	}
	
	//맵 리턴
	@RequestMapping("/map")
	public HashMap<String, Object> returnMap(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("msg", "Exception이 발생되지 않았습니다.");
		map.put("resultCount", 30);
		ArrayList<RegisterDTO> list = new ArrayList<RegisterDTO>();
		list.add(new RegisterDTO("A0001", "123456", "홍길동", "남", 30));
		list.add(new RegisterDTO("A0002", "123456", "홍길동", "남", 40));
		list.add(new RegisterDTO("A0003", "123456", "홍길동", "남", 50));
		list.add(new RegisterDTO("A0004", "123456", "홍길동", "남", 60));
		map.put("list", list);
		return map;		
	}
	
	@RequestMapping("/callData")
	public ResponseEntity<String> callData(String data){
		HashMap<String, Object> map = new HashMap<String, Object>();
		HttpStatus status = HttpStatus.OK;
		
		if(data.equals("true")) {
			map.put("result", "Exception이 발생하지 않았습니다.");
		} else {
			map.put("result", "Exception이 발생하였습니다.");
			map.put("errorCode", 777);
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity(map,status);
	}
}






