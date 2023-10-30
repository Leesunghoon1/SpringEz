<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert detail here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>

<jsp:include page="../common/header.jsp"></jsp:include>

<body>
<h1>Board Detail Page</h1>




<table border="1">
<c:forEach items="${list}" var="mvo">
	<tr>
		<th>EMAIL</th>
		<td>${mvo.Email }</td>
	</tr>
	<tr>
		<th>PWD</th>
		<td>${mvo.pwd }</td>
	</tr>
	<tr>
		<th>nick_name</th>
		<td>${mvo.nick_name }</td>
	</tr>
	<tr>
		<th>REG_DATE</th>
		<td>${bvo.registerDate }</td>
	</tr>
	</c:forEach>
</table>

</body>
</html>