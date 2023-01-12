<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,com.sist.dao.*,com.sist.vo.*"%>
<%
	String fno = request.getParameter("fno");
	FoodDAO dao = new FoodDAO();
	FoodVO vo = dao.foodDetailData(Integer.parseInt(fno));
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  <div class="row">
	<table class="table">
     <tr >
       <%
          String poster = vo.getPoster();
          poster = poster.replace("#", "&");
          StringTokenizer st = new StringTokenizer(poster, "^");
          while(st.hasMoreTokens()) {
       %>
             <td class="text-center">
               <img src="<%=st.nextToken() %>" style="width:100%">
             </td>
         <%
          }
       %>
     </tr>
   </table>
   </div>
   
   <div class= "col-sm-5">
   	 <div></div>
   </div>
</body>
</html>
<%

// 474ea1f30b292f1e0644ae92fbff2778
%>