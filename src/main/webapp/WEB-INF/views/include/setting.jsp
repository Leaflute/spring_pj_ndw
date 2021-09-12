<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>

<c:set var="path" value="${pageContext.request.contextPath}/"/>

<c:set var="cssPath" value="${path}resources/css/"/>
<c:set var="jsPath" value="${path}resources/js/"/>
<c:set var="imgPath" value="${path}resources/img/"/>
<c:set var="upPath" value="${path}resources/uploaded/"/>

<script src="https://kit.fontawesome.com/ef75f47104.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/xeicon@2.3.3/xeicon.min.css"/>
<link rel="stylesheet" type="text/css" href="http://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<link rel="stylesheet" type="text/css" href="${cssPath}common.css"/>