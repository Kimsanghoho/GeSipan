<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="context" value="<%=request.getContextPath() %>"></c:set>
    
  <table style="width: 100%; border: 1px solid black;">
        <tr style="align:center;border: 1px solid black;">
            <th style="width: 20%">ID</th>
            <th style="width: 30%">이름</th>
            <th style="width: 10%">나이</th>
            <th style="width: 40%">이메일</th>
        </tr>
        <c:forEach var="member" items="${memberList}" varStatus="status">
            <tr style="align:center;border: 1px solid black;">
                <td>${member.id}</td>
                <td>${member.name }</td>
                <td>${member.age }</td>
                <td>${member.email }</td>
            </tr>
        </c:forEach>
        
    </table>    