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
// 		var url = "${path}/guestBook_servlet/" + value1 + ".do";
		var url = path + "/guestBook_servlet/" + value1 + ".do";
		
		if(value1 == "list") {
			param = {
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text()
			}
		} else if(value1 == "write") {
			param = {}
		} else if(value1 == "writeProc" || value1 == "updateProc" || value1 == "deleteProc") {
			param = {
					"no" : $("#span_no").text(),
					"name" : $("#name").val(),
					"email" : $("#email").val(),
					"passwd" : $("#passwd").val(),
					"content" : $("#content").val(),
					"passwdChk" : $("#span_passwdChk").text()
			}
		} else if(value1 == "update" || value1 == "delete") {
			param = {
					"no" : $("#span_no").text()
			}
		} else if(value1 == "view") {
			param = {
					"no" : $("#span_no").text(),
					"pageNumber" : $("#span_pageNumber").text(),
					"search_option" : $("#span_search_option").text(),
					"search_data" : $("#span_search_data").text()
			}
		} else if(value1 == "getPassword") {
			param = {
					"no" : $("#span_no").text(),
					"passwd" : $("#passwd").val()
			}
		}
		
		$.ajax({
			type: "post",
			data: param,
			url: url,
			success: function(data) {
				if(value1 == "list" || value1 == "write" || value1 == "view" || value1 == "update" || value1 == "delete") {
					$("#result").html(data);
				} else if(value1 == "writeProc") {
					suntaek_proc('list', '1', '');
				} else if(value1 == "updateProc") {
					suntaek_proc('view', '0', $("#span_no").text());
				} else if(value1 == "deleteProc") {
					if(data.trim() == 'success') {
						alert('삭제 되었습니다.');
						suntaek_proc('list', '1', '');
					} else if(data.trim() == 'passwordFail') {
						alert('비밀번호를 잘못 입력 하였습니다.');
// 						suntaek_proc('delete', '1', $("#span_no").text());
					} else if(data.trim() == 'dbFail') {
						alert('db오류');
// 						suntaek_proc('delete', '1', $("#span_no").text());
					}
				} else if(value1 == "getPassword") {
					if(data == 'notMember') {
						alert('notMember');
					} else if(data.trim() == param.passwd.trim()) {
						$("#span_passwdChk").text('T');
						alert('비밀번호가 일치합니다');
					} else {
						$("#span_passwdChk").text('F');
						alert('비밀번호가 일치하지 않습니다.');
					}
				}
			}
		});
	}
</script>