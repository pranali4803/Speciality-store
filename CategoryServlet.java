package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.company.util.DBUtil;
@WebServlet("/CategoryServlet")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String category =request.getParameter("category");

		try {
				Connection connection =DBUtil.getConnection();
				
			PreparedStatement ps = connection.prepareStatement("INSERT INTO categories (category) VALUES(?)");
					

				ps.setString(1,category);
				ps.executeUpdate();
				response.sendRedirect("success.jsp");
				
					
			}catch(Exception e) {
				e.printStackTrace();
			}
			
	}

}
