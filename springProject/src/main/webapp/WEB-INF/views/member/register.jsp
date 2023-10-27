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

<!-- email, pwd, nik_name -->
<div class="container">


<form action="/member/register" method="post" >

<h4>회원 가입 </h4>
<div class="mb-3">
  <label for="e" class="form-label">email</label>
  <input type="email" class="form-control" name="email" id="e" placeholder="example@">
</div>

<div class="mb-3">
  <label for="p" class="form-label">pwd</label>
  <input type="password" class="form-control" name="pwd" id="p" placeholder="pwd">
</div>

<div class="mb-3">
  <label for="n" class="form-label">nick_name</label>
  <input type="text" class="form-control" name="nickName" id="n" placeholder="nick_name">
</div>
<button type="submit">Register</button>

</form>
</div>




<jsp:include page="../common/footer.jsp"/>
</body>
</html>