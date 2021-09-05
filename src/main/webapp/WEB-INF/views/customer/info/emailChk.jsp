<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/setting.jsp"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="${cssPath}article.css">
<title>Leafcom 이메일 인증 요청 페이지</title>
<script src="./asset/js/jquery-3.6.0.min.js" type="text/javascript"></script>
<script src="${jsPath}member.js" type="text/javascript"></script>
</head>
<body>
<%@ include file="../../include/header.jsp" %>
<form action="emailResend.co" method="POST">
<input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}">
<input type="hidden" name="id" value="${sessionScope.member.id}">
<input type="hidden" name="key" value="${sessionScope.member.key}">
<input type="hidden" name="email" value="${sessionScope.member.email}">
<!-- article 시작 -->
<article class="container">
	<section class="wrapper">
		<div id="my_page_box">
			<%@ include file="../../include/nav.jsp" %>
			<div class="outer_content">
				<div class="inner_content">
					<table>
						<tr>
							<th>이메일 인증이 아직 완료되지 않았습니다.</th>
						</tr>
						<tr>
							<th>등록하신 이메일은 ${sessionScope.member.email}입니다. </th>
							<td><input type="submit" value="재전송"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</section>
</article>	

<%@ include file="../../include/footer.jsp" %>
</form>
</body>
</html>