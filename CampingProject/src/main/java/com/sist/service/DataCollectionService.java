package com.sist.service;
import com.sist.dao.*;
import java.util.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class DataCollectionService {
	public static void main(String[] args) {
		DataCollectionService ds=new DataCollectionService();
		//ds.itemDetailData();
		ds.itemAllData();
		
	}
	
	
	
	public void itemListGetData() {
		ItemDAO dao=new ItemDAO();
		try {
			Document doc=Jsoup.connect("https://campinglist.co.kr").get();
			
		}catch(Exception ex) {}
	}
	
	
	public void itemDetailData() {
		ItemDAO dao=new ItemDAO();
		try {
			for(int i=1;i<=5;i++) {
				Document doc=Jsoup.connect("https://campinglist.co.kr/product/%ED%85%90%ED%8A%B8%EB%A7%88%ED%81%AC%EB%94%94%EC%9E%90%EC%9D%B8-%EC%84%9C%EC%BB%A4%EC%8A%A4-%EC%9D%B4%EB%84%88-%EC%84%B8%ED%8A%B8-%ED%95%98%ED%94%84-%EC%9D%B4%EB%84%88%ED%85%90%ED%8A%B8-%EA%B7%B8%EB%9D%BC%EC%9A%B4%EB%93%9C%EC%8B%9C%ED%8A%B8-tm-1812/3342/category/201/display/1/").get();
				Element image=doc.selectFirst("#contents > div > div.bg_gray.guide_100p > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.imgArea > div.xans-element-.xans-product.xans-product-image > div.keyImg > div > a > img");
			}
			
		}catch(Exception ex) {
			ex.printStackTrace();}

	}
	
	
		public void itemAllData() {
			try {
				for(int i=1;i<=5;i++) {
					Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=201&page="+i).get();
					Elements image=doc.select("div.prdImg a img");
					Elements name=doc.select("div.description strong.name a");
					Elements price=doc.select("div.description ul li");
					
					for(int j=1;j<image.size();j++) {
						System.out.println(image.get(j).attr("src"));
						//System.out.println(name.get(j).attr("href"));
						System.out.println(name.get(j).text());
						System.out.println(price.get(j).text());

						
					}
					
				}
			
			}catch(Exception ex) {
				
			}
		
		}
}

//#contents > div > div.bg_gray.guide_100p > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea
// 상세보기
