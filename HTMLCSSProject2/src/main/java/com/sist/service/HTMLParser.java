package com.sist.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.StringTokenizer;
public class HTMLParser {

	
	public void htmlGetData() {
		try {
			/*
			 	connect() -> url을 이용
			 	parse() -> file을 이용
			 	-------------------------open Api
			 */
			Document doc=Jsoup.parse(new File("C:\\webDev\\WebStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_2.html"));
			// parse : 파일 읽어오는 것
			//System.out.println(doc.toString());
//			Elements h1=doc.select("div.b h1"); // 1개 일 때 : element / 여러개일 때 : elements
//												//			  (상세보기)           	 (목록)
//			System.out.println(h1.toString()); // h1태그값 가져오기 (<h1 class="f1">홍길동</h1>...<h1 class="f5">바나나</h1>)
//			System.out.println(h1.size()); // h1태그 개수
//			for(int i=0;i<h1.size();i++) {
//				System.out.println(h1.get(i).text()); // 태그사이의 값 가져오기(홍길동...바나나) 221220 10:25
//			}
			Element name=doc.select("div.a h1").get(2); //세번째 위치(0번부터시작 0 1 2 ...)
			System.out.println(name.text());
			Element name1=doc.select("div.b h1").get(3); //세번째 위치(0번부터시작 0 1 2 ...)
			System.out.println(name1.text());
		}catch(Exception ex) {}
	}
	// 구분이 없는 경우 : <table> => <td>,<ul> => <li> <dl>..
	public void foodDetailData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\webDev\\WebStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_3.html"));
//			System.out.println(doc.toString());
			Elements detail=doc.select("table.info tbody tr th");
			String address="", tel="",type="",price="",parking="",time="",menu=""; // => 초기값을 공백으로 줘서 값이 없을 땐 공백을 출력하도록
			for(int i=0;i<detail.size();i++) {
				//System.out.println(detail.get(i).text());
				/*
				 	주소
					전화번호
					음식 종류
					가격대
					주차
					영업시간
					휴일
					웹 사이트
					메뉴
				 */
				String label=detail.get(i).text(); // 태그값을 구분할 수 없는 경우 라벨로 구분
				if(label.equals("주소")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					address=a.text();
				}
				else if(label.equals("전화번호")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					tel=a.text();
				}
				else if(label.equals("음식 종류")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					type=a.text();
				}
				else if(label.equals("가격대")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					price=a.text();
				}
				else if(label.equals("주차")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					parking=a.text();
				}
				else if(label.equals("영업시간")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					time=a.text();
				}
				else if(label.equals("휴일")) {
					Element a=doc.select("table.info tbody tr td").get(i);
				}
				else if(label.equals("웹 사이트")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					
				}
				else if(label.equals("메뉴")) {
					Element a=doc.select("table.info tbody tr td").get(i);
					menu=a.text();
				}
			}
			String addr1=address.substring(0,address.indexOf("지"));
			String addr2=address.substring(address.indexOf("지")+3);
			
			System.out.println("주소:"+addr1);
			System.out.println("지번:"+addr2);
			
			System.out.println("전화:"+tel);
			System.out.println("음식 종류:"+type);
			System.out.println("가격대:"+price);
			System.out.println("시간:"+time);
			System.out.println("주차:"+parking);
			System.out.println("메뉴:"+menu);
			StringTokenizer st=new StringTokenizer(menu,"원");
			while(st.hasMoreTokens()) {
				System.out.println(st.nextToken()+"원");
			}
		}catch(Exception ex) {}
	}
	
	// 속성값 읽기 <img src="이미지명"> doc.select("~~img") => attr("src")
	public void foodAttributeData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\webDev\\WebStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_4_속성값.html"));
			Elements image=doc.select("div.list-photo_wrap img.center-croping");
			System.out.println(image.toString());
			for(int i=0;i<image.size();i++) {
				System.out.println(image.get(i).attr("src"));
			}
		}catch(Exception ex) {}
	}
	
	
	// html 읽기
	/*
	 	<div class="a">
	 		<span>Hello</span>
	 		<span>
	 			<p>html/css</p>
	 		</span>
	 	</div>
	 	
	 	doc.select("div.a") => text() => hello html/css
	 	doc.select("div.a") => html() => <span>
	 										<p>html/css</p>
	 									 </span>
	 */
	
	public void htmlData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\webDev\\WebStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_5.html"));
			Element div=doc.selectFirst("div.a");
			System.out.println(div.text());
			System.out.println(div.html());
		}catch(Exception ex	) {}
	}
	
	
	// 자바스크립트
	
	public void scriptData() {
		try {
			Document doc=Jsoup.parse(new File("C:\\webDev\\WebStudy\\HTMLCSSProject2\\src\\main\\webapp\\css\\css_6.html"));
			Element script=doc.selectFirst("script#reviewCountInfo");
			System.out.println(script.data()); //script는 text가 아니고data
			/*
			 * text()
			 * attr()
			 * html()
			 * data()
			 */
			// [{"action_value":1,"count":0},{"action_value":2,"count":0},{"action_value":3,"count":29}] => JSON
			String s=script.data();
			JSONParser jp=new JSONParser();
			/*
			 	html 데이터 추출 => htmlparser => jsoup
			 	json 데이터 추출 => jsonparser (ajax, vue, react)
			 		=> arraylist : [] - jsonarray
			 		=> ~vo : {} -jsonobject
			 	xml 데이터 추출 => documentBuilder (spring,mybatis)
			 */
			JSONArray arr=(JSONArray)jp.parse(s);
			System.out.println(arr.toString());
			for(int i=0;i<arr.size();i++) {
				JSONObject obj=(JSONObject)arr.get(i);
				if(i==0) {
					System.out.print("별로("+obj.get("count")+") ");
				}
				else if(i==1) {
					System.out.print("괜찮다("+obj.get("count")+") ");
				}
				else if(i==2) {
					System.out.print("맛있다("+obj.get("count")+") ");
				}
			}
		}catch(Exception ex) {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HTMLParser hp=new HTMLParser();
		//hp.htmlGetData();
		//hp.foodDetailData();
		//hp.foodAttributeData();
		//hp.htmlData();
		hp.scriptData();
	}

}
