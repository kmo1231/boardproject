<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>board</title>

<script type="text/javascript" src="${contextPath}/js/boardView.js"></script>

</head>
<body>
	<form id="form1" method="post">
	<input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">
	<table border="1" style="width: 600px">
		<caption>게시판</caption>
		<colgroup>
			<col width='15%' />
			<col width='*%' />
		</colgroup>
		<tbody>
			<tr>
				<td>작성자</td>
				<td><c:out value="${boardInfo.brdwriter}" /></td>
			</tr>
			<tr>
				<td>제목</td>
				<td><c:out value="${boardInfo.brdtitle}" /></td>
			</tr>
			<tr>
				<td>내용</td>
				<!-- 내용부분에 입력한 html코드를 적용하기 위해 escapeXml설정 -->
				<td><c:out value="${boardInfo.brdmemo}" escapeXml="false"/></td>
			</tr>
			<tr>
				<td>첨부</td> 
				<td>
					<c:forEach var="filelist" items="${filelist}" varStatus="status">	
           				<a href="fileDownload?filename=<c:out value="${filelist.filename}"/>&downname=<c:out value="${filelist.realname }"/>"> 							 
						<c:out value="${filelist.filename}"/></a> <c:out value="${filelist.size2String()}"/><br/>
					</c:forEach>
				</td> 
			</tr>
			
		</tbody>
	</table>
	</form>
	<input type="button" onclick="brdList()" value="돌아가기" />
	<input type="button" onclick="brdDel()" value="삭제" />
	<input type="button" onclick="brdUpd()" value="수정" />
	
</body>
</html>
