<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    
<%@ include file="../include/setting.jsp" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="${cssPath}header.css">
<script src="https://kit.fontawesome.com/ef75f47104.js" crossorigin="anonymous"></script>
<style type="text/css">

.dropdown {
    position: relative;
    display: inline-block;
}

.dropdown_content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 240px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    top: 100%;
    z-index: 15;
}

.dropdown_content a {
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    font-size: 24px;
}

.dropdown_content a:hover {
    background-color: #f1f1f1
}
.dropdown:hover .dropdown_content {
    display: block;
}
.dropdown:hover .dropbtn {
    background-color: #3e8e41;
}

</style>
</head>
<body>
<!-- header 시작 -->
<header>
	<div class="full_width" >
		<div class="wrapper" id="hd_top">
			<img src="${imgPath}logo_little.png">			
		</div>
	</div>
</header>
<!-- header 끝 -->
</body>
</html>