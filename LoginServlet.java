package com.company.servlet;

import java.io.IOException;


import com.company.dao.UserDao;
import com.company.dao.UserDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private static UserDao userDao=new UserDaoImpl();
    /**
     * Default constructor. 
     */
    public LoginServlet() {
    	super();
        // TODO Auto-generated constructor stub
    }

	
	protected  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String UserName=request.getParameter("UserName");
		String Password=request.getParameter("Password");
		
		
		if(userDao.isValidUser(UserName, Password))  {
			HttpSession session=request.getSession();
			session.setAttribute("UserName",UserName);
			session.setAttribute("Password",Password);

			response.sendRedirect("index2.jsp");
			
		}else {
			response.sendRedirect("Login.jsp?error=1");
			System.out.println("Error a gya !");
		}
		
		
		}

}
