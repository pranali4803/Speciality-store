package com.company.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import com.company.Product.Cart;
import com.company.Product.Order;
import com.company.dao.OrderDao;
import com.company.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try(PrintWriter out = response.getWriter()){
			
			SimpleDateFormat  formatter = new SimpleDateFormat ("yyyy-MM-dd");
			Date date = new Date();
			
					
			
				String productId = request.getParameter("id");
				int productQuantity = Integer.parseInt(request.getParameter("quantity"));
				
				if(productQuantity <=0) {
					
					productQuantity =1;
				}
				
				Order o =new Order();
				o.setId(Integer.parseInt(productId));
				//orderModel.setUid(UserName.getid());
				o.setQuantity(productQuantity);
				o.setDate(formatter.format(date));
				
				try {
				OrderDao orderDao = new OrderDao(DBUtil.getConnection());
			boolean result =	orderDao.insertOrder(o);
			
			if(result) {
				
					
	ArrayList<Cart> cart_list =(ArrayList<Cart>) request.getSession().getAttribute("cart-list");
						
						
					if(cart_list != null) {
						for(Cart c: cart_list) {
							if(c.getId()== Integer.parseInt(productId)) {
								cart_list.remove(cart_list.indexOf(c));
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
