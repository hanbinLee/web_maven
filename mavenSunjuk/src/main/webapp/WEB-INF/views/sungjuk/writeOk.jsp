<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>s ${check }</h3>
	<c:choose>
		<c:when test="${check > 0 }">
			<script>
				alert("성적입력 완료");
			//	location.href="${root}/start.jsp";
			</script>
		</c:when>
		<c:otherwise>
			<script>
			alert("입력 실패");
		//	location.href="${root}/write.do";
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>