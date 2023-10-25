<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
                     <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<body>
<form action="/board/modify" method="post" enctype="multipart/form-data">
<c:set value="${bdto.bvo }" var="bvo"></c:set>
<table border="1">
	<tr>
		<th>BNO</th>
		<td><input type="text" name="bno" value="${bvo.bno}"readonly="readonly"></td>	
	</tr>
	<tr>
		<th>TITLE</th>
		<td><input type="text" name="title" value="${bvo.title}"></td>	
	</tr>
	<tr>
		<th>WRITER</th>
		<td>${bvo.writer }</td>	
	</tr>
	<tr>
		<th>CONTENT</th>
		<td><textarea rows="3" cols="30" name="content">${bvo.content }</textarea></td>	
	</tr>
</table>
<c:forEach items="${bdto.flist }" var="fvo">
					<li>
					<!-- /upload/year/month/dat/uuid_th_filename -->
						<img alt="fffff" src="/upload/${fn: replace(fvo.saveDir, '\\', '/')}/${fvo.uuid}_th_${fvo.fileName}">
					<div>  
				<div>${fvo.fileName }</div>
				<button type="button" class = 'remove-x' data-uuid="${fvo.uuid }">삭제</button>
				${fvo.regAt}
			</div>
			<span>${fvo.fileSize }Byte</span>
		</li>
	</c:forEach>
	
	file : <input type="file" id="files" name="files" multiple="multiple" style="display:none"><br> <!-- multiple="multiple" 한번에 여러 파일을 업로드 가능하게 만들어줌 -->
	<button type="button" id="trigger">FileUpload</button><br>
	<div id="fileZone">
	
	</div>

<button type="submit" class="btn btn-dark" id="regBtn">수정</button>
</form>
<script type="text/javascript" src="/resources/js/boardModify.js"></script>
<script type="text/javascript" src="/resources/js/boardRegister.js"></script>
<jsp:include page="../common/footer.jsp"/>
</body>
</html>