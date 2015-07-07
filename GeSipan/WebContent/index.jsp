<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
</head>
<body>
	<h1> jQuery N jQuery Mobile </h1>

	<h3> jQuery</h3>
	<ul>
		<li> <a href="${context}/jquery/ch01.do"> Chapter 01. jQuery 기본 </a> </li>
		<li> <a href="${context}/jquery/ch02.do"> Chapter 02. 셀렉터 다루기 </a> </li>
		<li> <a href="${context}/jquery/ch03.do"> Chapter 03. 어트리뷰트 다루기</a> </li>
		<li> <a href="${context}/jquery/ch04.do"> Chapter 04. DOM 탐색하기</a> </li>
		<li> <a href="${context}/jquery/ch05.do"> Chapter 05. DOM 조작하기</a> </li>
		<li> <a href="${context}/jquery/ch06.do"> Chapter 06. 이벤트 처리하기</a> </li>
		<li> <a href="${context}/jquery/ch07.do"> Chapter 07. 효과</a> </li>
		<li> <a href="${context}/jquery/ch08.do"> Chapter 08. 유틸리티</a> </li>
		<li> <a href="${context}/jquery/ch09.do"> Chapter 09. Ajax </a> </li>
		<li> <a href="${context}/jquery/ch10.do"> Chapter 10. 플러그인 사용법과 사용자 정의 플러그인 제작 </a> </li>
		<li> <a href="${context}/jquery/ch11.do"> Chapter 11. jQuery UI  </a> </li>
		<li> <a href="${context}/home/main.do"> 게시판 메인 바로가기  </a> </li>
	</ul>

	<h3> jQuery Mobile</h3>
	<ul>
		<li> <a href="${context}/jquery/ch12.do"> Chapter 12. jQuery Mobile 개요 </a> </li>
		<li> <a href="${context}/jquery/ch13.do"> Chapter 13. jQuery Mobile 사용자인터페이스 </a> </li>
		<li> <a href="${context}/jquery/ch14.do"> Chapter 14. jQuery Mobile 폼 </a> </li>
	</ul>
	
</body>
</html>