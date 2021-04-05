<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<table>
	<tr>
		<td colspan="2"><h2>comment 작성</h2></td>
	</tr>
	<tr>
		<td>작성자</td>
		<td><input type="text" name="writer" id="writer"></td>
		<td>비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td colspan="3">
			<input type="text" name="content" id="content" style="width:400px;">
			<button type="button">확인</button>
		</td>
	</tr>
</table>