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
@WebServlet("/ChangeProductPrice")
public class ChangeProductPriceServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product= req.getParameter("pname");
		double price= Double.parseDouble(req.getParameter("price"));
		Product products = new Product();
		products.setProductName(product);
		products.setProductPrice(price);
		ProductDaoImpl obj =new ProductDaoImpl();
		 PrintWriter out=resp.getWriter();  
		 resp.setContentType("text/html");
		try {
		boolean	flag =obj.changePrice(products);
			if(flag)
			{
				 out.print("Change price product"); 
				 req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);
					
			}
			else
			{
				 out.print("Fail"); 	
				 req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
