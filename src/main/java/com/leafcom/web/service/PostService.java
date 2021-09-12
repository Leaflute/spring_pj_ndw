package com.leafcom.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface PostService {
	
	// 게시판 목록 조회
	public void postList(HttpServletRequest req, Model model);

	// 게시글 상세 조회
	public void postDetail(HttpServletRequest req, Model model);

	// 게시글 작성 
	public void postWriteAction(HttpServletRequest req, Model model);
	
	// 게시글 수정 처리
	public void postUpdateAction(HttpServletRequest req, Model model);
	
	// 게시글 삭제
	public void postDeleteAction(HttpServletRequest req, Model model);
}
