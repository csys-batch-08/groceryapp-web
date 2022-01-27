package com.chainsys.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.daoimpl.ProductDaoImpl;
import com.chainsys.model.Product;


@WebServlet("/Addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddProductServlet() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String productimage= request.getParameter("productimage");
		System.out.println(productimage);
		String products= request.getParameter("pname");
		double price= Double.parseDouble(request.getParameter("price"));
		Product product = new Product();
		product.setProductImage(productimage);
		product.setProductName(products);
		product.setProductPrice(price);
		ProductDaoImpl obj =new ProductDaoImpl();
		
		 response.setContentType("text/html");
		try {
		boolean	flag =obj.addproduct(product);
			if(flag)
			{
				response.sendRedirect("AddProductAdminServlet");
				 
					
			}
			else
			{
				 	
				response.sendRedirect("AddProductAdminServlet");
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
