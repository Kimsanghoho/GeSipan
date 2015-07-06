package controller;
 
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.MemberBean;
import service.MemberService;
import service.MemberServiceImpl;
 

/**
 * @ Date : 2015.06;
 * @ Author : itb-1;
 * @ Story : 회원관리 컨트롤러;
 */
@WebServlet({"/member/join.do","/member/login.do",
    "/member/searchIdForm.do","/member/searchPassForm.do",
    "/member/searchAllMembers.do"})
public class MemberController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    Map<String,Object> map = new HashMap<String,Object>();
    MemberBean bean = new MemberBean();
   
    
   
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
        case "/member/searchIdForm.do" :
            RequestDispatcher dispatcher3 
            = request.getRequestDispatcher("/view/member/searchIdForm.jsp");
            dispatcher3.forward(request, response);
            break;
        case "/member/searchPassForm.do": 
            RequestDispatcher dispatcher4 
            = request.getRequestDispatcher("/view/member/searchPassForm.jsp");
            dispatcher4.forward(request, response);
            break;
        case "/member/searchAllMembers.do": 
            request.setAttribute("memberList", MemberServiceImpl.getInstance().memberList());
            RequestDispatcher dispatcher5 
                = request.getRequestDispatcher("/view/memberList.jsp");
            dispatcher5.forward(request, response);
            break;
        default:
            break;
        }
    }
    
    protected void doPost(HttpServletRequest request,
        HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        switch (path) {
        case "/member/join.do" : 
            String name = request.getParameter("name");
            String id = request.getParameter("id");
            String password = request.getParameter("password");
            String age = request.getParameter("age");
            String email = request.getParameter("email");
            bean.setName(name);
            bean.setEmail(email);
            bean.setId(id);
            bean.setPassword(password);
            bean.setAge(age);
            
            MemberServiceImpl.getInstance().join(bean);
            RequestDispatcher dispatcher 
                = request.getRequestDispatcher("/view/main.jsp");
            dispatcher.forward(request, response);
            break;
        case "/member/login.do" : 
            
            String id2 = request.getParameter("id");
            String pass = request.getParameter("password");
            String msg = MemberServiceImpl.getInstance().login(id2,pass);
            
            if(msg.equals("환영합니다..")){
                System.out.println("로그인 성공시 : "+bean.getId());
                request.setAttribute("id", bean.getId());
                request.setAttribute("password", bean.getPassword());
                request.setAttribute("name", bean.getName());
                request.setAttribute("age", bean.getAge());
                request.setAttribute("address", bean.getAddr());
                RequestDispatcher dispatcher2 
                    = request.getRequestDispatcher("/view/member.jsp");
                dispatcher2.forward(request, response);
                break;
            }else{
                request.setAttribute("msg", msg);
                RequestDispatcher dispatcher2 
                    = request.getRequestDispatcher("/view/member/loginFail.jsp");
                dispatcher2.forward(request, response);
                break;
            }
            
        
        default: break;
        }
    }
}