<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script>
	var path = $("#span_path").text();
	
	$(document).ready(function() {
		suntaek_proc('list', '1', '');
		
		$("#btnSave").click(function() {
			suntaek_proc('writeProc', '1', '');
		});
		
		$("#btnUpdate").click(function() {
			suntaek_proc('updateProc', '1', $("#span_no").text());
		});
		
		if($("#span_no").text() == '') {
			$("#btnSave").show();
			$("#btnUpdate").hide();
		}
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
		var url = path + "/memo_servlet/" + value1 + ".do";
		
		if(value1 == "list") {
			param = {
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text()
			}
		} else if(value1 == "writeProc" || value1 == "updateProc" || value1 == "deleteProc") {
			param = {
					"no" : $("#span_no").text(),
					"writer" : $("#writer").val(),
					"content" : $("#content").val()
			}
			
			$("#btnSave").show();
			$("#btnUpdate").hide();
		}
		
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: function(data) {
				if(value1 == "list" || value1 == "write") {
					$("#result").html(data);
				} else if(value1 == "writeProc" || value1 == "updateProc" || value1 == "deleteProc") {
					$("#writer").val("");
					$("#content").val("");
					suntaek_proc('list', '1', '');
				} else if(value1 == "deleteProc") {
					if(data.trim() == 'success') {
						alert('삭제 되었습니다.');
						suntaek_proc('list', '1', '');
					} else if(data.trim() == 'password') {
						alert('비밀번호를 잘못 입력 하였습니다.');
						suntaek_proc('delete', '1', $("#span_no").text());
					} else if(data.trim() == 'dbFail') {
						alert('db오류');
						suntaek_proc('delete', '1', $("#span_no").text());
					}
				}
			}
		});
	}
</script>