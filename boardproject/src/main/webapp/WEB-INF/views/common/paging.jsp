<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${searchVO.totPage > 1}">
	<div class="paging">
		<c:if test="${searchVO.pageStart > 1 }">
			<a href="javascript:searchPageSubmit(1);">[처음]</a>
			<a href="javascript:searchPageSubmit(${searchVO.page-1 });">&lt;</a>
		</c:if>
		
		<c:forEach var="i" begin="${searchVO.pageStart }" end="${searchVO.pageEnd }" step="1" varStatus="status">
			<c:url var="pageLink" value="boardList">
				<c:param name="page" value="${i }" />
			</c:url>
			<c:choose>
				<c:when test="${i eq searchVO.page }">
					<c:out value="${i }" />
				</c:when>
				<c:otherwise>
					<a href="javascript:searchPageSubmit(${i});"><c:out value="${i }" /></a>
				</c:otherwise>
			</c:choose>
			<c:if test="${not status.last }">|</c:if>
		</c:forEach>
		<c:if test="${searchVO.pageEnd < searchVO.totPage }">
			<a href="javascript:searchPageSubmit(${searchVO.page+1 });">&gt;</a>
			<a href="javascript:searchPageSubmit(${searchVO.totPage });">[마지막]</a>
		</c:if>
	</div>
	
	<input type="hidden" name="page" id="page" value="" />
	<br/>
	
</c:if>