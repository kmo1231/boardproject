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
	
	<c:forEach var="replylist" items="${replylist}" varStatus="status">
	    <div style="border: 1px solid gray; width: 600px; padding: 5px; margin-top: 5px;">    
	        <c:out value="${replylist.rewriter}"/> <c:out value="${replylist.redate}"/>
	        <input type="button" onclick="replyDelete(${replylist.reno})" value="삭제">
	        <input type="button" onclick="replyUpdate(${replylist.reno})" value="수정">
	        <br/>
	        <div id="reply<c:out value="${replylist.reno}"/>"><c:out value="${replylist.rememo}"/></div>
	    </div>
	</c:forEach>
	
	<div id="replyDiv" style="width: 99%; display:none">
	    <form name="form2" id="form2" action="board5ReplySave" method="post">
	        <input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>"> 
	        <input type="hidden" name="reno" > 
	        <textarea name="rememo" rows="3" cols="60" maxlength="500"></textarea>
	        <input type="button" onclick="replyUpdateSave()" value="저장" >
	        <input type="button" onclick="replyUpdateCancel()" value="취소">
	    </form>
	</div>

	<p>&nbsp;</p>
	<div style="border: 1px solid; width: 600px; padding: 5px">
		<form name="form3" id="form3" method="post">
	    <input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">
		
		작성자: <input type="text" name="rewriter" size="20" maxlength="20"> <br/>
		<textarea name="rememo" rows="3" cols="60" maxlength="500" placeholder="댓글을 달아주세요."></textarea>
	    <input type="button" onclick="replySubmit()" value="저장" >
	    </form>
	</div>

</body>
</html>
