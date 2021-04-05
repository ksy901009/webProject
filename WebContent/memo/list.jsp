<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	function update(no, writer, content) {
		$("#span_no").text(no);
		$("#writer").val(writer);
		$("#content").val(content);
		
		$("#btnSave").hide();
		$("#btnUpdate").show();
	}
</script>
</head>
<body>
<table border="1">
	<tr>
		<td colspan="11">${totalRecord }개의 메모가 있습니다.</td>
	</tr>
	<tr>
		<th>순번</th>
		<th>NO</th>
		<th>이름</th>
		<th>메모</th>
		<th>날짜</th>
		<th>수정</th>
		<th>삭제</th>
	</tr>
	<c:forEach var="row" items="${list }">
		<tr>
			<td>${jj }</td>
			<td>${row.no}</td>
			<td>${row.writer}</td>
			<td>${row.content}</td>
			<td>${row.regiDate}</td>
			<td><button type="button" onclick="update('${row.no}', '${row.writer }', '${row.content }');">수정</button></td>
			<td><button type="button" onclick="suntaek_proc('deleteProc', '0', '${row.no}');">삭제</button></td>
		</tr>
		<c:set var="jj" value="${jj = jj - 1 }"/>
	</c:forEach>
	
	<c:if test="${totalRecord > 0 }">
		<tr>
			<td colspan="11">
				<a href="#" onclick="suntaek_proc('list', '1', '');">[첫페이지]</a>&nbsp;&nbsp;
				
				<c:if test="${startPage > blockSize }">
					<a href="#" onclick="suntaek_proc('list', '${startPage - blockSize }', '');">[이전10개]</a>
				</c:if>
				<c:if test="${startPage <= blockSize }">
					[이전10개]
				</c:if>
				&nbsp;
				
				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
					<c:if test="${i == pageNumber }">
						[${i }]
					</c:if>
					<c:if test="${i != pageNumber }">
						<a href="#" onclick="suntaek_proc('list', '${i}', '');">${i }</a>
					</c:if>
				</c:forEach>
				
				&nbsp;
				<c:if test="${lastPage < totalPage }">
					<a href="#" onclick="suntaek_proc('list', '${startPage + blockSize}', '');">[다음10개]</a>
				</c:if>
				<c:if test="${lastPage >= totalPage }">
					[다음10개]
				</c:if>
				
				&nbsp;&nbsp;
				<a href="#" onclick="suntaek_proc('list', '${totalPage}', '');">[끝페이지]</a>&nbsp;&nbsp;
			</td>
		</tr>
	</c:if>
</table>
</body>
</html>