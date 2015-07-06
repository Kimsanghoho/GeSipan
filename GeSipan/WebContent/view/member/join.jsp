<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!--회원가입 뷰 -->
        <fieldset>
        <legend>회원 가입</legend>
        <table>
            <tr>
                <td>이름 </td>
                <td>
                    <%-- <%=request.getAttribute("name")%> --%>
                    ${name}
                </td>
            </tr>
            <tr>
                <td>ID </td>
                <td>
                    ${id}
                </td>
            </tr>
            
                <tr>
                <td>나이 </td>
                <td>
                    ${age}세
                </td>
            </tr>
                <tr>
                <td>주소 </td>
                <td>
                    ${address}
                </td>
            </tr>
            
        </table>
        </fieldset>
    
 
