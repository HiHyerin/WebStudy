package com.sist.service;
import com.sist.dao.*;
import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
public class DataCollectionService {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DataCollectionService ds=new DataCollectionService();
		//ds.campingCategoryGetData();
		//ds.itemDetailData();
		ds.itemAllData();
	}
	
	public void campingCategoryGetData()
	   {

	      ItemDAO dao = new ItemDAO();
	      try
	      {
	         // 사이트 연결 => HTML 태그 읽기
	         // 캠핑 리스트 
	         Document doc = Jsoup.connect("https://campinglist.co.kr").get();
	         // 확인 => html 나오는거
	         //System.out.println(doc.toString());
	         Elements name =doc.select("#left_cate > div > ul div.catenabber  a");
	                           
	            for(int i=0; i<name.size()-1; i++)
	            {
	              System.out.println(i+1);
	              System.out.println(name.get(i+1).text());

	              System.out.println("===============");
	              
	              ItemCategoryVO vo = new ItemCategoryVO();
	              vo.setName(name.get(i+1).text());
	              // 데이터 수집 (camping category)
	              dao.campingCategoryInsert(vo);           
	            }      
	      }catch (Exception e) {}
	   }
	
	
	
//	public void itemDetailData() {
//		ItemDAO dao=new ItemDAO();
//		try {
//			
//			for(int j=0;j<link.size();j++) {
//				
//			}
//		}catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
	
	
//	int discount=0, stock=0;
//	String status=" ";
	int icno=12,like_cnt=0,jjim_cnt=0, stock=1000;
	String delivery_price="3,000원(50,000원 이상 무료)";
	
//	String discount="0%";
	public void itemAllData() { //221229 14:40 언저리
		try {
			ItemDAO dao = new ItemDAO();
			for(int i=1;i<=5;i++) {
				//Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=169&page="+i).get(); //cno=12
				//Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=47&page="+i).get(); //cno=11
				//Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=45&page="+i).get(); //cno=10(5page)
				//Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=92&page="+i).get(); //cno=9(3page)
				//Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=42&page="+i).get(); //cno=8(4page) 화로/비비큐
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=26&page="+i).get(); //cno=7(9page) 식기/쿠커
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=42&page="+i).get(); //cno=6(4page) 스토브/랜턴
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=15&page="+i).get(); //cno=5(4page) 베드,침구,매트
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=28&page="+i).get(); //cno=4(6page) 테이블/체어
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=282&page="+i).get(); //cno=3(4page) 폴대
//				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=210&page="+i).get(); //cno=2(2page) 타프/쉘터
				Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=201&page="+i).get(); //cno=1(5page) 텐트
				Elements link=doc.select("div.prdImg a");// 제품 상세보기 링크
			// <a href="/product/텐트마크디자인-서커스-이너-세트-하프-이너텐트-그라운드시트-tm-1812/3342/category/201/display/1/" name="anchorBoxName_3342"><img src="//campinglist.co.kr/web/product/medium/202212/73c83770a31ef46a7db3d6e03c946bf7.jpg" onmouseover="this.src='//campinglist.co.kr/web/product/big/202212/d784a5b70cb59d19bc2ad541081e065f.jpg'" onmouseout="this.src='//campinglist.co.kr/web/product/medium/202212/73c83770a31ef46a7db3d6e03c946bf7.jpg'" id="eListPrdImage3342_1" alt="텐트마크디자인 서커스 이너 세트 하프 (이너텐트 + 그라운드시트) (TM-1812)"></a>
				// 링크가 제대로 들어왔는지 확인
				System.out.println(link.size());
				
				for(int j=0;j<link.size();j++) {
					
					ItemVO ivo=new ItemVO();
					
					System.out.println(link.get(j).attr("href")); 
						//<a href="~~"></a> => 속성값이므로 attr() 사용
						// <></> : 태그와 태그 사이에 있을 때엔 .text()사용
						//#anchorBoxId_3342 > div.thumbnail > div.prdImg > a
						// 상세페이지 링크가 하나씩 들어옴
					Document doc2=Jsoup.connect("https://campinglist.co.kr/"+link.get(j).attr("href")).get();
						// 	link.get(j).attr("href") 넣는 의미 : 상세페이지 안으로 들어가겠다
						// 위에 Document doc=Jsoup.connect("https://campinglist.co.kr/product/list.html?cate_no=201&page="+i).get();는 상품목록페이지에 들어가겠다
					//상품이름
					Element name=doc2.selectFirst("#contents > div > div.bg_gray.guide_100p > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.headingArea > h2");
					System.out.println(name.text());
					ivo.setName(name.text());
					
					// 상품이미지
					Element image=doc2.selectFirst("#contents > div > div.bg_gray.guide_100p > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.imgArea > div.xans-element-.xans-product.xans-product-image > div.keyImg > div > a");
					// Element poster=doc2.select("").get(0);
					System.out.println(image.attr("href"));
					ivo.setImage(image.attr("href"));
					
					// 상품 가격
					Elements price=doc2.select("#span_product_price_text");
					//System.out.println(price.text());
					//System.out.println((price.text()).substring(0,(price.text()).length() -1));
					//ivo.setPrice((price.text()).substring(0,(price.text()).length() -1));
					System.out.println(price.text());
//					
					// 상세설명
					Elements description=doc2.select("#contents > div > div.xans-element-.xans-product.xans-product-additional > div.cont.guide_100p > div:nth-child(2) > div.continner > img");
					//Elements description=doc2.select("#contents > div > div.xans-element-.xans-product.xans-product-additional > div.cont.guide_100p > div:nth-child(2) > div.continner > div > p:nth-child(4) > img");
					
//					System.out.println(description.attr("continner"));
					System.out.println(description.attr("src"));
					
					ivo.setDescription(description.attr("src"));
					
					// 상세설명2
//					Elements description_2=doc2.select("#contents > div > div.xans-element-.xans-product.xans-product-additional > div.cont.guide_100p > div:nth-child(2) > div.continner > img[hspace$=.jpg]");
////					Elements description_2=doc2.select("#contents > div > div.xans-element-.xans-product.xans-product-additional > div.cont.guide_100p > div:nth-child(2) > div.continner > p > img");
//					System.out.println("상세2:"+description_2.attr("hspace"));
					
					// 재고stock
					System.out.println(stock);
					ivo.setStock(stock);
					
					
					// status
					Element status=doc2.selectFirst("#scbtnzone > div.xans-element-.xans-product.xans-product-action > div.ec-base-button.gColumn > a:nth-child(1)");
					System.out.println(status.toString());
							// class="btnSubmit sizeL " => 재고O 
							// class="btnSubmit sizeL displaynone" => 품절
					ivo.setStatus(status.toString());
					
					

					// discount
					Element discount=doc2.selectFirst("#contents > div > div.bg_gray.guide_100p > div > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.pricearea > div.pricearea_a1 > div.leftper > div > span.price");
					
					System.out.println(discount.text());

							// 할인 안하면  0.00
							// 할인 하면 정가가 나
					ivo.setDiscount(discount.text());
				
					
					// delivery_price : 3,000원 (50,000원 이상 구매 시 무료) => 그냥 적기
					System.out.println(delivery_price);
					ivo.setDelivery_price(delivery_price);
					
					// like_cnt
//					like_cnt=(int)(Math.random()*100)+1;
					System.out.println(like_cnt);
					ivo.setLike_cnt(like_cnt);
					
					
					// jjim_cnt
//					jjim_cnt=(int)(Math.random()*100)+1;
					System.out.println(jjim_cnt);
					ivo.setJjim_cnt(jjim_cnt);
					
					
					// icno
					
					System.out.println(icno);
					ivo.setIcno(icno);
					
					System.out.println("============================================================================");
					//dao.itemDetailInsert(ivo);
				}
			}
		
		}catch(Exception ex) {}
	}
	

}













