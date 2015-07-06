<%@ page contentType = "text/html; charset=euc-kr" %>
<%
    request.setCharacterEncoding("euc-kr");
%>
<jsp:forward page="/template/template.jsp">
    <jsp:param name="CONTENTPAGE" value="/deleteForm_view.jsp" />
</jsp:forward>
