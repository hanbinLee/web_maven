<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${root }/resources/js/jquery.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${check > 0}">
			<script type="text/javascript">
			alert("삭제완료");
			$(location).attr("href" , "${root}/start.jsp");
		</script>
		</c:when>
		
		<c:otherwise>
			<script type="text/javascript">
				alert("삭제 실패");
				$(location).attr("attr" , "${root}/addr/delete.do");
			</script>
		</c:otherwise>
	</c:choose>
</body>
</html>