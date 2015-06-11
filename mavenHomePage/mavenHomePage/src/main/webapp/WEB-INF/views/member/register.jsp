<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<c:set var="root" value="${pageContext.request.contextPath }"/>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입</title>
<script type="text/javascript" src="${root }/css/member/script.js"></script>
<script type="text/javascript" src="${root }/css/jquery.js"></script>
<link rel="stylesheet" type="text/css" href="${root }/css/member/style.css" />
</head>
<body>
	
	<a href="${root }/member/main.do">main</a>
	<div align="center">
		<font size="2"><b>회원가입(*필수입력사항입니다.)</b></font>
	</div>

	<div align="center">
		<form class="form_style" name="memberForm" action="${root }/member/register.do" method="post" onsubmit="return registerForm(this)">
			<div class="line">
				<label class="title">아이디</label>
				<span class="content">
					*<input type="text" name="id" />	
					<input type="button" value="아이디중복" onclick="idCheck(memberForm, '${root}')" />
				</span>
			</div>
			
			<div class="line">
				<label  class="title">비밀번호</label>
				<span class="content">
					*<input type="password" name="password" />
				</span>
			</div>
			
			<div class="line">
				<label class="title">비밀번호확인</label>
				<span class="content">
					*<input type="password" name="passwordCheck"/>
				</span>
			</div>
	
			<div class="line">
				<label class="title">이름</label>
				<span class="content">
					*<input type="text" name="name"/>
				</span>
			</div>
			
			<div class="line">
				<label class="title">주민번호</label>
				<span class="content">
					*<input type="text" name="jumin1" size="11" maxlength="6" />
					-<input type="text" name="jumin2" size="12" maxlength="7" />
				</span>	
			</div>
			
			<div class="line">
				<label class="title">이메일</label>
				<span class="content">
					<input type="text" name="email" size="25" />
				</span>
			</div>
			
			<div class="line">
				<label class="title">우편번호</label>
				<span class="content">
					<input type="text" name="zipcode" />
					<input type="button" value="우편번호검색" onclick="zipSearch(memberForm, '${root}')"/>
				</span>
		  	</div>
			
			<div class="line">
				<label class="title">주소</label>
				<span class="content">
					<input type="text" name="address" size="48" />
				</span>
			</div>
			
			<div class="line">
				<label class="title">직업</label>
				<span class="content">
					<select name="job">
						<option></option>
						<option value="회사원">회사원</option>
						<option value="학생">학생</option>
						<option value="전문직">전문직</option>
						<option value="기타">기타</option>
					</select>
				</span>
			</div>
			
			<div class="line">
				<label class="title">메일수신</label>
				<span class="content">
					<input type="radio" name="mailing" value="yes" />yes
					<input type="radio" name="mailing" value="no" />no
				</span>
			</div>
			
			<div class="line">
				<label class="title">관심분야</label>
				<span class="content">
					<input type="checkbox" name="interestValue" value="경제"/> 경제 &nbsp;
					<input type="checkbox" name="interestValue" value="IT"/> IT &nbsp;
					<input type="checkbox" name="interestValue" value="음악"/> 음악 &nbsp;
					<input type="checkbox" name="interestValue" value="미술"/> 미술 &nbsp;
					<input type="hidden" name="interest"/>
				</span>
			</div>
			
			<div class="line" style="width:498px; border-width:2px; text-align:center;">
				<input type="submit" value="가입" />
				<input type="reset" value="취소" />
			</div>
		</form>
	</div>
</body>
</html>