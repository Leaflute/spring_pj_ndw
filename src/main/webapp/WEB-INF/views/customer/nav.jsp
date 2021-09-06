<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${cssPath}article.css">
<link rel="stylesheet" type="text/css" href="${cssPath}dashboard.css">
<title>Insert title here</title>
<script src="./asset/js/jquery-3.6.0.min.js" type="text/javascript"></script>

</head>
<body>
	<nav class="nav_bar">
		<div id="mem_pic">회원정보 박스
		</div>
		<div>
			<ul>
				<li class="page_title"><a href="mypage.html">MY페이지</a></li>				
				<li><a href="pwChk.cu">MY정보관리</a></li>
				<li><a href="orderList.cu">MY주문관리</a></li>
				<li><a href="addressList.cu">MY배송지</a></li>
				<li><a href="myestimate.html">MY견적서</a></li>
				<li><a href="csList.bo?boardId=1&fullList=false">MY문의</a></li>
			</ul>
		</div>
	</nav>
</body>
</html>