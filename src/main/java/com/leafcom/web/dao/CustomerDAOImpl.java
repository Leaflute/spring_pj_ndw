package com.leafcom.web.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.AddressVO;
import com.leafcom.web.vo.OrderVO;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
	
	@Autowired
	SqlSession sqlSession;

	// 배송지 리스트
	@Override
	public List<AddressVO> addressList(String meId) {
		return sqlSession.selectList("com.leafcom.web.dao.CustomerDAO.addressList", meId);
	}
	
	// 배송지 추가
	@Override
	public int insertAddress(AddressVO aVo) {
		return sqlSession.insert("com.leafcom.web.dao.CustomerDAO.insertAddress", aVo);
	}
	
	// 배송지 시퀀스 반환
	@Override
	public int addressSeq() {
		return sqlSession.selectOne("com.leafcom.web.dao.CustomerDAO.addressSeq");
	}
	
	// 배송지 업데이트
	@Override
	public int updateAddress(AddressVO aVo) {
		return sqlSession.update("com.leafcom.web.dao.CustomerDAO.updateAddress",aVo);
	}
	
	// 배송지 삭제
	@Override
	public int deleteAddress(int adId) {
		return sqlSession.delete("com.leafcom.web.dao.CustomerDAO.deleteAddress",adId);
	}
	
	// 배송지 상세 정보
	@Override
	public AddressVO getAddressInfo(int adId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CustomerDAO.getAddressInfo",adId);
	}
	
	// 기본 배송지 정보
	@Override
	public AddressVO getPrimaryAddressInfo(String meId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CustomerDAO.getPrimaryAddressInfo",meId);
	}
	
	// 주문 리스트
	@Override
	public List<OrderVO> orderList(String meId) {
		return sqlSession.selectList("com.leafcom.web.dao.CustomerDAO.orderList", meId);
	}
	
	// 주문 상세
	@Override
	public OrderVO getOrderInfo(int odId) {
		return sqlSession.selectOne("com.leafcom.web.dao.CustomerDAO.updateOrder",odId);
	}
	
	// 주문하기
	@Override
	public int insertOrder(OrderVO oVo) {
		return sqlSession.insert("com.leafcom.web.dao.CustomerDAO.insertOrder",oVo);
	}
	
	// 주문 상태 변경
	@Override
	public int updateOrder(int odId, int condition) {
		return sqlSession.update("com.leafcom.web.dao.CustomerDAO.updateOrder");
	}


}
