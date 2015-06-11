<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Select Ok</title>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	
	<c:choose>
		<c:when test="${fn:length(addr)==0}">
			<table border="0" align="center" cellpadding="0" cellspacing="0" width="300">
				<tr>
					<td height="30">해당 데이터가 존재하지 않습니다.</td>
				</tr>	
			</table>
		</c:when>
		
		<c:otherwise>
				<div align="center">
					<c:out value="${fn:length(addr)}명 검색되었습니다"/>
				</div><br/>
				
				<c:forEach var="address" items="${addr }">
					<table border="0" align="center" cellpadding="0" cellspacing="0" width="300">
						<tr>
							<td height="30"> 이름</td>
							<td height="30">
								<input type="text" name="name"  value="${address.name }" disabled="disabled" />
							</td>
						</tr>
						
						<tr>
							<td height="30">전화번호</td>
							<td height="30">
								<input type="text" name="phone" value="${address.phone }" disabled="disabled"/>
							</td>
						</tr>
						
						<tr>
							<td height="30">이메일</td>
							<td height="30">
								<input type="text" name="email" value="${address.email }"  disabled="disabled"/>
							</td>
						</tr>
					</table>
					<br/><br/>
				</c:forEach>
		</c:otherwise>
	</c:choose>
		<div align="center">
			<a href="${root}/start.jsp">홈으로</a>
		</div>
</body>
</html>