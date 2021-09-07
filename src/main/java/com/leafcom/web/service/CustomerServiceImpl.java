package com.leafcom.web.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.leafcom.web.dao.CommonDAO;
import com.leafcom.web.dao.CustomerDAO;
import com.leafcom.web.util.Code;
import com.leafcom.web.vo.AddressVO;
import com.leafcom.web.vo.CartVO;
import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.MemberVO;
import com.leafcom.web.vo.OrderVO;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO dao;
	
	@Autowired
	CommonDAO codao;

	// 장바구니 구매 페이지 리스트 요청
	@Override
	public void buyInCartInfo (HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		String[] strArrCaId = req.getParameterValues("caIdArray")==null 
				? new String[1] : req.getParameterValues("caIdArray");
		System.out.println("strArrCaId" + Arrays.toString(strArrCaId));
		// 무조건 공백을 반영하는 0번 index의 값을 0으로 바꾸고
		strArrCaId[0] = "0";
		// 0번 인덱스의 값을 제외한 채 String배열을 int배열로 바꾸는 스트림
		int[] arrCaId = Arrays.stream(strArrCaId).skip(1).mapToInt(Integer::parseInt).toArray();
		System.out.println("arrCaId" + Arrays.toString(arrCaId));
		
		AddressVO aVo = dao.getPrimaryAddressInfo(meId);
		List<CartVO> coList = new ArrayList<CartVO>();
		
		for (int caId: arrCaId) {
			// 각각의 ca_id에 해당하는 장바구니 정보를 가져옴
			CartVO cVo = codao.getCartInfo(caId);
			ItemVO iVo = codao.getItemDetail(cVo.getItId());
			cVo.setItName(iVo.getItemName());
			cVo.setSmallImg(iVo.getSmallImg());
			cVo.setPrice(iVo.getPrice());
			coList.add(cVo);
		}
		
		model.addAttribute("coList", coList);
		model.addAttribute("aVo", aVo);
	}
	
	// 장바구니 구매 처리
	@Override
	public void buyInCart(HttpServletRequest req, Model model) {
		String[] strArrCaId = req.getParameterValues("caIdArray")==null 
				? new String[1] : req.getParameterValues("caIdArray") ;
		System.out.println("strArrCaId" + Arrays.toString(strArrCaId));
		int[] arrCaId = Arrays.stream(strArrCaId).mapToInt(Integer::parseInt).toArray(); 
		System.out.println("arrCaId" + Arrays.toString(arrCaId));
		int adId = Integer.parseInt(req.getParameter("adId"));
		int insertCnt = 0;
		int deleteCnt = 0;
		
		for (int caId: arrCaId) {
			// 각각의 ca_id에 해당하는 장바구니 정보를 가져옴
			CartVO cVo = codao.getCartInfo(caId);
			ItemVO iVo = codao.getItemDetail(cVo.getItId());
			if (iVo.getStock()>cVo.getAmount()) {
				// 주문VO에 대한 새 인스턴스 생성, 장바구니 정보 이식
				OrderVO oVo = new OrderVO();
				oVo.setItId(cVo.getItId());
				oVo.setMeId(cVo.getMeId());
				oVo.setAdId(adId);
				oVo.setRegDate(new Timestamp(System.currentTimeMillis()));
				oVo.setQuantity(cVo.getAmount());
				oVo.setCondition(Code.PURCHASE_REQUEST);
				insertCnt += dao.insertOrder(oVo);
				deleteCnt += codao.deleteCart(caId);
			} else {
				System.out.println("상품번호  " + cVo.getItId() + "재고부족");
			}
		}
		model.addAttribute("insertCnt", insertCnt);
	}

	// 바로구매 페이지 요청
	@Override
	public void buyNowInfo(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		int itId = Integer.parseInt(req.getParameter("itemId"));
		int quantity = Integer.parseInt(req.getParameter("amount"));
		
		AddressVO aVo = dao.getPrimaryAddressInfo(meId);
		
		ItemVO iVo = codao.getItemDetail(itId);
		
		model.addAttribute("iVo", iVo);
		model.addAttribute("quantity", quantity);
		model.addAttribute("aVo", aVo);
	}
	
	// 바로구매 처리
	@Override
	public void buyNow(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		int adId = Integer.parseInt(req.getParameter("adId"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		int itId = Integer.parseInt(req.getParameter("itId"));
		ItemVO iVo = codao.getItemDetail(itId);
		int insertCnt = 0;
		int reduceCnt = 0;
		
		if (iVo.getStock()>amount) {
			// 주문VO에 대한 새 인스턴스 생성, 장바구니 정보 이식
			OrderVO oVo = new OrderVO();
			oVo.setItId(iVo.getItemId());
			oVo.setMeId(meId);
			oVo.setAdId(adId);
			oVo.setRegDate(new Timestamp(System.currentTimeMillis()));
			oVo.setQuantity(amount);
			oVo.setCondition(Code.PURCHASE_REQUEST);
			insertCnt = dao.insertOrder(oVo);
		} else {
			System.out.println("상품번호  " + itId + "재고부족");
		}
		System.out.println("수량 변경 여부:" + reduceCnt);
		model.addAttribute("insertCnt", insertCnt);
	}
	
	// 배송지 가져오기
	@Override
	public void addressList(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		
	 	List<AddressVO> addressList = dao.addressList(meId);
	 	model.addAttribute("addressList", addressList);
	}
	
	// 배송지 추가
	@Override
	public void addAddress(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		String recipient = req.getParameter("recipient");
		String tel = req.getParameter("tel");
		int zipcode = Integer.parseInt(req.getParameter("zipcode"));
		String main = req.getParameter("main");
		String detail = req.getParameter("detail");
		int condition = Integer.parseInt(req.getParameter("condition"));
		
		AddressVO aVo = new AddressVO();
		aVo.setAdId(dao.addressSeq());
		aVo.setMeId(meId);
		aVo.setRecipient(recipient);
		aVo.setTel(tel);
		aVo.setZipcode(zipcode);
		aVo.setMain(main);
		aVo.setDetail(detail);
		aVo.setCondition(condition);
		
		int updateCnt = 0;
		
		int insertCnt = dao.insertAddress(aVo);
		 
		if (dao.addressList(meId)!=null) {
			// 입력한 상태가 메인 주소일 경우 나머지 주소들 일반 주소로 변경
			if (condition==Code.MAIN_ADDRESS) {
	
				List<AddressVO> addressList = dao.addressList(meId);
				Iterator<AddressVO> itr = addressList.iterator();
				while (itr.hasNext()) {
					AddressVO iaVo = itr.next();
					if (iaVo.getAdId()!=aVo.getAdId())
					iaVo.setCondition(Code.NORMAL_ADDRESS);
					updateCnt += dao.updateAddress(iaVo);
				}
			}
		}
		System.out.println("갱신된 항목: " + updateCnt);
		model.addAttribute("insertCnt", insertCnt);
	}
	
	// 배송지 상세정보
	@Override
	public void addressInfo(HttpServletRequest req, Model model) {
		int adId = Integer.parseInt(req.getParameter("adId"));
		
		AddressVO aVo = dao.getAddressInfo(adId);
		
		model.addAttribute("dto", aVo);
	}
	
	// 배송지 수정
	@Override
	public void updateAddress(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		int adId = Integer.parseInt(req.getParameter("adId"));
		String recipient = req.getParameter("recipient");
		String tel = req.getParameter("tel");
		int zipcode = Integer.parseInt(req.getParameter("zipcode"));
		String main = req.getParameter("main");
		String detail = req.getParameter("detail");
		int condition = req.getParameter("condition")==null ? 0 : Integer.parseInt(req.getParameter("condition"));
		
		AddressVO aVo = new AddressVO();
		aVo.setAdId(adId);
		aVo.setMeId(meId);
		aVo.setRecipient(recipient);
		aVo.setTel(tel);
		aVo.setZipcode(zipcode);
		aVo.setMain(main);
		aVo.setDetail(detail);
		aVo.setCondition(condition);
		
		int conCnt = 0;
		
		int updateCnt = dao.updateAddress(aVo);
		
		if (dao.addressList(meId)!=null) {
			if (condition==Code.MAIN_ADDRESS) {
				List<AddressVO> addressList = dao.addressList(meId);
				Iterator<AddressVO> itr = addressList.iterator();
				while (itr.hasNext()) {
					AddressVO iaVo = itr.next();
					if (iaVo.getAdId()!=adId)
					iaVo.setCondition(Code.NORMAL_ADDRESS);
					conCnt += dao.updateAddress(iaVo);
				}
			}
		}	
		System.out.println("상태 변경된 주소 수: " + conCnt);
		model.addAttribute("updateCnt", updateCnt);
	}
	
	// 주소록 삭제
	@Override
	public void deleteAddress(HttpServletRequest req, Model model) {
		int adId = Integer.parseInt(req.getParameter("adId"));
		int deleteCnt = dao.deleteAddress(adId);
		
		model.addAttribute("deleteCnt", deleteCnt);
	}
	
	// 주문 목록
	@Override
	public void orderList(HttpServletRequest req, Model model) {
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		
		OrderVO oVo = new OrderVO();
		Map<Integer,String> odConMap = oVo.getOdConMap();
		List<OrderVO> orderList = dao.orderList(meId);
		model.addAttribute("orderList", orderList);
		model.addAttribute("odConMap", odConMap);
	}
	
	// 주문상태 변경(교환요청, 환불요청, 구매확정)
	@Override
	public void updateOrder(HttpServletRequest req, Model model) {
		int odId = Integer.parseInt(req.getParameter("odId"));
		int condition = Integer.parseInt(req.getParameter("condition"));
		
		int increaseCnt = dao.updateOrder(odId, condition);
		
		System.out.println("수량 변경: " + increaseCnt);
	}

}
