<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:if test="${sessionScope.cookNo == 0 || sessionScope.cookNo == null }">
	<script>
		alert('로그인 하지 않고 접근할수 없습니다.\n로그인페이지로 이동합니다.');
		location.href = '${path}/member_servlet/login.do';
	</script>
</c:if>