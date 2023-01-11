<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*"%>
<%--
	1. 저장공간 : 클라이언트 브라우저에 저장(보안 취약), 서버 메모리에 저장(보완)
	
	2. 동일점은 생성시 request로 생성
	   request.getCookie()
	   request.getSession()
	   
 	3. 저장 => cookie(문자열만 저장), session(Object저장)
 	4. 동일점 : Map 형식(키, 값으로 이루어짐) => 키가 동일하면 덮어씀(ex)쿠키)
 	
 	--------------------------------------------------------------
 	
 	주요메소드
 	https://www.notion.so/cookie-6cedcc01746d479db787b2940de9c4da
 	cookie
 		1. 생성
 			Cookie cookie = new Cookie(키, 값)
 									  ------- 문자열로 저장
 		2. 저장위치 지정
 		3. 저장기간
 		4. 브라우저 전송
 		5. 쿠키 읽기
 		6. 삭제
 	
 	session 
 		1. 저장
 		2. 저장기간
 		3. 읽기
 		4. 삭제(일부)
 		   전체삭제
 		5. 확인
 		6. 처음 생성
 		7. 각 브라우저마다 sessionId => getID()
 		------------------------------------ 공통점 : 모든 JSP에서 사용이 가능(필요한 위치에서 언제든 사용이 가능)
 		
	page : this(자신의 객체) => Object
 		 => Object page=this;
	config : 환경설정 => ServletConfig => web.xml
										------- 에러처리, 한글변환, 파일 저장위치 등록, 서블릿 등록
	exception : exception 보단 try~catch를 더 많이 사용
 --%>
<%
	String mode=request.getParameter("mode");
	if(mode==null)
		mode="0";
	int index=Integer.parseInt(mode);
	String jsp="";
	// main.jsp 안에서 화면을 변경하는 위치
	switch(index){
	case 0:
		jsp="../food/category.jsp";
		break;
	case 1:
		jsp="../food/food_list.jsp";
		break; 
	case 2:
		jsp="../food/food_detail.jsp";
		break;
}
	
	String log_jsp="";
	String id=(String)session.getAttribute("id");
	if(id==null){ // 로그인 안한 상태
		log_jsp="login.jsp";
	}else{ // 로그인 된 상태
		log_jsp="logout.jsp";
	}
	
	// 쿠키처리///////////////////////////////////////////////////
	//1. 쿠키 읽기
	Cookie[] cookies = request.getCookies();
	ArrayList<FoodVO> cList = new ArrayList<FoodVO>();
	FoodDAO dao = new FoodDAO();
	if(cookies!=null){ //쿠키가 존재하면
		for(int i=cookies.length-1;i>=0;i--){ // 최신 쿠키부터 불러오는거
			if(cookies[i].getName().startsWith("f")){
				/*
					new Cookie(키,값)
					 - 키 : 중복 저장 x  => ex) f맛집번호
						    getName()
					 - 값 : getValue()
				*/
				String fno = cookies[i].getValue();
				FoodVO vo = dao.foodDetailData(Integer.parseInt(fno));
				String poster = vo.getPoster();
				if(poster!=null){
					poster = poster.substring(0,poster.indexOf("^")).replace("#","&");
					vo.setPoster(poster);
				}
				
				cList.add(vo);
			}
		}
	}
	
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.main{
	margin-top: 50px;
	
}
.row{
	width: 800px;
	margin: 0px auto;
}
h1{
	text-align: center;
}

</style>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
<%--$(function(){
	$('.radios').on('click',function(){
		console.log("click");
		let fno = $(this).attr("value");
		console.log("fno="fno);
		$('#cookie_frm'+fno).submit();
		
	})
}) --%>
</script>
</head>
<body>
	<%
		pageContext.include("header.jsp"); // <jsp:include page="header.jsp">
	%>
	<div class="container main">
		<div class="col-sm-3">
			<%
				pageContext.include(log_jsp);
			%>
		</div>
		<div class="col-sm-9">
			<%
			pageContext.include(jsp); // include를 통해 main.jsp 에 포함된다 => main.jsp가 경로가 된다 => food 폴더의 다른 파일에 접근하고자 할 땐 ../food/로 경로 이동해야 함
			%>
			<div style="height: 20px"></div>
			<h3>최신 방문 맛집 &nbsp; 더보기 &nbsp; <a href="../food/cookie_delete.jsp">삭제</a></h3>
			<hr>
			<form method=post action="../food/cookie_one_delete.jsp" id="cookie_frm">
			<button class="btn btn-sm btn-danger">삭제</button>
			<%
				
				for(int i=0;i<cList.size();i++){
					if(i<5){
					FoodVO vo = cList.get(i);
			%>
					
					<input type="radio" name="cookie" value="<%=vo.getFno() %>" class="radios">
					<img src="<%=vo.getPoster() %>" title="<%=vo.getName() %>" style="width: 150px; height: 150px">
					
			<%
					}
				}
			%>
			
			</form>
			
		</div>
	</div>
</body>
</html>












