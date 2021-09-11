package com.leafcom.web.dao;

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
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.idChk", strId);
	}
	
	// 멤버 정보 반환
	@Override
	public MemberVO memberInfo(String id) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.memberInfo",id);
	}
	
	// 멤버 정보 삽입
	@Override
	public int insertMember(MemberVO mVo) {
		return sqlSession.insert("com.leafcom.web.dao.CommonDAO.insertMember", mVo);
	}
	
	// 멤버 Enabled 활성화
	@Override
	public int updateMemberEnabled(String id) {
		return sqlSession.update("com.leafcom.web.dao.CommonDAO.updateMemberEnabled",id);
	}

	@Override
	public int withrawMember(String id) {
		return sqlSession.delete("com.leafcom.web.dao.CommonDAO.withrawMember",id);
	}

	@Override
	public int updateMember(MemberVO vo) {
		return sqlSession.update("com.leafcom.web.dao.CommonDAO.updateMember",vo);
	}
	
	// 카테고리별 상품 수
	@Override
	public int getItemCnt(int categoryId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getItemCnt",categoryId);
	}
	
	// 카테고리 id별 카테고리 맵 반환
	@Override
	public String getCategoryName(int categoryId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getCategoryName", categoryId);
	}
	
	// 상품리스트 반환
	@Override
	public List<ItemVO> getItemList(int start, int end, int categoryId) {
		return sqlSession.selectList("com.leafcom.web.dao.CommonDAO.getItemList");
	}
	
	// 상품 상세정보 반환
	@Override
	public ItemVO getItemDetail(int itemId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getItemDetail",itemId);
	}
	
	// 장바구니 숫자 반환
	@Override
	public int getCartCnt(String meId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getCartCnt",meId);
	}
	
	// 장바구니 시퀀스 반환
	@Override
	public int cartSeq() {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.cartSeq");
	}

	// 카트 리스트
	@Override
	public List<CartVO> cartList(String meId) {
		return sqlSession.selectList("com.leafcom.web.dao.CommonDAO.cartList", meId);
	}

	@Override
	public List<Integer> getItIdList(String meId) {
		return sqlSession.selectList("com.leafcom.web.dao.CommonDAO.cartList", meId);
	}

	@Override
	public CartVO getCartInfo(int caId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getCartInfo", caId);
	}

	@Override
	public CartVO getCartInfo2(int itId, String meId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CommonDAO.getCartInfo2");
	}
	
	@Override
	public int updateCart(int caId, int amount) {
		return sqlSession.update("com.leafcom.web.dao.CommonDAO.updateCart");
	}

	@Override
	public int deleteCart(int caId) {
		return sqlSession.delete("com.leafcom.web.dao.CommonDAO.deleteCart",caId);
	}

	@Override
	public int insertCart(CartVO vo) {
		return sqlSession.insert("com.leafcom.web.dao.CommonDAO.getCartInfo.insertCart", vo);
	}




}
