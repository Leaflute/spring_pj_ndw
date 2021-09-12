package com.leafcom.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leafcom.web.service.PostService;

@Controller
public class PostController {
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@Autowired
	PostService service;
	
	// 고객문의 게시판(po_id=1)
	@RequestMapping("/csList.bo")
	public String csList(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csList]");
		
		service.postList(req, model);
		
		return "board/cs/csList";
	}
	
	// 게시글 상세 보기	
	@RequestMapping("/csDetail.bo")
	public String csDetail(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csDetail]");
		
		service.postDetail(req, model);
		
		return "board/cs/csDetail";
	}
	
	// 게시글 수정 요청
	@RequestMapping("/csUpdate.bo")
	public String csUpdate(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csUpdate]");
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		boolean fullList = Boolean.parseBoolean(req.getParameter("fullList"));
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		service.postDetail(req, model);
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fullList", fullList);
		model.addAttribute("boardId", boardId);
		
		return "board/cs/csUpdate";
	}
	
	// 게시글 수정 처리	
	@RequestMapping("/csUpdateAction.bo")
	public String csUpdateAction(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csUpdateAction]");
		
		service.postUpdateAction(req, model);
		
		return "board/cs/csUpdateAction";
	}
	
	// 게시글 작성 - 화면	
	@RequestMapping("/csWrite.bo")
	public String csWrite(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csWrite]");
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		boolean fullList = Boolean.parseBoolean(req.getParameter("fullList"));
		int num = 0;
		int pageNum = 0;
		int ref = 0;
		int refStep = 0;
		int refLevel = 0;
		
		if(req.getParameter("num")!=null) {
			num = Integer.parseInt(req.getParameter("num"));
			ref = Integer.parseInt(req.getParameter("ref"));
			refStep = Integer.parseInt(req.getParameter("refStep"));
			refLevel = Integer.parseInt(req.getParameter("refLevel"));
		}
		
		pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		model.addAttribute("num", num);
		model.addAttribute("ref", ref);
		model.addAttribute("refStep", refStep);
		model.addAttribute("refLevel", refLevel);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fullList", fullList);
		model.addAttribute("boardId", boardId);
		
		return "board/cs/csWrite";
	}
	
	// 게시글 작성 처리	
	@RequestMapping("/csWriteAction.bo")
	public String csWriteAction(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csWriteAction]");
		
		service.postWriteAction(req, model);
		
		return "board/cs/csWriteAction";
	}
	
	// 게시글 삭제 - 페이지 호출	
	@RequestMapping("/csDelete.bo")
	public String csDelete(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csDelete]");
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		boolean fullList = Boolean.parseBoolean(req.getParameter("fullList"));
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fullList", fullList);
		model.addAttribute("boardId", boardId);
		
		return "board/cs/csDelete";
	}
	
	// 게시글 삭제 - 처리
	@RequestMapping("/csDeleteAction.bo")
	public String csDeleteAction(HttpServletRequest req, Model model) {
		logger.info("[bo][cnt][url->csDeleteAction]");
		
		int boardId = Integer.parseInt(req.getParameter("boardId"));
		boolean fullList = Boolean.parseBoolean(req.getParameter("fullList"));
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		service.postDeleteAction(req, model);
		
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("fullList", fullList);
		model.addAttribute("boardId", boardId);
		
		return "board/cs/csDeleteAction";
	}
}
