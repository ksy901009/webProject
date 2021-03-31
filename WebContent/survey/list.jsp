<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<script>
	$(document).ready(function() {
		$("#goWrite").click(function() {
			write();
		});
	});
	
	function writeFlow() {
		write();
	}
</script>
<table border="0" align="center" width="80%">
	<tr>
		<td>
			<h2>설문조사 목록</h2>
		</td>
	</tr>
	<tr>
		<td colspan="7">
			<select name="search_option" id="search_option">
			<c:choose>
				<c:when test="${search_option == 'question' }">
					<option value="">- 선택 -</option>
					<option value="question" selected>질문내용</option>
				</c:when>
				<c:otherwise>
					<option value="" selected>- 선택 -</option>
					<option value="question">질문내용</option>
				</c:otherwise>
			</c:choose>
			</select>
			<input type="text" name="search_data" id="search_data" value="${search_data }" style="width: 150px;">&nbsp;
			<input type="date" id="search_date_s" value="${search_date_s }" style="width: 150px;">~
			<input type="date" id="search_date_e" value="${search_date_e }" style="width: 150px;">
			<br>
			<input type="checkbox" id="search_date_check" name="search_date_check" value="0" onclick="dateCheckBox();"><span style="color:blue; font-size: 9px;">(날짜 검색시 체크)</span>&nbsp;
			<input type="button" value="검색" onclick="search();">
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
	<tr>
		<td>
			<table border="1" width="100%">
				<tr>
					<th>순번</th>
					<th>질문</th>
					<th>기간</th>
					<th>참여수</th>
					<th>상태</th>
				</tr>
				<c:if test="${list.size() == 0 }">
					<tr>
						<td colspan="11" height="200" align="center">
							등록된 설문이 없습니다.
						</td>
					</tr>
				</c:if>
				<c:forEach var="row" items="${list }">
					<tr>
						<td>${jj }</td>
						<td>
							<a href="#" onclick="suntaek_proc('view', '0', '${row.no }');">
								${row.question }
							</a>
						</td>
						<td>${row.start_date }<br>${row.last_date }</td>
						<td>${row.survey_counter }</td>
						<td>${row.status }</td>
					</tr>
					<c:set var="jj" value="${jj = jj - 1 }"></c:set>
				</c:forEach>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="7" height="50" align="center">
			페이저
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
					<a href="#" onclick="suntaek_proc('list', '${i }', '');">${i }</a>
				</c:if>
			</c:forEach>
			
			&nbsp;
			<c:if test="${lastPage < totalPage }">
				<a href="#" onclick="suntaek_proc('list', '${startPage + blockSize }', '');">[다음10개]</a>
			</c:if>
			<c:if test="${lastPage >= totalPage }">
				[다음10개]
			</c:if>
			
			&nbsp;&nbsp;
			<a href="#" onclick="suntaek_proc('list', '${totalPage}', '');">[끝페이지]</a>&nbsp;&nbsp;
		</td>
	</tr>
	<tr>
		<td colspan="7" height="50" align="right">
			<button type="button" onclick="suntaek_list('all');">전체 설문목록</button>&nbsp;
			<button type="button" onclick="suntaek_list('ing');">진행중인 설문목록</button>&nbsp;
			<button type="button" onclick="suntaek_list('end');">종료된 설문목록</button>&nbsp;
			<button type="button" onclick="suntaek_proc('write', '', '');">등록하기</button>
		</td>
	</tr>
</table>
<script>
// function goPage(value1, value2, value3) {
// 	if(value1 == 'survey_list') {
// 		pageNumberChange(value2);
// 	} else if(value1 == 'survey_write') {
// 		location.href = '${path}/survey_servlet/chuga.do';
// 	} else if(value1 == 'survey_view') {
// 		location.href = '${path}/survey_servlet/view.do?pageNumber=' + value2 + '&no=' + value3;
// 	}
// }

function search() {
	$("#span_search_option").text($("#search_option").val());
	$("#span_search_data").text($("#search_data").val());
	
	if($("input:checkbox[id=search_date_check]").is(":checked") == true) {
		$("#span_search_date_check").text($("#search_date_check").val());
		$("#span_search_date_s").text($("#search_date_s").val());
		$("#span_search_date_e").text($("#search_date_e").val());
	} else {
		$("#span_search_date_check").text('');
		$("#span_search_date_s").text('');
		$("#span_search_date_e").text('');
		$("#search_date_s").val('');
		$("#search_date_e").val('');
	}
	
	suntaek_proc('list', '1', '');
// 	suntaek_page('1');
}

function dateCheckBox() {
	if($("input:checkbox[id=search_date_check]").is(":checked") == true) {
		$("#span_search_date_check").text($("#search_date_check").val());
		$("#span_search_date_s").text($("#search_date_s").val());
		$("#span_search_date_e").text($("#search_date_e").val());
	} else {
		$("#span_search_date_check").text('');
		$("#span_search_date_s").text('');
		$("#span_search_date_e").text('');
		$("#search_date_s").val('');
		$("#search_date_e").val('');
	}
}
</script>