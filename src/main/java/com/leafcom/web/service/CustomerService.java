package com.leafcom.web.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.Model;

public interface CustomerService {

	void buyInCartInfo(HttpServletRequest req, Model model);

	void buyInCart(HttpServletRequest req, Model model);

	void buyNowInfo(HttpServletRequest req, Model model);

	void buyNow(HttpServletRequest req, Model model);

	void addressList(HttpServletRequest req, Model model);

	void addAddress(HttpServletRequest req, Model model);

	void addressInfo(HttpServletRequest req, Model model);

	void updateAddress(HttpServletRequest req, Model model);

	void deleteAddress(HttpServletRequest req, Model model);

	void orderList(HttpServletRequest req, Model model);

	void updateOrder(HttpServletRequest req, Model model);
	
	
	
}
