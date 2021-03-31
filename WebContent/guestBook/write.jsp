<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>방명록 작성</h2>

<form name="form" method="post">
	<table border="1" width="800">
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" id="name" value=""></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="email" id="email" value=""></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd" id="passwd" value=""></td>
		</tr>
		<tr>
			<td colspan="2"><textarea name="content" id="content" style="width: 790px; height: 100px;"></textarea></td>
		</tr>
		<tr align="center">
			<td colspan="2">
				<input type="button" value="확인" onclick="suntaek_proc('writeProc', '0', '${dto.no}');">
				<input type="button" value="취소">
			</td>
		</tr>
	</table>
</form>
</body>
</html>