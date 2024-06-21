package com.login;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;

@Controller
public class MainController {
	private final String CLIENT_ID = "CLIENT_ID";
	private final String CLIENT_SECRET_ID = "CLIENT_SECRET_ID";
	
	@GetMapping("/naver")
	public ModelAndView naverLoginView(ModelAndView view, HttpSession session) throws UnsupportedEncodingException {
		String redirectURI = URLEncoder.encode("http://localhost:9999/naver/callback", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + CLIENT_ID;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		session.setAttribute("state", state);
		view.addObject("apiURL", apiURL);
		view.setViewName("naver_login");
		return view;
	}

}
