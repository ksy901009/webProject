<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- AJAX를 사용하여 메모의 리스트를 실시간으로 불러오기 위해 분리하는 페이지 -->
<c:if test="${menu_gubun == 'memo_list' }">
	<jsp:include page="../memo/list.jsp" />
</c:if>
</body>
</html>