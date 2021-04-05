<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<% request.setCharacterEncoding("UTF-8"); %>
<script>
	var httpReq = null;
	
	function getInstance() {
		httpReq = null;
		
		httpReq = new XMLHttpRequest();
		return httpReq;
	}
	
	function insertCustomer() {
		httpReq = getInstance();
		httpReq.onreadystatechange = handleInsertCustomer;
		
		var name = document.getElementById("name").value;
		var age = document.getElementById("age").value;
		var tel = document.getElementById("tel").value;
		var addr = document.getElementById("addr").value;
		var url = 'input.jsp?name=' + name + "&age=" + age + "&tel=" + tel + "&addr=" + addr;
		
		httpReq.open("GET", url, true);
		httpReq.send();
	}
	
	function handleInsertCustomer() {
		if(httpReq.readyState == 4) {
			document.form.name.value = "";
			document.form.age.value = "";
			document.form.tel.value = "";
			document.form.addr.value = "";
			
			alert("입력되었습니다.");
		}
	}


	
// 	가져오기
	function getData(no) {
// 		alert(no);
		var param = 'no=' + no;
		httpReq = getInstance();
		httpReq.responseType = 'json'; // json으로 받을때 사용
		httpReq.onreadystatechange = handleStateChange;
		httpReq.open("POST", "getPassword.jsp", true);
		httpReq.send(param);
	}
	
	function handleStateChange() {
		if(httpReq.readyState == 4) {
// 			var xmlDocument = httpReq.responseXML;
// 			var xmlDocument = httpReq.responseText;
			var xmlDocument = httpReq.response; // json

			var passwd = xmlDocument.passwd;
			var inputPasswd = document.deleteForm.passwd;
			
// 			alert(passwd);
			
			if(passwd == inputPasswd.value) {
				alert('비밀번호 동일');
			} else {
				alert('비밀번호가 맞지않습니다.');
			}
		}
	}
	
	
</script>

<div style="display: none;">
	passwdChk : <span id="span_passwdChk"></span><br>
</div>

<h2>방명록 삭제 페이지</h2>

<form name="deleteForm" method="post">
	<input type="hidden" name="no" value="${dto.no}">
	<table border="1" width="800">
		<tr>
			<td>순번</td>
			<td>${dto.no}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${dto.email}</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="passwd" id="passwd" value="">&nbsp;
				<input type="button" value="비밀번호 체크" onclick="suntaek_proc('getPassword', '', '${dto.no}');">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${dto.regi_date}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="suntaek_proc('deleteProc', '0', '${dto.no}');">삭제</button>&nbsp;&nbsp;
				<button type="button" onclick="suntaek_proc('list', '1', '');">리스트</button>
			</td>
		</tr>
	</table>
</form>