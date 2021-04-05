<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<c:if test="${sessionScope.cookNo == null || sessionScope.cookNo == 0 }">
<%-- 	<img src="${path}/attach/images/dn_see.jpg" title="풍경1" alt="풍경2" style="width:100%; height:100%;"> --%>
	메인 페이지
</c:if>

<c:if test="${sessionScope.cookNo != null && sessionScope.cookNo > 0 }">
	<font style="font-size: 100px; font-weight: bold;">
		${sessionScope.cookName }님 환영합니다.
	</font>
</c:if>