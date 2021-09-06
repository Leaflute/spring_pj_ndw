package com.leafcom.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CommonService {
	
	// 로그인 처리
//	public void loginAction(HttpServletRequest req, Model model);
	
	// 아이디 중복확인
	public void idDupChk(HttpServletRequest req, Model model);
		
	// 회원가입 처리
	void signUpAction(String id, String pw, String name, String email, String mobile, String auth, Model model);
	
	// 아이디 활성화
	public void activateId(String id, String key, Model model);
	
//	// 회원탈퇴 처리
//	public void withdrawMemAction(HttpServletRequest req, Model model);
//	
//	// 회원정보 출력
//	public void viewMemInfoAction(HttpServletRequest req, Model model);
//	
//	// 회원정보 수정
//	public void updateMemInfoAction(HttpServletRequest req, Model model);


}
