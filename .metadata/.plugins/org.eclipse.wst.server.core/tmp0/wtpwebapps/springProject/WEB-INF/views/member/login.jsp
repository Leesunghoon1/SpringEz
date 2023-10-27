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
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp"/>

<form action="/member/login" method="post">
email : <input type="email" name="id" placeholder="아이디 입력">
Password : <input type="password" name="pw" placeholder="비밀번호 입력">
<br>
<button type="submit">log in</button>
</form>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>