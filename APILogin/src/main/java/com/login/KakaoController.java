package com.login;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class KakaoController {
	private final String REST_API_KEY = "";
	private final String REDIRECT_URI = "http://localhost:9999/kakao/callback";
	//kakao_login.html
	//kakao_login_result.html
	
	@GetMapping("/kakao")
	public ModelAndView kakaoLoginView(ModelAndView view) {
		String apiURL = "https://kauth.kakao.com/oauth/authorize?"
				+ "response_type=code"
				+ "&client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI;
		
		view.addObject("apiURL", apiURL);
		view.setViewName("kakao_login");
		return view;
	}
	
	@GetMapping("/kakao/callback")
	public ModelAndView kakaoCallBack(ModelAndView view, HttpSession session, String code) {
		String apiURL = "https://kauth.kakao.com/oauth/token?";
		apiURL += "grant_type=authorization_code"
				+ "&client_id=" + REST_API_KEY
				+ "&redirect_uri=" + REDIRECT_URI
				+ "&code=" + code;
		
		String res = requestKakaoServer(apiURL, null);
		
		if(res != null && !res.equals("")) {
			JSONObject json = new JSONObject(res);
			session.setAttribute("user", res);
			session.setAttribute("accessToken", json.get("access_token"));
			session.setAttribute("refreshToken", json.get("refresh_token"));
		}else {
			view.addObject("res", "로그인 실패");
		}
		
		view.setViewName("kakao_login_result");
		
		return view;
	}
	
	public String requestKakaoServer(String apiURL, String header) {
		StringBuffer res = new StringBuffer();
		try {
			URL url = new URL(apiURL);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			if (header != null && !header.equals("")) {
				con.setRequestProperty("Authorization", header);
			}

			int responseCode = con.getResponseCode();
			BufferedReader br;
			System.out.print("responseCode=" + responseCode);
			if (responseCode == 200) { // 정상 호출
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else { // 에러 발생
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			while ((inputLine = br.readLine()) != null) {
				res.append(inputLine);
			}
			br.close();
			if (responseCode == 200) {
				System.out.println(res.toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return res.toString();
	}
	
}










