package com.company.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.company.Product.Cart;
import com.company.Product.Order;
import com.company.dao.OrderDao;
import com.company.dao.User;
import com.company.util.DBUtil;

@WebServlet("/CheckOutServlet")

public class CheckOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In do post method Add CheckOutServlet");	
		
		SimpleDateFormat  formatter = new SimpleDateFormat ("yyyy-MM-dd");
		Date date = new Date();
		
		
		//retrive all cart Products
		ArrayList<Cart> cart_list =(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
		User UserName=	(User) request.getSession().getAttribute("UserName");

		
		try {
			
			if(cart_list != null && UserName !=null) {
				for(Cart c: cart_list) {
					
					//Prepare the order object
					Order order= new Order();
					order.setId(c.getId());
					order.setUid(UserName.getId());
					order.setQuantity(c.getQuantity());
					order.setDate(formatter.format(date));
					
					//instantiate the dao class
					OrderDao oDao = new OrderDao(DBUtil.getConnection());
					
					//calling the insert method
					boolean result = oDao.insertOrder(order);
					if(!result) break;
					
					response.sendRedirect("orders.jsp");
					cart_list.clear();
					
				}
				
				
				
				
			}else {
				
				if(UserName == null ) response.sendRedirect("Login.jsp");
				
				
			}

			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request,response);
	}

}
