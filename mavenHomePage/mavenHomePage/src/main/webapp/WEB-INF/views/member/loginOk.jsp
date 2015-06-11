<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<script type="text/javascript" src="${root }/css/jquery.js"></script>
</head>
<body>
	
	<c:if test="${memberLevel !=null }">
		<c:set var="id" value="${id }" scope="session"/>
		<c:set var="memberLevel" value="${memberLevel }" scope="session"/>
		<script type="text/javascript">
			alert("로그인 성공");
			//location.href="${root}/member/main.do";
			$(location).attr("href", "${root}/member/main.do");
		</script>
	</c:if>
	
	<c:if test="${memberLevel==null }">
		<script type="text/javascript">
			alert("로그인 실패");
			//location.href="${root}/member/login.do";
			$(location).attr("href", "${root}/member/login.do");
		</script>
	</c:if>
</body>
</html>