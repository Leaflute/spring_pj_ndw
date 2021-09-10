package com.leafcom.web.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.OrderVO;
import com.leafcom.web.vo.ReportVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession sqlSession;
	
	@Override
	public int getItemCnt(int categoryId) {
		return sqlSession.selectOne("com.leafcom.web.dao.AdminDAO.getItemCnt", categoryId);
	}

	@Override
	public List<ItemVO> getItemList(int start, int end, int categoryId) {
		return sqlSession.selectList("com.leafcom.web.dao.AdminDAO.getItemCnt.getItemList");
	}

	@Override
	public ItemVO getItemDetail(int itemId, int categoryId) {
		return sqlSession.selectOne("com.leafcom.web.dao.AdminDAO.getItemCnt.getItemDetail");
	}

	@Override
	public int insertItem(ItemVO iVo) {
		return sqlSession.insert("com.leafcom.web.dao.AdminDAO.getItemCnt.insertItem",iVo);
	}

	@Override
	public int updateItem(ItemVO iVo) {
		return sqlSession.update("com.leafcom.web.dao.AdminDAO.getItemCnt.updateItem",iVo);
	}

	@Override
	public int deleteItem(int itemId) {
		return sqlSession.delete("com.leafcom.web.dao.AdminDAO.getItemCnt.deleteItem",itemId);
	}

	@Override
	public int getOrderCnt(int condition) {
		return sqlSession.selectOne("com.leafcom.web.dao.AdminDAO.getItemCnt.getOrderCnt",condition);
	}

	@Override
	public List<OrderVO> orderList(int start, int end, int condition) {
		return sqlSession.selectList("com.leafcom.web.dao.AdminDAO.getItemCnt.orderList");
	}

	@Override
	public int updateOrder(int odId, int condition) {
		return sqlSession.update("com.leafcom.web.dao.AdminDAO.getItemCnt.updateOrder");
	}

	@Override
	public int stockReduce(int itId, int quantity) {
		return sqlSession.update("com.leafcom.web.dao.AdminDAO.getItemCnt.stockReduce");
	}

	@Override
	public int stockIncrease(int itId, int quantity) {
		return sqlSession.update("com.leafcom.web.dao.AdminDAO.getItemCnt.stockIncrease");
	}

	@Override
	public OrderVO orderInfo(int odId) {
		return sqlSession.selectOne("com.leafcom.web.dao.AdminDAO.getItemCnt.orderInfo", odId);
	}

	@Override
	public List<ReportVO> fiveDayReport() {
		return sqlSession.selectList("com.leafcom.web.dao.AdminDAO.getItemCnt.orderInfo");
	}

}
