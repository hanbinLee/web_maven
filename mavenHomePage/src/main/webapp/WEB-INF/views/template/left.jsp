<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body>
<c:set var="root" value="${pageContext.request.contextPath}"/>

<c:if test="${sessionScope.id == null}">
	<c:set var="str1" value="<a href='${root}/member/login.do'>로그인 </a>"/>
	<c:set var="str2" value="<a href='${root}/member/register.do'>|회원가입</a><br>" />
</c:if>

<c:if test="${sessionScope.id != null}">
	<c:set var="str1" value="<a href='${root}/member/logout.do'>로그아웃</a>"/>
	<c:set var="str2" value="<a href='${root}/member/update.do?id=${id}'>|회원수정 </a><br>"/>
	<c:set var="str3" value="<a href='${root}/member/delete.do'>|회원탈퇴</a><br>" />
	<c:set var="memberLevel" value="${sessionScope.memberLevel}" />
</c:if>

<tr valing="top">
	<td width="180" height="359" bgcolor="D1DBDB">
		<table width="200" height="500" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30">${str1}</td>
			</tr>
			<tr>
				<td height="30">
					<a href="${root}/member/main.do">HOME</a>
				</td>
			</tr>
			<tr>
				<td height="50"><p>☞ 회원 <br />
					&nbsp;&nbsp;${str2}
					&nbsp;&nbsp;${str3}
				</td>
			</tr>
			<tr>
				<td height="50"><p> ☞  게시판<br />
					&nbsp;&nbsp;<a href="${root}/board/list.do">|일반게시판</a><br />
					&nbsp;&nbsp;<a href="${root}/fileBoard/list.do">|자료게시판</a><br />
				</p></td>
			</tr>
			<tr>
				<td height="50"><p> ☞  방명록<br />
					&nbsp;&nbsp;<a href="${root}/guest/write.do">|방명록작성</a><br />
				</p></td>
			</tr>
			<tr>
				<td height="50"><p>☞ 메일<br/>
					&nbsp;&nbsp;|메일보내기 <br />
					&nbsp;&nbsp;|메일로 파일 전송 <br />
				</p></td>
			</tr>
			<c:if test="${memberLevel == 'AA' }"> 
			<tr>
				<td height="50">
						&nbsp;&nbsp;<a href="${root}/member/manager.do">관리자</a>
				</td>
			</tr>
			</c:if>
		</table></td>
		<td width="860">
			<table width="800" height="359" cellpadding="0" cellspacing="0">
			<tr>
				<td height="348">