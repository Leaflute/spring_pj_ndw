package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.CartVO;
import com.leafcom.web.vo.ItemVO;
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
	public int withrawMember(String id) {
		int deleteCnt = sqlSession.delete("com.leafcom.web.dao.CommonDAO.deleteMember",id);
		return deleteCnt;
	}
	
	// 카테고리별 상품 수
	@Override
	public int getItemCnt(int categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<Integer, String> getCategoryMap() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategoryName(int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ItemVO> getItemList(int start, int end, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemVO getItemDetail(int itemId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CartVO> cartList(String meId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getItIdList(String meId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartVO getCartInfo(int caId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CartVO getCartInfo2(int itId, String meId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int updateCart(int caId, int amount) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteCart(int caId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCartCnt(String meId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int cartSeq() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertCart(CartVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


	

}
