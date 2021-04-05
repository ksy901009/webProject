<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<h2>방명록 상세보기</h2>

<table border="1" width="100%">
	<tr>
		<td width="150">순번</td>
		<td>${dto.no }</td>
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
		<td>${dto.passwd}</td>
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
			<button type="button" onclick="suntaek_proc('update', '0', '${dto.no}');">수정하기</button>
			<button type="button" onclick="suntaek_proc('delete', '0', '${dto.no}');">삭제하기</button>
			<button type="button" onclick="suntaek_proc('list', '1', '');">리스트보기</button>
		</td>
	</tr>
</table>