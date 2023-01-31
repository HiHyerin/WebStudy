<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@include file="/happy/fragments/head.jsp" %>
<!-- category css -->
<link rel="stylesheet" href="/assets/project/main/css/category.css">
<!-- slider css -->
<link rel="stylesheet" href="/assets/slick/css/slick.css">
<link rel="stylesheet" href="/assets/slick/css/slick-theme.css">




<!-- <link rel="stylesheet" href="/assets/project/item/css/layout.css"> -->
<body>
	<jsp:include page="/happy/fragments/header.jsp"></jsp:include>
	<jsp:include page="${main_jsp }"/>
	<jsp:include page="/happy/fragments/footer.jsp"></jsp:include>
	<jsp:include page="/happy/fragments/common-script.jsp"></jsp:include>
<!-- slider js -->
<script rel="script" src="/assets/slick/js/slick.min.js"></script>
<script rel="script" src="/assets/project/main/js/slider.js"></script>
</body>
</html>