<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>board</title>

<script type="text/javascript" src="${contextPath}/js/boardList.js" ></script>


</head>
<body>
	
	<input type="button" onclick="boardWrite()" value="글쓰기" />
					
	<table border="1" style="width:600px">
		<caption>게시판</caption>
		<colgroup>
			<col width='8%' />
			<col width='*%' />
			<col width='15%' />
			<col width='15%' />
		</colgroup>
		<thead>
			<tr>
				<th>번호</th> 
				<th>제목</th>
				<th>등록자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="listview" items="${boardList}" varStatus="status">	
				<c:url var="link" value="boardView">
					<c:param name="brdno" value="${listview.brdno}" />
				</c:url>		
										  				
				<tr>
					<td><c:out value="${searchVO.totRow-((searchVO.page-1)*searchVO.displayRowCount + status.index)}"/></td>
					<td style="max-width:100px; overflow:hidden; white-space:nowrap; text-overflow:ellipsis;"><a href="${link}"><c:out value="${listview.brdtitle}"/></a></td>
					<td><c:out value="${listview.brdwriter}"/></td>
					<td><c:out value="${listview.brddate}"/></td>
					<td><c:out value="${listview.brdhit}"/></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<form id="form1" name="form1" method="post" action="boardList">
	
	<jsp:include page="/WEB-INF/views/paging.jsp"/>
	
	<div>
		<input type="checkbox" name="searchType" value="brdtitle" <c:if test="${fn:indexOf(searchVO.searchType, 'brdtitle')!=-1}">checked="checked"</c:if>/>
		<label >제목</label>
		<input type="checkbox" name="searchType" value="brdmemo" <c:if test="${fn:indexOf(searchVO.searchType, 'brdmemo')!=-1}">checked="checked"</c:if>/>
		<label >내용</label>
		<input type="text" name="searchKeyword" style="width:150px;" maxlength="50" value='<c:out value="${searchVO.searchKeyword}"/>' onkeydown="if(event.keyCode == 13) { fn_formSubmit();}">
		<input name="btn_search" value="검색" type="button" onclick="searchSubmit()" />
	</div>
	
	</form>
</body>
</html>
