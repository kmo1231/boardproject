<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common/common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>board</title>

<script src="${contextPath }/js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/js/boardForm.js" ></script>

</head>
<body>
	<form name="form1" action="boardSave" method="post" enctype="multipart/form-data" >
		<table border="1" style="width:600px">
			<caption>게시판</caption>
			<colgroup>
				<col width='15%' />
				<col width='*%' />
			</colgroup>
			<tbody>
				<tr>
					<td>작성자</td> 
					<td><input type="text" id="brdwriter" name="brdwriter" size="20" maxlength="20" value="<c:out value="${boardInfo.brdwriter}"/>"/></td> 
				</tr>
				<tr>
					<td>제목</td> 
					<td><input type="text" id="brdtitle" name="brdtitle" size="70" maxlength="250" value="<c:out value="${boardInfo.brdtitle}"/>" /></td> 
				</tr>
				<tr>
					<td>내용</td> 
					<td><textarea id="brdmemo" name="brdmemo" rows="5" cols="60"><c:out value="${boardInfo.brdmemo}" /> </textarea></td> 
				</tr>
				<tr>
					<td>첨부</td> 
					<td>
						<p>삭제할 파일을 체크 후 저장하세요</p>
						<c:forEach var="filelist" items="${filelist}" varStatus="status">
							<input type="checkbox" name="fileno" value="<c:out value="${filelist.fileno}"/>">	
            				<a href="fileDownload?filename=<c:out value="${filelist.filename}"/>&downname=<c:out value="${filelist.realname }"/>"> 							 
							<c:out value="${filelist.filename}"/></a> <c:out value="${filelist.size2String()}"/><br/>
						</c:forEach>					
					
						<input type="file" name="uploadfile" multiple="" />
					</td>
				</tr>
				
			</tbody>
		</table>
		<input type="hidden" name="brdno" value="<c:out value="${boardInfo.brdno}"/>">  
		<input type="button" id="saveBtn" value="저장" onclick="formSave()"/>
	</form>
	
</body>
</html>
