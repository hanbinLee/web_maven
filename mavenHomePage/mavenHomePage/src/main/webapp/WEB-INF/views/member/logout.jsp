<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>로그아웃</title>
<script type="text/javascript" src="${root }/css/jquery.js"></script>
</head>
<body>
	<c:remove var="id" scope="session"/>
	<c:remove var="memberLevel" scope="session"/>
	
	
	<script type="text/javascript">
		alert="로그아웃됨";
		//location.href="${root}/member/login.do";		
		//jquery
		$(location).attr("href", "${root}/member/login.do");
	</script>
</body>
</html>