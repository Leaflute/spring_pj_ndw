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
<script type="text/javascript">
	function btnClick(formName) {
		formName.submit();
	};
</script>
</head>
<body>
	<s:authorize access="hasRole('ROLE_ADMIN')">
		<form name="logout" action="logout.co" method="post">
			<s:csrfInput />
		<nav class="nav_bar">
			<div id="mem_pic">DASHBOARD
			</div>
			<div>
				<ul>
					<li class="page_title"><a href="dashboard.html">DASHBOARD</a></li>				
					<li><a href="#">관리자정보관리</a></li>
					<li><a href="#">회원관리</a></li>
					<li><a href="orderList.ad">주문관리</a></li>
					<li><a href="itemManagement.ad">상품관리</a></li>
					<li><a href="report.ad">결산관리</a></li>
					<li><a href="csList.bo?boardId=1&fullList=true">고객문의관리</a></li>
					<li><a href="#" onclick="btnClick(logout)">로그아웃</a></li>
				</ul>
			</div>
		</nav>
		</form>
	</s:authorize>
	<s:authorize access="hasRole('ROLE_USER')">
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
	</s:authorize>
</body>
</html>