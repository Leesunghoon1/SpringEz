<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<h1>member list</h1>
	<table>
		<thead>
			<tr>
				<th>아이디</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${Mlist }" var ="Mlist" >
			<tr>
				<td>${Mlist.email }</td>
			</tr>
			</c:forEach>
		</tbody>
	</table>


	<jsp:include page="../common/footer.jsp"/>
</body>
</html>