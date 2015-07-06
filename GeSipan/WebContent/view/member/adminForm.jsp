<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <form action="<%=request.getContextPath()%>/member/searchAllMembers.do"  
            name="frmAdmin">
        <fieldset>
        <legend>회원관리</legend>
        <table>
            <tr>
                <td>전체회원 목록보기 </td>
                <td>
                    <!-- <input type="text" id="id" name="id" placeholder="ID 입력" /> -->
                </td>
            </tr>
            <tr>
                <td>ID 로 회원검색</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
        
            <tr>
                <td>이름으로 회원검색(동명이인 허용)</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
                <tr>
                <td>회원 삭제</td>
                <td>
                    <!-- <input type="text" id="password" name="password" /> -->
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="전 송" /> 
                    
                </td>
            </tr>
        </table>
        </fieldset>
    </form>