<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_sessionChk.jsp" %>
<div style="display: none;">
	proc : <span id="span_proc"></span><br>
	naljaMap : ${naljaMap }<br>
	ip : ${ip }<br>
	tbl : <span id="span_tbl">${tbl }</span><br>
	pageNumber : <span id="span_pageNumber">${pageNumber }</span><br>
	no : <span id="span_no">${no }</span><br>
	search_option : <span id="span_search_option">${search_option }</span><br>
	search_data : <span id="span_search_data">${search_data }</span><br>
	passwd : <span id="span_passwd"></span><br>
	path : <span id="span_path">${path }</span><br>
</div>
<div id="result"></div>
<%@ include file="../board/_board.jsp" %>