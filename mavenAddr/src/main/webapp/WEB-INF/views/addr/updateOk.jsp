<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script src="${root }/resources/js/jquery.js"></script>
</head>
<body>
	<c:choose>
		<c:when test="${check > 0 }">
			<script>
				alert("수정완료");
			</script>
		</c:when>
		<c:otherwise>
			<script>
				alert("수정실패");
			</script>
		</c:otherwise>
	</c:choose>
	
	<script>
			$(location).attr("href" , "${root}/start.jsp");
	</script>
</body>
</html>