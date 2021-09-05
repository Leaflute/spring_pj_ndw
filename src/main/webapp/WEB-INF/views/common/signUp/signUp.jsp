<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/setting.jsp" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="${cssPath}member.css">
<link rel="stylesheet" type="text/css" href="${cssPath}article.css">
<title>회원가입</title>
<script type="text/javascript" src="${jsPath}jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="${jsPath}member.js"></script>
</head>
<body>
<!-- header 시작 -->
<%@ include file="../logHeader.jsp" %>

<!-- article 시작 -->
<article class="container">
	<section class="wrapper">
	<div id="signInBox">
		<a href=""><img src="${imgPath}leafcom-logo.png"></a>
		<form action="signUpAction.co" method="post" name="signInForm" onsubmit="return signInChk();">
			<input type="hidden" name = "${_csrf.parameterName}" value="${_csrf.token}">
			<input type="hidden" name="hiddenEmail" value="0">
			<input type="hidden" name="hiddenId" value="0">
			<fieldset>
				<table>
					<tr>
						<th class="icon" ><i class="xi-user-o"></i></th>
						<td align="left">
							<input type="text" name="id" size="20" placeholder="아이디">
							<input type="button" class="btn_green popup" name="idDupChk"
								value="중복확인" onclick="confirmId();">
						</td>			
					</tr>
					<tr>
						<th class="icon"><i class="fas fa-key"></i></th>
						<td><input type="password" name="pw" size="20" placeholder="비밀번호"></td>
					</tr>
					<tr>
						<th class="icon"><i class="fas fa-key"></i></th>
						<td><input type="password" name="rePw" size="20" placeholder="비밀번호 확인"></td>
					</tr>
					<tr>
						<th class="icon"><i class="far fa-id-badge"></i></th>
						<td><input type="text" name="name" size="40" placeholder="이름"></td>
					</tr>
					<tr>
						<th class="icon" ><i class="far fa-envelope"></i></th>
						<td align="left">
							<input type="text" name="email" size="40" placeholder="이메일 주소">
						</td>	
					</tr>
					<tr>
						<th class="icon"><i class="fas fa-mobile-alt"></i></th>
						<td align="left">
							<input type="text" name="mobile" size="11" placeholder="휴대폰 번호">
							<input type="button" value="인증" class="btn_green">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" class="cell_green">
						<input type="submit" value="회원가입" class="btn_green" style="width:100%;height:100%">
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center" class="cell_white">
						<input type="button" value="돌아가기" class="btn_white" style="width:100%;height:100%" onclick="history.back()">
						</td>
					</tr>
				</table>
			</fieldset>
		</form>
		</div>	
	</section>	
</article>

<%@ include file="../../include/footer.jsp" %>
</body>
</html>