<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border="0" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>방명록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'name' }">
						<option value="">- 선택 -</option>
						<option value="name" selected>이름</option>
						<option value="email">이메일</option>
						<option value="name_email">이름+이메일</option>
					</c:when>
					<c:when test="${search_option == 'email' }">
						<option value="">- 선택 -</option>
						<option value="name">이름</option>
						<option value="email" selected>이메일</option>
						<option value="name_email">이름+이메일</option>
					</c:when>
					<c:when test="${search_option == 'name_email' }">
						<option value="">- 선택 -</option>
						<option value="name">이름</option>
						<option value="email">이메일</option>
						<option value="name_email" selected>이름+이메일</option>
					</c:when>
					<c:otherwise>
						<option value="" selected>- 선택 -</option>
						<option value="name">이름</option>
						<option value="email">이메일</option>
						<option value="name_email">이름+이메일</option>
					</c:otherwise>
				</c:choose>
			</select>
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;">
			&nbsp;
			<input type="button" value="검색" onclick="search();">
			
			<script>
				function search() {
					if(confirm('검색 OK?')) {
						$("#span_search_option").text($("#search_option").val());
						$("#span_search_data").text($("#search_data").val());
						suntaek_proc('list', '1', '');
					}
				}
			</script>
		</td>
	</tr>
	<tr>
		<td><input type="button" value="글쓰기" onclick="suntaek_proc('write', '1', '0');"></td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">${totalRecord }개의 방명록이 있습니다.</td>
	</tr>
	<tr>
		<td style="padding: 0 0 20px 0;">
			<table border="1" align="center" style="width:100%;">
				<c:if test="${list.size() == 0 }">
					<tr>
						<td colspan="11" height="200">
							작성된 방명록이 없습니다.
						</td>
					</tr>
				</c:if>
				<c:forEach var="row" items="${list }">
					<tr>
						<td>이름</td>
						<td>${row.name}</td>
						<td>날짜</td>
						<td>${row.regi_date}</td>
					</tr>
					<tr>
						<td>이메일</td>
						<td colspan="3">${row.email}</td>
					</tr>
					<tr>
						<td colspan="4">
							<a href="#" onclick="suntaek_proc('view', '0', '${row.no}');">
								${row.content}
							</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
</table>