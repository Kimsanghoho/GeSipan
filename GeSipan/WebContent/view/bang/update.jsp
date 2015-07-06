<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page import = "dao.BangDaoImpl"%>
<%@ page import = "bean.BangBean" %>
<%@ page import = "java.sql.Timestamp" %>
<%@ page errorPage = "/error.jsp" %>
<%
    request.setCharacterEncoding("euc-kr");
%>
<jsp:useBean id="book" class="bean.BangBean">
    <jsp:setProperty name="book" property="*" />
</jsp:useBean>
<%
	BangDaoImpl manager = null;
	BangBean oldBook = null;
    if (book.getPassword().compareTo(oldBook.getPassword()) == 0) {
        manager.update(book);
%>
<script language="JavaScript">
alert("글을 수정하였습니다.");
location.href = "list.jsp";
</script>
<%
    } else {
%>
<script language="JavaScript">
alert("암호가 다릅니다.");
history.go(-1);
</script>
<%
    }
%>
