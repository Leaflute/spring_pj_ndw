package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.OrderVO;
import com.leafcom.web.vo.ReportVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Override
	public int getItemCnt(int categoryId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public HashMap<Integer, String> getCategoryName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<ItemVO> getItemList(int start, int end, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemVO getItemDetail(int itemId, int categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertItem(ItemVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateItem(ItemVO vo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteItem(int itemId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOrderCnt(int condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderVO> orderList(int start, int end, int condition) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOrder(int odId, int condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stockReduce(int itId, int quantity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int stockIncrease(int itId, int quantity) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderVO orderInfo(int odId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ReportVO> fiveDayReport() {
		// TODO Auto-generated method stub
		return null;
	}

}
