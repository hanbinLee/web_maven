<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Write Ok</title>
<script src="${root }/resources/js/jquery.js"></script>
</head>
<body>
	<c:if test="${check > 0 }">
		<script>
			alert("주소록 추가 완료");
			$(location).attr("href" , "${root}/start.jsp");
		</script>
	</c:if>
	
	<c:if test="${check==0 }">
		<script type="text/javascript">
			alert("주소록 추가 실패");
			$(location).attr("href" , "${root}/write.do");
		</script>
	</c:if>
</body>
</html>