<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
	<tr>
		<td colspan="2"><h2>게시글쓰기</h2></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" id="writer"></td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input type="text" name="email" id="email"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td>제목</td>
		<td><input type="text" name="subject" id="subject" value="${dto.subject }"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea name="content" id="content" style="width:300px; height:100px;" wrap="hard">${dto.content }</textarea></td>
	</tr>
	<tr>
		<td>공지글</td>
		<td>
			<input type="text" name="noticeGubun" id="noticeGubun">
			<input type="checkbox" name="noticeGubunCheckBox" id="noticeGubunCheckBox" value="T" onclick="clickChk('noticeGubun');">
			공지글 체크
		</td>
	</tr>
	<tr>
		<td>공지글</td>
		<td>
			<input type="text" name="secretGubun" id="secretGubun">
			<input type="checkbox" name="secretGubunCheckBox" id="secretGubunCheckBox" value="T" onclick="clickChk('secretGubun');">
			비밀글 체크
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px;">
			<button type="button" id="btnWrite">등록하기</button>
			<button type="button" id="btnList">목록으로</button>
		</td>
	</tr>
</table>
<script>
	$(document).ready(function() {
		$("#btnWrite").click(function() {
			$("#writer").focus();
			
// 			if(confirm('등록하시겠습니까?')) {
				suntaek_proc('writeProc', '', $("#span_no").text());
// 			}
		});
		
		$("#btnList").click(function() {
			suntaek_proc('list', '', '');
		});
	});
</script>
</body>
</html>