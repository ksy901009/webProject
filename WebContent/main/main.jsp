<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ include file="../include/inc_header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<meta http-equiv="Cache-Control" content="no-cache" />
<meta http-equiv="Expires" content="0" />
<meta http-equiv="Pragma" content="no-cache" />
</head>
<body style="background-color: white;">
	<table border="1" align="center">
		<tr>
			<td style="padding:20px 20px;">
				<jsp:include page="../include/inc_menu.jsp"></jsp:include>
			</td>
		</tr>
		
		
		
		<tr>
			<td align="center" style="padding:50px 50px;">
				<c:choose>
					<c:when test="${menu_gubun == 'index' }">
						<jsp:include page="../main/main_sub.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'member_index' }">
						<jsp:include page="../member/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'memo_index' }">
						<jsp:include page="../memo/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'guestBook_index' }">
						<jsp:include page="../guestBook/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'survey_index' }">
						<jsp:include page="../survey/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'surveyAnswer_index' }">
						<jsp:include page="../surveyAnswer/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'board_index' }">
						<jsp:include page="../board/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'product_index' }">
						<jsp:include page="../shop/product/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'mall_index' }">
						<jsp:include page="../shop/mall/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'email_index' }">
						<jsp:include page="../email/index.jsp" />
					</c:when>
					<c:when test="${menu_gubun == 'chart_index' }">
						<jsp:include page="../chart/index.jsp" />
					</c:when>
					<c:otherwise>
						bbb
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		
		
		
		<tr>
			<td align="center" style="padding:20px 20px;">
				<jsp:include page="../include/inc_bottom.jsp"></jsp:include>
			</td>
		</tr>
	</table>
</body>
</html>