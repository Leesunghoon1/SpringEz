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

<!-- 검색라인 -->

	<div class="container-fluid">	
		<form action="/board/list" method="get">
		<c:set value="${ph.pgvo.type }" var="typed"></c:set>
			<select class="form-select" name="type" aria-label="Default select example">
				<option ${typed eq null ? 'selected' : ''}>Open this select menu</option>
				<option value="t" ${typed eq 't' ? 'selected' : '' }>Title</option>
				<option value="w" ${typed eq 'w' ? 'selected' : '' }>Writer</option>
				<option value="c" ${typed eq 'c' ? 'selected' : '' }>Content</option>
				<option value="tw "${typed eq 'tw' ? 'selected' : '' }>Title or Writer</option>
				<option value="tc "${typed eq 'tc' ? 'selected' : '' }>Title or Content</option>
				<option value="cw "${typed eq 'cw' ? 'selected' : '' }>Content or Writer</option>
				<option value="twc "${typed eq 'twc' ? 'selected' : '' }>all</option>
			</select>
			
			
			<input class="form-control me-2" name="keyword" value="${ph.pgvo.keyword }" type="search" placeholder="Search" aria-label="Search">
			<input type="hidden" name="pageNo" value='1'>
			<input type="hidden" name="qty" value=${ph.pgvo.qty }>
			<button class="btn btn-outline-success" type="submit">
				검색 <span class="btn btn-outline-success">
					${ph.totalCount } <span class="visually-hidden"></span>
				</span>
			</button>
		</form>
	</div>
	<table class="table table=hover">
<thead>


<tr>
	<th>#</th>
	<th>제목</th>
	<th>작성자</th>
	<th>작성일</th>
	<th>조회수</th>
</tr>
</thead>

<tbody>
<c:forEach items="${list}" var="bvo">
<tr>
<td>${bvo.bno }</td>
<td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a></td>
<td>${bvo.writer }</td>
<td>${bvo.modAt }</td>
<td>${bvo.readCount }</td>
</tr>
</c:forEach>
</tbody>
</table>

<!-- 페이징 라인 -->

<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!-- 이전  -->
  
    <li class="page-item ${(ph.prev eq false)? 'disabled':''}">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage - 1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
		pv
      </a>
    </li>
    
    <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
    </c:forEach>
    <!-- 다음 -->
    <li class="page-item ${(ph.next eq false) ? 'disabled' : ''}" >
      <a class="page-link" href="/board/list?pageNo=${ph.endPage + 1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
        nx
      </a>
    </li>
  </ul>
</nav>

<jsp:include page="../common/footer.jsp"/>
</body>
</html>