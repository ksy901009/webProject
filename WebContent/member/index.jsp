<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/inc_header.jsp" %>
<div style="display: none;">
<%-- 	join : <span id="span_join">${join }</span><br> --%>
	proc : <span id="span_proc">${proc }</span><br>
	pageNumber : <span id="span_pageNumber">${pageNumber }888</span><br>
	no : <span id="span_no">${no }999</span><br>
	search_option : <span id="span_search_option">${search_option }</span><br>
	search_data : <span id="span_search_data">${search_data }</span><br>
	path : <span id="span_path">${path }</span><br>
</div>
<div id="result" style="border: 1px solid red; position: relative;"></div>
<%@ include file="../member/_member.jsp" %>