<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
           <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
              <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
                  <%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
           
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert detail here</title>



</head>
<jsp:include page="../common/header.jsp"/>
<jsp:include page="../common/nav.jsp"/>
<body>
<c:set value="${bdto.bvo}" var="bvo"></c:set>

<h1>확인 페이지</h1>
<table border="1">
<!--value="${bdto.bvo }" 를 bvo 이름으로 쓰겠다고 선언함 c:set  -->
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

<!-- 댓글라인  -->
<!-- 댓글라인 -->
<div>
<sec:authorize access="isAuthenticated()">
<!-- 권한 있을때 안쪽 테그라인 사용 -->
<sec:authentication property="principal.mvo.email" var="authEmail"/>
   <div class="input-group mb-3">
   <c:if test="${authEmail ne null }" >
     <span class="input-group-text" id="cmtWriter">${authEmail }</span>
	</c:if>
     <input type="text" class="form-control" placeholder="Comment Content" id="cmtText">
     
     <button class="btn btn-primary" type="button" id="cmtPostBtn">POST</button>
     
   </div>
</sec:authorize>
   <!-- 댓글 표시 라인 -->
<table class="table">
    <tr>
      <th scope="col">#</th>
      <th scope="col">Writer</th>
      <th scope="col">Content</th>
      <th scope="col">Mod-Date</th>
    </tr>
   <tbody id="cmtArea">
       <tr>
         <th scope="row">1</th>
         <td>writer</td> <!-- writer -->
         <td>content</td> <!-- content -->
         <td>reg_date</td> <!-- modAt -->
       </tr>
    </tbody>
</table>
</div>

	<!-- file 표현 영역 -->

<div>
	<ul>
		<!-- 파일 개수만큼 li를 추가하여 파일을 표시 타입이 1일경우만 표시 -->	
		<!-- li
		     div => img 그림표시
		     div => div 파일이름, 작성일자 span 크기 설정
		-->	
		<!-- 하나의 파일로 생성 -->
		<c:forEach items="${bdto.flist}" var="fvo">
					<li>
					<!-- /upload/year/month/dat/uuid_th_filename -->
						<img alt="fffff" src="/upload/${fn: replace(fvo.saveDir, '\\', '/')}/${fvo.uuid}_th_${fvo.fileName}">
					<div>
				<!-- 파일모양 아이콘 같은걸 넣을수있음-->
				
				<div>${fvo.fileName }
		
				</div>
				
				${fvo.regAt}
			</div>
			<span>${fvo.fileSize }Byte</span>
		</li>
		
		</c:forEach>
	</ul>
	

</div>

	

<!-- 댓글 페이징 라인 -->
<div>
	<div>
		<button type="button" id="moreBtn" data-page="1"
		class="btn btn-outline-dark" style="visibility:hidden">MORE+</button>
	</div>
</div>


<!-- 모달창  -->
<div class="modal" id="myModal" tabindex="-1">
      <div class="modal-dialog">
         <div class="modal-content">
            <div class="modal-header">
               <h5 class="modal-title">Writer</h5>
               <button type="button" class="btn-close" data-bs-dismiss="modal"
                  aria-label="Close"></button>
            </div>
            <div class="modal-body">
               <div class="input-group mb-3">
                  <input type="text" class="form-control" placeholder="Comment Content" id="cmtTextMod">
                  <button class="btn btn-primary" type="button" id="cmtModBtn">POST</button>
               </div>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
            </div>
         </div>
      </div>
   </div>


<script >
let bnoVal=`<c:out value='${bvo.bno}'/>`;

console.log(bnoVal);
</script>


	<script type="text/javascript" src="/resources/js/boardComment.js"></script>
	<script type="text/javascript">
	getCommentList(bnoVal);
	</script>
	
	<jsp:include page="../common/footer.jsp"/>
</body>
</html>