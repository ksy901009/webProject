<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_sessionChk.jsp" %>
<table border="1" align="center" width="95%">
	<tr>
		<td colspan="2"><h2>회원 상세보기</h2></td>
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
		<td>${dto.passwd }</td>
	</tr>
	<tr>
		<td>이름</td>
		<td>${dto.name }</td>
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
		<td colspan="2" height="50px">
			<table border="1" width="100%" align="center">
				<tr>
					<td width="100px">이전글 : </td>
					<td>
						<c:if test="${dto.preId == null }">
							이전글이 없습니다.
						</c:if>
						<c:if test="${dto.preId != null }">
							<a href="#" onclick="suntaek_proc('view', '0', '${dto.preNo}');">${dto.preId }</a>
						</c:if>
					</td>
				</tr>
				<tr>
					<td width="100px">다음글 : </td>
					<td>
						<c:if test="${dto.nxtId == null }">
							다음글이 없습니다.
						</c:if>
						<c:if test="${dto.nxtId != null }">
							<a href="#" onclick="suntaek_proc('view', '0', '${dto.nxtNo}');">${dto.nxtId }</a>
						</c:if>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center" style="height:50px;">
			<button type="button" onclick="suntaek_proc('list', '0', '');">목록으로</button>
			<button type="button" onclick="suntaek_proc('update', '0', '${dto.no}');">수정하기</button>
			<button type="button" onclick="suntaek_proc('delete', '0', '${dto.no}');">삭제하기</button>
		</td>
	</tr>
</table>