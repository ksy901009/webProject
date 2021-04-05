<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var path = $("#span_path").text();
	
	$(document).ready(function() {
		suntaek_proc('list', '1', '');
	});
	
	function suntaek_all() {
		$("#span_search_option").text("");
		$("#span_search_data").text("");
		$("#span_search_date").text("");
		$("#span_search_date_check").text("");
		$("#span_search_date_s").text("");
		$("#span_search_date_e").text("");
		suntaek_proc('list', '1', '');
	}
	
	function suntaek_proc(value1, value2, value3) {
		$("#span_proc").text(value1);
		
		if(value2 != "0") { // 0-건너뜀
			$("#span_pageNumber").text(value2);
		}
		
		if(value3 != "0") {
			$("#span_no").text(value3);
		}
		
		var totalText = $("#span_answer").text();
		
		if(totalText != '') {
			var indexText = $("#span_answer_total").text();
			totalText += "|" + indexText;
			
// 			alert('totalText >>> ' + totalText);
			var totalTextArray = totalText.split("|");			// index.jsp에 있는 모든페이지의 답변
			var indexTextArray = indexText.split("|");			// list.jsp에 있는 해당 페이지의 답변
			for(var i = 0; i < totalTextArray.length; i++) {
				for(var j = 0; j < i; j++) {
// 					console.log("indexTextArray : " +indexTextArray[j]);
// 					console.log("totalTextArray : " +totalTextArray[i]);
					if(indexTextArray[j] == totalTextArray[i]) {
// 						console.log("같은것 : " + indexTextArray[j]);
					}
				}
			}
		} else {
			$("#span_answer").text($("#span_answer_total").text());
		}
		
		goPage(value1);
	}
	
	function suntaek_list(status) {
		$("#span_list_gubun").text(status);
		$("#span_pageNumber").text(1);
		goPage('list');
	}
	
	function goPage(value1) {
		var param;
		var url = path + "/surveyAnswer_servlet/" + value1 + ".do";
		
		if(value1 == "list") {
			param = {
					"list_gubun" : $("#span_list_gubun").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text(),
					"search_date" : $("#span_search_date").text(),
					"search_date_check" : $("#span_search_date_check").text(),
					"search_date_s" : $("#span_search_date_s").text(),
					"search_date_e" : $("#span_search_date_e").text()
			}
		} else if(value1 == "writeProc") {
			param = {
					"answer_total" : $("#span_answer_total").text()
			}
		}
		
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: function(data) {
				if(value1 == "list" || value1 == "write" || value1 == "view" || value1 == "update" || value1 == "delete" || value1 == "list_2") {
					$("#result").html(data);
				} else if(value1 == "writeProc") {
					suntaek_proc('list', '1', '');
				} else if(value1 == "updateProc") {
					suntaek_proc('view', '0', $("#span_no").text());
				} else if(value1 == "deleteProc") {
					if(data.trim() == 'success') {
						alert('삭제 되었습니다.');
						suntaek_proc('list', '1', '');
					} else if(data.trim() == 'fail') {
						alert('삭제실패');
						suntaek_proc('view', '1', $("#span_no").text());
					}
				} else if(value1 == "surveyAnswerWriteProc") {
					suntaek_proc('list', '1', '');
				}
			}
		});
	}
	
	var count = 0;
	
	function surveyAnswerCheck(value1, no) {
		var pageNumber = document.getElementById("span_pageNumber").innerHTML;
		
		var surveyNoResult = document.getElementById("surveyNoResult" + no);
		
		surveyNoResult.innerHTML = no + ':' + value1;
		
		$("#span_answer_" + no).text(value1);
		
		var temp = ['❶', '❷', '❸', '❹'];
		var changeNumber = document.getElementById("answer_" + no + "_" + value1);
		
		if(changeNumber.innerHTML == '①') {
			document.getElementById("answer_" + no + "_2").innerHTML = '②';
			document.getElementById("answer_" + no + "_3").innerHTML = '③';
			document.getElementById("answer_" + no + "_4").innerHTML = '④';
			
			document.getElementById("answer_" + no + "_" + value1).innerHTML = temp[0];
		} else if(changeNumber.innerHTML == '②') {
			document.getElementById("answer_" + no + "_1").innerHTML = '①';
			document.getElementById("answer_" + no + "_3").innerHTML = '③';
			document.getElementById("answer_" + no + "_4").innerHTML = '④';
			
			document.getElementById("answer_" + no + "_" + value1).innerHTML = temp[1];
		} else if(changeNumber.innerHTML == '③') {
			document.getElementById("answer_" + no + "_1").innerHTML = '①';
			document.getElementById("answer_" + no + "_2").innerHTML = '②';
			document.getElementById("answer_" + no + "_4").innerHTML = '④';
			
			document.getElementById("answer_" + no + "_" + value1).innerHTML = temp[2];
		} else if(changeNumber.innerHTML == '④') {
			document.getElementById("answer_" + no + "_1").innerHTML = '①';
			document.getElementById("answer_" + no + "_2").innerHTML = '②';
			document.getElementById("answer_" + no + "_3").innerHTML = '③';
			
			document.getElementById("answer_" + no + "_" + value1).innerHTML = temp[3];
		}
		
		var counter = parseInt($("#span_list_size").text());
// 		console.log("counter:" + counter);
		var msg = "";
		for (i=counter; i>0; i--) {
			q_no = $("#q_" + i).text();
			answer = $("#span_answer_" + i).text();
// 			console.log("q_no : " + q_no);
// 			console.log("answer : " + answer);
			if (answer.length > 0) {
				if (msg == '') {
					msg = q_no + ":" + answer;
				} else {
					msg = msg + "|" + q_no + ":" + answer;
				}
			}
		}
// 		var answerTotal = '';
// 		if($("#span_answer").text() == '') {
// 			answerTotal = msg;
// 		} else {
// 			$("#span_answer").text(msg);
// 		}
// 		$("#span_answer").text(msg);
		$("#span_answer_total").text(msg);
		
// 		console.log("msg : " + msg);
	}
</script>