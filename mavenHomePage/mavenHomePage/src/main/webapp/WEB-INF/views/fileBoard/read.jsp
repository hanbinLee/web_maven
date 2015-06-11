<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt"	uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<c:set var="root" value="${pageContext.request.contextPath }"/>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>게시판 읽기</title>
	<link rel="stylesheet" type="text/css" href="${root }/css/fileBoard/style.css">
	<script type="text/javascript">
		function replyFun(root, boardNumber, groupNumber, sequenceNumber, sequenceLevel, pageNumber){			
			var url=root + "/fileBoard/write.do?boardNumber=" + boardNumber ; 
				url+="&groupNumber=" + groupNumber; 
				url+="&sequenceNumber=" + sequenceNumber; 
				url+="&sequenceLevel=" + sequenceLevel; 
				url+="&pageNumber=" + pageNumber;
			//alert(url);
			location.href=url;
		}
		
		function delFun(root, boardNumber , pageNumber){
			var url=root + "/fileBoard/delete.do?boardNumber=" + boardNumber;
			url+="&pageNumber=" + pageNumber;
			//alert(url);
			location.href=url;
		}
		
		function updateFun(root, boardNumber, pageNumber){
			var url= root+ "/fileBoard/update.do?boardNumber=" + boardNumber;
			url+="&pageNumber=" + pageNumber;
			//alert(url);
			location.href=url;
		}
	</script>
</head>
<body>
	<c:set var="root" value="${pageContext.request.contextPath }"/>
	<table border="1" width="510" cellpadding="2"  cellspacing="0" align="center">
		<tr>
			<td align="center"  height="20" width="125">글번호</td>
			<td align="center"  height="20" width="125">${board.boardNumber }</td>
			
			<td align="center"  height="20" width="125">조회수</td>
			<td align="center"  height="20" width="125">${board.readCount }</td>
		</tr>
		
		<tr>
			<td align="center"  height="20" width="125">작성자</td>
			<td align="center"  height="20" width="125">${board.writer }</td>
			
			<td align="center"  height="20" width="125">작성일</td>
			<td align="center"  height="20" width="125"><fmt:formatDate value="${board.writeDate }" type="date"/></td>
		</tr>
		
		<tr>
			<td align="center"  height="200" width="125">글내용</td>
			<td valign="top"  height="200" colspan="3">${board.content }</td>
		</tr>
		
		<c:if test="${board.fileName!=null }">
			<tr>
				<td align="center"  height="20" width="125">파일명</td>
				<td colspan="3">
					<a href="${root }/fileBoard/downLoad.do?boardNumber=${board.boardNumber}">${board.fileName }</a>
				</td>
			</tr>
		</c:if>
		
		<tr>
			<td height="30" colspan="4" align="center">
				<input type="button" value="글수정" onclick="updateFun('${root}', '${board.boardNumber}', '${pageNumber}')" />
				<input type="button" value="글삭제" onclick="delFun('${root}', '${board.boardNumber }', '${pageNumber }')"/>
				<input type="button" value="답글" onclick="replyFun('${root}', '${board.boardNumber }', '${board.groupNumber }', '${board.sequenceNumber }', '${board.sequenceLevel }', '${pageNumber }')"/>
				<input type="button" value="글목록" onclick="location.href='${root}/fileBoard/list.do?pageNumber=${pageNumber }'"/>
			</td>
		</tr>
	</table>
</body>
</html>