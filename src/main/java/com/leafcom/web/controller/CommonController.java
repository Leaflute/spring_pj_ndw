package com.leafcom.web.controller;

import java.nio.file.AccessDeniedException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leafcom.web.service.CommonService;
import com.leafcom.web.util.MailSendHandler;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CommonService service;
	
	//메인 페이지
	@RequestMapping(value = {"/","/index.co"})
	public String home(HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->index]");
		
		return "index";
	}
	
	//로그인 페이지
	@RequestMapping("/login.co")
	public String login(HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->login]");
		
		return "common/login/login";
	}
	
	
	// 회원 가입 페이지 로드
	@RequestMapping("/signUp.co")
	public String signUp(HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->signUp]");
		
		return "common/signUp/signUp";
	}
	
	// 아이디 중복 체크 팝업창
	@RequestMapping("/idDupChk.co")
	public String idDupChk(HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->idDupChk]");
		
		service.idDupChk(req, model);
		
		return "common/signUp/idDupChk";
	}
	
	// 회원가입 처리
	@RequestMapping("/signUpAction.co")
	public String signUpAction(@RequestParam String id, @RequestParam String pw, 
			@RequestParam String name, @RequestParam String email, @RequestParam String auth,
			@RequestParam String mobile, HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->signUpAction]");
		
		service.signUpAction(id, pw, name, email, mobile, auth, model);
		
		return "common/signUp/signUpAction";
	}
	
	// 이메일 체크
	@RequestMapping("/emailChkAction.co")
	public String emailChkAction(@RequestParam String id, @RequestParam String key, HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->emailChkAction]");
		
		service.activateId(id, key, model);
		
		return "customer/info/emailChkAction";
	}
	
	// 이메일 재전송 페이지
	@RequestMapping("/emailChk.co")
	public String signUpAction(@RequestParam String id, @RequestParam String key, HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->signUpAction]");
		
		return "customer/info/emailchk";
	}
	
	// 이메일 재전송
	@RequestMapping("/emailResend.co")
	public String emailResend(@RequestParam String id, @RequestParam String key, @RequestParam String email, HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->emailResend]");
		
		MailSendHandler msh = new MailSendHandler();
		msh.sendActivationEmail(id, email, key);
		
		return "redirect:/customer/info/emailchk";
	}
	
	// 권한이 없는 사용자에게 안내 페이지 출력 - 403 에러
	@RequestMapping("/denied.co")
	public String denied(HttpServletRequest req, Model model, Authentication auth) {
		
		logger.info("[co][cnt][url->denied]");
		AccessDeniedException exception = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
		model.addAttribute("errMsg", exception);
		
		return "error/error_403";
	}
	
	// 로그아웃 처리
	@RequestMapping("/logout.co")
	public String logout(HttpSession session, HttpServletRequest req, Model model) {
		logger.info("[co][cnt][url->logout]");
		session.invalidate();
		
		return "redirect:/";
	}
	
	// ----------------------------------
	// 상품 카테고리
	@RequestMapping("/itemList.co")
	public String itemList(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->itemList]");
		
		return "common/item/list";
	}
	
	// 상품 상세
	@RequestMapping("/itemDetail.co")
	public String itemDetail(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->itemDetail]");
		
		return "common/item/detail";
	}
	
	// 장바구니 추가 후 리스트 페이지 로드 여부 확인 팝업창
	@RequestMapping("/cartPop.co")
	public String cartPop(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->cartPop]");
		
		return "common/item/cartPop";
	}
	
	// 장바구니 리스트
	@RequestMapping("/cartList.co")
	public String cartList(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->cartList]");
		
		return "commonn/cart/list";
	}
	
	// 장바구니 추가
	@RequestMapping("/addcart.co")
	public String addcart(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->addcart]");
		
		return "common/cart/list";
	}
	
	// 장바구니 수량 변경
	@RequestMapping("/updateCart.co")
	public String updateCart(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->updateCart]");
		
		return "common/cart/list";
	}
	
	// 장바구니 삭제(개별)
	@RequestMapping("/deleteCart.co")
	public String deleteCart(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->deleteCart]");
		
		return "common/cart/list";
	}
	
	// 장바구니 삭제(리스트)	
	@RequestMapping("/deleteCartList.co")
	public String deleteCartList(HttpServletRequest req, Model mode) {
		logger.info("[co][cnt][url->deleteCartList]");
		
		return "common/cart/list";
	}
	
}
