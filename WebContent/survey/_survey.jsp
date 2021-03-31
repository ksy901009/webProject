<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var path = $("#span_path").text();
	
	$(document).ready(function() {
// 		alert('11');
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
		
		goPage(value1);
	}
	
	function suntaek_list(status) {
		$("#span_list_gubun").text(status);
		$("#span_pageNumber").text(1);
		goPage('list');
	}
	
	function goPage(value1) {
		var param;
// 		var url = "${path}/member_servlet/" + value1 + ".do";
		var url = path + "/survey_servlet/" + value1 + ".do";
		
		if(value1 == "list") {
			param = {
					"list_gubun" : $("#span_list_gubun").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text(),
					"search_date" : $("#span_search_date").text(),
					"search_date_check" : $("#span_search_date_check").text(),
					"search_date_s" : $("#span_search_date_s").text() + ' 00:00:00.0',
					"search_date_e" : $("#span_search_date_e").text() + ' 23:59:59.9'
			}
		} else if(value1 == "write" || value1 == "list_2") {
			param = {}
		} else if(value1 == "writeProc" || value1 == "updateProc" || value1 == "deleteProc") {
			param = {
					"no" : $("#span_no").text(),
					"question" : $("#question").val(),
					"ans1" : $("#ans1").val(),
					"ans2" : $("#ans2").val(),
					"ans3" : $("#ans3").val(),
					"ans4" : $("#ans4").val(),
					"status" : $("#status").val(),
					"sYear" : $("#sYear").val(),
					"sMonth" : $("#sMonth").val(),
					"sDay" : $("#sDay").val(),
					"lYear" : $("#lYear").val(),
					"lMonth" : $("#lMonth").val(),
					"lDay" : $("#lDay").val()
			}
		} else if(value1 == "update") {
			param = {
					"no" : $("#span_no").text()
			}
		} else if(value1 == "view") {
			param = {
					"no" : $("#span_no").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text(),
					"search_date" : $("#span_search_date").text(),
					"search_date_check" : $("#span_search_date_check").text(),
					"search_date_s" : $("#span_search_date_s").text(),
					"search_date_e" : $("#span_search_date_e").text()
			}
		} else if(value1 == "surveyAnswerWriteProc") {
			param = {
					"no" : $("#span_no").text(),
					"answer" : $("#span_answer").text()
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
					$("#span_pageNumber").text('1');
					$("#span_search_option").text('');
					$("#span_search_data").text('');
					$("#span_search_date").text('');
					$("#span_search_date_check").text('');
					$("#span_search_date_s").text('');
					$("#span_search_date_e").text('');
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
</script>