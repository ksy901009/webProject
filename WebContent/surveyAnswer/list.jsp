<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<script>
	$(document).ready(function() {
		$("#goWrite").click(function() {
			write();
		});
	});
</script>
<div style="display: none;">
	<span id="span_answer_no"></span><br>
	count : <span id="span_count1"></span><br>
	listSize : <span id="span_list_size">${totalRecord }</span><br>
	answerTotal : <span id="span_answer_total"></span>
</div>
<table border="0" align="center" width="80%">
	<tr>
		<td>
			<h2>설문조사 목록</h2>
		</td>
	</tr>
	<tr>
		<td style="padding: 10px 0px 5px;">전체 ${totalRecord }건입니다.</td>
	</tr>
	<tr>
		<td>
			<c:forEach var="row" items="${list }">
				<div style="display: none;">
					<a named="a_${row.no }"></a>
					q_${jj } : <span id="q_${jj}" style="display: ;">${row.no}</span>
		            <br>
		            span_answer_${jj } : <span id = "span_answer_${jj }" style = "display:;"></span>
					<span id="surveyNoResult${jj }" style="margin: 20px 0px;"></span>
				</div>
				<table border="1" width="100%">
					<tr>
						<td>Q. ${jj})&nbsp;&nbsp;${row.question }</td>
					</tr>
					<tr>
						<td>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('1', '${jj}');"><font style="font-family:'MS Gothic';"><span id="answer_${jj }_1">①</span></font></a>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('1', '${jj}');">
								${row.ans1 }
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('2', '${jj}');"><font style="font-family:'MS Gothic';"><span id="answer_${jj }_2">②</span></font></a>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('2', '${jj}');">
								${row.ans2 }
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('3', '${jj}');"><font style="font-family:'MS Gothic';"><span id="answer_${jj }_3">③</span></font></a>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('3', '${jj}');">
								${row.ans3 }
							</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('4', '${jj}');"><font style="font-family:'MS Gothic';"><span id="answer_${jj }_4">④</span></font></a>
							<a href="#a_${row.no }" onclick="surveyAnswerCheck('4', '${jj}');">
								${row.ans4 }
							</a>
						</td>
					</tr>
				</table>
				
				<br>
				
				<c:set var="jj" value="${jj = jj - 1 }"></c:set>
			</c:forEach>
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
			<button type="button" onclick="suntaek_proc('writeProc', '', '');">등록하기</button>
		</td>
	</tr>
</table>
<script>
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
	
	suntaek_page('1');
}

// function dateCheckBox() {
// 	if($("input:checkbox[id=search_date_check]").is(":checked") == true) {
// 		$("#span_search_date_check").text($("#search_date_check").val());
// 		$("#span_search_date_s").text($("#search_date_s").val());
// 		$("#span_search_date_e").text($("#search_date_e").val());
// 	} else {
// 		$("#span_search_date_check").text('');
// 		$("#span_search_date_s").text('');
// 		$("#span_search_date_e").text('');
// 		$("#search_date_s").val('');
// 		$("#search_date_e").val('');
// 	}
// }
</script>