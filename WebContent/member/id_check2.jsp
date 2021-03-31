<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %> 
<form name="form">
	<table border="1" align="center">
		<tr>
			<td>아이디</td>
			<td>
				<input type="text" name="id" id="id" value="${inputId }">
			</td>
		</tr>
		<c:if test="${result.length() > 0}">
			<tr>
				<td colspan="2">
					사용 불가능합니다.
					<span id="idCheck"></span>
				</td>
			</tr>
		</c:if>
		<c:if test="${result.length() == 0}">
			<tr>
				<td colspan="2">사용 가능 합니다.
					<span id="idCheck" style="display:none;">${inputId }</span>
				</td>
			</tr>
		</c:if>
		<tr>
			<td colspan="2" align="center" style="height:50px;">
				<button type="button" onclick="search();">검색</button>
				<button type="button" onclick="save();">적용</button>
			</td>
		</tr>
	</table>
</form>
<script>
	function search() {
		form.method = "post";
		form.action = "${path}/member_servlet/id_check_win_open_Proc.do";
		form.submit();
	}
	
	function save() {
		var result = document.getElementById("idCheck");
// 		var resultNodeValue = document.getElementById("idCheck").childNodes[0].nodeValue;
		
// 		if(result.childNodes[0].nodeValue == null) {
// 			alert('here');
// 		}
		
		if(result.childNodes[0] == undefined) {
			alert('아이디를 다시 입력하세요.');
		} else {
			var resultNodeValue = result.childNodes[0].nodeValue;
			opener.document.getElementById("id").value = resultNodeValue;
			
// 			$("#idCheck").css("display:hidden");
			
			window.close();
		}
	}
</script>