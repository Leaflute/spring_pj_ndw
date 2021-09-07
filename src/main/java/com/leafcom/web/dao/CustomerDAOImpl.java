package com.leafcom.web.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.AddressVO;
import com.leafcom.web.vo.OrderVO;

@Repository
public class CustomerDAOImpl implements CustomerDAO {


	@Override
	public int insertOrder(OrderVO oVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertAddress(AddressVO aVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<AddressVO> addressList(String meId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int addressSeq() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateAddress(AddressVO aVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteAddress(int adId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrderVO> orderList(String meId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AddressVO getAddressInfo(int adId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public AddressVO getPrimaryAddressInfo(String meId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOrder(int odId, int condition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public OrderVO getOrderInfo(int odId) {
		// TODO Auto-generated method stub
		return null;
	}

}
