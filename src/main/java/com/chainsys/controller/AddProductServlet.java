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
		String product= request.getParameter("pname");
		double price= Double.parseDouble(request.getParameter("price"));
		Product products = new Product();
		products.setProductImage(productimage);
		products.setProductName(product);
		products.setProductPrice(price);
		ProductDaoImpl obj =new ProductDaoImpl();
		 PrintWriter out=response.getWriter();  
		 response.setContentType("text/html");
		try {
		boolean	flag =obj.addproduct(products);
			if(flag)
			{
				 out.print("Add product"); 
				 request.getRequestDispatcher("AddProduct.jsp").include(request, response);
					
			}
			else
			{
				 out.print("Fail"); 	
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
