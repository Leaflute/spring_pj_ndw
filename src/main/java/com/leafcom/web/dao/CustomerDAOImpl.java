package com.leafcom.web.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.leafcom.web.vo.AddressVO;
import com.leafcom.web.vo.CartVO;
import com.leafcom.web.vo.ItemVO;
import com.leafcom.web.vo.OrderVO;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

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
	public CartVO getCartInfo2(int itId, String meId) {
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
