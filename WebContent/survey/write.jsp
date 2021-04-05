<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<script>
	$(document).ready(function() {
		$("#btnSave").click(function() {
			suntaek_proc('writeProc', '', '');
		});
		$("#btnList").click(function() {
			suntaek_proc('list', '1', '');
		});
	});
</script>
<form name="dirForm">
	<table border="1" align="center" width="80%">
		<tr>
			<td colspan="2">
				<h2>
					설문조사 등록
				</h2>
			</td>
		</tr>
		<tr>
			<td style="align: center;">question</td>
			<td><input type="text" name="question" id="question"></td>
		</tr>
		<tr>
			<td style="align: center;">ans1</td>
			<td><input type="text" name="ans1" id="ans1"></td>
		</tr>
		<tr>
			<td style="align: center;">ans2</td>
			<td><input type="text" name="ans2" id="ans2"></td>
		</tr>
		<tr>
			<td style="align: center;">ans3</td>
			<td><input type="text" name="ans3" id="ans3"></td>
		</tr>
		<tr>
			<td style="align: center;">ans4</td>
			<td><input type="text" name="ans4" id="ans4"></td>
		</tr>
		<tr>
			<td style="align: center;">status</td>
			<td>
				<input type="radio" name="status" id="status" value="1"> 진행중
				<input type="radio" name="status" id="status" value="0"> 종료
			</td>
		</tr>
		<tr>
			<td>start_date</td>
			<td>
				<select name="sYear" id="sYear">
					<c:forEach var="i" begin="${naljaMap.now_y - 1 }" end="${naljaMap.now_y + 1 }" step="1">
						<c:if test="${naljaMap.now_y == i }">
							<option value="${i }" selected>${i }</option>
						</c:if>
						<c:if test="${naljaMap.now_y != i }">
							<option value="${i }">${i }</option>
						</c:if>
					</c:forEach>
				</select>년
				<select name="sMonth" id="sMonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${i < 10 }">
							<c:if test="${naljaMap.now_m == i }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_m != i }">
								<option value="0${i }">0${i }</option>
							</c:if>
						</c:if>
						<c:if test="${i >= 10 }">
							<c:if test="${naljaMap.now_m == i }">
								<option value="${i }" selected>${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_m != i }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>월
				<select name="sDay" id="sDay">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${i < 10 }">
							<c:if test="${naljaMap.now_d == i }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_d != i }">
								<option value="0${i }">0${i }</option>
							</c:if>
						</c:if>
						<c:if test="${i >= 10 }">
							<c:if test="${naljaMap.now_d == i }">
								<option value="${i }" selected>${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_d != i }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>일
			</td>
		</tr>
		<tr>
			<td>last_date</td>
			<td>
				<select name="lYear" id="lYear">
					<c:forEach var="i" begin="${naljaMap.now_y - 1 }" end="${naljaMap.now_y + 1 }" step="1">
						<c:if test="${naljaMap.now_y == i }">
							<option value="${i }" selected>${i }</option>
						</c:if>
						<c:if test="${naljaMap.now_y != i }">
							<option value="${i }">${i }</option>
						</c:if>
					</c:forEach>
				</select>년
				<select name="lMonth" id="lMonth">
					<c:forEach var="i" begin="1" end="12" step="1">
						<c:if test="${i < 10 }">
							<c:if test="${naljaMap.now_m == i }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_m != i }">
								<option value="0${i }">0${i }</option>
							</c:if>
						</c:if>
						<c:if test="${i >= 10 }">
							<c:if test="${naljaMap.now_m == i }">
								<option value="${i }" selected>${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_m != i }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>월
				<select name="lDay" id="lDay">
					<c:forEach var="i" begin="1" end="31" step="1">
						<c:if test="${i < 10 }">
							<c:if test="${naljaMap.now_d == i }">
								<option value="0${i }" selected>0${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_d != i }">
								<option value="0${i }">0${i }</option>
							</c:if>
						</c:if>
						<c:if test="${i >= 10 }">
							<c:if test="${naljaMap.now_d == i }">
								<option value="${i }" selected>${i }</option>
							</c:if>
							<c:if test="${naljaMap.now_d != i }">
								<option value="${i }">${i }</option>
							</c:if>
						</c:if>
					</c:forEach>
				</select>일
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center" height="80">
				<input type="button" value="저장하기" id="btnSave">&nbsp;
				<input type="button" value="목록으로" id="btnList">
			</td>
		</tr>
	</table>
</form>