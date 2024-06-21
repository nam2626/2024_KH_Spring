package com.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class KakaoController {
	private final String REST_API_KEY = "";
	private final String REDIRECT_URI = "";
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
	
	
}




