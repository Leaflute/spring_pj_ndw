<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--  3초가 지나면 해당url 즉 home으로 이동 -->
<meta http-equiv="refresh" content="3,${path}">
<title>Insert title here</title>
</head>
<body>
<p>${errMsg}</p>
</body>
</html>