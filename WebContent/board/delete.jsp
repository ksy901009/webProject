<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>

<table border ="1" align="center" width="80%">
	<tr>
		<td colspan="2"><h2>게시물 삭제하기 </h2></td>
	</tr>
	<tr>
		<td style="align: center;">작성자</td>
		<td><input type="text" name="writer" id="writer" value="${dto.writer }"></td>
	</tr>
	<tr>
		<td style="align: center;">비밀번호</td>
		<td><input type="text" name="passwd" id="passwd"></td>
	</tr>
	<tr>
		<td align="center" colspan="2" height="50px">
			<button type="button" id="btnDelete">삭제 하기</button>
			<button type="button" id="btnList">목록 으로</button>
		</td>   
	</tr>
</table>
<script>
	$(document).ready(function(){
		$("#btnDelete").click(function(){
      
		if(confirm('삭제 하시겠습니까?')){
			suntaek_proc('deleteProc', '', $("#span_no").text());
		}
		});
		$("#btnList").click(function(){
			suntaek_proc('list', '', '1');
		});
   });
</script>