<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <style>
        @IMPORT url("<%=request.getContextPath()%>/css/member.css");
   </style>
 
    <form action="<%=request.getContextPath()%>/model2/login.do" method="post" name="frmLogin">
        <fieldset>
        <legend>로그인</legend>
        <table>
            <tr>
                <td>아이디 </td>
                <td>
                    <input type="text" id="id" name="id" placeholder="ID 입력" />
                </td>
            </tr>
            <tr>
                <td>비밀번호</td>
                <td>
                    <input type="text" id="password" name="password" />
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <!-- <input type="submit" value="전 송" /> -->
                    <img src="<%=request.getContextPath()%>/images/btnLogin.jpg"
                         style="cursor: pointer" alt="" onclick="return login()"/>
                </td>
            </tr>
        </table>
        </fieldset>
    </form>
    <div>
        <span>
            <a href="#" onclick="searchId('<%=request.getContextPath()%>/member/searchIdForm.do')">
                아이디 찾기</a>
        </span>
        <span>
            <a href="#" onclick="searchPass('<%=request.getContextPath()%>/member/searchPassForm.do')">
                비밀번호 찾기</a>
        </span>
    </div>
    <script type="text/javascript" src="<%=request.getContextPath()%>/js/member.js"></script>