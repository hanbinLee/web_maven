<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소록 삭제</title>
<script type="text/javascript" src="${root }/resources/js/addr.js"></script>
<link rel="stylesheet" type="text/css" href="${root }/resources/css/addr.css"/>
</head>
<body>	
	<form name="addrForm">
		<table border="0" align="center" cellpadding="0" cellspacing="0" 
					width="300">
			<tr>
				<td colspan="2" align="center" height="30">
					삭제하실 이름을 입력하세요.
				</td>
			</tr>
			
			<tr>
				<td height="30">삭제 이름</td>
				<td height="30">
					<input type="text" name="name"/>
					<input type="button" value="검색" onclick="deleteCheck(addrForm, '${pageContext.request.contextPath}')"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>