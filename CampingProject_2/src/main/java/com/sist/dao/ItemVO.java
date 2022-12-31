package com.sist.dao;
/*
이름             널?       유형            
-------------- -------- ------------- 
INO            NOT NULL NUMBER        
NAME           NOT NULL VARCHAR2(300) 
IMAGE          NOT NULL VARCHAR2(260) 
PRICE          NOT NULL NUMBER        
DESCRIPTION    NOT NULL CLOB          
STOCK                   NUMBER        
STATUS         NOT NULL VARCHAR2(200) 
DISCOUNT                NUMBER        
DELIVERY_PRICE NOT NULL NUMBER        
LIKE_CNT                NUMBER        
JJIM_CNT                NUMBER        
ICNO                    NUMBER
 */

public class ItemVO {
	private int ino,icno;
	private String name,image,description,status, price,discount;
	private int stock=0,like_cnt=0,jjim_cnt=0;
	private String delivery_price="3000원(50,000원 이상 무료)";
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	
	
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(String delivery_price) {
		this.delivery_price = delivery_price;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getJjim_cnt() {
		return jjim_cnt;
	}
	public void setJjim_cnt(int jjim_cnt) {
		this.jjim_cnt = jjim_cnt;
	}
	public int getIcno() {
		return icno;
	}
	public void setIcno(int icno) {
		this.icno = icno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
