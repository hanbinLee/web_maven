<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath}"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="${root }/css/member/style.css" />   
<title>Insert title here</title>
</head>
<body>
	
	<a href="${root }">Controller page</a>
	
	<br/><br/>
	
	<a href="${root }/fileBoard/write.do">게시판 쓰기</a> &nbsp; &nbsp; &nbsp;	
	<a href="${root }/fileBoard/list.do">게시판 목록</a> &nbsp; &nbsp; &nbsp;
	
	<br/>
	<br/>
	<br/>
	
	<a href="${root }/member/test.do">회원가입 테스트입니다.</a>
	<br/><br/>	
	
	<c:if test="${memberLevel==null }">
		<a href="${root }/member/register.do">회원가입</a>
		<a href="${root }/member/login.do">로그인</a>
	</c:if>
	
	<c:if test="${memberLevel!=null }">
		<a href="${root }/member/logout.do">로그아웃</a>
		<a href="${root }/member/update.do?id=${id}">회원수정</a>
		<a href="${root }/member/delete.do">회원탈퇴</a>
		
		<c:if test="${memberLevel=='AA' }">
			<a href="">관리자</a>
		</c:if>
	</c:if>
	
	<br/><br/>
	<h3>방명록</h3>
	<a href="${root}/guest/write.do">방명록</a>
	
	<br/><br/>
	<h3>게시판</h3>
	<a href="${root }/board/write.do">게시판 쓰기</a>
	<a href="${root}/board/list.do">게시판 목록</a>
</body>
</html>