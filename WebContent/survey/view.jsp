<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<script>
	$(document).ready(function() {
		$("#btnUpdate").click(function() {
			suntaek_proc('update', '', '${dto.no}');
		});
		$("#btnRemove").click(function() {
			suntaek_proc('deleteProc', '', '${dto.no}');
		});
		$("#btnList").click(function() {
			suntaek_proc('list', '', '');
		});
		$("#btnAnswerWrite").click(function() {
			suntaek_proc('surveyAnswerWriteProc', '', '${dto.no}');
		});
	});
	
	function surveyAnswerCheck(value1) {
		document.getElementById("span_answer").innerHTML = value1;
		
		var temp = ['❶', '❷', '❸', '❹'];
		var changeNumber = document.getElementById("answer" + value1);
		
		if(changeNumber.innerHTML == '①') {
			document.getElementById("answer2").innerHTML = '②';
			document.getElementById("answer3").innerHTML = '③';
			document.getElementById("answer4").innerHTML = '④';
			
			document.getElementById("answer" + value1).innerHTML = temp[0];
		} else if(changeNumber.innerHTML == '②') {
			document.getElementById("answer1").innerHTML = '①';
			document.getElementById("answer3").innerHTML = '③';
			document.getElementById("answer4").innerHTML = '④';
			
			document.getElementById("answer" + value1).innerHTML = temp[1];
		} else if(changeNumber.innerHTML == '③') {
			document.getElementById("answer1").innerHTML = '①';
			document.getElementById("answer2").innerHTML = '②';
			document.getElementById("answer4").innerHTML = '④';
			
			document.getElementById("answer" + value1).innerHTML = temp[2];
		} else if(changeNumber.innerHTML == '④') {
			document.getElementById("answer1").innerHTML = '①';
			document.getElementById("answer2").innerHTML = '②';
			document.getElementById("answer3").innerHTML = '③';
			
			document.getElementById("answer" + value1).innerHTML = temp[3];
		}
	}
</script>
<span id="span_answer"></span>
<form name="dirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>
					설문조사 상세보기
				</h2>
			</td>
		</tr>
		<tr>
			<td>Q)&nbsp;&nbsp;${dto.question }</td>
		</tr>
		<tr>
			<td>
<!-- 				<input type="radio" name="answer" value="1"> -->
				<a href="#" onclick="surveyAnswerCheck('1');"><font style="font-family:'MS Gothic';"><span id="answer1">①</span></font></a>
				<a href="#" onclick="surveyAnswerCheck('1');">
					${dto.ans1 }
				</a>
			</td>
		</tr>
		<tr>
			<td>
<!-- 				<input type="radio" name="answer" value="2"> -->
				<a href="#" onclick="surveyAnswerCheck('2');"><font style="font-family:'MS Gothic';"><span id="answer2">②</span></font></a>
				<a href="#" onclick="surveyAnswerCheck('2');">
					${dto.ans2 }
				</a>
			</td>
		</tr>
		<tr>
			<td>
<!-- 				<input type="radio" name="answer" value="3"> -->
				<a href="#" onclick="surveyAnswerCheck('3');"><font style="font-family:'MS Gothic';"><span id="answer3">③</span></font></a>
				<a href="#" onclick="surveyAnswerCheck('3');">
					${dto.ans3 }
				</a>
			</td>
		</tr>
		<tr>
			<td>
<!-- 				<input type="radio" name="answer" value="4"> -->
				<a href="#" onclick="surveyAnswerCheck('4');"><font style="font-family:'MS Gothic';"><span id="answer4">④</span></font></a>
				<a href="#" onclick="surveyAnswerCheck('4');">
					${dto.ans4 }
				</a>
			</td>
		</tr>
		<tr>
			<td>상태 : ${dto.status }</td>
		</tr>
		<tr>
			<td>기간 : ${dto.start_date } ~ ${dto.last_date }</td>
		</tr>
		<tr>
			<td align="center" height="80">
				<input type="button" value="설문조사 답변 작성하기" id="btnAnswerWrite">&nbsp;
				<input type="button" value="설문조사 수정하기" id="btnUpdate">&nbsp;
				<input type="button" value="삭제하기" id="btnRemove">&nbsp;
				<input type="button" value="목록으로" id="btnList">
			</td>
		</tr>
	</table>
</form>