package com.sist.dao;
/*
 * => 기본은 데이터베이스 컬럼명 전체를 처리 => 추가가 가능 / Entity : 추가 불가능
 * 												  ------- 컬럼명이 동일해야한다
 	이름      널?       유형             
------- -------- -------------- 
NO      NOT NULL NUMBER         
NAME    NOT NULL VARCHAR2(34)   
SUBJECT NOT NULL VARCHAR2(4000) 
CONTENT NOT NULL CLOB           
PWD     NOT NULL VARCHAR2(10)   
REGDATE          DATE           
HIT              NUMBER 
 */

import java.util.*;
public class BoardVO {
	private int no, hit;
	private String name,subject,content,pwd,dbday;
	//										----- Date를 스트링으로 받을 것이기 때문에 변수 하나 추가(Entity는 추가 불가능)
	private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	
}
