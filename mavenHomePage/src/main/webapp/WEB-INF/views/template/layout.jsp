<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>홈페이지 템플릿</title>
<script type="text/javascript" src="${root}/css/guest/script.js"></script>
<link href="${root}/css/guest/style.css" rel="stylesheet" type="text/css"/>
</head>
<body>
	<jsp:include page="top.jsp" />
	<jsp:include page="left.jsp" />
	
	<center>Welcome HomePage</center>

	<jsp:include page="bottom.jsp"/>
</body>
</html>
	
	<!--  검색 : http://localhost:8080/myBatisHomePage/template/ -->