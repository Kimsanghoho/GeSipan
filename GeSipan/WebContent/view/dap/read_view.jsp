<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import = "bean.ThemeBean" %>
<%@ page import = "dao.ThemeDaoImpl" %>
<%
    String themeId = request.getParameter("id");
    
	ThemeDaoImpl manager = null;
    ThemeBean theme = null;
%>
<c:set var="theme" value="<%= theme %>" />
<c:if test="${empty theme}">
존재하지 않는 테마 이미지입니다.
</c:if>
<c:if test="${! empty theme}">
<table width="100%" border="1" cellpadding="1" cellspacing="0">
<tr>
    <td>제목</td>
    <td>${theme.title}</td>
</tr>
<tr>
    <td>작성자</td>
    <td>
    ${theme.name}
    <c:if test="${empty theme.email}">
    <a href="mailto:${theme.email}">[이메일]</a>
    </c:if>
    </td>
</tr>
<c:if test="${! empty theme.image}">
<tr>
    <td colspan="2" align="center">
        <a href="javascript:viewLarge('/chap18/image/${theme.image}')">
        <img src="/chap18/image/${theme.image}" width="150" border="0">
        <br>[크게보기]
        </a>
    </td>
</tr>
</c:if>
<tr>
    <td>내용</td>
    <td><pre>${theme.content}</pre></td>
</tr>
<tr>
    <td colspan="2">
    <a href="javascript:goReply()">[답변]</a>
    <a href="javascript:goModify()">[수정]</a>
    <a href="javascript:goDelete()">[삭제]</a>
    <a href="javascript:goList()">[목록]</a>
    </td>
</tr>
</table>
</c:if>

<script language="JavaScript">
function goReply() {
    document.move.action = "writeForm.jsp";
    document.move.submit();
}
function goModify() {
    document.move.action = "updateForm.jsp";
    document.move.submit();
}
function goDelete() {
    document.move.action = "deleteForm.jsp";
    document.move.submit();
}
function goList() {
    document.move.action = "list.jsp";
    document.move.submit();
}
function viewLarge(imgUrl) {
    
}
</script>

<form name="move" method="post">
    <input type="hidden" name="id" value="${theme.id}">
    <input type="hidden" name="parentId" value="${theme.id}">
    <input type="hidden" name="groupId" value="${theme.groupId}">
    
    <input type="hidden" name="page" value="${param.page}">
    <c:forEach var="searchCond" items="${paramValues.search_cond}">
        <c:if test="${searchCond == 'title'}">
        <input type="hidden" name="search_cond" value="title">
        </c:if>
        <c:if test="${searchCond == 'name'}">
        <input type="hidden" name="search_cond" value="name">
        </c:if>
    </c:forEach>
    
    <c:if test="${! empty param.search_key}">
    <input type="hidden" name="search_key" value="${param.search_key}">
    </c:if>
</form>