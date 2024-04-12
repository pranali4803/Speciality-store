package com.company.servlet;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.company.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig

@WebServlet("/AddImageServlet")
public class AddImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	System.out.println("In do post method Add Image servlet");	
	
	String name = request.getParameter("name");
	String category =request.getParameter("category");
	String description =request.getParameter("description");
	String price = request.getParameter("price");

	Part file =request.getPart("image");
	
	
	String imageFileName =file.getSubmittedFileName();       //get selected file  name
	

	System.out.println("Selected Image FileName :" +imageFileName);	
	
	String uploadPath= "C:/Users/Admin/Desktop/Project/Speciality Store/src/main/webapp/images/"+imageFileName;   //upload path where we have to upload our actual image
	System.out.println("Upload Path :" +uploadPath);	
	
	
	//Uploading our selected image into images folder
	
	try {
	FileOutputStream fos = new FileOutputStream(uploadPath);
	InputStream is =file.getInputStream();

    byte[] data = new byte[is.available()];
    is.read(data);
    fos.write(data);
    fos.close();
	
	}catch(Exception e) {
		e.printStackTrace();
	}
	
	

	//***********************************************************************
	//***********************************************************************
	
		
	
		try {
			Connection connection =DBUtil.getConnection();
			
			
			Date date= new Date();
			java.sql.Date sqldate= new java.sql.Date(date.getTime());
			

			String query ="insert into products(date,image,name,category,description,price) values(?,?,?,?,?,?)";
			PreparedStatement stmt=connection.prepareStatement(query);

			 
			stmt.setDate(1,sqldate);
			stmt.setString(2,imageFileName);
			stmt.setString(3, name);
			stmt.setString(4, category);
			stmt.setString(5, description);
			stmt.setString(6, price);
			
			
	//	stmt.executeQuery();//it returns no of rows affected.
			int row =stmt.executeUpdate();//it returns no of rows affected.
			
			if(row>0) {
				System.out.println("Image added into database successfully.");
			}else {
				System.out.println("Failed to upoad image");

			}
			
			response.sendRedirect("successfull_ImageUpload.jsp");

		}catch(Exception e) {
			e.printStackTrace();
	}
	
	
}

}

