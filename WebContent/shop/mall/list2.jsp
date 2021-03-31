<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>
<table border="0" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>상품 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'name' }">
						<option value="">- 선택 -</option>
						<option value="name" selected>상품명</option>
						<option value="description">상품내용</option>
						<option value="name_description">상품명+상품내용</option>
					</c:when>
					<c:when test="${search_option == 'description' }">
						<option value="">- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description" selected>상품내용</option>
						<option value="name_description">상품명+상품내용</option>
					</c:when>
					<c:when test="${search_option == 'name_description' }">
						<option value="">- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description">상품내용</option>
						<option value="name_description" selected>상품명+상품내용</option>
					</c:when>
					<c:otherwise>
						<option value="" selected>- 선택 -</option>
						<option value="name">상품명</option>
						<option value="description">상품내용</option>
						<option value="name_description">상품명+상품내용</option>
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
						suntaek_page('1');
					}
				}
			</script>
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
	<tr>
		<td style="padding: 0 0 20px 0;">
			${1 mod 3 }
			${2 % 3 }
			${3 % 3 }
			${3 mod 3 }
			<table border="1" align="center" style="width:100%;">
				<c:forEach var="i" begin="0" end="8" step="1"> <!-- 리스트 -->
					<c:if test="${i mod 3 == 0 }"></c:if>
					<tr>
						<td></td>
					</tr>
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<c:if test="${totalRecord > 0 }">
		<tr>
			<td colspan="11" height="50" align="center">
				<a href="#" onclick="suntaek_proc('list', '1', '');">[첫페이지]</a>&nbsp;&nbsp;
				
				<c:if test="${startPage > blockSize }">
					<a href="#" onclick="suntaek_proc('list', '${startPage - blockSize}', '');">[이전10개]</a>
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
	<tr>
		<td colspan="11" height="50" align="right">
			<button type="button" onclick="suntaek_all();">전체목록</button>
			<button type="button" onclick="goPage('write', '');">글쓰기</button>
		</td>
	</tr>
</table>