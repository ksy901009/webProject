<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../../include/inc_header.jsp" %>
<table border="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>상품삭제 - ${dto.no }</h2></td>
	</tr>
	<tr>
		<td style="align: center;">상품명</td>
		<td>${dto.name }</td>
	</tr>
	<tr>
		<td style="align: center;">가격</td>
		<td>${dto.price }</td>
	</tr>
	<tr>
		<td style="align: center;">상품설명</td>
		<td><textarea name="description" id="description" style="width:300px; height:100px;" wrap="hard">${dto.description }</textarea></td>
	</tr>
	<tr>
		<td style="align: center;">현재이미지</td>
		<td>
			<c:if test="${dto.product_img == '-,-,-' }">
				이미지X
			</c:if>
			<c:if test="${dto.product_img != '-,-,-' }">
				<c:set var="temp1" value="${fn:split(dto.product_img,',')[0]}"></c:set>
				<c:set var="temp2" value="${fn:split(temp1,'|')[0]}"></c:set>
				<c:set var="temp3" value="${fn:split(temp1,'|')[1]}"></c:set>
				<img src="${path}/attach/product_img/${temp3}" alt="${dto.name}" title="${dto.name}" style="width: 100px; height: 100px;">
			</c:if>
		</td>
	</tr>
	<tr>
		<td style="align: center;">상품이미지</td>
		<td>
			<input type="file" name="file" readonly style="display: none;">
			<input type="file" name="file" readonly>
			<input type="file" name="file" readonly>
		</td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px;">
			<button type="button" id="btnDelete">삭제하기</button>
			<button type="button" id="btnList">목록으로</button>
		</td>
	</tr>
</table>
<script>
	$(document).ready(function() {
		$("#name").select();
		$("#name").focus();
		
		$("#btnDelete").click(function() {
			if(confirm('삭제하시겠습니까?')) {
				suntaek_proc('deleteProc', '', '${dto.no}');
			}
		});
		
		$("#btnList").click(function() {
			suntaek_proc('list', '1', '');
		});
	});
</script>