package com.leafcom.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.leafcom.web.service.CustomerService;

@Controller
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	CustomerService service;
	
	// 회원정보 수정 페이지 비밀번호 인증 페이지 로드
	@RequestMapping("/pwChk.cu")
	public String pwChk() {
		logger.info("[cu][cnt][url->pwChk]");
		
		return "customer/info/pwChk";
	}
	
	// 회원정보 조회와 동시에 수정
	@RequestMapping("/memberInfo.cu")
	public String memberInfo() {
		logger.info("[cu][cnt][url->memberInfo]");
		
		return "customer/info/memberInfo";
	}
	
	// 회원정보 수정처리
	@RequestMapping("/updateAction.cu")
	public String updateAction() {
		logger.info("[cu][cnt][url->updateAction]");
		
		return "customer/info/updateAction";
	}
	
	// 배송지 리스트
	@RequestMapping("/addressList.cu")
	public String addressList(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->addressList]");
		service.addressList(req, model);
		return "customer/address/list";
	} 
	
	// 배송지 추가
	@RequestMapping("/addAddress.cu")
	public String addAddress() {
		logger.info("[cu][cnt][url->addAddress]");
		
		return "customer/address/add";
	} 
	
	// 배송지 추가 처리
	@RequestMapping("/addAddressAction.cu")
	public String addAddressAction(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->addAddressAction]");
		service.addAddress(req, model);
		return "customer/address/addAction";
	} 
	
	// 배송지 수정 페이지
	@RequestMapping("/updateAddress.cu")
	public String updateAddress(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->updateAddress]");
		service.addressInfo(req, model);
		return "customer/address/update";
	} 
	
	// 배송지 수정 처리
	@RequestMapping("/updateAddressAction.cu")
	public String updateAddressAction(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->updateAddressAction]");
		service.updateAddress(req, model);
		return "customer/address/updateAction";
	} 
	
	// 배송지 삭제
	@RequestMapping("/deleteAddress.cu")
	public String deleteAddress(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->deleteAddress]");
		service.deleteAddress(req, model);
		return "customer/address/deleteAddress";
	}
	
	// 장바구니 구매 페이지 로드
	@RequestMapping("/buyInCart.cu")
	public String buyInCart(HttpSession session, HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->buyInCart]");
		
		if(session.getAttribute("member")==null) {
			return "common/login/login";
			
		} else {
			service.buyInCartInfo(req, model);
			return "customer/order/buyInCart";
		}
	}
	
	// 장바구니 구매 처리
	@RequestMapping("/buyInCartAction.cu")
	public String buyInCartAction(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->buyInCartAction]");
		service.buyInCart(req, model);
		return "customer/order/buyInCartAction";
	}
	
	// 바로구매 페이지 로드
	@RequestMapping("/buyNow.cu")
	public String buyNow(HttpSession session, HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->buyNow]");
		
		if(session.getAttribute("member")==null) {
			return "common/login/login";
		
		} else {
			service.buyNowInfo(req, model);
			return "customer/order/buyNow";
		}
	}
	
	// 바로구매 처리
	@RequestMapping("/buyNowAction.cu")
	public String buyNowAction(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->buyNowAction]");
		service.buyNow(req, model);
		return "customer/order/buyNowAction";
	}
	
	// 주문 목록
	@RequestMapping("/orderList.cu")
	public String orderList() {
		logger.info("[cu][cnt][url->orderList]");
		
		return "customer/order/orderList";
	}
	
	// 주문 상태 수정
	@RequestMapping("/updateOrder.cu")
	public String updateOrder(HttpServletRequest req, Model model) {
		logger.info("[cu][cnt][url->updateOrder]");
		service.updateOrder(req, model);
		return "customer/order/updateOrder";
	}
}
