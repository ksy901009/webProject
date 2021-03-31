<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_sessionChk.jsp" %>
<form name="form">
	<input type="hidden" name="no" value="${dto.no }">
	<table border="1" align="center" width="95%">
		<tr>
			<td colspan="2"><h2>회원 정보삭제</h2></td>
		</tr>
		<tr>
			<td width="150">순번</td>
			<td>${dto.no }</td>
		</tr>
		<tr>
			<td>아이디</td>
			<td>회원ID : ${dto.id }, 세션ID : ${sessionScope.cookId }</td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td>
				<input type="password" name="passwd" id="passwd" value="">
			</td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name } / ${sessionScope.cookName }</td>
		</tr>
		<tr>
			<td>성별</td>
			<td>${dto.gender }</td>
		</tr>
		<tr>
			<td>출생년도</td>
			<td>${dto.bornYear }</td>
		</tr>
		
		<tr>
			<td rowspan="3">주소</td>
			<td>
				${dto.postCode }
			</td>
		</tr>
		<tr>
			<td>${dto.address }</td>
		</tr>
		<tr>
			<td>
				${dto.detailAddress }&nbsp;&nbsp;&nbsp;&nbsp;
				${dto.extraAddress }
			</td>
		</tr>
		
		<tr>
			<td>작성일</td>
			<td>${dto.regiDate }</td>
		</tr>
		<tr>
			<td colspan="2" align="center" style="height:50px;">
				<button type="button" onclick="suntaek_proc('deleteProc', '0', '${dto.no}');">삭제하기</button>
				<button type="button" onclick="suntaek_proc('list', '1', '');">목록으로</button>
			</td>
		</tr>
	</table>
</form>