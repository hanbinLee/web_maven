<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="root" value="${pageContext.request.contextPath }" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${root }/resources/sungjuk.css"/>
<script type="text/javascript" src="${root }/resources/sungjuk.js"></script>
<title>Insert title here</title>
</head>
<body>
	<a href="${root }/start.jsp">메뉴</a>
	<br/><br/>
	
	<form action="${root }/write.do" method="post" onsubmit="return writeForm(this)">
		<label>이름</label>
		<input type="text" name="name"/>
		<br/><br/>
		
		<label>국어</label>
		<input type="text" name="kor"/>
		<br/><br/>
		
		<label>영어</label>
		<input type="text" name="eng"/>
		<br/><br/>
		
		<label>수학</label>
		<input type="text" name="math"/>
		<br/><br/>
		
		<input type="submit" value="작성" />
		<input type="reset" value="취소" />
	</form>
</body>
</html>