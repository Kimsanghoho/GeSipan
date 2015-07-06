<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var= "context" value="<%=request.getContextPath()%>"></c:set>

	<style>
		@IMPORT url("${context}/css/common.css");
	</style>
<header id ="top">
	<div id="login">
		<a href="${context}/member/joinForm.do">회원가입</a>
		<a href="${context}/member/loginForm.do">로그인</a>
		<a href="${context}/member/siteMap.do">사이트맵</a>
	</div>
	<div class="clear"></div>
	<nav id="topMenu">
		<table id="noneT" aligen="center" style="width: 80%; height: 50px" >
			<tr>
				<td class="centT"><a href="${context}/home/main.do">HOME</a></td>
				<td class="centT"><a href="${context}/member/admin.do">회원검색</a></td>
				<td class="centT"><a href="${context}/bang/main.do">방명록</a></td>
				<td class="centT"><a href="${context}/dap/main.do">답글게시판</a></td>
			</tr>
		</table>
	
	</nav>
</header>
<div class="clear"></div>