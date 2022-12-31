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
public class DataCollectionService_2 {
	public static void main(String[] args) {
		DataCollectionService_2 ds=new DataCollectionService_2();
		//ds.foodCategoryGetData();
		ds.foodDetailData();
	}
    public void foodCategoryGetData()
    {
    	FoodDAO dao=new FoodDAO();
    	try
    	{
    		// 사이트 연결 => HTML태그 읽기 
    		Document doc=Jsoup.connect("https://www.mangoplate.com/").get();
    		//System.out.println(doc.toString());
    		Elements title=doc.select("div.top_list_slide div.info_inner_wrap span.title");
    		Elements subject=doc.select("div.top_list_slide div.info_inner_wrap p.desc");
    		Elements poster=doc.select("div.top_list_slide img.center-croping");
    		Elements link=doc.select("div.top_list_slide a");
    		//System.out.println(link.toString());
    		/*
    		 *   text()  <태그명>데이터</태그명>
    		 *   attr("속성명")  <태그명 속성="값">
    		 *   data()  <script></script>
    		 *   html()  <태그명>
    		 *             <태그명>
    		 *               데이터
    		 *             </태그명>
    		 *           </태그명>
    		 *           
    		 *     <div class="a">
    		 *       <ul>
    		 *        <li>
    		 *          제목 , ....
    		 *        </li>
    		 *        <li>
    		 *          제목...
    		 *        </li>
    		 *      </ul>
    		 *     </div>
    		 */
    		for(int i=0;i<title.size();i++)
    		{
    			System.out.println(i+1);
    			System.out.println(title.get(i).text());
    			System.out.println(subject.get(i).text());
    			System.out.println(poster.get(i).attr("data-lazy"));
    			System.out.println(link.get(i).attr("href"));
    			System.out.println("=========================================================================");
    			CategoryVO vo=new CategoryVO();
    			vo.setTitle(title.get(i).text());
    			vo.setSubject(subject.get(i).text());
    			vo.setPoster(poster.get(i).attr("data-lazy"));
    			vo.setLink(link.get(i).attr("href"));
    			dao.foodCategoryInsert(vo);
    		}
    	}catch(Exception ex){}
    }
    /*
     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/652152_1632445289291_19886
     *   ?fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80
     *   ^https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_6948?
     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_6995?
     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
     *   https://mp-seoul-image-production-s3.mangoplate.com/15964/2221854_1656027051275_7005?
     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80^
     *   https://mp-seoul-image-production-s3.mangoplate.com/707184_1648003735535360.jpg?
     *   fit=around|512:512&crop=512:512;*,*&output-format=jpg&output-quality=80
     */
    public void foodDetailData()
    {
    	FoodDAO dao=new FoodDAO();
    	try
    	{
    		ArrayList<CategoryVO> list=dao.foodCategoryInfoData();
    		for(CategoryVO vo:list)
    		{
    			//System.out.println(vo.getCno()+" "+vo.getTitle()+" "+vo.getLink());
    			FoodVO fvo=new FoodVO();
    			fvo.setCno(vo.getCno());
    			System.out.println(vo.getCno()+"."+vo.getTitle());
    			Document doc=Jsoup.connect(vo.getLink()).get();
    			Elements link=doc.select("section#contents_list span.title a");
    			for(int k=0;k<link.size();k++)
    			{
    				//System.out.println(link.get(i).attr("href"));
    				Document doc2=Jsoup.connect("https://www.mangoplate.com"+link.get(k).attr("href")).get();
    				// 1. 이미지 
    				Elements image=doc2.select("div.list-photo_wrap img.center-croping");
    				String poster="";
    				for(int j=0;j<image.size();j++)
    				{
    					String s=image.get(j).attr("src");
    					poster+=s+"^";
    				}
    				poster=poster.substring(0,poster.lastIndexOf("^"));
    				
    				poster=poster.replace("&", "#");
    				fvo.setPoster(poster);
    				
    				//System.out.println(poster);
    				// 2. 맛집명
    				/*
    				 *   <span class="title">
			                  <h1 class="restaurant_name">봉산옥</h1>
			                    <strong class="rate-point ">
			                      <span>4.4</span>
			                    </strong>
    				 */
    				Element name=doc2.selectFirst("span.title h1.restaurant_name");
    				Element score=doc2.selectFirst("strong.rate-point span");
    				System.out.println(name.text()+" "+score.text());
    				fvo.setName(name.text());
    				fvo.setScore(Double.parseDouble(score.text()));
    				// 3. 평점 
    				// 4. 주소 , 전화 , 음식종료 , 가격대 , 주차 , 시간 , 메뉴 , 좋아요 , 괜찮다 , 별로 
    				String address="no",tel="no",type="no",price="no",time="no",menu="no",parking="no";
    				Elements detail=doc2.select("table.info tbody tr th");
  
    				for(int i=0;i<detail.size();i++)
    	    		{
    	    			//System.out.println(detail.get(i).text());
    	    			/*
    	    			 *   주소
    						전화번호
    						음식 종류
    						가격대
    						주차
    						영업시간
    						휴일
    						웹 사이트
    						메뉴
    	    			 */
    	    			String label=detail.get(i).text();
    	    			if(label.equals("주소"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				address=a.text();
    	    			}
    	    			else if(label.equals("전화번호"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				tel=a.text();
    	    			}
    	    			else if(label.equals("음식 종류"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				type=a.text();
    	    			}
    	    			else if(label.equals("가격대"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				price=a.text();
    	    			}
    	    			else if(label.equals("주차"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				parking=a.text();
    	    			}
    	    			else if(label.equals("영업시간"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				time=a.text();
    	    			}
    	    			else if(label.equals("메뉴"))
    	    			{
    	    				Element a=doc2.select("table.info tbody tr td").get(i);
    	    				menu=a.text();
    	    				
    	    			}
    	    		}
    	    		String addr1=address.substring(0,address.indexOf("지"));
    	    		String addr2=address.substring(address.indexOf("지")+3);
    	    		
    	    		System.out.println("주소:"+addr1);
    	    		System.out.println("지번:"+addr2);
    	    		
    	    		System.out.println("전화:"+tel);
    	    		System.out.println("음식종류:"+type);
    	    		System.out.println("가격대:"+price);
    	    		System.out.println("시간:"+time);
    	    		System.out.println("주차:"+parking);
    	    		System.out.println("메뉴:"+menu);
    	    		
    	    		fvo.setAddress(address);
    	    		fvo.setTel(tel);
    	    		fvo.setTime(time);
    	    		fvo.setType(type);
    	    		fvo.setParking(parking);
    	    		fvo.setPrice(price);
    	    		fvo.setMenu(menu);
    	    		
    	    		
    	    		Element script=doc2.selectFirst("script#reviewCountInfo");
    	    		System.out.println(script.data());// script안에 있는 데이터 읽기 => data()
    	    		// [{"action_value":1,"count":3},{"action_value":2,"count":12},{"action_value":3,"count":129}] => JSON
    	    		String s=script.data();
    	    		JSONParser jp=new JSONParser();
    	    		/*
    	    		 *   1. HTML 데이터 추출 => HTMLParser => Jsoup
    	    		 *   2. JSON 데이터 추출 => JSONParser (Ajax , Vue , React)
    	    		 *      => ArrayList => []
    	    		 *      => ~VO       => {}
    	    		 *   3. XML 데이터 추출 => DocumentBulider (Spring,Mybatis)
    	    		 *   ----------------------- 3대 
    	    		 *   
    	    		 *   [] => JSONArray
    	    		 *   {} => JSONObject
    	    		 */
    	    		// ==> RestFul ==> Spring 
    	    		JSONArray arr=(JSONArray)jp.parse(s);
    	    		System.out.println(arr.toString());
    	    		for(int i=0;i<arr.size();i++)
    	    		{
    	    			JSONObject obj=(JSONObject)arr.get(i);
    	    			if(i==0)
    	    			{
    	    				System.out.print("별로("+obj.get("count")+") ");
    	    				String ss=String.valueOf(obj.get("count"));
    	    				fvo.setBad(Integer.parseInt(ss));
    	    				
    	    				
    	    			}
    	    			else if(i==1)
    	    			{
    	    				System.out.print("괜찮다("+obj.get("count")+") ");
    	    				String ss=String.valueOf(obj.get("count"));
    	    				fvo.setSoso(Integer.parseInt(ss));
    	    			}
    	    			else if(i==2)
    	    			{
    	    				System.out.print("맛있다("+obj.get("count")+") ");
    	    				String ss=String.valueOf(obj.get("count"));
    	    				fvo.setGood(Integer.parseInt(ss));
    	    			}
    	    		}
    	    		System.out.println();
    	    		System.out.println("=======================================================================");
    	    		dao.foodDetailInsert(fvo);
    			}
    		}
    	}catch(Exception ex){}
    }
}