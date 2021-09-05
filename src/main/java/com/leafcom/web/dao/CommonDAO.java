package com.leafcom.web.dao;

import com.leafcom.web.vo.MemberVO;

public interface CommonDAO {
	
	// 아이디 존재 여부 확인
	public int idChk(String strId);

	// 회원정보 조회
	public MemberVO memberInfo(String strId);
	
	// 회원정보 DB에 입력
	public int insertMember(MemberVO vo);
	
	// 회원 활성화 변경
	int updateMemberEnabled(String id);
	
	// 회원정보 DB로부터 삭제
	public int withrawMember(String strId);
	
}
