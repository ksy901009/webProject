<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../include/inc_header.jsp" %>

<!-- 구글 차트 라이브러리 로딩 -->
<script src="https://www.google.com/jsapi"></script>

<button type="button" id="btnCreateJson">?????</button>&nbsp;&nbsp;&nbsp;
<button type="button" id="btnGoogleChartJson">구글차트(json)</button>&nbsp;&nbsp;&nbsp;
<!-- <button type="button" id="btnGoogleChartDb">구글차트(db)</button>&nbsp;&nbsp;&nbsp; -->
<!-- <button type="button" id="btnJfreeChartPng">JFreeChart(png)</button>&nbsp;&nbsp;&nbsp; -->
<!-- <button type="button" id="btnJfreeChartPdf">JFreeChart(pdf)</button> -->

<br><br>

<div id="result" style="border: 1px solid red; posion: relative;"></div>

<script>
	$(document).ready(function(){
		$("#btnCreateJson").click(function(){
			goPage('createJson');
		});
		
		$("#btnGoogleChartJson").click(function(){
			goPage('googleChartJson');
		});
		
// 		$("#btnGoogleChartDb").click(function(){
// 			goPage('googleChartDb');
// 		});
		
// 		$("#btnJfreeChartPng").click(function(){
// 			goPage('jfreeChartPng');
// 		});
		
// 		$("#btnJfreeChartPdf").click(function(){
// 			goPage('jfreeChartPdf');
// 		});
	});
	
	function goPage(value1) {
		var method_type = "post";
		var param = {}
		var url = "${path}/chart_servlet/" + value1 + ".do";
		
		$.ajax({
			type: method_type,
			data: param,
			url: url,
			success: function(data) {
				$("#result").html(data);
			}
		});
	}
</script>