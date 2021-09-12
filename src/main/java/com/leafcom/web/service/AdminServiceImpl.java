package com.leafcom.web.service;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import com.leafcom.web.dao.AdminDAOImpl;
import com.leafcom.web.util.Code;
import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.OrderVO;
import com.leafcom.web.vo.ReportVO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	AdminDAOImpl dao;

	private static final String UPLOADED_IMG_PATH = 
		"D:\\Dev_88\\workspace\\spring_pj_ndw\\resources\\";
	
	// 상품 리스트
	@Override
	public void itemList(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][itemList()]");
		int categoryId = 0;
		
		if (req.getParameter("categoryId")!=null) {
			categoryId = Integer.parseInt(req.getParameter("categoryId"));
		}
		System.out.println("categoryId: " + categoryId);
			
		int pageSize = 5; 		// 한 페이지당 출력할 글 개수
		int pageBlock = 5;		// 한 블럭당 페이지 개수
		
		int cnt = 0;			// 게시글 개수
		int start = 0;			// 현재 페이지 시작 게시글
		int end = 0;			// 현제 페이지 마지막 게시글
		int number = 0; 		// 출력용 글 번호
		String pageNum = ""; 	// 페이지 번호
		int currentPage = 0;	// 현재 페이지
 
		int pageCount = 0;		// 페이지 개수
		int startPage = 0; 		// 시작페이지
		int endPage = 0;		// 마지막 페이지
		
		cnt = dao.getItemCnt(categoryId);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";		// 첫 페이지를 1페이지로 지정
		}
		
		// 상품 30건 기준
		currentPage = Integer.parseInt(pageNum);
		System.out.println("currentPage: " + currentPage);
		
		// 페이지 개수 6 = (30 / 5) + (0) : 나머지가 있으면 1페이지 추가
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		// 현재 페이지의 시작 글 번호(페이지별)
		// start = (currentPage - 1) * pageSize + 1;
		start = (currentPage - 1) * pageSize + 1;
		
		// 현재 페이지의 마지막 글 번호(페이지별)
		// end = start + pageSize - 1;
		end = start + pageSize - 1;
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		
		// 출력용 글 번호
		// number = cnt - (currentPage - 1) * pageSize;
		number = cnt - (currentPage - 1) * pageSize;
		
		System.out.println("number:" + number);
		
		// 시작 페이지
		// startPage = (currentPage / pageBlock) * pageBlock + 1;
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if (currentPage % pageBlock == 0) startPage -= pageBlock;
		System.out.println("startPage: " + startPage);
		
		// 마지막 페이지
		endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
		
		System.out.println("endPage: " + endPage);
		System.out.println("===================");
		
		List<ItemVO> itemDtos= null;
		ItemVO iVo = new ItemVO();
		Map<Integer, String> categoryMap = iVo.getCgMap();
		
		if(cnt > 0) {
			itemDtos = dao.getItemList(start, end, categoryId);
		}
		
		model.addAttribute("itemDtos", itemDtos);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("cnt", cnt);		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}
	
	// 상품 상세 정보
	@Override
	public void itemDetail(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][itemDetail()]");
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		ItemVO vo = dao.getItemDetail(itemId);
		
		model.addAttribute("dto", vo);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
	}
	
	// 카테고리 맵 가져오기
	@Override
	public void categoryMap(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][categoryMap()]");
		ItemVO iVo = new ItemVO();
		Map<Integer, String> categoryMap = iVo.getCgMap();
		model.addAttribute("categoryMap", categoryMap);
	}
	
	// 상품 추가 처리
	@Override
	public void addItem(MultipartHttpServletRequest mReq, Model model) {
		System.out.println("[ad][service][addItem()]");
		int categoryId = Integer.parseInt(mReq.getParameter("categoryId"));
		
		ItemVO iVo = new ItemVO();
		Map<Integer, String> categoryMap = iVo.getCgMap();
		String categoryName = categoryMap.get(categoryId);
		
		String itemName = mReq.getParameter("itemName");
		String company = mReq.getParameter("company");
		// sql에 저장할 이미지 경로 = "/플젝명/updload/" + 이미지 핸들러에서 보낸 속성값("fileName");
		
		String saveDir = mReq.getRealPath("/resources/uploaded");
		
		MultipartFile sImg = mReq.getFile("smallImg");
		
		String info = mReq.getParameter("info");
		int stock = Integer.parseInt(mReq.getParameter("stock"));
		int cost = Integer.parseInt(mReq.getParameter("cost"));
		int price = Integer.parseInt(mReq.getParameter("price"));
		
		ItemVO vo = new ItemVO();
		vo.setCategoryId(categoryId);
		vo.setCategoryName(categoryName);
		vo.setItemName(itemName);
		vo.setCompany(company);
		vo.setSmallImg(smallImg);
		vo.setLargeImg(largeImg);
		vo.setDetailImg(detailImg);
		vo.setRegDate(new Timestamp(System.currentTimeMillis()));
		vo.setInfo(info);
		vo.setStock(stock);
		vo.setCost(cost);
		vo.setPrice(price);
		vo.setGrade(0);
		
		int insertCnt = dao.insertItem(vo);
		
		model.addAttribute("insertCnt",insertCnt);
	}
	
	// 상품 수정 처리
	@Override
	public void updateItem(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][updateItem()]");
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		
		ItemVO iVo = new ItemVO();
		Map<Integer, String> categoryMap = iVo.getCgMap();
		String categoryName = categoryMap.get(categoryId);
		
		String itemName = req.getParameter("itemName");
		String company = req.getParameter("company");
		
		String smallImg = "";
		String largeImg = "";
		String detailImg = "";
		if((String)req.getAttribute("fileName")==null) {
			smallImg = req.getParameter("originalSmallImg");
		} else {
			smallImg = "/jsp_pj_ndw/asset/uploaded/" + (String)req.getAttribute("fileName");
		}
		
		if((String)req.getAttribute("fileName")==null) {
			largeImg = req.getParameter("originalLargeImg");
		} else {
			largeImg = "/jsp_pj_ndw/asset/uploaded/" + (String)req.getAttribute("fileName");
		}
		
		if((String)req.getAttribute("fileName")==null) {
			largeImg = req.getParameter("originalLargeImg");
		} else {
			largeImg = "/jsp_pj_ndw/asset/uploaded/" + (String)req.getAttribute("fileName");
		}
		
		String info = req.getParameter("info");
		int stock = Integer.parseInt(req.getParameter("stock"));
		int cost = Integer.parseInt(req.getParameter("cost"));
		int price = Integer.parseInt(req.getParameter("price"));
		double grade = Double.parseDouble(req.getParameter("grade"));
		Timestamp regdate = Timestamp.valueOf(req.getParameter("regDate"));
		
		ItemVO vo = new ItemVO();
		vo.setItemId(itemId);
		vo.setCategoryId(categoryId);
		vo.setCategoryName(categoryName);
		vo.setItemName(itemName);
		vo.setCompany(company);
		vo.setSmallImg(smallImg);
		vo.setLargeImg(largeImg);
		vo.setDetailImg(detailImg);
		vo.setRegDate(regdate);
		vo.setInfo(info);
		vo.setStock(stock);
		vo.setCost(cost);
		vo.setPrice(price);
		vo.setGrade(grade);
		
		int updateCnt = dao.updateItem(vo);
		
		model.addAttribute("updateCnt",updateCnt);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
	}
	
	// 상품 삭제 처리
	@Override
	public void deleteItem(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][deleteItem()]");
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int categoryId = Integer.parseInt(req.getParameter("categoryId"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		dao.deleteItem(itemId);
		
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
	}
	
	// 주문 목록
	@Override
	public void orderList(HttpServletRequest req, Model model) {
		int pageSize = 8; 		// 한 페이지당 출력할 글 개수
		int pageBlock = 5;		// 한 블럭당 페이지 개수
		
		int cnt = 0;			// 게시글 개수
		int start = 0;			// 현재 페이지 시작 게시글
		int end = 0;			// 현제 페이지 마지막 게시글
		int number = 0; 		// 출력용 글 번호
		String pageNum = ""; 	// 페이지 번호
		int currentPage = 0;	// 현재 페이지
 
		int pageCount = 0;		// 페이지 개수
		int startPage = 0; 		// 시작페이지
		int endPage = 0;		// 마지막 페이지
		
		int condition = req.getParameter("condition")!=null 
				? Integer.parseInt(req.getParameter("condition")) : 0;
		
		cnt = dao.getOrderCnt(condition);
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";		// 첫 페이지를 1페이지로 지정
		}
		
		// 상품 30건 기준
		currentPage = Integer.parseInt(pageNum);
		System.out.println("currentPage: " + currentPage);
		
		// 페이지 개수 6 = (30 / 5) + (0) : 나머지가 있으면 1페이지 추가
		pageCount = (cnt / pageSize) + (cnt % pageSize > 0 ? 1 : 0);
		
		// 현재 페이지의 시작 글 번호(페이지별)
		// start = (currentPage - 1) * pageSize + 1;
		start = (currentPage - 1) * pageSize + 1;
		
		// 현재 페이지의 마지막 글 번호(페이지별)
		// end = start + pageSize - 1;
		end = start + pageSize - 1;
		System.out.println("start: " + start);
		System.out.println("end: " + end);
		
		// 출력용 글 번호
		// number = cnt - (currentPage - 1) * pageSize;
		number = cnt - (currentPage - 1) * pageSize;
		
		System.out.println("number:" + number);
		
		// 시작 페이지
		// startPage = (currentPage / pageBlock) * pageBlock + 1;
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if (currentPage % pageBlock == 0) startPage -= pageBlock;
		System.out.println("startPage: " + startPage);
		
		// 마지막 페이지
		endPage = startPage + pageBlock - 1;
		if (endPage > pageCount) endPage = pageCount;
		
		System.out.println("endPage: " + endPage);
		System.out.println("===================");
		
		List<OrderVO> orderList = null;
		
		if(cnt > 0) {
			orderList = dao.orderList(start, end, condition);
		}
		OrderVO oVo = new OrderVO();
		Map<Integer, String> odConMap = oVo.getOdConMap();
		
		model.addAttribute("orderList", orderList);
		model.addAttribute("odConMap", odConMap);
		model.addAttribute("condition", condition);
		model.addAttribute("cnt", cnt);		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}
	
	// 주문 상태 변경(구매 승인, 배송중, 배송완료, 환불 승인)
	@Override
	public void updateOrder(HttpServletRequest req, Model model) {
		int odId = Integer.parseInt(req.getParameter("odId"));
		int condition = Integer.parseInt(req.getParameter("condition"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		OrderVO oVo = dao.orderInfo(odId);
		
		switch (condition) {
			case Code.REFUND_COMPLETE :    
				dao.stockIncrease(oVo.getItId(), oVo.getQuantity());
				break;
			case Code.PURCHASE_APPROVAL: 
				dao.stockReduce(oVo.getItId(), oVo.getQuantity());
		}
		
		int updateCnt = dao.updateOrder(odId, condition);
		
		model.addAttribute("condition", condition);
		model.addAttribute("pageNum", pageNum);
	}
	
	// 결산 항목 호출
	@Override
	public void fiveDayReport(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][fiveDayReport()]");
		List<ReportVO> reportList = dao.fiveDayReport();
		int i = 0;
		Iterator<ReportVO> itr = reportList.iterator();
		while(itr.hasNext()) {
			ReportVO rVo = itr.next();
			System.out.println(rVo);
			String oDate = rVo.getoDate();
			// 날짜 쪼개서 배열로 담고
			String dateArr[] = oDate.split("-");
			System.out.println(dateArr);
			// 쪼갠걸 VO 바구니에 담고
			rVo.setYear(dateArr[0]);
			rVo.setMonth(dateArr[1]);
			rVo.setDay(dateArr[2]);
			// 리스트 갱신
			reportList.set(i, rVo);
			i++;
		}
		System.out.println(reportList);
		model.addAttribute("reportList", reportList);
	}
	
}
