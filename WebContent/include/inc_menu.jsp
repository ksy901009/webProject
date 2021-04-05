<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:set var="endIndex" value="${fn:indexOf(menu_gubun, '_')}"/>
<c:set var="menu_gubunSubstring" value="${fn:substring(menu_gubun, 0, endIndex)}"/>
<table border="0" align="center">
	<tr>
		<td colspan="15" align="right" style="padding:5px 20px 10px;">
			<c:choose>
				<c:when test="${sessionScope.cookNo != null && sessionScope.cookNo > 0 }">
					${sessionScope.cookName }님 환영합니다.
					<a href="${path }/member_servlet/index.do?no=${sessionScope.cookNo}&proc=update">[회원정보수정]</a>
					<a href="${path }/member_servlet/index.do?no=${sessionScope.cookNo}&proc=delete">[회원탈퇴]</a>
					<a href="${path }/member_servlet/logout.do">[로그아웃]</a><br>
				</c:when>
				<c:otherwise>
					<a href="${path }/member_servlet/login.do">로그인</a><br>
				</c:otherwise>
			</c:choose>
			<div style="display: none;">
				${path }<br>
				${url }<br>
				${uri }<br>
			</div>
			
			<br><br><br>
			
		</td>
	</tr>
	<tr>
		<td style="padding:0px 20px;" id="home">
			<a href="${path }">HOME</a>
		</td>
		<td style="padding:0px 20px;" id="member">
			<a href="${path }/member_servlet/index.do">회원관리</a>
		</td>
		<td style="padding:0px 20px;" id="memo">
			<a href="${path }/memo_servlet/index.do">메모장</a>
		</td>
		<td style="padding:0px 20px;" id="guestBook">
			<a href="${path }/guestBook_servlet/index.do">방명록</a>
		</td>
		<td style="padding:0px 20px;" id="survey">
			<a href="${path }/survey_servlet/index.do">설문조사</a>
		</td>
		<td style="padding:0px 20px;" id="surveyAnswer">
			<a href="${path }/surveyAnswer_servlet/index.do">문제은행</a>
		</td>
		<td style="padding:0px 20px;" id="product">
			<a href="${path }/product_servlet/index.do">쇼핑몰</a>
		</td>
		<td style="padding:0px 20px;" id="mall">
			<a href="${path }/mall_servlet/index.do">장바구니</a>
		</td>
		<td style="padding:0px 20px;" id="board">
			<a href="${path }/board_servlet/index.do">게시판</a>
		</td>
		<td style="padding:0px 20px;" id="chart">
			<a href="${path }/chart_servlet/index.do">chart</a>
		</td>
		<td style="padding:0px 20px;" id="email_">
			<a href="${path }/email_servlet/index.do">이메일</a>
		</td>
	</tr>
	<tr>
		<td colspan="15">
			<div style="display: none;">
				${menu_gubun } / ${endIndex} / ${fn:substring(menu_gubun, 0, endIndex)}
			</div>
		</td>
	</tr>
</table>
<c:choose>
	<c:when test="${menu_gubunSubstring == 'home'}">
		<script>
			$("#home").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'member'}">
		<script>
			$("#member").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'memo'}">
		<script>
			$("#memo").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'guestBook'}">
		<script>
			$("#guestBook").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'survey'}">
		<script>
			$("#survey").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'surveyAnswer'}">
		<script>
			$("#surveyAnswer").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'product'}">
		<script>
			$("#product").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'mall'}">
		<script>
			$("#mall").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'board'}">
		<script>
			$("#board").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'chart'}">
		<script>
			$("#chart").css("background-color", "gray");
		</script>
	</c:when>
	<c:when test="${menu_gubunSubstring == 'email'}">
		<script>
			$("#email_").css("background-color", "gray");
		</script>
	</c:when>
</c:choose>