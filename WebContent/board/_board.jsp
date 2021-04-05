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
	
	function goPage(value1) {
		var param;
		var url = path + "/board_servlet/" + value1 + ".do";
		
		if(value1 == "list") {
			param = {
					"tbl" : $("#span_tbl").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text()
			}
		} else if(value1 == "writeProc" || value1 == "updateProc" || value1 == "deleteProc") {
			param = {
					"no" : $("#span_no").text(),
					"tbl" : $("#span_tbl").text(),
					"writer" : $("#writer").val(),
					"email" : $("#email").val(),
					"passwd" : $("#passwd").val(),
					"subject" : $("#subject").val(),
					"content" : $("#content").val(),
					"noticeGubun" : $("#noticeGubun").val(),
					"secretGubun" : $("#secretGubun").val()
			}
		} else if(value1 == "write" || value1 == "update" || value1 == "delete" || value1 == "reply") {
			param = {
					"no" : $("#span_no").text()
			}
		} else if(value1 == "view") {
			param = {
					"no" : $("#span_no").text(),
					"tbl" : $("#span_tbl").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text(),
					"view_passwd" : $("#view_passwd").val()
			}
		}
			
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: function(data) {
				if(value1 == "list" || value1 == "write" || value1 == "view" || value1 == "update" || value1 == "delete" || value1 == "reply") {
					$("#result").html(data);
				} else if(value1 == "writeProc") {
					suntaek_proc('list', '1', '');
				} else if(value1 == "updateProc") {
					suntaek_proc('view', '0', $("#span_no").text());
				} else if(value1 == "deleteProc") {
					$("#result").html(data);
					if($("#span_passwd").text() == 'O') {
						alert('삭제되었습니다.');
						suntaek_proc('list', '1', '');
					} else if($("#span_passwd").text() == 'X') {
						alert('비밀번호가 다릅니다.');
						suntaek_proc('delete', '1', $("#span_no").text());
					}
				}
			}
		});
	}
	
	function clickChk(value1) {
		if(value1 == 'noticeGubun') {
			if($("input:checkbox[name=noticeGubunCheckBox]").is(":checked") == true) {
				$("#noticeGubun").val("T");
			} else {
				$("#noticeGubun").val("");
			}
		} else {
			if($("input:checkbox[name=secretGubunCheckBox]").is(":checked") == true) {
				$("#secretGubun").val("T");
			} else {
				$("#secretGubun").val("");
			}
		}
	}
</script>