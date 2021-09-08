package com.leafcom.web.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import com.leafcom.web.dao.CommonDAO;
import com.leafcom.web.util.Code;
import com.leafcom.web.util.MailSendHandler;
import com.leafcom.web.vo.CartVO;
import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.MemberVO;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	// 회원가입 - 아이디 중복확인
	@Override
	public void idDupChk(HttpServletRequest req, Model model) {
		System.out.println("[co][service][idDupChk()]");
		// 3단계. 화면에서 입력받은 값을 추출
		String strId = req.getParameter("id");
		
		// 5단계. 중복확인 처리
		int selectCnt = dao.idChk(strId);
		
		// 6단계. jsp로 결과 전달(request로 처리결과 저장)
		req.setAttribute("selectCnt", selectCnt);
		req.setAttribute("id", strId);
	}
	
	// 회원가입 - DB INSERT
	@Override
	public void signUpAction(String id, String pw, String name, 
			String email, String mobile, String auth, Model model) {
		System.out.println("[co][service][signInActions()]");

		System.out.println("암호화 전의 비밀번호" + pw);
		
		//비밀번호 암호화
		String encryptPassword = passwordEncoder.encode(pw);
		
		System.out.println("암호화 후의 비밀번호" + encryptPassword);
		
		// 랜덤 key 생성
		StringBuffer temp = new StringBuffer();
		Random rd = new Random();

		for(int i=0; i<8; i++) {
			int rIndex = rd.nextInt(2);
			switch (rIndex) {
			case 0:
				temp.append((char)((int)(rd.nextInt(26))+65));
				break;
			case 1:
				temp.append((rd.nextInt(10)));
				break;
			}
		}
		
		String key = temp.toString();
		
		MemberVO mVo = new MemberVO();
		mVo.setId(id);
		mVo.setPw(encryptPassword);
		mVo.setName(name);
		mVo.setEmail(email);
		mVo.setMobile(mobile);
		mVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		mVo.setAuthority(auth);
		mVo.setEnabled(Code.NOT_ACTIVATED);
		mVo.setCondition(Code.NORMAL);
		mVo.setKey(key);
		
		int insertCnt = dao.insertMember(mVo);
		if(insertCnt==1) {
			MailSendHandler msh = new MailSendHandler();
			msh.sendActivationEmail(id, email, key);
		}
		
		model.addAttribute("insertCnt", insertCnt);
	}
	
	// 회원탈퇴 -> 비밀번호 재확인
//	@Override
//	public void withdrawMemAction(HttpServletRequest req, Model model) {
//		System.out.println("[co][service][withdrawMemAction()]");
//		MemberVO vo = (MemberVO) req.getSession().getAttribute("member");
//		String strId = vo.getId();
//		String strPw = req.getParameter("pw");
//		
//		int selectCnt = dao.idPwChk(strId, strPw);
//		int deleteCnt = 0;
//		
//		if (selectCnt==1) {
//			deleteCnt = dao.withrawMember(strId);
//		}
//		req.setAttribute("selectCnt", selectCnt);
//		req.setAttribute("deleteCnt", deleteCnt);
//	}
//
//	// 회원정보 조회
//	@Override
//	public void viewMemInfoAction(HttpServletRequest req, Model model) {
//		System.out.println("[co][service][viewMemInfoAction()]");
//		MemberVO vo = (MemberVO) req.getSession().getAttribute("member");
//		String strId = vo.getId();
//		String strPw = req.getParameter("pw");
//		
//		int selectCnt = dao.idPwChk(strId, strPw);
//		
//		if (selectCnt==1) {
//			vo = dao.getMemberInfo(strId);
//		}
//		req.setAttribute("selectCnt", selectCnt);
//		req.setAttribute("dto", vo);
//		
//	}
//	
//	// 회원정보 수정
//	@Override
//	public void updateMemInfoAction(HttpServletRequest req, Model model) {
//		System.out.println("[co][service][updateMemInfoAction()]");
//		MemberVO vo = (MemberVO) req.getSession().getAttribute("member");
//		
//		vo.setId(vo.getId());
//		vo.setPw(req.getParameter("pw"));
//		vo.setName(req.getParameter("name"));
//		vo.setEmail(req.getParameter("email"));
//		vo.setMobile(req.getParameter("mobile"));
//		
//		int updateCnt = dao.updateMember(vo);
//		
//		req.setAttribute("updateCnt", updateCnt);
//	}
//	
	// 아이디 권한 활성화
	@Override
	public void activateId(String id, String key, Model model) {
		System.out.println("[co][service][activateID()]");
		
		int selectCnt = dao.idChk(id);
		int updateCnt = 0;
		
		if(selectCnt==1) {
			MemberVO mVo = dao.memberInfo(id);
			if (key.equals(mVo.getKey())) {
				updateCnt = dao.updateMemberEnabled(id);
			}
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("updateCnt", updateCnt);
	}
	
	// -------------------------------------------------------------	
	
	// 상품 리스트 
	@Override
	public void itemList(HttpServletRequest req, Model model) {
		System.out.println("[cu][service][itemList()]");
		int categoryId = 0;
		
		if (req.getParameter("categoryId")!=null) {
			categoryId = Integer.parseInt(req.getParameter("categoryId"));
		}
		System.out.println("categoryId: " + categoryId);
		
		String categoryName = "";
			
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
		Map<Integer, String> categoryMap = dao.getCategoryMap();
		categoryName = dao.getCategoryName(categoryId);
		
		if(cnt > 0) {
			itemDtos = dao.getItemList(start, end, categoryId);
		}
		
		model.addAttribute("itemDtos", itemDtos);
		model.addAttribute("categoryMap", categoryMap);
		model.addAttribute("cnt", cnt);		
		model.addAttribute("number", number);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
		model.addAttribute("categoryName", categoryName);
		
		if (cnt > 0) {
			model.addAttribute("startPage", startPage);
			model.addAttribute("endPage", endPage);
			model.addAttribute("pageBlock", pageBlock);
			model.addAttribute("pageCount", pageCount);
			model.addAttribute("currentPage", currentPage);
		}
	}	
	
	// 카테고리 맵 가져오기
	@Override
	public void categoryMap(HttpServletRequest req, Model model) {
		System.out.println("[cu][service][categoryMap()]");
		Map<Integer, String> categoryMap = dao.getCategoryMap();
		model.addAttribute("categoryMap", categoryMap);
	}
		
	// 상품 상세 정보
	@Override
	public void itemDetail(HttpServletRequest req, Model model) {
		System.out.println("[ad][service][itemDetail()]");
		int categoryId = req.getParameter("categoryId")==null ? 1 : Integer.parseInt(req.getParameter("categoryId"));
		int itemId = Integer.parseInt(req.getParameter("itemId"));
		int pageNum = req.getParameter("pageNum")==null ? 1 : Integer.parseInt(req.getParameter("pageNum"));
		
		ItemVO vo = dao.getItemDetail(itemId);
		
		model.addAttribute("dto", vo);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("categoryId", categoryId);
		
	}
		
	// 장바구니 리스트 호출
	@Override
	public void cartList(HttpServletRequest req, Model model) {
		System.out.println("[cu][service][cartList()]");
		List<CartVO> cartList = null;
		String meId = "";
		
		// 로그인 세션의 존재유무 판단
		if(req.getSession().getAttribute("member")!=null) {
			MemberVO member = (MemberVO)req.getSession().getAttribute("member");
			System.out.println("id:" + member.getId());
			cartList = dao.cartList(member.getId());
			
		// 없으면 세션에 저장된 카트를 가져온다
		} else {
			cartList = (ArrayList<CartVO>)req.getSession().getAttribute("cartList");
			System.out.println(cartList);
		}
		
		model.addAttribute("cartList", cartList);
	}
		
	// 최초 로그인시 세션에 있는 장바구니 리스트를 DB에 추가
	@Override
	public void loginAddCart(HttpServletRequest req, Model model) {
		System.out.println("[cu][service][loginAddCart()]");
		List<CartVO> sCartList = (ArrayList<CartVO>)req.getSession().getAttribute("cartList");
		List<Integer> itemIdList = null;
		
		MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
		String meId = mVo.getId();
		int insertCnt = 0; 
		int updateCnt = 0;
		int amount = 0;
		int caId = 0;

		Iterator<CartVO> itr = sCartList.iterator();
		
		// 장바구니 리스트에 들어간 상품 리스트 목록이 있다면
		if (dao.cartList(meId)!=null) {
			// 회원의 장바구니 상품 목록을 가져와서
			itemIdList = dao.getItIdList(meId);
			System.out.println("itemIdList: " + itemIdList.toString());
		
			while (itr.hasNext()) {
				CartVO cVo = itr.next();
				cVo.setMeId(meId);
				// 해당 상품 id가 있는지 여부 판단
				if (itemIdList.contains(cVo.getItId())) {
					// DB에 저장되어있던 itId와 meId에 해당하는 수량
					amount = dao.getCartInfo2(cVo.getItId(),meId).getAmount();
					caId = dao.getCartInfo2(cVo.getItId(),meId).getCaId();
					// DB에 저장되어 있던 상품의 수량에 누적
					updateCnt += dao.updateCart(caId, amount+cVo.getAmount());
				} else {
					insertCnt += dao.insertCart(cVo);
				}
			}
			// 장바구니 리스트 세션 제거
			req.getSession().removeAttribute("cartList");
		
		// 회원 장바구니가 비어있을 때
		} else {
			while(itr.hasNext()) {
				CartVO cVo = itr.next();
				cVo.setMeId(meId);
				insertCnt += dao.insertCart(cVo);
			}
		}
		System.out.println("addCnt: " + insertCnt);
		System.out.println("updateCnt: " + updateCnt);
	}
		
	// 상세페이지나 리스트 페이지에서 버튼을 눌렀을 때 카트에 상품 추가
	@Override
	public void addCart(HttpServletRequest req, Model model) {
		System.out.println("[cu][service][addCart()]");
		int itId = req.getParameter("itemId")==null ? 0 : Integer.parseInt(req.getParameter("itemId"));
		int amount = req.getParameter("amount")==null ? 0 : Integer.parseInt(req.getParameter("amount"));
		
		int insertCnt=0;
		
		System.out.println("itid:" + itId);
		System.out.println("amount:" + amount);
 		List<CartVO> sessionCartList = null;
		
		itId = Integer.parseInt(req.getParameter("itemId"));
		// id를 매개변수로 cVo.set에 필요한 상품 정보 가져옴(iVo)
		ItemVO iVo = dao.getItemDetail(itId);
		int price = iVo.getPrice();
		String itName = iVo.getItemName();
		String smallImg = iVo.getSmallImg();
		
		// 세션 또는 DB에 올라갈 작은바구니 생성
		CartVO cVo = new CartVO();
		cVo.setRegDate(new Timestamp(System.currentTimeMillis()));
		cVo.setPrice(price);
		cVo.setItName(itName);
		cVo.setMeId("");
		cVo.setSmallImg(smallImg);
		cVo.setItId(itId);
		cVo.setAmount(amount);
		cVo.setCondition(0);	// 0: 일반 장바구니, 1: 견적서(미구현)
		System.out.println(cVo);
		
		// 세션에 멤버 정보가 존재할때 
		if(req.getSession().getAttribute("member")!=null) {
			System.out.println("로그인 중 - DB로감");
			MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
			String meId = mVo.getId();
			cVo.setMeId(meId);
			
			// 장바구니 리스트가 있을 때
			if (dao.cartList(meId)!=null) {
				List<CartVO> memberCartList = dao.cartList(meId);
				Iterator<CartVO> itr = memberCartList.iterator();
				while (itr.hasNext()) {
					CartVO icVo = itr.next();
					// itemId가 동일한 것이 있으면 수량 누적
					if (icVo.getItId()==itId) {
						dao.updateCart(icVo.getCaId(), icVo.getAmount()+amount);
						System.out.println("DB-상품수량바꿈");
						break;
					// 없으면 들어온 DB에 새 상품 추가
					} else {
						insertCnt = dao.insertCart(cVo);
						System.out.println("DB-새 상품추가");
						break;
					}
				}
			// 없을 때
			} else {
				insertCnt = dao.insertCart(cVo);
				System.out.println("DB-새 상품추가");
			}
			
		// 세션에 멤버 정보가 존재하지 않을 때(세션에 장바구니 리스트 저장)	
		} else {
			cVo.setCaId(dao.cartSeq());
			// 장바구니 세션이 없으면 장바구니 세션을 추가
			if(req.getSession().getAttribute("cartList")==null) {
				System.out.println("비회원 장바구니");
				sessionCartList = new ArrayList<CartVO>();
				
				// 세션 장바구니 리스트에 CartVO 정보를 누적
				sessionCartList.add(cVo);
				System.out.println(sessionCartList);
			
			// 장바구니 세션이 존재할 때
			} else {
				System.out.println("비회원 장바구니 누적");
				sessionCartList = (ArrayList<CartVO>)req.getSession().getAttribute("cartList");
				Iterator<CartVO> itr = sessionCartList.iterator();
				while (itr.hasNext()) {
					CartVO icVo = itr.next();
					// itemId가 동일한 것이 있으면 수량 누적
					if (icVo.getItId()==itId) {
						icVo.setAmount(amount+icVo.getAmount());
						System.out.println("세션-상품수량바꿈");
						break;
					// 없으면 들어온 수량값을 장바구니 VO에 설정
					} else {
						sessionCartList.add(cVo);
						System.out.println("세션-새 상품추가");
						break;
					}
				}
			}
			req.getSession().setAttribute("cartList", sessionCartList);
		}
	}
		
	// 수량 변경시
	@Override
	public void updateCart(HttpServletRequest req, Model model) {
		int caId = Integer.parseInt(req.getParameter("caId"));
		int amount = Integer.parseInt(req.getParameter("amount"));
		
		// 로그인 했을 때
		if (req.getSession().getAttribute("member")!=null) {
			MemberVO mVo = (MemberVO)req.getSession().getAttribute("member");
			String meId = mVo.getId();
			dao.updateCart(caId, amount);
		// 로그인 안 했을 때
		} else {
			List<CartVO> sessionCartList = (List<CartVO>)req.getSession().getAttribute("cartList");
			Iterator<CartVO> itr = sessionCartList.iterator();
			int index = 0;
			while (itr.hasNext()) {
				CartVO cVo = itr.next();
				if (cVo.getCaId()==caId) {
					cVo.setAmount(amount+cVo.getAmount());
					sessionCartList.set(index, cVo);
					break;
				}
				index++;
			}
		req.getSession().setAttribute("cartList", sessionCartList);	
		}
	}
		
	// 장바구니 삭제(개별 삭제)
	public void deleteCart(HttpServletRequest req, Model model) {
		int caId = Integer.parseInt(req.getParameter("caId"));
		
		if (req.getSession().getAttribute("member")!=null) {
			System.out.println("DB에서 삭제");
			dao.deleteCart(caId);
		} else {
			List<CartVO> sessionCartList = (List<CartVO>)req.getSession().getAttribute("cartList");
			Iterator<CartVO> itr = sessionCartList.iterator();
			while (itr.hasNext()) {
				CartVO cVo = itr.next();
				if(cVo.getCaId()==caId) {
					sessionCartList.remove(cVo);
					break;
				}
			}
		
			// 장바구니가 비었을 때 세션 삭제처리
			if (sessionCartList.isEmpty()) {
				req.getSession().removeAttribute("cartList");
			} else {
				req.getSession().setAttribute("cartList", sessionCartList);
			}
		
		}
	}
		
	// 장바구니 삭제(리스트 삭제)
	@Override
	public void deleteCartList(HttpServletRequest req, Model model) {
		String[] strArrCaId = req.getParameterValues("caIdArray")==null ? new String[1] : req.getParameterValues("caIdArray") ;
		System.out.println("strArrCaId" + Arrays.toString(strArrCaId));
		// 무조건 공백을 반영하는 0번 index의 값을 0으로 바꾸고
		strArrCaId[0] = "0";
		// 0번 인덱스의 값을 제외한 채 String배열을 int배열로 바꾸는 스트림
		int[] arrCaId = Arrays.stream(strArrCaId).skip(1).mapToInt(Integer::parseInt).toArray(); 
		System.out.println("arrCaId");
		
		// 로그인 했을 때
		if (req.getSession().getAttribute("member")!=null) {
			System.out.println("DB 상품 삭제");
			for(int caId : arrCaId) {
				dao.deleteCart(caId);
			}
			
		// 로그인 안 했을 때
		} else {
			System.out.println("세션 상품 삭제");
			List<CartVO> sessionCartList = (List<CartVO>)req.getSession().getAttribute("cartList");
			Iterator<CartVO> itr = sessionCartList.iterator();
			while (itr.hasNext()) {
				CartVO cVo = itr.next();
				if (Arrays.asList(arrCaId).contains(cVo.getCaId())) {
					sessionCartList.remove(cVo);
				}
			}
			
			if (sessionCartList.isEmpty()) {
				req.getSession().removeAttribute("cartList");
			} else {

				req.getSession().setAttribute("cartList", sessionCartList);
			}
		}
		
	}	
}
