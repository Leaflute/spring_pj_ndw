package com.leafcom.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CommonService {
	
	// 아이디 중복확인
	public void idDupChk(HttpServletRequest req, Model model);
		
	// 회원가입 처리
	void signUpAction(String id, String pw, String name, String email, String mobile, String auth, Model model);
	
	// 아이디 활성화
	public void activateId(String id, String key, Model model);

	void itemList(HttpServletRequest req, Model model);

	void categoryMap(HttpServletRequest req, Model model);

	void itemDetail(HttpServletRequest req, Model model);

	void cartList(HttpServletRequest req, Model model);

	void loginAddCart(HttpServletRequest req, Model model);

	void addCart(HttpServletRequest req, Model model);

	void updateCart(HttpServletRequest req, Model model);

	void deleteCartList(HttpServletRequest req, Model model);

}
