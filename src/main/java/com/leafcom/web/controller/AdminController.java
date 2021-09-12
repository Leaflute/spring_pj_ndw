package com.leafcom.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.leafcom.web.service.AdminService;

@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	AdminService service;
	
	// 관리자 페이지로 이동
	@RequestMapping("/admin.ad")
	public String admin(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->admin]");
		
		return "admin/main";
	}
	
	// 관리자 상품 리스트
	@RequestMapping("/itemManagement.ad")
	public String itemManagement(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->itemManagement]");
		
		service.itemList(req, model);
		
		return "admin/item/list";
	}
	
	// 상품 상세 페이지
	@RequestMapping("/itemDetail.ad")
	public String itemDetail(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->itemDetail]");
		
		service.itemDetail(req, model);
		
		return "admin/item/detail";
	}
	
	// 상품 추가 입력
	@RequestMapping("/addItem.ad")
	public String addItem(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->addItem]");
		
		service.categoryMap(req, model);
		
		return "admin/item/add";
	}
	
	// 상품 추가 처리
	@RequestMapping(value = "/addItemAction.ad", method=RequestMethod.POST)
	public String addItemAction(MultipartHttpServletRequest mReq, Model model) {
		logger.info("[ad][cnt][url->addItemAction]");
		
		service.addItem(mReq, model);
		
		return "admin/item/addAction";
	}
	
	// 상품 수정 입력
	@RequestMapping("/updateItem.ad")
	public String updateItem(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->updateItem]");
		service.categoryMap(req, model);
		service.itemDetail(req, model);
		
		return "admin/item/addAction";
	}
	
	// 상품 수정 처리
	@RequestMapping("/updateItemAction.ad")
	public String updateItemAction(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->updateItemAction]");
		
		service.updateItem(req, model);
		
		return "admin/item/updateAction";
	}
	
	// 상품 삭제 확인 페이지 로드
	@RequestMapping("/deleteItem.ad")
	public String deleteItem(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->deleteItemAction]");
		
		model.addAttribute("itemId", Integer.parseInt(req.getParameter("itemId")));
		model.addAttribute("pageNum", Integer.parseInt(req.getParameter("pageNum")));
		model.addAttribute("categoryId", Integer.parseInt(req.getParameter("categoryId")));
		
		service.itemDetail(req, model);
		
		return "admin/item/delete";
	}
	
	// 상품 삭제 처리
	@RequestMapping("/deleteItemAction.ad")
	public String deleteItemAction(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->deleteItemAction]");
		service.deleteItem(req, model);
		
		return "admin/item/deleteAction";
	}
	
	// 주문 리스트 확인
	@RequestMapping("/orderList.ad")
	public String orderList(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->orderList]");
		service.orderList(req, model);
		
		return "admin/order/list";
	}
	
	// 주문 상태 변경
	@RequestMapping("/updateOrder.ad")
	public String updateOrder(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->updateOrder]");
		service.updateOrder(req, model);
		service.orderList(req, model);
		
		return "admin/order/updateAction";
	}
	
	// 결산 페이지 로드
	
	@RequestMapping("/report.ad")
	public String report(HttpServletRequest req, Model model) {
		logger.info("[ad][cnt][url->report]");
		service.fiveDayReport(req, model);
		
		return "admin/report/report";
	}
}
