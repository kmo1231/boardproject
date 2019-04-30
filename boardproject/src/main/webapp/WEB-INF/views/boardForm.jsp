<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="common.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>board</title>

<script type="text/javascript" src="${contextPath}/js/boardForm.js" ></script>

</head>
<body>
	<form name="form1" action="boardSave" method="post">
		<table border="1" style="width:600px">
			<caption>게시판</caption>
			<colgroup>
				<col width='15%' />
				<col width='*%' />
			</colgroup>
			<tbody>
				<tr>
					<td>작성자</td> 
					<td><input type="text" name="brdwriter" size="20" maxlength="20"></td> 
				</tr>
				<tr>
					<td>제목</td> 
					<td><input type="text" name="brdtitle" size="70" maxlength="250"></td> 
				</tr>
				<tr>
					<td>내용</td> 
					<td><textarea name="brdmemo" rows="5" cols="60"></textarea></td> 
				</tr>
			</tbody>
		</table>    
		<input type="button" id="saveBtn" value="저장" onclick="formSave()"/>
	</form>
	
</body>
</html>
