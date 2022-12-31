package com.sist.dao;
/*
 	이름             널?       유형            
-------------- -------- ------------- 
INO            NOT NULL NUMBER        
IMAGE          NOT NULL VARCHAR2(260) 
NAME           NOT NULL VARCHAR2(300) 
PRICE          NOT NULL NUMBER        
DESCRIPTION    NOT NULL CLOB          
STOCK          NOT NULL NUMBER        
STATUS         NOT NULL VARCHAR2(200) 
DISCOUNT                NUMBER        
DELIVERY_PRICE NOT NULL NUMBER        
LIKE_CNT                NUMBER        
JJIM_CNT                NUMBER        
ICNO                    NUMBER
 */
public class ItemVO {
	private int ino,ilno,price;
	private String image,name;
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getIlno() {
		return ilno;
	}
	public void setIlno(int ilno) {
		this.ilno = ilno;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
