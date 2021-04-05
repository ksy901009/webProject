<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="0" align="center" width="95%">
	<tr>
		<td colspan="2"><h2>comment 작성</h2></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" id="writer"></td>
		<td>비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">
			<input type="text" name="commentContent" id="commentContent" style="width:400px;">
			<button type="button" onclick="commentProc('commentProc', '${dto.no}');">확인</button>
		</td>
	</tr>
</table>
					
<c:if test="${list.size() > 0 }">
	<table border="0" align="center" width="95%">
		<c:forEach var="dto" items="${list }">
			<tr>
				<td style="padding: 0 0 10 0;">
					<table border="0" align="center" style="width: 100%;">
						<tr>
							<td>${dto.writer } &nbsp; (${dto.regiDate })</td>
						</tr>
						<tr>
							<td>${dto.content }</td>
						</tr>
					</table>
					<hr>
				</td>
			</tr>
			<c:set var="jj" value="${jj = jj - 1 }"/>
		</c:forEach>
		
		<tr>
			<td colspan="7" height="50" align="center">
				<a href="#comment" onclick="comment_suntaek_page('1');">[첫페이지]</a>&nbsp;&nbsp;
				
				<c:if test="${startPage > blockSize }">
					<a href="#comment" onclick="comment_suntaek_page('${startPage - blockSize}');">[이전10개]</a>
				</c:if>
				<c:if test="${startPage <= blockSize }">
					[이전10개]
				</c:if>
				&nbsp;
				
				<c:forEach var="i" begin="${startPage }" end="${lastPage }" step="1">
					<c:if test="${i == commentPageNumber }">
						[${i }]
					</c:if>
					<c:if test="${i != commentPageNumber }">
						<a href="#comment" onclick="comment_suntaek_page('${i}');">${i }</a>
					</c:if>
				</c:forEach>
				
				&nbsp;
				<c:if test="${lastPage < totalPage }">
					<a href="#comment" onclick="comment_suntaek_page('${startPage + blockSize}');">[다음10개]</a>
				</c:if>
				<c:if test="${lastPage >= totalPage }">
					[다음10개]
				</c:if>
				
				&nbsp;&nbsp;
				<a href="#comment" onclick="comment_suntaek_page('${totalPage}');">[끝페이지]</a>&nbsp;&nbsp;
			</td>
		</tr>
	</table>
</c:if>
<c:if test="${list.size() == 0 }">
	댓글이 없습니다.
</c:if>
<script>
	function comment_suntaek_page(value1) {
		$("#span_commentPageNumber").text(value1);
		commentList();
	}
</script>