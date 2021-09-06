<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/setting.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${cssPath}article.css">
<link rel="stylesheet" type="text/css" href="${cssPath}dashboard.css">

<script src="./asset/js/jquery-3.6.0.min.js" type="text/javascript"></script>
<title>상품관리</title>
</head>
<body>
<form action="" method="post">
<%@ include file="../../common/header.jsp" %> 
<!-- article 시작 -->
<article class="container">
	<!-- 컨테이너 -->
	<div class="wrapper">
		<div id="my_page_box">
			<!-- 좌측 메뉴바 -->
			<%@ include file="../nav.jsp" %> 
			
			<!-- section -->
			<section class="mem_content">
			<div class="outer_content">
				<div class="inner_content">
					<div class="title_letter">MY주문정보</div>
						<table>
							<thead>
								<tr>
									<th>주문 상태</th>
									<th>No</th>
									<th>주문자</th>
									<th>상품명</th>
									<th>가격</th>
									<th>주문 수량</th>
									<th>요청</th>									
								</tr>
							</thead>
							<tbody>
							<c:forEach var="dtos" items="${orderList}">
							<tr>
								<td>
								<c:if test="${dtos.condition eq 1}">
									[구매요청]
								</c:if>
								<c:if test="${dtos.condition eq 2}">
									[결제취소]
								</c:if>
								<c:if test="${dtos.condition eq 3}">
									[구매승인]
								</c:if>
								<c:if test="${dtos.condition eq 4}">
									[배송중]
								</c:if>
								<c:if test="${dtos.condition eq 5}">
									[배송완료]
								</c:if>
								<c:if test="${dtos.condition eq 6}">
									[교환요청]
								</c:if>
								<c:if test="${dtos.condition eq 7}">
									[환불요청]
								</c:if>
								<c:if test="${dtos.condition eq 8}">
									[환불완료]
								</c:if>
								<c:if test="${dtos.condition eq 9}">
									[구매확정]
								</c:if>
								</td>
								<td>${dtos.odId}</td>
								<td>${dtos.meId}</td>
								<td>${dtos.itName}</td>
								<td><fmt:formatNumber value="${dtos.price}" pattern="￦#,###"/></td>
								<td>${dtos.quantity}</td>
								<td colspan="6" align="right">
									<c:choose>
										<c:when test="${dtos.condition eq 1}">
										<input type="button" value="결제취소" class="little_btn"
											onclick="window.location='updateOrder.cu?odId=${dtos.odId}&condition=2'">
										</c:when>
										<c:when test="${dtos.condition eq 4||dtos.condition eq 5}">
										<input type="button" value="교환요청" class="little_btn"
											onclick="window.location='updateOrder.cu?odId=${dtos.odId}&condition=6'">
										<input type="button" value="환불요청" class="little_btn"
											onclick="window.location='updateOrder.cu?odId=${dtos.odId}&condition=7'">
										<input type="button" value="구매확정" class="little_btn"
											onclick="window.location='updateOrder.cu?odId=${dtos.odId}&condition=9'">
										</c:when>
									</c:choose>
								</td>
							</tr>
							 </c:forEach>
							</tbody>
						</table>
	
						<br>
					</div>
				</div>
			</section>
			<!-- section 종료 -->
		</div>
	</div>
</article>
<!-- article 끝 -->

<%@ include file="../../include/footer.jsp" %>
</form>
</body>
</html>