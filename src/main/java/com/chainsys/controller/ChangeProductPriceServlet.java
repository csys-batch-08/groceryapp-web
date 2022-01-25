package com.chainsys.controller;

import java.io.IOException;
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
		String products= req.getParameter("pname");
		double price= Double.parseDouble(req.getParameter("price"));
		Product product = new Product();
		product.setProductName(products);
		product.setProductPrice(price);
		ProductDaoImpl obj =new ProductDaoImpl(); 
		 resp.setContentType("text/html");
		try {
		boolean	flag =obj.changePrice(product);
			if(flag)
			{
				
				 req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);
					
			}
			else
			{
				
				 req.getRequestDispatcher("ChangeProductPrice.jsp").include(req, resp);
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
	}

}
