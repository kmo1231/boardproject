<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<%
	String host = "http://"+ request.getServerName() + ":" + request.getServerPort();
	String contextPath = request.getContextPath();
%>

<c:set var="host" value="<%=host%>" />
<c:set var="contextPath" value="<%=contextPath%>" />

<script type="text/javascript">
var host = "${host}";
var contextPath = "${contextPath}";
</script>
