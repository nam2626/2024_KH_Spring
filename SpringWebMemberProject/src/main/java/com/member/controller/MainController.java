package com.member.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.member.dto.MemberDTO;
import com.member.service.MemberService;
import com.member.tokken.JwtTokenProvider;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MainController {
	private MemberService service;
	private JwtTokenProvider tokenprovider;
	
	@Autowired
	public MainController(MemberService service, JwtTokenProvider tokenprovider) {
		this.service = service;
		this.tokenprovider = tokenprovider;
	}
	
	@GetMapping("/")
	public String index() {
		return "index";
	}

	@PostMapping("/member/login")
	public ResponseEntity<String> login(HttpServletRequest request) throws JSONException {
		JSONObject json = getJsonData(request);
		System.out.println(json);
		String id = json.getString("id");
		String pwd = json.getString("pwd");
		Map map = new HashMap();
		boolean flag = false;
		MemberDTO dto = service.login(id, pwd);
		if (dto != null) {
			String token = tokenprovider.generateJwtToken(dto);
			flag = true; // 로그인 성공 실패 flag 값
			id = tokenprovider.getUserIDFromToken(token);
			map.put("id", id);
			map.put("token", token);
		}
		map.put("flag", flag);
		return new ResponseEntity(map, HttpStatusCode.valueOf(HttpStatus.OK.value()));
	}

	// 검색 (로그인 완료 후 로그인 한 사람의 정보를 꺼내는 url)
	@GetMapping("/member/info")
	public ResponseEntity<String> getInfo(String token) {
		boolean flag = true;
		Map map = new HashMap();
		try {
			String id = tokenprovider.getUserIDFromToken(token);
			String name = tokenprovider.getUserNameFromToken(token);
			map.put("id", id);
			map.put("name", name);
		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
		map.put("flag", flag);
		return new ResponseEntity(map, HttpStatusCode.valueOf(HttpStatus.OK.value()));
	}

	// 회원 가입
	@PostMapping("/member/register")
	public ResponseEntity<String> registerMember(HttpServletRequest request) throws IOException, JSONException {
		JSONObject json = getJsonData(request);
		System.out.println(json);
		MemberDTO dto = new MemberDTO();
		dto.setUserID(json.getString("userID"));
		dto.setUserPassword(json.getString("userPassword"));
		dto.setUserName(json.getString("userName"));
		dto.setUserEmail(json.getString("userEmail"));
		dto.setUserPhone(json.getString("userPhone"));
		dto.setUserNickName(json.getString("userNickName"));
		dto.setUserGender(json.getString("userGender"));

		int row = service.insertMember(dto);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("resultCode", row);
		if (row == 1)
			map.put("resultMsg", "회원 정보 등록 성공");
		else
			map.put("resultMsg", "회원 정보 등록 실패");

		return new ResponseEntity(map, HttpStatusCode.valueOf(HttpStatus.OK.value()));
	}

	public JSONObject getJsonData(HttpServletRequest request) {
		StringBuffer sb = new StringBuffer();
		JSONObject json = null;
		try (BufferedReader br = request.getReader()){
			String str = null;
			while ((str = br.readLine()) != null) {
				sb.append(str);
			}
			System.out.println(sb.toString());
			json = new JSONObject(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return json; 
	}
}
