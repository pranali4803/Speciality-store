package com.company.servlet;


import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try(PrintWriter out =response.getWriter()){
			
			if((request.getSession().getAttribute("UserName") != null && (request.getSession().getAttribute("Password") !=null)) ) {
				request.getSession().removeAttribute("Usename");
				request.getSession().removeAttribute("Password");
				
				response.sendRedirect("Login.jsp");
				
			}else {
				
				response.sendRedirect("index2.jsp");

			}
			
		}
	}

	
}
