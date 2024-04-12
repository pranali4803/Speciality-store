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
import java.util.Date;

import com.company.util.DBUtil;

@WebServlet("/paymentServlet")
public class paymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
		String fullname =request.getParameter("fullname");
		String email =request.getParameter("email");
		String phone =request.getParameter("phone");
		String address =request.getParameter("address");
		String cnumber =request.getParameter("cnumber");
		String month =request.getParameter("month");
		String year =request.getParameter("year");
		String cvv =request.getParameter("cvv");

		
			try {
				Connection connection =DBUtil.getConnection();
				
			PreparedStatement ps = connection.prepareStatement("INSERT INTO payment (date,fullname,email,phone,address,cnumber,month,year,cvv) VALUES(?,?,?,?,?,?,?,?,?)");
					
				//PreparedStatement ps = connection.prepareStatement("INSERT INTO feedback (username, feedback_text) VALUES (?, ?)");

			Date date= new Date();
			java.sql.Date sqldate= new java.sql.Date(date.getTime());
			
					ps.setDate(1,sqldate);
					ps.setString(2,fullname);
					ps.setString(3, email);
					ps.setString(4, phone);
					ps.setString(5, address);
					ps.setString(6, cnumber);
					ps.setString(7, month);
					ps.setString(8, year);
					ps.setString(9, cvv);

					ps.executeUpdate();
					response.sendRedirect("thankyou.jsp");
						
			}catch(Exception e) {
				e.printStackTrace();
			}
			
		
	}

}
