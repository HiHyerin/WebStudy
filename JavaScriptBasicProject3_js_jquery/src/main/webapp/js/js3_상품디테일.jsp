<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <style type="text/css">
  	.container{
  		margin-top:30px;
  	}
  	.row{
  	width: 500px;
  	margin: 0px auto;
  	}
  </style>
  
  <script type="text/javascript">
  let calc=()=>{
	  let price = document.querySelector("#price").getAttribute("value");  // $("#price").attr("value")
	  // alert(price)
	  let count = document.querySelector("#count").value; // $(#count).val()
	  // alert("count="+count)
	  let total = price+Number(count);
	  document.querySelector("#total").innerText=total; // $("#total").text(total)
  }
  </script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
		  <table class="table">
		    <tr>
		      <td width=30% class="text-center" rowspan="7">
		        <img src="https://recipe1.ezmember.co.kr/cache/data/goods/21/12/51/1000024877/1000024877_detail_060.jpg" style="width: 300px; height: 350px">
		      </td>
		      <td colspan="2"><h3>[만개특가] 부샤드 초콜릿 스톡 메르시 초콜릿 모음전</h3><sub style="color:gray">달달한 초콜렛 , 초콜렛계의 에르메스!</sub></td>
		    </tr>
		    <tr>
		      <td colspan="2">
		        <h3><span id="price" style="color:magenta">50%</span>&nbsp;&nbsp;2,000원</h3>
		        <sub style="color:gray">4,000원</sub>
		      </td>
		    </tr>
		    <tr>
		      <td colspan="2">
		      	<span style="color:green">첫구매할인가 <h3>1,900원</h3></span>
		      </td>
		    </tr>
		    
		    <tr>
		      <td colspan="2">
		      	배송 :  3,000원 (30,000원이상 무료배송)
		      </td>
		    </tr>
		    
		    <tr>
		      <td colspan="2">
		      	적립 10원 적립 (모든 회원 구매액의 0.5% 적립)
		      </td>
		    </tr>
		    
		    <tr>
		      <td width=30%>
		      	<span id="price">2000</span>
		      </td>
		      <td width=40%>
		      	<select id="count" class="input-sm" onchange="calc()">
		      		<option style="color:gray" selected >옵션을 선택하세요</option>
		      		<option value="2900">1. 부샤드 씨솔트 130g (+2,900원)</option>
		      		<option value="2900">2. 부샤드 밀크 초콜릿 130g (+2,900원)</option>
		      		<option value="2900">3. 부샤드 밀크 초콜릿 130g (+2,900원)</option>
		      		<option value="12900">4. 부샤드 씨솔트 초콜릿 455g (+12,900원)</option>
		      		<option value="12900">5. 부샤드 다크 초콜릿 455g (+12,900원)</option>
		      	</select>
		      </td>
		    </tr>
		    <tr>
		    	<td colspan="3" class="text-right">
		    		주문금액 &nbsp;<span type="text" style="color:green;font-size: 20px" id="total">&nbsp;</span>원
		    	</td>
		    </tr>
		  </table>
		</div>
	</div>
</body>
</html>




