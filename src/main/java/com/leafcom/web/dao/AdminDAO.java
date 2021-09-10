package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.OrderVO;
import com.leafcom.web.vo.ReportVO;

public interface AdminDAO {
	
	// 전체 상품 리스트 개수 구하기
	public int getItemCnt(int categoryId);
	
	// 상품 목록 조회
	public List<ItemVO> getItemList(int start, int end, int categoryId);
	
	// 상품 상세 페이지
 	public ItemVO getItemDetail(@Param("itemId") int itemId, @Param("categoryId") int categoryId);
	
	// 상품 추가
	public int insertItem(ItemVO vo);
	
	// 상품 수정
	public int updateItem(ItemVO vo);
	
	// 상품 삭제
	public int deleteItem(int itemId);

	int getOrderCnt(int condition);

	List<OrderVO> orderList(@Param("start") int start, 
			@Param("end") int end, @Param("condition") int condition);

	int updateOrder(@Param("odId") int odId, @Param("condition") int condition);

	int stockReduce(@Param("itId") int itId, @Param("quantity") int quantity);

	int stockIncrease(@Param("itId") int itId, @Param("quantity") int quantity);

	OrderVO orderInfo(int odId);

	public List<ReportVO> fiveDayReport();
}
