package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.leafcom.web.vo.CartVO;
import com.leafcom.web.vo.ItemVO;
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

	int getItemCnt(int categoryId);

	String getCategoryName(int categoryId);

	List<ItemVO> getItemList(@Param("start") int start, 
		@Param("end") int end, 
		@Param("categoryId") int categoryId);

	ItemVO getItemDetail(int itemId);

	List<CartVO> cartList(String meId);

	List<Integer> getItIdList(String meId);

	CartVO getCartInfo(int caId);

	int updateCart(@Param("caId") int caId, @Param("amount") int amount);

	int deleteCart(int caId);

	int getCartCnt(String meId);

	int cartSeq();

	int insertCart(CartVO vo);

	public CartVO getCartInfo2(@Param("itId") int itId, @Param("meId") String meId);

	public int updateMember(MemberVO vo);
	
}
