package com.leafcom.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	// 관리자 페이지로 이동
	@RequestMapping("/admin.ad")
	public String admin() {
		logger.info("[co][cnt][url->admin]");
		
		return "admin/main";
	}
	
}
