package com.leafcom.web.service;

import java.sql.Timestamp;
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
import com.leafcom.web.vo.MemberVO;

@Service
public class CommonServiceImpl implements CommonService {
	
	@Autowired
	CommonDAO dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
//	// 로그인 처리
//	@Override
//	public void loginAction(HttpServletRequest req, Model model) {
//		System.out.println("[co][service][loginAction()]");
//		
//		String strId = req.getParameter("id");
//		String strPw = req.getParameter("pw");
//		
//		int selectCnt = dao.idChk(strId);
//		
//		MemberVO mVo = null;
//		
//		// 로그인 세션 추가
//		if(selectCnt>0) {
//			mVo = dao.memberInfo(strId);
//			String pw = mVo.getId();
//			if(strPw.equals(pw)) {
//			req.getSession().setAttribute("member", mVo);
//			System.out.println("role: " + mVo.getRole());
//		} else {
//			
//		}
//		
//		req.setAttribute("selectCnt", selectCnt);
//	}
	
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

}
