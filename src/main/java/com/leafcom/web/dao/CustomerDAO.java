package com.leafcom.web.dao;

import java.util.List;

import com.leafcom.web.vo.AddressVO;
import com.leafcom.web.vo.OrderVO;

public interface CustomerDAO {

	int insertOrder(OrderVO oVo);

	int insertAddress(AddressVO aVo);

	List<AddressVO> addressList(String meId);

	int addressSeq();

	int updateAddress(AddressVO aVo);

	int deleteAddress(int adId);

	List<OrderVO> orderList(String meId);

	AddressVO getAddressInfo(int adId);

	AddressVO getPrimaryAddressInfo(String meId);

	int updateOrder(int odId, int condition);

	OrderVO getOrderInfo(int odId);
	
}