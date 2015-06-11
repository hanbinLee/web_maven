<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>우편번호</title>
<script type="text/javascript" src="${root }/css/member/script.js"></script>
<script type="text/javascript" src="${root }/css/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${root }/css/member/style.css" />
</head>
<body>
	
	<c:if test="${zipcodeDtoList==null }">
		<form action="${root }/member/zipcode.do" method="get">
			<div align="center">
				<div class="line">
					<span class="content">
							<input type="text" name="dong" />
							<input type="submit" value="검색" />
					</span>
				</div>
			</div>
		</form>
	</c:if>
	
	<c:if test="${zipcodeDtoList!=null }">
		<form action="${root }/member/zipcode.do" name="memberForm" method="get">
			<div class="line">
					<span class="content">
						<input type="text" name="zipcode" />
						<input type="submit" value="검색" />
					</span>
			  	</div>
		</form>
		
		<table>
			<c:forEach var="zipDto" items="${zipcodeDtoList }">
				<c:set var="zipcode777" value="${zipDto.zipcode}"/>
				<c:set var="address777" value="${zipDto.sido} ${zipDto.gugun } ${zipDto.dong } ${zipDto.bunji } ${zipDto.ri }"/>	
				<tr>
					<td>
						
						<a href="javascript:sendAddress('${zipcode777 }', '${address777 }')">
							<c:out value="${zipcode777 } ${address777 }"/>
						</a>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
</body>
</html>