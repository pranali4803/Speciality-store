package com.company.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.company.Product.Cart;
import com.company.Product.Order;
import com.company.Product.Product;
import com.company.dao.OrderDao;
import com.company.dao.User;
import com.company.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderNowServlet")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
			try(PrintWriter out = response.getWriter()){
				
			SimpleDateFormat  formatter = new SimpleDateFormat ("yyyy-MM-dd");
			Date date = new Date();
			
					
			
			//	String productId = request.getParameter("id");
			
			//int productId = request.getInt("id");

				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				if(productQuantity <=0) {
					
					productQuantity =1;
				
				}
				
				
				String productId = request.getParameter("Id");
				
				Order orderModel =new Order();
				orderModel.setId(Integer.parseInt(productId));

			//	orderModel.setUid(UserName.getid());
			orderModel.setQuantity(productQuantity);
				orderModel.setDate(formatter.format(date));
				
				
				try {
				OrderDao orderDao = new OrderDao(DBUtil.getConnection());
			boolean result =	orderDao.insertOrder(orderModel);
			
			if(result) {
				
					
	ArrayList<Cart> cart_list =(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						
						
					if(cart_list != null) {
						for(Cart c: cart_list) {
							if(c.getId()== Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
								
								out.println("Order is subbmitted..");
								break;
							}
						}
					}
						
			}else {
				
					out.println("Order Failed.......!");
			}
				
				}catch(Exception e) {
					
					e.printStackTrace();
				}
				}
			
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	}

}
