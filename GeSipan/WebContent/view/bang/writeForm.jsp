<%@ page contentType = "text/html; charset=euc-kr" %>
<html>
<head><title>글쓰기</title></head>
<body>
<center>
<form action="write.jsp" method="post">
<table width="100%" border="1" cellpadding="0" cellspacing="0">
<tr>
    <td>이름</td>
    <td><input type="text" name="name" size="10"></td>
</tr>
<tr>
    <td>암호</td>
    <td><input type="password" name="password" size="10"></td>
</tr>
<tr>
    <td>이메일</td>
    <td><input type="text" name="email" size="30"></td>
</tr>
<tr>
    <td>내용</td>
    <td><textarea name="content" rows="5" cols="50"></textarea></td>
</tr>
<tr>
    <td colspan="2"><input type="submit" value="글남기기"></td>
</tr>

</table>
</form>

</center>
</body>
</html>