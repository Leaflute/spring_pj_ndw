package com.leafcom.web.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.MemberVO;

@Repository
public class CommonDAOImpl implements CommonDAO {
	
	@Autowired
	SqlSession sqlSession;
	
	// 아이디 존재여부 확인
	@Override
	public int idChk(String strId) {
		int selectCnt = sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.idChk", strId);
		return selectCnt;
	}
	
	// 멤버 정보 반환
	@Override
	public MemberVO memberInfo(String id) {
		MemberVO mVo = sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.memberInfo",id);
		return mVo;
	}
	
	// 멤버 정보 삽입
	@Override
	public int insertMember(MemberVO mVo) {
		int insertCnt = sqlSession.insert("com.leafcom.web.dao.CommonDAO.insertMember", mVo);
		return insertCnt;
	}
	
	// 멤버 Enabled 활성화
	@Override
	public int updateMemberEnabled(String id) {
		int updateCnt = sqlSession.update("com.leafcom.web.dao.CommonDAO.updateMemberEnabled",id);
		return updateCnt;
	}

	@Override
	public int withrawMember(String strId) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// 장바구니
	
	// 장바구니
	
	// 장바구니
	
	// 장바구니


}
