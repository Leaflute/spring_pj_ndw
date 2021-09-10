package com.leafcom.web.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import com.leafcom.web.util.Code;

public class ItemVO implements Cloneable {
	


	private int itemId;
	private int categoryId;
	private String categoryName;
	private String itemName;
	private String company;
	private String smallImg;
	private String largeImg;
	private String detailImg;
	private Timestamp regDate;
	private String info;
	private int stock;
	private int cost;
	private int price;
	private double grade;
	private Map<Integer,String> cgMap;
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getSmallImg() {
		return smallImg;
	}

	public void setSmallImg(String smallImg) {
		this.smallImg = smallImg;
	}

	public String getLargeImg() {
		return largeImg;
	}

	public void setLargeImg(String largeImg) {
		this.largeImg = largeImg;
	}

	public String getDetailImg() {
		return detailImg;
	}

	public void setDetailImg(String detailImg) {
		this.detailImg = detailImg;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}
	
	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public double getGrade() {
		return grade;
	}

	public void setGrade(double grade) {
		this.grade = grade;
	}
	
	public Map<Integer, String> getCgMap() {
		this.cgMap = new HashMap<Integer,String>();
		cgMap.put(Code.CPU, "CPU");
		cgMap.put(Code.RAM, "RAM");
		cgMap.put(Code.MBD, "메인보드");
		cgMap.put(Code.GPU, "그래픽카드");
		cgMap.put(Code.PWS, "파워서플라이");
		cgMap.put(Code.SSD, "SSD");
		cgMap.put(Code.HDD, "HDD");
		cgMap.put(Code.CSE, "케이스");
		cgMap.put(Code.MNT, "모니터");
		return cgMap;
	}

}
