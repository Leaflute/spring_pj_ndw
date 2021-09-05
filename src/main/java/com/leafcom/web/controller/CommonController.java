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
	
	//로그인 처리 페이지(스프링 시큐리티가 담당)
//	@RequestMapping("/loginAction.co")
//	public String loginAction(HttpServletRequest req, Model model) {
//		logger.info("[co][cnt][url->loginAction]");
//		
//		return "common/login/loginAction";
//	}
	
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
			@RequestParam String name, @RequestParam String email, 
			@RequestParam String mobile, Model model) {
		logger.info("[co][cnt][url->signUpAction]");
		
		service.signUpAction(id, pw, name, email, mobile, model);
		
		return "common/signUp/signUpAction";
	}
	
	// 이메일 체크
	@RequestMapping("/emailChkAction.co")
	public String emailChkAction(@RequestParam String id, @RequestParam String key, Model model) {
		logger.info("[co][cnt][url->emailChkAction]");
		
		service.activateId(id, key, model);
		
		return "customer/info/emailChkAction";
	}
	
	// 이메일 재전송 페이지
	@RequestMapping("/emailChk.co")
	public String signUpAction(@RequestParam String id, @RequestParam String key, Model model) {
		logger.info("[co][cnt][url->signUpAction]");
		
		return "customer/info/emailchk";
	}
	
	// 이메일 재전송
	@RequestMapping("/emailResend.co")
	public String emailResend(@RequestParam String id, @RequestParam String key, @RequestParam String email, Model model) {
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
	
	// 관리자 페이지로 이동
	@RequestMapping("/admin.ad")
	public String admin() {
		logger.info("[co][cnt][url->admin]");
		
		return "admin/main";
	}
	
	// 로그아웃 처리
	@RequestMapping("/logout.do")
	public String logout(HttpSession session) {
		logger.info("[co][cnt][url->insertUser]");
		session.invalidate();
		
		return "redirect:/";
	}
	
}
