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

/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/Addproduct")
public class AddProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
				 
				 request.getRequestDispatcher("AddProduct.jsp").include(request, response);
					
			}
			else
			{
				 	
				 request.getRequestDispatcher("AddProduct.jsp").include(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
