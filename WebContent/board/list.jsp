<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<table border="0" align="center" width="100%">
	<tr>
		<td colspan="7">
			<h2>게시글 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
				<c:choose>
					<c:when test="${search_option == 'writer' }">
						<option value="">- 선택 -</option>
						<option value="writer" selected>작성자</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="writer_subject_content">작성자+제목+내용</option>
					</c:when>
					<c:when test="${search_option == 'subject' }">
						<option value="">- 선택 -</option>
						<option value="writer">작성자</option>
						<option value="subject" selected>제목</option>
						<option value="content">내용</option>
						<option value="writer_subject_content">작성자+제목+내용</option>
					</c:when>
					<c:when test="${search_option == 'content' }">
						<option value="">- 선택 -</option>
						<option value="writer">작성자</option>
						<option value="subject">제목</option>
						<option value="content" selected>내용</option>
						<option value="writer_subject_content">작성자+제목+내용</option>
					</c:when>
					<c:when test="${search_option == 'writer_subject_content' }">
						<option value="">- 선택 -</option>
						<option value="writer">작성자</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="writer_subject_content" selected>작성자+제목+내용</option>
					</c:when>
					<c:otherwise>
						<option value="" selected>- 선택 -</option>
						<option value="writer">작성자</option>
						<option value="subject">제목</option>
						<option value="content">내용</option>
						<option value="writer_subject_content">작성자+제목+내용</option>
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
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
	<tr>
		<td style="padding: 0 0 20px 0;">
			<table border="1" align="center" style="width:100%;">
				<tr>
					<th>순번</th>
					<th>제목</th>
					<th>작성자</th>
					<th>등록일</th>
					<th>조회수</th>
					<th>ip</th>
					<th>회원번호</th>
					<th>공지</th>
					<th>비밀글</th>
					<th>자식글여부</th>
				</tr>
				<c:if test="${list.size() == 0 }">
					<tr>
						<td colspan="11" height="200">
							등록된 게시글이 없습니다.
						</td>
					</tr>
				</c:if>
				<c:forEach var="row" items="${list }">
					<tr>
						<td>
							<c:if test="${row.noticeNo > 0 }">
								공지
							</c:if>
							<c:if test="${row.noticeNo == 0 }">
								${jj }
							</c:if>
						</td>
						<td>
							<c:forEach var="i" begin="2" end="${row.stepNo }" step="1"><!-- varStatus="status" -->
								&nbsp;
							</c:forEach>
							<c:if test="${row.stepNo > 1 }">
								<c:set var="reVar" value="[Re]:" />
							</c:if>
							<c:if test="${row.stepNo <= 1 }">
								<c:set var="reVar" value="" />
							</c:if>
							
							<a href="#" onclick="suntaek_proc('view', '0', '${row.no}');">${reVar } ${row.subject }</a>
						</td>
						<td>${row.writer}</td>
						<td>${row.regiDate}</td>
						<td>${row.hit}</td>
						<td>${row.ip}</td>
						<td>${row.memberNo}</td>
						<td>${row.noticeNo}</td>
						<td>${row.secretGubun}</td>
						<td>${row.child_counter}</td>
					</tr>
					<c:set var="jj" value="${jj = jj - 1 }"/>
				</c:forEach>
			</table>
		</td>
	</tr>
	
	<c:if test="${totalRecord > 0 }">
		<tr>
			<td colspan="11" height="50" align="center">
				<div style="display: none;">
					pageNumber : ${pageNumber }<br>
					pageSize : ${pageSize }<br>
					blockSize : ${blockSize }<br>
					totalRecord : ${totalRecord }<br>
					jj : ${jj }<br>
					startRecord : ${startRecord }<br>
					lastRecord : ${lastRecord }<br>
					totalPage : ${totalPage }<br>
					startPage : ${startPage }<br>
					lastPage : ${lastPage }
				</div>
				
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
	<tr>
		<td colspan="11" height="50" align="right">
			<button type="button" onclick="suntaek_all();">전체목록</button>
			<button type="button" onclick="suntaek_proc('write', '1', '');">글쓰기</button>
		</td>
	</tr>
</table>