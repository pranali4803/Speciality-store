package com.company.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.company.util.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig(maxFileSize = 169999999)
@WebServlet("/AddProductServlet")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			
			Part filePart =request.getPart("image");
			System.out.println(filePart);
			
			
			InputStream inputStream= null;
			
			
			if(filePart != null) {
				long filesize =filePart.getSize(); //it will not use
				String fileContent =filePart.getContentType();  //this is also not use
				inputStream = filePart.getInputStream();
				
			}
			
			
		//Class.forName("com.mysql.cj.jdbc.Driver");				
		//	System.out.println("Connection build");
			
		//	Connection connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/demodatase","root","pratiksha@19");

			Connection connection =DBUtil.getConnection();
			String query ="insert into images(image) values(?)";
			PreparedStatement stmt=connection.prepareStatement(query);

			 
			stmt.setBlob(1, inputStream);
			
			int returnCode = stmt.executeUpdate();
			
			if(returnCode == 0) {
				System.out.println("Failed to upoad image");

			}else {
				System.out.println("Image added into database successfully.");

			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	
}

}

