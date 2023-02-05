package com.sist.controller;

import java.io.*;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.*;
import com.sist.model.*;
import java.net.*;

//DispatcherServelet : 스프링에서 제공하는 컨트롤러 => 동작 -> 구분(어노테이션), 등록(xml)

/*
 	MVC 동작 과정
 	1. 요청 => DispatcherServlet을 찾는다
 			 ------------------- url 주소로 찾는다 -> .do(list.do, insert.do... => 무조건 DispatcherServlet을 찾는다)
 		** Controller : 요청을 받는다 => MODEL로 전송(요청내용 전송 => request)
 						----------
 						Model이 처리결과를 받는다(request) => request.setAttribute() (여러개 사용 가능)
 						= JSP로 request를 전송 출력 => forward
 						= 이미 존재하는 화면으로 이동 => sendRedirect
 		** Model : 요청에 대한 처리 (데이터베이스 처리, 크롤링,request에 값을 담아주는 역할)
 		** View : Controller가 전송한 request에 담긴 데이터를 출력
 		MVC 사용 목적 : 보안 , 역할분담이 가능 (Front-end, Back-End) -> 여러명이 동시작업
 		
 		JSP -> DispatcherServlet => Model => dao => 오라클
 		
 		DispatcherServlet는 고정
 		Model이 가지고 있는 모든 메소드를 자동으로 호출 => 어노테이션(invoke())
 		어노테이션은 밑에 있는 내용을 제어
 		@ -> class
 		public class A{
 			@ -> field
 			B b;
 			public void display(){
 			
 			}
 			public void disp(@ => PARAMETER B b){
 			
 			}
 		}
 		
 		** Controller 역할
 			1) 사용자 요청 => 해당 Model 클래스를 찾는다 => 등록된 Model 클래스 로딩
 							-------------------- xml로 등록
 											
 			2) 요청 처리 => Model 메소드 호출 => invoke
 			3) Model 메소드 호출 후 결과값을 받는다 (request, session)
 			4) jsp를 찾아서 request를 전송 (forward())
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArrayList<String> clsList = new ArrayList<String>(); // application.xml에 등록된 클래스를 모아둔다 => Model클래스를 인식시키는 역할
	
	
	// 초기화  => 사용자에게 서비스(jsp로 request => 화면 브라우저에 출력)
	// Model 클래스를 가지고 있어야 한다.
	public void init(ServletConfig config) throws ServletException {
		// model 클래스를 읽어온다 => 일반 window 형식 => 경로 : 1)get , 2)aws(리눅스)
		try {
			// 1. xml 파일을 가지고 온다
			//1-1. xml의 파일 익기 => 호환성 => 실제 톰캣이 인식하는 폴더에서 읽기
			URL url = this.getClass().getClassLoader().getResource("."); // . = 현재폴더
			File file = new File(url.toURI());
			System.out.println(file.getPath()); // 파일 경로 보기 (톰캣이 인식하는 폴더)
			// C:\webDev\WebStudy\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\JSPFinalProject\WEB-INF\classes
			String path = file.getPath();
			path = path.replace("\\", File.separator);
			path = path.substring(0,path.lastIndexOf(File.separator)); // file.separator : 파일구분자(운영체제마다 다름 - windows:\, mac:/)
			path = path + File.separator+"application.xml";
			
			
			
			// 2. 클래스명을 읽는다
			// 2-1. xml 파싱 =? JAXP(DOM,SAX:MyBatis,Spring:SAX) , JAXB(1.8)
			/*
			 	DOM : 메모리 저장 => 트리형식(속도가 늦다) => 추가 ,수정, 삭제 가능
			 	SAX : 한줄씩 읽어서 데이터만 추출(속도가 빠르다) => 추가, 수정, 삭제가 불가능
			 	스프링, 마이바티스 => 파싱이 이미 되어있다.
			 */
			
			// 2-2 XML 파싱 : 파싱 클래스 생성
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			// html, xml, wml, hdml ... factory 패턴(DriverManager) => Spring(클래스 관리자)
			
			// 2-3 파싱기를 가지고 온다
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			//2-4 xml을 파싱 => 메모리에 트리형태로 저장
			Document doc = db.parse(new File(path));
			//System.out.println(doc.toString());
			
			// 2-5 최상위 태그 읽기 (테이블명) -> <beans>
			Element root = doc.getDocumentElement();
			//System.out.println(root.toString());
			
			//2-6 같은 태그 묶어서 데이터 읽기
			NodeList node = root.getElementsByTagName("bean");
			//System.out.println(node.toString());
			
			// 3. ArrayList에 저장
			for(int i=0;i<node.getLength();i++) {
				Element bean = (Element)node.item(i);
				String cls = bean.getAttribute("class");
				System.out.println(cls);
				clsList.add(cls);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}

	// 브라우저에 필요한 데이터를 전송위치..?
	// Model이 가지고 있는 메소드를 호출 -> 요청처리 -> request를 해당 jsp로 전송
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 사용자 요청 => 등록된 모델의 해당 메소드를 찾는다 => 호출 => request,session을 jsp로 전송
		try {
			// 1. 사용자가 요청한 주소를 확인 => ? 뒤에는 자동으로 request
			String uri = request.getRequestURI();
			System.out.println(uri);
			// /JSPFinalProject/main.do => 구분자 : /main.do
			uri = uri.substring(request.getContextPath().length()+1);
			System.out.println("uri:"+ uri);
			//uri에 해당하는 메소드를 찾아서 호출 => 결과값 jsp로 전송
			for(String cls:clsList) {// 등록된 클래스 안에서 해당 요청의 메소드 호출
				Class clsName=Class.forName(cls);
				//클래스 위에 @controller가 없는 경우에는 제외 => spring이 그래서 
				if(clsName.isAnnotationPresent(Controller.class)==false)
					continue;
				
				// 1) 메모리 할당 (클래스)
				Object obj = clsName.getDeclaredConstructor().newInstance();
				
				// 2) 메소드를 찾아서 요청처리
				Method[] methods = clsName.getDeclaredMethods();
				for(Method m:methods) {
					//어노테이션을 이용해서 메소드를 찾는다
					RequestMapping rm = m.getAnnotation(RequestMapping.class);
					if(rm.value().equals(uri)) {
						// 찾은 경우 메소드를 호출해라
						String jsp = (String)m.invoke(obj, request,response); //메소드명 상관없이 어노테이션이 일치하는 메소드를 호출
						// request => jsp로 전송
						// sendRedirect() => request를 초기화
						// request 전송 => forward
						if(jsp==null) {
							return; // String, void(ajax)
						}
						else if(jsp.startsWith("redirect:")) { // _ok.jsp => Controller에서 직접 처리
							response.sendRedirect(jsp.substring(jsp.indexOf(":")+1));
						}else {
							RequestDispatcher rd = request.getRequestDispatcher(jsp);
							rd.forward(request, response);
						}
						return;
					}
				}
			}
		}catch(Exception ex) {}
		
	}

}






