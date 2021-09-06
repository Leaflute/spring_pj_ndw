package com.leafcom.web.service;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import com.leafcom.web.vo.MemberVO;

public class UserLoginFailureHandler implements AuthenticationFailureHandler{

	@Autowired
	SqlSessionTemplate sqlSession;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	public UserLoginFailureHandler(SqlSessionTemplate sqlSession, BCryptPasswordEncoder passwordEncoder ) {
		this.sqlSession = sqlSession;
		this.passwordEncoder = passwordEncoder;
	}
	
	// 로그인이 실패할 경우 자동으로 실행되는 코드 
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
	
		System.out.println("실패 ");
		String strId = request.getParameter("id");
		String strPw = request.getParameter("pw");
		
		int cnt = sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.idChk",strId);
		if(cnt!=0) {
			
			MemberVO mVo = sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.memberInfo",strId);
			String pw = mVo.getPw();
			System.out.println(strPw);
			System.out.println(pw);
			System.out.println(passwordEncoder.matches(strPw, pw));
			if(passwordEncoder.matches(strPw, pw)) {
				request.setAttribute("errMsg", "이메일 인증하세요.");
			}else {
				request.setAttribute("errMsg", "비밀번호가 일치하지 않습니다.");
			}
			
		}else {
			request.setAttribute("errMsg", "아이디가 존재하지 않습니다.");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/common/login/login.jsp");
		rd.forward(request, response);
	}

}
