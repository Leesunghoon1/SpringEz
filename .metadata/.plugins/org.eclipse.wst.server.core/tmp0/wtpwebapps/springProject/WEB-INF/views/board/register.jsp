<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register page</title>
</head>
<body>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>

<form action="/board/register" method="post" >



<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">제목</label>
  <input type="text" class="form-control" name="title" id="exampleFormControlInput1" placeholder="제목">
</div>

<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">작성자</label>
  <input type="text" class="form-control" name="writer" id="exampleFormControlInput1" placeholder="작성자">
</div>


<div class="mb-3">
  <label for="exampleFormControlInput1" class="form-label">내용</label>
  <input type="text" class="form-control" name="content" id="exampleFormControlInput1" placeholder="내용">
</div>

<button type="submit" class="btn btn-dark">Dark</button>
</form>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>