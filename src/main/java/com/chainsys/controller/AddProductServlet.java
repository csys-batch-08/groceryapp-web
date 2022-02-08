package com.chainsys.controller;

import java.io.IOException;



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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		boolean flag =false;
		Product product = new Product();
		ProductDaoImpl obj = new ProductDaoImpl();
		String productimage = request.getParameter("productimage");
		String products = request.getParameter("pname");
		try {
			String prices = request.getParameter("price");
			double price = Double.parseDouble(prices);

			product.setProductImage(productimage);
			product.setProductName(products);
			product.setProductPrice(price);
		} catch (NumberFormatException e1) {
			System.out.println(e1.getMessage());
		}

		response.setContentType("text/html");
		try {
			flag = obj.addproduct(product);
			if (flag) {
				response.sendRedirect("AddProductAdminServlet");

			} 
		} catch ( IOException e) {

			System.out.println(e.getMessage());
		}

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
