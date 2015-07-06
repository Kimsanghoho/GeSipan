<%@ page contentType = "text/html; charset=euc-kr" %>
<%@ page errorPage = "/error/error_view.jsp" %>

<%@ page import = "java.io.File" %>
<%@ page import = "org.apache.commons.fileupload.FileItem" %>

<%@ page import = "util.ImageUtil" %>
<%@ page import = "util.FileUploadUtil" %>

<%@ page import = "bean.ThemeBean" %>
<%@ page import = "dao.ThemeDaoImpl" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
	FileUploadUtil requestWrap = new FileUploadUtil(
        request, -1, -1,
        "C:\\jakarta-tomcat-5.0.19\\webapps\\chap18\\temp");
    HttpServletRequest tempRequest = request;
    request = null; 

%>
<jsp:useBean id="theme" class="bean.ThemeBean">
    <jsp:setProperty name="theme" property="*" />
</jsp:useBean>
<%
    
	ThemeDaoImpl manager = null;
    ThemeBean oldTheme = null;
    
    if (theme.getPassword() == null || 
        oldTheme.getPassword().compareTo(theme.getPassword()) == 0) {
        // 암호가 같은 경우에만 작업 처리
         FileItem imageFileItem = null; 
        String image = "";
         if (imageFileItem.getSize() > 0) {
            int idx = imageFileItem.getName().lastIndexOf("\\");
            if (idx == -1) {
                idx = imageFileItem.getName().lastIndexOf("/");
            }
            image = imageFileItem.getName().substring(idx + 1); 
            
            // 이미지를 지정한 경로에 저장
            File imageFile = new File(
                "C:\\jakarta-tomcat-5.0.19\\webapps\\chap18\\image",
                image);
            // 같은 이름의 파일이름 처리
            if (imageFile.exists()) {
                for (int i = 0 ; true ; i++) {
                    imageFile = new File(
                        "C:\\jakarta-tomcat-5.0.19\\webapps\\chap18\\image",
                        "("+i+")"+image);
                    if (!imageFile.exists()) {
                        image = "("+i+")"+image;
                        break;
                    }
                }
            }
            imageFileItem.write(imageFile);
            
            // 썸네일 이미지 생성
            File destFile = new File(
                "C:\\jakarta-tomcat-5.0.19\\webapps\\chap18\\image",
                image+".small.jpg");
            ImageUtil.resize(imageFile, destFile, 50, ImageUtil.RATIO);
        }
        if (image.equals("")) {
            theme.setImage(oldTheme.getImage());
        } else {
            theme.setImage(image);
        }
        manager.update(theme);
%>
<html><head><title>수정</title></head><body>

<c:set var="search_cond" 
       value="<%= requestWrap.getParameterValues("search_cond") %>" />
<c:set var="pageNo" value="<%= requestWrap.getParameter("page") %>" />
<c:set var="search_key" 
       value="<%= requestWrap.getParameter("search_key") %>" />

<form name="move" method="post">
<input type="hidden" name="page" value="${pageNo}">
<c:forEach var="searchCond" items="${search_cond}">
    <c:if test="${searchCond == 'title'}">
    <input type="hidden" name="search_cond" value="title">
    </c:if>
    <c:if test="${searchCond == 'name'}">
    <input type="hidden" name="search_cond" value="name">
    </c:if>
</c:forEach>
<c:if test="${! empty search_key}">
<input type="hidden" name="search_key" value="${search_key}">
</c:if>
</form>

<script language="JavaScript">
alert("수정했습니다.");
document.move.action = "list.jsp";
document.move.submit();
</script>

</body></html>
<%
    } else {
%>
<script>
alert("암호가 다릅니다.");
history.go(-1);
</script>
<%
    }
%>
