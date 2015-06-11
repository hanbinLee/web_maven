<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>메인페이지</title>
<link rel="stylesheet" type="text/css" href="${root }/css/member/style.css" />
</head>
<body>	
	<a href="${root }/start.jsp">startPage</a>
	<c:if test="${id==null }">
		<c:set var="id" value="guest"/>
	</c:if>
	<div align="center">
		안녕하세요. <c:out value="${id }"/>님 <br/>
		즐거운 시간 되세요.
	</div>
</body>
</html>