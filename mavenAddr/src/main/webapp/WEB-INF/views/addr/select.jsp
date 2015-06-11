<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath }" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>주소록</title>
<link rel="stylesheet" type="text/css" href="${root }/resources/css/addr.css">
</head>
<body>
	<form action="${root }/selectOk.do" method="post" onsubmit="return selectForm(this)">
		<table border="0" align="center" cellpadding="0" cellspacing="0" 
					width="300">
			
			<tr>
				<td colspan="2" align="center" height="30">
					검색하실 이름 입력하세요.
				</td>
			</tr>
			
			<tr>
				<td height="30">이름</td>
				<td height="30">
					<input type="text" name="name"/>
					<input type="submit" value="검색"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>