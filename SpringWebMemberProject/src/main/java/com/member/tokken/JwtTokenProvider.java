package com.member.tokken;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.member.dto.MemberDTO;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtTokenProvider {
	private final Long expiredTime = 1000 * 60L * 60L * 1L; // 유효시간 1시간 (토큰의 유효시간)
	Key key = Keys.hmacShaKeyFor("sdfgfdsgdsfgfdsgsdfgsdfhfdgjhfgjghfjghfj".getBytes(StandardCharsets.UTF_8));
	//cd값으로 넣어줄 토큰 값. 아무거나 막 친거임. 265비트 이상으로 안하면 오류남. 암호화에 사용할 키 값임

	//토큰 생성기
	public String generateJwtToken(MemberDTO member) {
		Date now = new Date();
		return Jwts.builder().setSubject(member.getUserID()) // 보통 username. id를 subject로 해줌
				.setHeader(createHeader())
				.setClaims(createClaims(member)) // 클레임, 토큰에 포함될 정보
				.setExpiration(new Date(now.getTime() + expiredTime)) // 만료일
				.signWith(key, SignatureAlgorithm.HS256)
				.compact(); //암호화
	}

	//헤더 셋팅
	private Map<String, Object> createHeader() {
		Map<String, Object> header = new HashMap<>();
		header.put("typ", "JWT"); //토큰 종류 설정
		header.put("alg", "HS256"); // 해시 256 사용하여 암호화
		header.put("regDate", System.currentTimeMillis()); //생성시간
		return header;
	}

	//토큰에 추가 정보 셋팅
	private Map<String, Object> createClaims(MemberDTO member) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("userID", member.getUserID()); // 로그인 id		
		claims.put("userName", member.getUserName()); // 로그인 id		
		return claims;
	}

	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
	}

	//토큰에 저장한 로그인 id값 꺼내서 반환
	public String getUserIDFromToken(String token) {
		return (String) getClaims(token).get("userID");
	}
	public String getUserNameFromToken(String token) {
		return (String) getClaims(token).get("userName");
	}


	//토큰에 저장한 로그인 type값 꺼내서 반환
	public int getRoleFromToken(String token) {
		return (int) getClaims(token).get("roles");
	}
	

}