package com.sist.main;

import java.lang.reflect.Method;

class A{
	public void aaa() {
		System.out.println("aaa() Call...");
	}
}

public class MainClass {
	public static void main(String[] args) {
		A a = new A();
		a.aaa();
		
		try {
			Class clsName=Class.forName("com.sist.main.A");
			Object obj = clsName.getDeclaredConstructor().newInstance();
			// ===  A a = new A(); 와 같다
			Method[] methods = clsName.getDeclaredMethods();
			methods[0].invoke(obj,null);
			// === a.aaa();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
