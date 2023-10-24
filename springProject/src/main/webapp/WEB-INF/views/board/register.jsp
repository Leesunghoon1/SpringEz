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

<form action="/board/register" method="post" enctype="multipart/form-data">

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

<div class="mb-3">
  <input type="file" class="form-control" name="files" id="files" style="display:none" multiple="multiple">
  <!-- input버튼 trigger 용도의 button  -->
  <button type="button" id="trigger" class="btn btn-outline-primary">파일 등록</button>
</div>

<!-- 첨부파일 표시될 영역  -->
<div class="mb-3" id="fileZone">


</div>

<button type="submit" class="btn btn-dark" id="regBtn">등록</button>

</form>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>