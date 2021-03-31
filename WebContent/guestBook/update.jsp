<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>방명록 수정 페이지</h2>

<form name="updateForm" method="post">
	<input type="hidden" name="no" value="${dto.no}">
	<table border="1" width="800">
		<tr>
			<td>순번</td>
			<td>${dto.no}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>
				<input type="text" name="name" id="name" value="${dto.name}">
			</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>
				<input type="text" name="email" id="email" value="${dto.email}">
			</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="passwd" id="passwd" value="${dto.passwd}">
			</td>
		</tr>
		<tr>
			<td>내용</td>
			<td><textarea name="content" id="content" style="width: 700px; height: 100px;">${dto.content}</textarea></td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${dto.regi_date}</td>
		</tr>
		<tr>
			<td colspan="2" align="center">
				<button type="button" onclick="suntaek_proc('updateProc', '0', '${dto.no}');">수정</button>&nbsp;&nbsp;
				<button type="button" onclick="suntaek_proc('list', '1', '');">리스트</button>
			</td>
		</tr>
	</table>
</form>