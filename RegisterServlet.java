package com.company.servlet;


import java.io.IOException;



import com.company.dao.User;
import com.company.dao.UserDao;
import com.company.dao.UserDaoImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static UserDao userDao=new UserDaoImpl();

    /**
     * Default constructor. 
     */
    public RegisterServlet() {
    	super();
    	
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String UserName=request.getParameter("UserName");
		String Email=request.getParameter("Email");
		String Password=request.getParameter("Password");
		
		
		User user =new User();
		user.setUserName(UserName);
		user.setEmail(Email);
		user.setPassword(Password);
		
		
		//Object userDao;
		if(userDao.addUser(user)){
			
			response.sendRedirect("Login.jsp?registration=success");
			
		}else {
			response.sendRedirect("Register.jsp?error=1");
			System.out.println("Error a gya !");
		}
	}

	
}