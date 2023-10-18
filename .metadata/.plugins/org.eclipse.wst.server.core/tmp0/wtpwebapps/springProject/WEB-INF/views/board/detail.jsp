<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert detail here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
</head>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<body>

<h1>확인 페이지</h1>
<table border="1">
	<tr>
		<th>BNO</th>
		<td>${bvo.bno }</td>
	</tr>
	<tr>
		<th>TITLE</th>
		<td>${bvo.title }</td>
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${bvo.writer }</td>
	</tr>
	<tr>
		<th>REG_DATE</th>
		<td>${bvo.regAt }</td>
	</tr>
	<tr>
		<th>CONTENT</th>
		<td>${bvo.content }</td>
	</tr>
	<tr>
		<th>read_count</th>
		<td>${bvo.readCount }</td>
	</tr>
</table>


<a href="/board/modify?bno=${bvo.bno }"><button>수정</button></a>
<a href="/board/remove?bno=${bvo.bno }"><button>삭제</button></a>
<a href="/board/list?bno=${bvo.bno }"><button>리스트</button></a>


<jsp:include page="../common/footer.jsp"/>
</body>
</html>