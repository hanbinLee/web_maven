<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath }" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${root }">HomeController Test</a>
	
	<a href="${root }/write.do">글쓰기</a>
	<a href="${root }/select.do">주소록 검색</a> &nbsp;&nbsp;
	<a href="${root }/delete.do">주소록 삭제</a> &nbsp;&nbsp;
	<a href="${root }/update.do">주소록 수정</a> &nbsp;&nbsp;
	
</body>
</html>