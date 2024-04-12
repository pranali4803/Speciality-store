package com.company.servlet;



import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.text.DateFormatter;

import com.company.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/FeedbackServlet")
public class FeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session =request.getSession(false);
			
	
		
			String username =request.getParameter("username");
			String feedback_text =request.getParameter("feedback_text");
			try {
				Connection connection =DBUtil.getConnection();
								
				Date date= new Date();
				java.sql.Date sqldate= new java.sql.Date(date.getTime());
				
				
				PreparedStatement ps = connection.prepareStatement("INSERT INTO feedback (date,username, feedback_text) VALUES (?, ?, ?)");
				
			
				ps.setDate(1,sqldate);
				ps.setString(2,username);
					ps.setString(3, feedback_text);
					ps.executeUpdate();
					response.sendRedirect("thankyou.jsp");
						
			}catch(Exception e) {
				e.printStackTrace();
			}
		
	}

}
