package com.leafcom.web.dao;

import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.MemberVO;

@Repository
public class CommonDAOImpl implements CommonDAO {

	@Override
	public int idPwChk(String strId, String strPw) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idDupChk(String strId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public MemberVO getMemberInfo(String strId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int idKeyChk(String id, String key) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateCondition(String id, int condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int withrawMember(String strId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateMember(MemberVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
